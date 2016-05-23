package ro.uaic.info.data_mining.conversion;

/**
 * Created by Ciubi on 27/04/16.
 */
public class FilterZones {
    String zone;
    FilterZones(String zone){
        this.zone=zone;
    }
    String getZone(){
        return  this.zone;
    }
    String getZoneAttribute(){
        return "ZONE";
    }


}
