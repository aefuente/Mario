import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.BufferOverflowException;
import java.util.ArrayList;

/*

Name: Andre Fuentes
Date: 9/22/20
Description: This is the file that holds the tube information

 */
public class Tube extends Sprite{
    // Creates class objects
    static BufferedImage tube_image = null;

    Tube(){

    }
    // For loading Json
    Tube(Json ob){
        if(tube_image == null)
            tube_image = View.loadImage("tube.png");

        X = (int)ob.getLong("x");
        Y = (int)ob.getLong("y");
        width = 55;
        height = 400;
        remove = false;
    }

    // Saving to Json
    Json marshal(){
        Json ob = Json.newObject();
        ob.add("x",X);
        ob.add("y",Y);
        ob.add("type",2);
        return ob;
    }


    void update(int floor) {

    }



    void draw(Graphics g,int scrollPos) {
        g.drawImage(tube_image, X - scrollPos, Y, null);
    }


    boolean Collision(ArrayList<Sprite> sprites) {
        return false;
    }


    int findFloor(ArrayList<Sprite> sprites) {
        return 0;
    }


    void death() {

    }

    boolean isTube() {
        return true;
    }

    boolean isMario() {
        return false;
    }


    boolean isFireBall() {
        return false;
    }


    boolean isGoomba() {
        return false;
    }
}
