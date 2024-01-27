/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergamefinal;

import static computergamefinal.Inicializator.p;
import javafx.scene.Group;
import static computergamefinal.Inicializator.spaceRectangle;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 *
 * @author Pocitac
 */
public class Controller {

    public static Group root = Main.root;
    private static final int DOBA_VYSTRELU = 500; // v milisekundach

    public static void nabijeniSchopnosti() {
        if (spaceRectangle.getWidth() <= 150) {
            spaceRectangle.setWidth(spaceRectangle.getWidth() + 10);
            spaceRectangle.setTranslateX(spaceRectangle.getTranslateX());
        } else { //sem pridat zniceni vsech nepratel
            if (Settings.pocetSchopnosti > 0) {
                Settings.pocetSchopnosti--;
                Inicializator.special.setImage(new Image("images/special"+Settings.pocetSchopnosti+".png"));
                for (int i = 0; i < Settings.neprateleVeHre.size(); i++) {
                    Settings.neprateleVeHre.get(i).zabit();
                    Monster s = Settings.neprateleVeHre.get(i);
                    s = null;
                    Settings.neprateleVeHre.remove(Settings.neprateleVeHre.get(i));
                }
                Settings.zabijeniChvili = true;
            }
            spaceRectangle.setTranslateX(Inicializator.xForRectangle);
            spaceRectangle.setWidth(0);
        }
    }

    public static void shotRight() {
        p.setDirection(Direction.RIGHT);
        p.setImage(Inicializator.rightShot);
        p.getImage().setTranslateX(p.getImage().getTranslateX() - Settings.RECOIL);
        Settings.rightRecoil = true;
        Thread vterina = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(DOBA_VYSTRELU);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (Settings.rightRecoil) {
                                p.getImage().setTranslateX(p.getImage().getTranslateX() + Settings.RECOIL);
                                Settings.rightRecoil = false;
                            }
                            if (p.getDirection() == Direction.RIGHT) {
                                p.setImage(Inicializator.right);
                            }
                        }
                    });
                } catch (InterruptedException ex) {
                    System.out.println("nejde cas");
                }
            }
        });
        vterina.start();
        trefovani(3);
    }

    public static void shotLeft() {
        p.setDirection(Direction.LEFT);
        p.setImage(Inicializator.leftShot);
        Thread vterina = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(DOBA_VYSTRELU);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (p.getDirection() == Direction.LEFT) {
                                p.setImage(Inicializator.left);
                            }
                        }
                    });
                } catch (InterruptedException ex) {
                    System.out.println("nejde cas");
                }
            }
        });
        vterina.start();
        trefovani(1);
    }

    public static void shotDown() {
        p.setDirection(Direction.DOWN);
        p.setImage(Inicializator.downShot);
        p.setTranslateY(p.getTranslateY() - Settings.RECOIL);
        Settings.downRecoil = true;
        Thread vterina = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(DOBA_VYSTRELU);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (Settings.downRecoil) {
                                p.setTranslateY(p.getTranslateY() + Settings.RECOIL);
                                Settings.downRecoil = false;
                            }
                            if (p.getDirection() == Direction.DOWN) {
                                p.setImage(Inicializator.down);
                            }
                        }
                    });
                } catch (InterruptedException ex) {
                    System.out.println("nejde cas");
                }
            }
        });
        vterina.start();
        trefovani(0);
    }

    public static void shotUp() {
        p.setImage(Inicializator.upShot);
        p.setDirection(Direction.UP);
        Thread vterina = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(DOBA_VYSTRELU);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (p.getDirection() == Direction.UP) {
                                p.setImage(Inicializator.up);
                            }
                        }
                    });
                } catch (InterruptedException ex) {
                    System.out.println("nejde cas");
                }
            }
        });
        vterina.start();
        trefovani(2);
    }

    public static void trefovani(int smer) {
        for (int i = 0; i < Settings.neprateleVeHre.size(); i++) {
            if (Settings.neprateleVeHre.get(i).getSmer() == smer && Settings.neprateleVeHre.get(i).getBarva() == Settings.slabina()) {
                Settings.neprateleVeHre.get(i).zabit(); //v teto metode i zmizi
                Monster s = Settings.neprateleVeHre.get(i);
                s = null;
                Settings.neprateleVeHre.remove(i);
            }
        }
    }
}
