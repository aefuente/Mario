/*

Name: Andre Fuentes
Date: 9/22/20
Description: This is the model file that handles the information of where our
tubes are being placed.

 */

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Model {
    // Member Variables to hold turtle location info
    ArrayList<Sprite> sprites;
    Mario mario;
    int scrollPos;

    // Constructor that initializes the sprite list
    Model()
    {
        scrollPos = 0;
        sprites = new ArrayList<Sprite>();

        //Loads the Json
        Json ob = Json.load("Map.json");
        Json tmpList = ob.get("sprites");

        //Adds data from jason to sprite arra list
        for (int i = 0; i < tmpList.size(); i++){
            //Type 0 is mario
            if (tmpList.get(i).getLong("type") == 0) {
                mario = new Mario(tmpList.get(i));
                sprites.add(mario);
            }
            // Type 1 is goomba
            else if (tmpList.get(i).getLong("type") == 1){
                Goomba goomba = new Goomba(tmpList.get(i));
                sprites.add(goomba);
            }

            // Type 2 is tube
            else if (tmpList.get(i).getLong("type") == 2) {
                Tube tube = new Tube(tmpList.get(i));
                sprites.add(tube);
            }

        }
    }

    // If you want to restart the map you can just hit "L"
    void setModel(Json ob){
        sprites = new ArrayList<Sprite>();
        Json tmpList = ob.get("sprites");
        for (int i = 0; i < tmpList.size(); i++){
            if (tmpList.get(i).getLong("type") == 0) {
                mario = new Mario(tmpList.get(i));
                sprites.add(mario);
            }

            else if (tmpList.get(i).getLong("type") == 1){
                Goomba goomba = new Goomba(tmpList.get(i));
                sprites.add(goomba);
            }
            else if (tmpList.get(i).getLong("type") == 2) {
                Tube tube = new Tube(tmpList.get(i));
                sprites.add(tube);
            }

        }
    }


    // Fix this update function
    public void update()
    {
        for (Sprite S : sprites){
            // Scrollpos follows mario
            if (S.isMario())
                scrollPos = S.X - 100;

            // I know this is a lot of data to be passing :( I would use pointers but we haven't worked with those yet (P.S I just learned java doesn't have pointers :( )
            S.update(S.findFloor(sprites));
            if (!S.isMario() && !S.isTube())
                S.Collision(sprites);
        }
        sprites.removeIf(S -> S.remove);
    }

    // Spawns a fireball
    void createFireBall() {
        FireBall fireball = new FireBall(mario.X + mario.width, mario.Y);
        sprites.add(fireball);
    }

    // Detects for tube collision on the right and moves right if not
    void marioMoveRight(){
        mario.X += mario.speed;
        if(mario.Collision(sprites)){
            mario.X-=mario.speed;
        }
        else
            mario.image_displayed = (mario.image_displayed +1)  % 5;
    }


    // Detects for tube collision on the left and moves left if not
    void marioMoveLeft(){
        mario.X -= mario.speed;
        if (mario.Collision(sprites))
            mario.X += mario.speed;
        else{
            if (mario.image_displayed - 1 < 0)
                mario.image_displayed = 4;
            else
                mario.image_displayed--;
            }
    }

    Json marshal(){
        Json ob = Json.newObject();
        Json spritesob = Json.newList();
        ob.add("sprites", spritesob);
        for (Sprite S : sprites)
        {
            spritesob.add(S.marshal());
        }
        return ob;
    }
}
