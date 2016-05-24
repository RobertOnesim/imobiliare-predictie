import unittest
from os.path import join as pjoin, abspath
from crawler import *
import io

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
        with io.open(pjoin('tests', 'acf.html'), 'r', encoding=encoding) as fd:
            self.assertEqual(fd.read(), get_page_content('file:' + abspath(pjoin('tests', 'acf.html')).replace('\\', '/')))

    def test_get_page_content2(self):
        with io.open(pjoin('tests', 'ap2camereTatarasi.html'), 'r', encoding=encoding) as fd:
            self.assertEqual(fd.read(), get_page_content('file:' + abspath(pjoin('tests', 'ap2camereTatarasi.html')).replace('\\', '/')))

    def test_process_content2(self):
        with io.open(pjoin('tests', 'ap2camereTatarasi.json'), 'r', encoding=encoding) as fd:
            sample_json = json.load(fd)
            processed_json = process_content('file:' + abspath(pjoin('tests', 'ap2camereTatarasi.html')).replace('\\', '/'))
            self.assertEqual(cmp(sample_json, processed_json), 0)


if __name__ == '__main__':
    unittest.main()
