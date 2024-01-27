/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergamefinal;


import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Pocitac
 */
public class Inicializator {

    static Group root = Main.root;
    static Scene scene = Main.scene;
    static Stage stage = Main.primaryStage;

    public static boolean spacePressed = false;

    public static Player p = new Player();
    public static Image left = new Image("images/SoldierLeft.png");
    public static Image right = new Image("images/SoldierRight.png");
    public static Image up = new Image("images/SoldierUp.png");
    public static Image upShot = new Image("images/SoldierUpShot.png");
    public static Image down = new Image("images/SoldierDown.png");
    public static Image downShot = new Image("images/SoldierDownShot.png");
    public static Image leftShot = new Image("images/SoldierLeftShot.png");
    public static Image rightShot = new Image("images/SoldierRightShot.png");

    public static ImageView nabidkaPozadi = new ImageView("images/nabidka.png"); //pozadi pro hlavni nabidku
    public static ImageView info = new ImageView("images/info.png");
    public static ImageView start = new ImageView("images/start.png");
    public static ImageView konec = new ImageView("images/konec.png");
    public static ImageView ovladaniInfo = new ImageView("images/ovladaniInfo.png");
    public static boolean hlavninabidkaAktivni = true;
    public static ImageView paused = new ImageView("images/paused.png");//ano je to v hlavni nabidce ale ne vzdy proto to neni v poli hlavni nabidka

    //nepratele
    public static Image demon = new Image("images/DEMON10.png");
    public static Image demon2 = new Image("images/DEMON20.png");

    //naboje
    public static Image redAmmo = new Image("images/REDAmmo.png");
    public static Image blueAmmo = new Image("images/BLUEAmmo.png");
    public static Image greenAmmo = new Image("images/GREENAmmo.png");
    public static Image yellowAmmo = new Image("images/YELLOWAmmo.png");
    public static ImageView ammo = new ImageView(redAmmo);

    //zvuky
    private static final ClassLoader classLoader = Inicializator.class.getClassLoader();
    /*
    public static AudioClip shot = new AudioClip(classLoader.getResource("sounds/shotSound.wav").getPath().substring(1).replace("/","\\"));
    public static AudioClip liveMinus = new AudioClip(classLoader.getResource("sounds/liveMinus.wav").getPath().substring(1).replace("/","\\"));
    public static AudioClip countDownSound = new AudioClip(classLoader.getResource("sounds/countDownSound.wav").getPath().substring(1).replace("/","\\"));
    public static AudioClip gameOverSound = new AudioClip(classLoader.getResource("sounds/gameOverSound.wav").getPath().substring(1).replace("/","\\"));
    */
    public static AudioClip shot = new AudioClip(Inicializator.class.getClassLoader().getResource("sounds/shotSound.wav").toString());
    public static AudioClip liveMinus = new AudioClip(Inicializator.class.getClassLoader().getResource("sounds/liveMinus.wav").toString());
    public static AudioClip countDownSound = new AudioClip(Inicializator.class.getClassLoader().getResource("sounds/countDownSound.wav").toString());
    public static AudioClip gameOverSound = new AudioClip(Inicializator.class.getClassLoader().getResource("sounds/gameOverSound.wav").toString());

    //pro score
    public static Text scoreText = new Text("Score = " + Settings.score);
    public static final Path hightScorePath = Paths.get(System.getProperty("user.dir")+"\\src\\documents\\Score.txt");
    public static int hightScoreInt;
    public static Text hightScoreText = new Text();

    //zivoty
    public static ImageView lives = new ImageView("images/lives" + Settings.pocetZivotu + ".png");

    private static ImageView odpocet = new ImageView();
    static ImageView special = new ImageView("images/special" + Settings.pocetSchopnosti + ".png"); //blesk u schopnosti

    static Rectangle spaceRectangle = new Rectangle(0, 20, Color.GREEN); //ukazatel schopnosti
    static ImageView edge = new ImageView("images/edge.png"); //okraj pro nacitani schopnosti
    static final int xForRectangle = 100;

