/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergamefinal;

import java.util.ArrayList;

/**
 *
 * @author Pocitac
 */
public class Settings {
    //promennne pro odskakovani pouzivane ve strileni doprava a dolu
    public static boolean rightRecoil = false;
    public static boolean downRecoil = false;
    public static final int RECOIL = 20; //odhozeni
    public static final int NEJVYSSI_RYCHLOST = 550;
    public static final int ZACATECNI_RYCHLOST = 1250;
    
    public static boolean zabijeniChvili = false;
    
    public static boolean konecHryBoolean = false;//je true pokud hru prohrajes ukonci vlakna
    
    public static int score = 0;
    
    public static int pocetSchopnosti = 3;
    public static int pocetZivotu = 3;
    
    public static int rychlostSpawnovani = 1250; //v milisekundach
    
    public static final int KOLIZE_ROZDIL = 27;
    
    public static boolean pauza = false; //boolean pro pauzu pracuje se s nim ve spawnovacim vlakne
    
    public static final String [] ALL_MONSTERS = {"DEMON","MOUSE","BOMB","SWORD"};
    
    public static ArrayList<Monster> neprateleVeHre = new ArrayList();
    
    //cisla zmacnute inicializace
    public static boolean onePressed = true; //zacina na true protoze je i naboj cerveny :)
    public static boolean twoPressed = false;
    public static boolean threePressed = false;
    public static boolean zeroPressed = false;
    
    public static boolean zadneCisloZmacknuto() {
        return !onePressed && !twoPressed && !threePressed && !zeroPressed;
    }
    
    public static MonsterColor slabina() {
        if (onePressed) {
            return MonsterColor.RED;
        }
        if (twoPressed) {
            return MonsterColor.BLUE;
        }
        if (threePressed) {
            return MonsterColor.GREEN;
        }
        if (zeroPressed) {
            return MonsterColor.YELLOW;
        }
        return MonsterColor.EMPTY;
    }
    public static void vypsat() {
        System.out.println(onePressed+", "+ twoPressed+", "+ threePressed+", "+ zeroPressed);
    }
}
