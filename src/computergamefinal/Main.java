/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergamefinal;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 *
 * @author Jan Praks
 * 
 * 3
 * 
 * Po vytvoreni sachu a tetrisu jsem chtel udelat hru, ktera neni udelana z hraci 
 * mrizky a je v ni pixelova grafika, na tomto projektu jsem poprve resil ukladani 
 * nejvyssiho score (pozdeji vylepsil diky matyasi Wotavovi takze se uklada do appdata slozky - trida MWGS) 
 * a malovani vlastnich obrazku. Znelka byla vytvorena vlastne 
 * spojenim nekolika jinych...Povazuji tuto hru jako jeden z mych nejlepsich vytvoru.
 */
public class Main extends Application {

    static Group root = new Group();
    static Scene scene = new Scene(root,1366,  768);
    static Stage primaryStage;
    static StackPane stackPane = new StackPane();
    //1.38 delka
    public static AudioClip theme = new AudioClip(Main.class.getClassLoader().getResource("sounds/theme.wav").toString());
    //0.48 delka
    public static AudioClip repeatable = new AudioClip(Main.class.getClassLoader().getResource("sounds/repeatable.wav").toString());

    private static int i = 0; //pro provadeni po 99 milisekundach
    private static int vterina = 0; //neni vterina pocita se s ni pri zvysovani skore
    private static int zrychlovaniSpawnovani = 0; //kazdych pet vterin se zrychli spawnovani
    private static int soundInt1 = 0;// slouzi na odpocet prvni hudby
    private static int soundInt2 = 0;
    private static boolean muzeSePoustetDruha = false;
    private static int muzeZabitInt = 0;
    
    public static Thread thread = new Thread(new Runnable() { //vlakno se pousti po prvnim startu hry
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(30);
                    Platform.runLater(() -> {
                        
                        if (muzeZabitInt < 40 && Settings.zabijeniChvili) {
                            muzeZabitInt++;
                        }else {
                            muzeZabitInt = 0;
                            Settings.zabijeniChvili = false;
                        }
                        
                        if (soundInt1 == 3266) {
                            muzeSePoustetDruha = true;
                            repeatable.play();
                        }else {
                            soundInt1++;
                        }
                        if (muzeSePoustetDruha) {
                            if (soundInt2 == 1600) {
                                soundInt2 = 0;
                                repeatable.play();
                            }else {
                                soundInt2++;
                            }
                        }
                        if (!Inicializator.hlavninabidkaAktivni && !Settings.konecHryBoolean) {
                            if (Inicializator.spacePressed) {
                                Controller.nabijeniSchopnosti(); //prozkoma pokud je zmacknuty mezernik a posune nabijeni schopnosti
                            }
                            if (zrychlovaniSpawnovani > 75) {
                                if (Settings.rychlostSpawnovani > Settings.NEJVYSSI_RYCHLOST) {
                                    zrychlovaniSpawnovani = 0;
                                    Settings.rychlostSpawnovani = Settings.rychlostSpawnovani - 30;
                                }
                            } else {
                                zrychlovaniSpawnovani++;
                            }
                            if (vterina < 10) {
                                vterina += 3;
                            } else {
                                Settings.score++;
                                Inicializator.scoreText.setText("Score = " + Settings.score);
                                vterina = 0;
                            }
                            if (i == 2) {
                                //pro pohyb nepratel a dotykani se hrace
                                for (int i1 = 0; i1 < Settings.neprateleVeHre.size(); i1++) {
                                    Settings.neprateleVeHre.get(i1).move();
                                    if (Settings.neprateleVeHre.get(i1).checkCollision(Inicializator.p.getImage())) {//dotkne se
                                        Inicializator.liveMinus.play();
                                        Settings.neprateleVeHre.get(i1).setVisible(false);
                                        Monster s = Settings.neprateleVeHre.get(i1);
                                        s = null;
                                        Settings.neprateleVeHre.remove(Settings.neprateleVeHre.get(i1));
                                        if (Settings.pocetZivotu > 0) {
                                            Settings.pocetZivotu--;
                                            if (Settings.pocetZivotu == 0) { //pokud prohrajes
                                                Inicializator.hraZkoncila();
                                            }
                                        }
                                        Inicializator.lives.setImage(new Image("images/lives" + Settings.pocetZivotu + ".png"));
                                    }
                                }
                                i = 0;
                            } else {
                                i++;
                            }
                        }
                    });
                } catch (InterruptedException ex) {
                    System.out.println("nejde spat");
                }
            }
        }
    });

    @Override
    public void start(Stage primaryStage) throws IOException {
        playAudio();
        Main.primaryStage = primaryStage;
        root.getChildren().add(stackPane);
        Inicializator.inicializate();

        primaryStage.setTitle("Vojak");
        //primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void playAudio() {
        theme.setVolume(10);
        repeatable.setVolume(10);
        theme.play();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
