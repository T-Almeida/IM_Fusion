
package scxmlgen.Modalities;

import genfusionscxml.GenFusionSCXML;
import scxmlgen.interfaces.IModality;

public enum Guestures implements IModality{

    WEATHER("[WEATHER][TYPE1][tomorrow][tomorrow][]",GenFusionSCXML.TIMEOUT),
    CANTEENS("[CANTEENS][TYPE5]",GenFusionSCXML.TIMEOUT),
    PARQUES("[SAS][TYPE1][SUBTYPE1]",GenFusionSCXML.TIMEOUT),
    SENHAS("[SAC][TYPE1]",GenFusionSCXML.TIMEOUT),
    //NEWS("[NEWS][TYPE1]",GenFusionSCXML.TIMEOUT)
    ;
    
    private String event;
    private int timeout;


    Guestures(String m, int time) {
        event=m;
        timeout=time;
    }

    @Override
    public int getTimeOut() {
        return timeout;
    }

    @Override
    public String getEventName() {
        //return getModalityName()+"."+event;
        return event;
    }

    @Override
    public String getEvName() {
        return getModalityName().toLowerCase()+event.toLowerCase();
    }
    
}
