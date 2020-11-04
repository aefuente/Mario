import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Iterator;

public class Mario extends Sprite{
    int count;
    static BufferedImage[] mario_image;
    int image_displayed;
    // Allocates memory for mario images
    static{
        mario_image = new BufferedImage[5];
        for(int i = 0; i < 5; i++)
            mario_image[i] = null;
    }
    Mario(){
        // Loads mario images if they are null
        for (int i = 0; i < 5; i++){
            if(mario_image[i] == null) {
                mario_image[i] = View.loadImage("mario" + (i + 1) + ".png");
            }
        }

        // Sets initial coordinates and count variable for mario
        count = 0;
        Y = 200;
        X = 100;
        width = 60;
        height = 95;
        speed = 7;
        remove = false;
    }

    Mario(Json ob){
        // Loads mario images if they are null
        for (int i = 0; i < 5; i++){
            if(mario_image[i] == null) {
                mario_image[i] = View.loadImage("mario" + (i + 1) + ".png");
            }
        }

        // Sets initial coordinates and count variable for mario
        count = 0;
        X = (int)ob.getLong("x");
        Y = (int)ob.getLong("y");
        width = 60;
        height = 95;
        speed = 7;
        remove = false;
    }

    // Keeps track of marios y position
    void update(int floor){
        // gets updated every frame so count counts up
        count++;
        // Mario hits the floor, count is reset
        if (Y >= floor){
            vert_vel = 0.0;
            Y = floor;
            count = 0;
        }
        // Mario is in the air
        else {
            vert_vel += 1.2;
            Y += vert_vel;
            // Makes sure he doesn't get moved to the floor
            if (Y >= floor){
                vert_vel = 0.0;
                Y = floor;
                count = 0;
            }
        }
    }
    // Draws the image
    void draw(Graphics g, int ScrollPos) {
        g.drawImage(Mario.mario_image[image_displayed], 100,Y, null);

    }


    Json marshal(){
        Json ob = Json.newObject();
        ob.add("x",X);
        ob.add("y",Y);
        ob.add("type",0);
        return ob;
    }

    boolean isTube() {
        return false;
    }


    boolean isMario() {
        return true;
    }


    boolean isFireBall() {
        return false;
    }


    boolean isGoomba() {
        return false;
    }

    // Detects collisions
    boolean Collision(ArrayList<Sprite> sprites){
        for (Sprite S : sprites){
            if (this != S && !(X + width < S.X || X > S.X +S.width || Y >= S.Y  +S.height || Y + height <= S.Y))
            {
                return true;
            }
        }
        return false;
    }

    // Calulates the floor
    int findFloor(ArrayList<Sprite> sprites) {
        Iterator<Sprite> itSprites = sprites.iterator();
        int tmpfloor = 450;
        while(itSprites.hasNext()) {
            Sprite S = itSprites.next();
            if (this != S && (S.isTube() || S.isGoomba()) && !(X + width < S.X || X > S.X +S.width)) {
                if(tmpfloor > S.Y)
                    tmpfloor = S.Y;
                // Makes goombas baby trampolines
                if(S.isGoomba() && S.Y <= Y + height + vert_vel) {
                    Y-=1;
                    if(vert_vel < 15)
                        vert_vel = -15;
                    else
                        vert_vel = -vert_vel;
                    // Kills the goomba if touched
                    S.death();
                }
            }
        }
        return tmpfloor - height;
    }

    void death() { }

    // Makes mario jump only if count < 5
    void jump(){
        if (count < 5) {
            Y-=1;
            vert_vel = -15;
        }
    }

}
