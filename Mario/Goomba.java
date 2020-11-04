import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Goomba extends Sprite{
    int image_displayed;
    int Dcount;
    int Dtime;
    static BufferedImage[] goombaimage;
    static {
        goombaimage = new BufferedImage[2];
        goombaimage[0] = null;
        goombaimage[1] = null;
    }

    //Initializes Gooba
    Goomba(int x, int y){
        if (goombaimage[0] ==null)
            goombaimage[0] = View.loadImage("goomba.png");
        if (goombaimage[1] == null)
            goombaimage[1] = View.loadImage("goomba_fire.png");
        X = x;
        Y = y;
        width = 40;
        height = 48;
        speed = 7;
        vert_vel = 0;
        image_displayed = 0;
        Dtime = 15;
        Dcount = Dtime;
        remove = false;
    }

    // Loads goomba from Json
    Goomba(Json ob){
        // Loads image
        if (goombaimage[0] ==null)
            goombaimage[0] = View.loadImage("goomba.png");
        if (goombaimage[1] == null)
            goombaimage[1] = View.loadImage("goomba_fire.png");
        X = (int)ob.getLong("x");
        Y = (int)ob.getLong("y");
        width = 40;
        height = 48;
        speed = 7;
        vert_vel = 0;
        image_displayed = 0;
        Dtime = 15;
        Dcount = Dtime;
        remove = false;
    }


    // Handles side to side motion, gravity and death timer
    void update(int floor) {
        X += speed;
        if (Y >= floor){
            vert_vel = 0.0;
            Y = floor;
        }
        // Goomba is in the air
        else {
            vert_vel += 1.2;
            Y += vert_vel;
            // Ensure goomba doesn't go into the floor
            if (Y >= floor){
                vert_vel = 0.0;
                Y = floor;
            }
        }
        // If death clock starts cause die
        if (Dcount != Dtime)
            Dcount--;
        if (Dcount <=0)
            remove = true;
    }

    // Death clock initiated
    void death(){
        speed = 0;
        image_displayed = 1;
        Dcount--;

    }

    // Draws the goom
    void draw(Graphics g, int scrollPos) { g.drawImage(goombaimage[image_displayed], X - scrollPos, Y, null);

    }

    // Detects colision with tube and fireball
    boolean Collision(ArrayList<Sprite> sprites) {
        for (Sprite S : sprites){
            if (this != S && !S.isMario() && !(X + width + speed < S.X || X + speed > S.X +S.width || Y > S.Y  +S.height || Y + height < S.Y))
            {
                speed = - speed;
                return true;
            }
        }
        return false;
    }

    // Goomba can fall down tubes if they want, so we find the floor.
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

    Json marshal(){
        Json ob = Json.newObject();
        ob.add("x",X);
        ob.add("y",Y);
        ob.add("type",1);
        return ob;
    }

    boolean isTube() {
        return false;
    }

    boolean isMario() {
        return false;
    }


    boolean isFireBall() {
        return false;
    }


    boolean isGoomba() {
        return true;
    }
}
