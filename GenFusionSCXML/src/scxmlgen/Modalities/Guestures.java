
package scxmlgen.Modalities;

import scxmlgen.interfaces.IModality;

public enum Guestures implements IModality{

    WEATHER("[WEATHER][TYPE1][tomorrow][tomorrow][]",2500),
    CANTEENS("[CANTEENS][TYPE5]",2500),
    PARQUES("[SAS][TYPE1][SUBTYPE1]",2500),
    SENHAS("[SAC][TYPE1]",2500),
    NEWS("[NEWS][TYPE1]",2500)
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