    //---------------------------POMOCNE PROMENNE---------------------------------//
   // private static MWGS ukladac = new MWGS("PraksovyGamesy", "Vojak");
    private static boolean infoZapnuto = false;
    public static boolean zpustenoPoprve = true;
    public static ImageView[] neHlavniNabidka = {p.getImage(), /*odpocet,*/ ammo, edge, lives, special}; //sem patri vsechny co nejsou v hlavni nabidce, neplati pro prisery a rectanbly
    public static ImageView[] anoHlavniNabidka = {nabidkaPozadi, info, start, konec/*,ovladaniInfo*/};
    private static boolean opravduPouzeJednou = true; //pouziva se pouze pro po odpoctu na jedno spusteni mainu pri restartu by to delalo chybu

    public static void inicializate() {
        countDownSound.setVolume(0.35); //nastaveni hlasitosti zvuku pro nacitani
        shot.setVolume(10);
        //nastaveni promenne hightscore
        try {
            //hightScoreInt = ukladac.loadIfExistsElseSave(0, "hightscore");
        } catch (Exception ex) {
            System.out.println("nejde nacist ze souboru");
        }

        //inicializace hightscore textu
        hightScoreText.setText("Nejvyssi skore = " + hightScoreInt);
        
        hightScoreText.setTranslateX(700);
        hightScoreText.setTranslateY(650);
        hightScoreText.setFont(Font.font(70));
        hightScoreText.setFill(Color.WHITE);

        //inicializace score text
        scoreText.setTranslateX(900);
        scoreText.setTranslateY(670);
        scoreText.setFont(Font.font(80));
        scoreText.setVisible(false);

        //inicializace zivoty
        lives.setTranslateX(30);
        lives.setTranslateY(600);

        //inicializace schopnosti
        special.setTranslateX(900);
        special.setTranslateY(80);

        ammo.setTranslateY(60);//inicializace naboje
        ammo.setTranslateX(20);

        edge.setTranslateX(98); //nastaveni okraje shopnosti
        edge.setTranslateY(11);
        spaceRectangle.setTranslateX(xForRectangle); //nasteveni obdelniku (schopnost)
        spaceRectangle.setTranslateY(20);

        ImageView background = new ImageView("images/backgroundFinal.png");

        p.getImage().setTranslateX(scene.getWidth() / 2 - 75); //nastaveni hracovych souradnic (neni prilis pekne udelane)
        p.getImage().setTranslateY(scene.getHeight() / 3);

        //nastavovani handleru
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) { //nakonci zakomentovat
                    Main.primaryStage.close();
                }
                if (!hlavninabidkaAktivni) {
                    if (Settings.downRecoil) { //dorovnavani po odrazu
                        p.setTranslateY(p.getTranslateY() + Settings.RECOIL);
                        Settings.downRecoil = false;
                    }
                    if (Settings.rightRecoil) {
                        p.setTranslateX(p.getTranslateX() + Settings.RECOIL);
                        Settings.rightRecoil = false;
                    }
                    if (event.getCode() == KeyCode.P) { //nastaveni pauzy
                        paused.setVisible(true);
                        hlavniNabidka();
                    }
                    if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                        Controller.shotUp();
                        shot.play();
                    }
                    if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                        Controller.shotRight();
                        shot.play();
                        Settings.rightRecoil = true;
                    }
                    if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                        Controller.shotLeft();
                        shot.play();
                    }
                    if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                        Controller.shotDown();
                        shot.play();
                        Settings.downRecoil = true;
                    }
                    if (event.getCode() == KeyCode.SPACE) { //timer s touto primennou pokracuje
                        spacePressed = true;
                    }/* else {
                        spacePressed = false;
                        spaceRectangle.setWidth(0);
                    }*///nastavovani cisel
                    if (event.getCode() == KeyCode.NUMPAD1 || event.getCode() == KeyCode.J/* && Settings.zadneCisloZmacknuto()*/) {
                        Settings.onePressed = true;
                        Settings.twoPressed = false;
                        Settings.threePressed = false;
                        Settings.zeroPressed = false;
                        ammo.setImage(redAmmo);
                    }
                    if (event.getCode() == KeyCode.NUMPAD2 || event.getCode() == KeyCode.K/* && Settings.zadneCisloZmacknuto()*/) {
                        Settings.onePressed = false;
                        Settings.twoPressed = true;
                        Settings.threePressed = false;
                        Settings.zeroPressed = false;
                        ammo.setImage(blueAmmo);
                    }
                    if (event.getCode() == KeyCode.NUMPAD3 || event.getCode() == KeyCode.L/* && Settings.zadneCisloZmacknuto()*/) {
                        Settings.onePressed = false;
                        Settings.twoPressed = false;
                        Settings.threePressed = true;
                        Settings.zeroPressed = false;
                        ammo.setImage(greenAmmo);
                    }
                    if (event.getCode() == KeyCode.NUMPAD0 || event.getCode() == KeyCode.M/* && Settings.zadneCisloZmacknuto()*/) {
                        Settings.onePressed = false;
                        Settings.twoPressed = false;
                        Settings.threePressed = false;
                        Settings.zeroPressed = true;
                        ammo.setImage(yellowAmmo);
                    }
                } else {
                    if (event.getCode() == KeyCode.P) {
                        zpetDoHry();
                    }
                    if (event.getCode() == KeyCode.ENTER) {
                        startHandler();
                    }
                }
                //nastaveni pomocne pro vraceni se zpatky v napovede
                if (infoZapnuto && event.getCode() == KeyCode.BACK_SPACE) {
                    scene.setOnMouseClicked(null);
                    start.setVisible(true);
                    konec.setVisible(true);
                    info.setVisible(true);
                    ovladaniInfo.setVisible(false);
                    infoZapnuto = false;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    spacePressed = false;
                    spaceRectangle.setWidth(0);
                }
            }
        });
        odpocet.setTranslateX(p.getTranslateX() + 33);//slouzi pro odpocet
        odpocet.setTranslateY(p.getTranslateY());

        ovladaniInfo.setVisible(false); //icinializane hlavni nabidky
        nabidkaPozadi.setVisible(false);
        start.setVisible(false);
        konec.setVisible(false);
        info.setVisible(false);
        start.setTranslateX(p.getTranslateX());
        start.setTranslateY(70);
        info.setTranslateX(p.getTranslateX());
        info.setTranslateY(p.getTranslateY());
        konec.setTranslateX(p.getTranslateX());
        konec.setTranslateY(p.getTranslateY() + info.getTranslateY() - start.getTranslateY());

        //inicializace pauzy
        paused.setTranslateX(200);
        paused.setTranslateY(600);
        paused.setVisible(false);

        root.getChildren().addAll(background, p.getImage(), edge, spaceRectangle, odpocet, scoreText, ammo, special, lives, nabidkaPozadi, start, konec, info, paused, ovladaniInfo/*musi byt nakonci*/, hightScoreText);

        hlavniNabidka();

    }

    public static void hraZkoncila() {
        gameOverSound.play();
        ImageView konecHryImage = new ImageView("images/konecHry.png");
        konecHryImage.setTranslateX(p.getTranslateX() - 300);
        konecHryImage.setTranslateY(p.getTranslateY());
        ImageView restart = new ImageView("images/restart.png");
        restart.setTranslateX(konecHryImage.getTranslateX());
        restart.setTranslateY(600);
        restart.setOnMousePressed((MouseEvent event) -> {
            restartHry(konecHryImage, restart);
        });
        root.getChildren().addAll(konecHryImage, restart);
        Settings.konecHryBoolean = true;
        //try {
            //ulozeni nejvyssiho skore
            if (Settings.score > hightScoreInt) {
                
                //hightScoreInt = ukladac.saveAndReturn(Settings.score, "hightscore");
                hightScoreText.setText("Nejvyssi score = "+ hightScoreInt);
            }
       // } catch (IOException ex) {
         //   System.out.println("nejde s dokumentem");
        //}
    }

    private static void restartHry(ImageView konecHryImage, ImageView restart) {
        for (int i = 0; i < Settings.neprateleVeHre.size(); i++) { //odstrani vsechny mrtvoly
            Settings.neprateleVeHre.get(i).setVisible(false);
            Settings.neprateleVeHre.remove(i);
        }
        Settings.rychlostSpawnovani = Settings.ZACATECNI_RYCHLOST;
        Settings.pocetZivotu = 3;
        lives.setImage(new Image("images/lives" + Settings.pocetZivotu + ".png"));
        Settings.pocetSchopnosti = 3;
        special.setImage(new Image("images/special" + Settings.pocetSchopnosti + ".png"));
        Settings.score = 0;
        scoreText.setText("Score = " + Settings.score);
        zpustenoPoprve = true;
        hlavniNabidka();
        konecHryImage.setVisible(false);
        konecHryImage = null;
        restart.setVisible(false);
        restart = null;
        ammo.setImage(redAmmo);
        Settings.konecHryBoolean = false;
    }

    public static void hlavniNabidka() {
        hightScoreText.setVisible(true);
        scoreText.setVisible(false);
        hlavninabidkaAktivni = true;
        for (int i = 0; i < neHlavniNabidka.length; i++) {
            neHlavniNabidka[i].setVisible(false);
        }
        spaceRectangle.setVisible(false);
        for (int i = 0; i < Settings.neprateleVeHre.size(); i++) {
            Settings.neprateleVeHre.get(i).setVisible(false);
        }
        for (int i = 0; i < anoHlavniNabidka.length; i++) {
            anoHlavniNabidka[i].setVisible(true);
        }
        konec.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        info.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start.setVisible(false);
                konec.setVisible(false);
                info.setVisible(false);
                ovladaniInfo.setVisible(true);
                infoZapnuto = true;
            }
        });
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startHandler();
            }
        });
    }

    //metoda se provede pokud se zmackne start nebo enter v hlavni nabidce
    public static void startHandler() {
        if (zpustenoPoprve) {
            zpustenoPoprve = false;
            zpetDoHry();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i < 4; i++) {
                        try {
                            Thread.sleep(1000);
                            countDownSound.play();
                            odpocet.setImage(new Image("images/" + i + ".png"));
                        } catch (InterruptedException ex) {
                            System.out.println("preruseno");
                        }
                    }
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ex) {
                        System.out.println("preruseno");
                    }
                    odpocet.setImage(new Image("images/startOdpocet.png"));
                    odpocet.setTranslateX(odpocet.getTranslateX() - 90);
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ex) {
                        System.out.println("preruseno");
                    }
                    odpocet.setVisible(false);
                    if (opravduPouzeJednou) {
                        vypustitMrtvoly();
                        Main.thread.setDaemon(true);
                        Main.thread.start();
                        opravduPouzeJednou = false;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } else {
            odpocet.setVisible(false);
            zpetDoHry();
        }
    }

    public static void zpetDoHry() {
        paused.setVisible(false);
        hightScoreText.setVisible(false);
        scoreText.setVisible(true);
        spaceRectangle.setVisible(true);
        hlavninabidkaAktivni = false;
        for (int i = 0; i < Settings.neprateleVeHre.size(); i++) {
            Settings.neprateleVeHre.get(i).setVisible(true);
        }
        for (int i = 0; i < neHlavniNabidka.length; i++) {
            neHlavniNabidka[i].setVisible(true);
        }
        for (int i = 0; i < anoHlavniNabidka.length; i++) {
            anoHlavniNabidka[i].setVisible(false);
        }
    }
    
    public static void vypustitMrtvoly() {
        Random r = new Random();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(Settings.rychlostSpawnovani);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //System.out.println(Settings.rychlostSpawnovani);
                                if (!hlavninabidkaAktivni && !Settings.konecHryBoolean) {
                                    //spawnovani monster
                                    Monster m = new Monster(Settings.ALL_MONSTERS[r.nextInt(Settings.ALL_MONSTERS.length)]); //vytvoreni nahodne prisery
                                    Settings.neprateleVeHre.add(m);
                                    Main.root.getChildren().add(m);
                                    switch (m.getSmer()) {
                                        case 0:
                                            m.setTranslateX(p.getTranslateX());
                                            m.setTranslateY(Main.scene.getHeight());
                                            break;
                                        case 1:
                                            m.setTranslateY(p.getTranslateY());
                                            break;
                                        case 2:
                                            m.setTranslateX(p.getTranslateX());
                                            break;
                                        case 3:
                                            m.setTranslateX(Main.scene.getWidth());
                                            m.setTranslateY(p.getTranslateY());
                                            break;
                                    }
                                }
                            }
                        });
                    } catch (InterruptedException ex) {
                        System.out.println("nejde cekat");
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
