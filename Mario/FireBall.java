import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class FireBall extends Sprite {
    int count;
    static BufferedImage fireballimage;
    static{
        fireballimage = null;
    }

    FireBall(int x, int y){
        // Loads the fireball image
        if(fireballimage == null)
            fireballimage = View.loadImage("fireball.png");
        X = x;
        Y = y;
        width = 47;
        height = 47;
        vert_vel = 0;
        speed = 10;
        remove = false;
    }

    FireBall(Json ob){
        // Loads image
        if(fireballimage == null)
            fireballimage = View.loadImage("fireball.png");
        X = (int)ob.getLong("x");
        Y = (int)ob.getLong("y");
        width = 47;
        height = 47;
        vert_vel = 0;
        speed = 10;
        remove = false;
    }

    // Updates floor location, and handles gravity and sidways motion
    void update(int floor){
        X += speed;
        if( Y >= floor){
            Y = floor-1;
            vert_vel = -vert_vel;
        }
        else{
            vert_vel += 1.2;
            Y += vert_vel;
            if (Y >= floor){
                Y = floor -1;
                vert_vel = -vert_vel;
            }
        }
    }

    // makes fire ball die if it hits the side of a tube or a goomba or goes off screen
    boolean Collision(ArrayList<Sprite> sprites){
        for (Sprite S : sprites){
            if ((S.isMario() && (Math.abs(X-S.X) > 400))) {
                System.out.println("FireBall Removed: went off screen");
                death();
            }
            if (this != S && !(X + width < S.X || X > S.X +S.width || Y > S.Y  +S.height || Y + height < S.Y))
            {
                if (!S.isMario() && (S.isTube() || S.isGoomba())) {
                    death();
                }
                if (S.isGoomba())
                    S.death();
                 return true;
            }
        }
        return false;
    }

    // Finds the floor, be it the ground or the top of a tube, or a goomba
    int findFloor(ArrayList<Sprite> sprites) {
        Iterator<Sprite> itSprites = sprites.iterator();
        int tmpfloor = 450;
        while(itSprites.hasNext()) {
            Sprite S = itSprites.next();
            if (this != S && S.isTube() && !(X + width < S.X || X > S.X +S.width)) {
                if(tmpfloor > S.Y)
                    tmpfloor = S.Y;
            }
        }
        return tmpfloor - height;
    }


    Json marshal() {
        Json ob = Json.newObject();
        ob.add("x",X);
        ob.add("y",Y);
        ob.add("type",3);
        return ob;
    }

    // Causes die
    void death() {
        remove = true;
    }

    // Draws the fireball
    void draw(Graphics g, int scrollPos) {
        g.drawImage(fireballimage, X - scrollPos, Y, null);
    }


    boolean isTube() {
        return false;
    }


    boolean isMario() {
        return false;
    }


    boolean isFireBall() {
        return true;
    }


    boolean isGoomba() {
        return false;
    }

}
