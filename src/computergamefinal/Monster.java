/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergamefinal;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Pocitac
 */
public class Monster extends ImageView {

    private int smer; //0 dole , 1 vlevo, 2 nahore , 3 vpravo
    private int rychlost; //neprehanet
    private String jmeno;
    int i = 1;//prepinani obrazku
    private Random r = new Random();
    private MonsterColor barva;
    private int pretocit = 0;// 0 je nastavena predem 1 znamena ze potrebuje pretocit

    public Monster(String jmeno) {
        this.jmeno = jmeno;
        smer = r.nextInt(4);
        switch (this.jmeno) {
            case "DEMON":
                rychlost = 20;
                barva = MonsterColor.RED;
                break;
            case "MOUSE":
                if (smer == 3) {
                    pretocit = 1;
                }
                rychlost = 16;
                barva = MonsterColor.YELLOW;
                break;
            case "BOMB":
                if (smer == 3) {
                    pretocit = 1;
                }
                rychlost = 22;
                barva = MonsterColor.GREEN;
                break;
            case "SWORD":
                if (smer == 3) {
                    pretocit = 1;
                }
                rychlost = 13;
                barva = MonsterColor.BLUE;
                break;
        }
        setImage(new Image("images/" + jmeno + i + pretocit + ".png"));
    }

    public void move() {
        if (Inicializator.p.getTranslateX() > getTranslateX()) {
            setTranslateX(getTranslateX() + rychlost);
        } else {
            setTranslateX(getTranslateX() - rychlost);
        }
        if (Inicializator.p.getTranslateY() > getTranslateY()) {
            setTranslateY(getTranslateY() + rychlost);
        } else {
            setTranslateY(getTranslateY() - rychlost);
        }
        if (i == 1) {
            i++;
        } else {
            i = 1;
        }
        setImage(new Image("images/" + jmeno + i +pretocit+ ".png"));
    }

    public void zabit() {
        Image death = new Image("images/" + jmeno + pretocit + "Death.png");
        setImage(death);
        setTranslateY(getTranslateY() - 30);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setVisible(false);
                        }
                    });
                } catch (InterruptedException ex) {
                    System.out.println("preruseno");
                }
            }
        });
        thread.start();
    }

    public boolean checkCollision(ImageView p) {
        if ((p.getTranslateX() - getTranslateX() <= Settings.KOLIZE_ROZDIL && p.getTranslateX() - getTranslateX() >= -Settings.KOLIZE_ROZDIL)
                && (p.getTranslateY() - getTranslateY() <= Settings.KOLIZE_ROZDIL && p.getTranslateY() - getTranslateY() >= -Settings.KOLIZE_ROZDIL)) {
            return true;
        }
        return false;
    }

    public int getPretocit() {
        return pretocit;
    }

    public void setPretocit(int pretocit) {
        this.pretocit = pretocit;
    }

    public MonsterColor getBarva() {
        return barva;
    }

    public int getSmer() {
        return smer;
    }

    public void setSmer(int smer) {
        this.smer = smer;
    }

    public int getRychlost() {
        return rychlost;
    }

    public void setRychlost(int rychlost) {
        this.rychlost = rychlost;
    }
    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

}
