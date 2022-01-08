package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    int x, y, w, h;
    int speed, dt = 0, md;
    boolean active = true;
    String type;
    float angle = 0f;

    Bullet(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = Resources.bullet.getWidth();
        h = Resources.bullet.getHeight();
        speed = 10;
        angle = calcangle();
    }

    void update(){
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
        checkcollisions();
    }

    float calcangle(){
        float zx = Main.zombies.get(0).x + (float)Main.zombies.get(0).w / 2;
        float zy = Main.zombies.get(0).y + (float)Main.zombies.get(0).h / 2;
        return (float)(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.bullet, x, y);
    }

    void checkcollisions(){
        for (Zombie z:Main.zombies)
            if (z.hitbox().contains(hitbox())){
                z.hp--;
                active=false;
            }
    }
    Rectangle hitbox(){return new Rectangle(x,y,w,h);}
}
