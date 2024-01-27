package computergamefinal;

import javafx.scene.media.AudioClip;

public class Test {

    private static final ClassLoader classLoader = Test.class.getClassLoader();


    public static void main(String[] args) {
        System.out.println("OLD one: " + Test.class.getClassLoader().getResource("sounds/shotSound.wav").toString());
        System.out.println("New one: " + classLoader.getResource("sounds/theme.wav").getPath());
    }
}
