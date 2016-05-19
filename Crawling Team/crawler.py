import HTMLParser
import unittest
import io
import json
import os
import re
import urllib2
from time import sleep
from urlparse import urljoin

root_link = 'http://www.casa-alba.ro/'
delay = 0.5
encoding = 'utf-8'


def get_page_content(link):
    req = urllib2.Request(link, headers={
        'User-agent': 'Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36'})
    tries = 0
    while tries < 3:
        tries += 1
        try:
            resp = urllib2.urlopen(req)
            # encoding = resp.headers['content-type'].split('charset=')[-1]
            # print encoding
            # return resp.read()
            return resp.read().decode(encoding)
        except Exception as e:
            print "Could not get response {} : {}".format(e, link)
    return None


def process_all_pages(link, filename):
    print 'Processing {}'.format(link)
    content = get_page_content(link)
    try:
        max_pages = int(re.findall('<a href="/[^/]+/pagina-(\d+)" data-rel="p">', content)[1])
        pages = re.search('<select data-url="/([^/]+/pagina-)\[val\]([^"]*)">', content)
        for i in range(1, max_pages + 1):
            process_list_page(urljoin(root_link, pages.group(1) + str(i) + pages.group(2)), filename)
    except IndexError:
        process_list_page(link, filename)


def process_list_page(link, filename):
    print link
    data = {}
    content = get_page_content(link)
    announce_regex = '<a href="([^"]+)" class="box clear" data-rel="p" id="box\d+">'
    announces = re.findall(announce_regex, content)
    for item in announces:
        try:
            ad_link = urljoin(root_link, item)
            data[ad_link] = process_content(ad_link)
        except Exception as e:
            print str(e)
    if os.path.isfile(os.path.join('data', filename)):
        with io.open(filename, encoding=encoding) as f:
            all_data = json.load(f)
    else:
        all_data = {}

    all_data.update(data)

    with io.open(os.path.join('data', filename), 'w', encoding=encoding) as f:
        f.write(json.dumps(all_data, indent=4, sort_keys=True, ensure_ascii=False))


def process_content(link):
    sleep(delay)
    print link
    content = get_page_content(link)
    if not content:
        return {}
    rez = {}

    detalii = re.search('<div class="ftr-grp" style="margin-top: 0px"><table style="width: 100%">([\s\S]+?)</table>',
                        content)
    rez['detalii'] = {}
    if detalii:
        for line in re.findall('<tr><td[^>]*>([^<]+)</td><td[^>]*>([^<]+)</td></tr>', detalii.group(1)):
            rez['detalii'][line[0]] = line[1]
        rez['vandut'] = 'Da' if 'Vandut!' in detalii.group(1) else 'Nu'

    rez['dotari'] = {}
    dotari = re.search('<h3>Caracteristici</h3><table style="width: 100%">([\s\S]+?)</table>', content)
    if dotari:
        for line in re.findall('<tr><td[^>]*>([^<]+)</td><td[^>]*>([^<]+)</td></tr>', dotari.group(1)):
            rez['dotari'][line[0]] = line[1]
        rez['dotari']['Utilitati:'] = []

        utilitati = re.search('<tr><td[^>]*>Utilitati:</td><td[^>]*><ul>(.+?)</ul>', dotari.group(1))
        if utilitati:
            rez['dotari']['Utilitati:'].extend(re.findall('<li>([^<]+)</li>', utilitati.group(1)))

    titlu = re.search('<h2 id="property-details-title" itemprop="name">([^<]+)</h2>', content)
    if titlu:
        rez['titlu'] = titlu.group(1)

    pret = re.search('<meta itemprop="currency" content="([^"]+)"><span itemprop="price">([^<]+)</span>', content)
    if pret:
        rez['pret'] = {'moneda': pret.group(1), 'valoare': pret.group(2)}

    descriere = re.search('div itemprop="description">(.*?)</div></div>', content)
    if descriere:
        h = HTMLParser.HTMLParser()
        rez['descriere'] = h.unescape(process_html_tags(descriere.group(1)))
        # print rez['descriere']

    rez['foto'] = []
    for line in re.findall('<img src="([^"]+)" alt="{0}" title="{0}">'.format(rez['titlu']), content):
        rez['foto'].append(line)
    return rez


def process_html_tags(s):
    return re.sub('[ ]+', ' ',
                  ''.join(filter(lambda x: x, map(lambda x: x, re.findall('>([^<]*?)<', s)))).replace('&nbsp;',
                                                                                                      ' ')).strip()


def main():
    print 'Beginning'
    if not os.path.isdir('data'):
        os.mkdir('data')
    process_all_pages('http://www.casa-alba.ro/garsoniere-de-vanzare-iasi', 'garsoniere.json')
    process_all_pages(
        'http://www.casa-alba.ro/imobile-de-vanzare-iasi?search=1&sort=1&ipp=25&q=&p_min=&p_max=&s=1&pt%5B%5D=7&z=',
        'garsoniere_vandute.json')
    process_all_pages('http://www.casa-alba.ro/case-vile-de-vanzare-iasi', 'case.json')
    process_all_pages(
        'http://www.casa-alba.ro/imobile-de-vanzare-iasi?search=1&sort=1&ipp=25&q=&p_min=&p_max=&s=1&pt%5B%5D=2&z=',
        'case_vandute.json')
    process_all_pages('http://www.casa-alba.ro/apartamente-de-vanzare-iasi', 'apartamente.json')
    process_all_pages(
        'http://www.casa-alba.ro/imobile-de-vanzare-iasi?search=1&sort=1&ipp=25&q=&p_min=&p_max=&s=1&pt%5B%5D=1&z=',
        'apartamente_vandute.json')

    # rez = process_content('http://www.casa-alba.ro/oferta/casa-vila-de-vanzare-iasi-breazu/5907')
    # print json.dumps(rez, indent=4, sort_keys=True)
    # io.open('asdf.json', 'w', encoding=encoding).write(json.dumps(rez, indent=4,
    #                                                               sort_keys=True, ensure_ascii=False))


class CrawlerTest(unittest.TestCase):
    def test_process_content(self):
        content = process_content('http://www.casa-alba.ro/oferta/casa-vila-de-vanzare-iasi-bucium/1644')
        self.assertEqual(u'Proprietate deosebita de vanzare Iasi, Bucium', content['titlu'])
        self.assertEqual(u'590.000', content['pret']['valoare'])
        self.assertTrue(u'Apa' in content['dotari']['Utilitati:'])


    def test_process_html_tags(self):
        content = '<td><div>text</div></td>'
        self.assertEqual(process_html_tags(content), 'text')


    def test_get_page_content(self):
        with open('page_content.tmp', 'r') as fd:
            self.assertEqual(fd.read(), get_page_content('http://profs.info.uaic.ro/~acf/java/'))


if __name__ == '__main__':
    # main()
    unittest.main()

    # content = process_content('http://www.casa-alba.ro/oferta/casa-vila-de-vanzare-iasi-bucium/1644')
    # print content['dotari']
    # with open('page_content.tmp', 'w') as fd:
        # fd.write(get_page_content('http://profs.info.uaic.ro/~acf/java/'))

    # print content
