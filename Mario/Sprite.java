import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Sprite {
    int X;
    int Y;
    int width;
    int height;
    int speed;
    boolean remove;
    double vert_vel;

    abstract void update(int floor);
    abstract void draw(Graphics g, int scrollPos);
    abstract boolean Collision(ArrayList<Sprite> sprites);
    abstract int findFloor(ArrayList<Sprite>  sprites);
    abstract Json marshal();
    abstract void death();
    abstract boolean isTube();
    abstract boolean isMario();
    abstract boolean isFireBall();
    abstract boolean isGoomba();

}
