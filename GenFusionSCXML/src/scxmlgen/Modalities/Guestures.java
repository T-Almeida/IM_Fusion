
package scxmlgen.Modalities;

import scxmlgen.interfaces.IModality;

public enum Guestures implements IModality{

    Weather("[color][RED]",1500),
    BLUE("[color][BLUE]",1500),
    YELLOW("[color][YELLOW]",1500),
    SQUARE_YELLOW("[shape][SQUARE][color][YELLOW]",1500)
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
