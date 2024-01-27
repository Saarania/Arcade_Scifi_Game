/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergamefinal;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Pocitac
 */
public class Player {

    private Direction direction;
    private int health; //zacina na 3
    ImageView image;

    public Player() {
        direction = Direction.RIGHT;
        health = 3;
        image = new ImageView("images/SoldierRight.png");
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public void setTranslateX(int i) {
        image.setTranslateX(i);
    }

    public void setTranslateY(int i) {
        image.setTranslateY(i);
    }

    public int getTranslateX() {
        return (int) image.getTranslateX();
    }

    public int getTranslateY() {
        return (int) image.getTranslateY();
    }

}
