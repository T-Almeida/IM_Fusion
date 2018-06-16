/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genfusionscxml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import scxmlgen.Fusion.FusionGenerator;
import scxmlgen.Modalities.Guestures;
import scxmlgen.Modalities.Output;
import scxmlgen.Modalities.Speech;

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
            if (sp.toString().startsWith("DAYS")){ //dias combinam com weather
                System.out.println(sp.toString()+" "+Output.valueOf("WEATHER_"+sp.toString()));
                fg.Complementary(sp, Guestures.WEATHER, Output.valueOf("WEATHER_"+sp.toString()));
                
                System.out.println(sp.toString()+" "+Output.valueOf("CANTEENS_"+sp.toString()));
                fg.Complementary(sp, Guestures.CANTEENS, Output.valueOf("CANTEENS_"+sp.toString()));
                
                fg.Single(sp, Output.GERAL_PASS_DONT_RECOGNIZE); 
            }else if(sp.toString().startsWith("PARQUE")){
                System.out.println(sp.toString()+" "+Output.valueOf(sp.toString()));
                fg.Complementary(sp, Guestures.PARQUES, Output.valueOf(sp.toString()));
                
                fg.Single(sp, Output.GERAL_PASS_DONT_RECOGNIZE); 
            }else if(sp.toString().startsWith("CANTEENS")){
                System.out.println(sp.toString()+" "+Output.valueOf(sp.toString()));
                fg.Complementary(sp, Guestures.CANTEENS, Output.valueOf(sp.toString()));
                
                fg.Single(sp, Output.GERAL_PASS_DONT_RECOGNIZE); 
            }else if(sp.toString().startsWith("SENHAS")){
                System.out.println(sp.toString()+" "+Output.valueOf(sp.toString()));
                fg.Complementary(sp, Guestures.SENHAS, Output.valueOf(sp.toString()));
                
                fg.Single(sp, Output.GERAL_PASS_DONT_RECOGNIZE); 
            }
        }

        //fg.Redundancy(Speech.CIRCLE_RED, SecondMod., Output.CIRCLE);  //EXAMPLE
        
        fg.Redundancy(Speech.RED_WEATHER, Guestures.WEATHER, Output.WEATHER_DAYSREL_TOMORROW);
        fg.Redundancy(Speech.RED_SENHAS, Guestures.SENHAS, Output.SENHAS_GERAL);
        fg.Redundancy(Speech.RED_PARQUES, Guestures.PARQUES, Output.PARQUE_GERAL);
        fg.Redundancy(Speech.RED_NEWS, Guestures.NEWS, Output.NEWS_GERAL);
       
        //deixar passar
        fg.Single(Guestures.WEATHER, Output.WEATHER_DAYSREL_TOMORROW); 
        fg.Single(Guestures.PARQUES, Output.PARQUE_GERAL);
        fg.Single(Guestures.CANTEENS, Output.CANTEENS_DAYSREL_TODAY);
        fg.Single(Guestures.SENHAS, Output.SENHAS_GERAL);
        fg.Single(Guestures.NEWS, Output.NEWS_GERAL);
        
        fg.Build("fusion.scxml");
        
        System.out.println("Move created files to FusionEngine runtime");
        
        //move file
        Files.move(Paths.get("fusion.scxml"), Paths.get("..","FusionEngine runtime","fusion.scxml"), StandardCopyOption.REPLACE_EXISTING);
        Files.move(Paths.get("eventslist.txt"), Paths.get("..","FusionEngine runtime","eventslist.txt"), StandardCopyOption.REPLACE_EXISTING);
    }
    
}
