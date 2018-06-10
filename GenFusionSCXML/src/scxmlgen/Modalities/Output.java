package scxmlgen.Modalities;

import genfusionscxml.GenFusionSCXML;
import scxmlgen.interfaces.IOutput;



public enum Output implements IOutput{
    //weather days week
    WEATHER_DAYSWEEK_DOMINGO("[WEATHER][TYPE1][dayOfWeek][domingo][0]"),
    WEATHER_DAYSWEEK_SEGUNDA("[WEATHER][TYPE1][dayOfWeek][segunda][1]"),
    WEATHER_DAYSWEEK_TERCA("[WEATHER][TYPE1][dayOfWeek][terça][2]"),
    WEATHER_DAYSWEEK_QUARTA("[WEATHER][TYPE1][dayOfWeek][quarta][3]"),
    WEATHER_DAYSWEEK_QUINTA("[WEATHER][TYPE1][dayOfWeek][quinta][4]"),
    WEATHER_DAYSWEEK_SEXTA("[WEATHER][TYPE1][dayOfWeek][sexta][5]"),
    WEATHER_DAYSWEEK_SABADO("[WEATHER][TYPE1][dayOfWeek][sábado][6]"),
    //weather days rel
    WEATHER_DAYSREL_TODAY("[WEATHER][TYPE1][today][today][]"),
    WEATHER_DAYSREL_TOMORROW("[WEATHER][TYPE1][tomorrow][tomorrow][]"),
    //weather days number
    
    WEATHER_DAYSNUMBER_1("[WEATHER][TYPE1][numberOfDay][1][]"),
    WEATHER_DAYSNUMBER_2("[WEATHER][TYPE1][numberOfDay][2][]"),
    WEATHER_DAYSNUMBER_3("[WEATHER][TYPE1][numberOfDay][3][]"),
    WEATHER_DAYSNUMBER_4("[WEATHER][TYPE1][numberOfDay][4][]"),
    WEATHER_DAYSNUMBER_5("[WEATHER][TYPE1][numberOfDay][5][]"),
    WEATHER_DAYSNUMBER_6("[WEATHER][TYPE1][numberOfDay][6][]"),
    WEATHER_DAYSNUMBER_7("[WEATHER][TYPE1][numberOfDay][7][]"),
    WEATHER_DAYSNUMBER_8("[WEATHER][TYPE1][numberOfDay][8][]"),
    WEATHER_DAYSNUMBER_9("[WEATHER][TYPE1][numberOfDay][9][]"),
    WEATHER_DAYSNUMBER_10("[WEATHER][TYPE1][numberOfDay][10][]"),
    WEATHER_DAYSNUMBER_11("[WEATHER][TYPE1][numberOfDay][11][]"),
    WEATHER_DAYSNUMBER_12("[WEATHER][TYPE1][numberOfDay][12][]"),
    WEATHER_DAYSNUMBER_13("[WEATHER][TYPE1][numberOfDay][13][]"),
    WEATHER_DAYSNUMBER_14("[WEATHER][TYPE1][numberOfDay][14][]"),
    WEATHER_DAYSNUMBER_15("[WEATHER][TYPE1][numberOfDay][15][]"),
    WEATHER_DAYSNUMBER_16("[WEATHER][TYPE1][numberOfDay][16][]"),
    WEATHER_DAYSNUMBER_17("[WEATHER][TYPE1][numberOfDay][17][]"),
    WEATHER_DAYSNUMBER_18("[WEATHER][TYPE1][numberOfDay][18][]"),
    WEATHER_DAYSNUMBER_19("[WEATHER][TYPE1][numberOfDay][19][]"),
    WEATHER_DAYSNUMBER_20("[WEATHER][TYPE1][numberOfDay][20][]"),
    WEATHER_DAYSNUMBER_21("[WEATHER][TYPE1][numberOfDay][21][]"),
    WEATHER_DAYSNUMBER_22("[WEATHER][TYPE1][numberOfDay][22][]"),
    WEATHER_DAYSNUMBER_23("[WEATHER][TYPE1][numberOfDay][23][]"),
    WEATHER_DAYSNUMBER_24("[WEATHER][TYPE1][numberOfDay][24][]"),
    WEATHER_DAYSNUMBER_25("[WEATHER][TYPE1][numberOfDay][25][]"),
    WEATHER_DAYSNUMBER_26("[WEATHER][TYPE1][numberOfDay][26][]"),
    WEATHER_DAYSNUMBER_27("[WEATHER][TYPE1][numberOfDay][27][]"),
    WEATHER_DAYSNUMBER_28("[WEATHER][TYPE1][numberOfDay][28][]"),
    WEATHER_DAYSNUMBER_29("[WEATHER][TYPE1][numberOfDay][29][]"),
    WEATHER_DAYSNUMBER_30("[WEATHER][TYPE1][numberOfDay][30][]"),
    WEATHER_DAYSNUMBER_31("[WEATHER][TYPE1][numberOfDay][31][]"),
    
    //parques
    PARQUE_BIBLIOTECA("[SAS][TYPE2][SUBTYPE3][Biblioteca]"),
    PARQUE_RESIDENCIAS("[SAS][TYPE2][SUBTYPE3][Residencias]"),
    PARQUE_SUBTERRANEO("[SAS][TYPE2][SUBTYPE3][Subterraneo]"),
    PARQUE_CERAMICA("[SAS][TYPE2][SUBTYPE3][Ceramica]"),
    PARQUE_LINGUAS("[SAS][TYPE2][SUBTYPE3][Linguas]"),
    PARQUE_INCUBADORA("[SAS][TYPE2][SUBTYPE3][Incubadora]"),
    PARQUE_ISCAAPUBLICO("[SAS][TYPE2][SUBTYPE3][ISCAA Publico]"),
    PARQUE_GERAL("[SAS][TYPE1][SUBTYPE1]");

    
    ;
    
    
    
    private final String event;

    Output(String m) {
        event=m;
    }
    
    @Override
    public String getEvent(){
        return this.toString();
    }

    @Override
    public String getEventName(){
        return event;
    }
}
