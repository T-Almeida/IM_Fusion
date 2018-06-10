/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genfusionscxml;

import java.io.IOException;
import java.util.stream.Stream;
import scxmlgen.Fusion.FusionGenerator;
import scxmlgen.Modalities.Guestures;
import scxmlgen.Modalities.Output;
import scxmlgen.Modalities.Speech;
import scxmlgen.Modalities.SecondMod;

/**
 *
 * @author nunof
 */
public class GenFusionSCXML {
    public static int TIMEOUT = 3000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

    FusionGenerator fg = new FusionGenerator();
  
    //EXEMPLOS
    //fg.Sequence(Speech.SQUARE, SecondMod.RED, Output.SQUARE_RED);
    //fg.Sequence(Speech.SQUARE, SecondMod.BLUE, Output.SQUARE_BLUE);
    //fg.Sequence(Speech.TRIANGLE, SecondMod.RED, Output.TRIANGLE_RED);
    //fg.Complementary(Speech.TRIANGLE, SecondMod.BLUE, Output.TRIANGLE_BLUE);
    //fg.Complementary(Speech.TRIANGLE, SecondMod.YELLOW, Output.TRIANGLE_YELLOW);
    //fg.Complementary(Speech.CIRCLE, SecondMod.RED, Output.CIRCLE_RED);
    //fg.Complementary(Speech.CIRCLE, SecondMod.BLUE, Output.CIRCLE_BLUE);
    //fg.Complementary(Speech.DAYSWEEK_DOMINGO, Guestures.WEATHER, Output.WEATHER_DOMINGO);
    
    //Days_week_domingo
    
    for (Speech sp: Speech.values()){
        if (sp.toString().contains("DAYS")){ //dias combinam com weather
            System.out.println(sp.toString()+" "+Output.valueOf("WEATHER_"+sp.toString()));
            fg.Complementary(sp, Guestures.WEATHER, Output.valueOf("WEATHER_"+sp.toString()));
        }
    }
    
    

    
    
    //fg.Single(Speech.PARQUES_LUGARES_LINGUAS, Output.PARQUES_LUGARES_LINGUAS);
    //fg.Single(Speech.PARQUES_LUGARES_BIBLIOTECA, Output.PARQUES_LUGARES_BIBLIOTECA);
    
    //fg.Single(Speech.DAYS_DOMINGO, Output.TRIANGLE_BLUE);  //EXAMPLE
    
    //
    
    //fg.Redundancy(Speech.CIRCLE_RED, SecondMod., Output.CIRCLE);  //EXAMPLE
    
    fg.Build("fusion.scxml");
        
        
    }
    
}
