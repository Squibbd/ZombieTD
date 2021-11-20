package com.main;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Cannon {
    int x, y, w, h;
    int counter = 0, delay = 50;
    boolean active = true;
    String type;
    Sprite sprite;

    Cannon(String type, int x, int y){
        this.type = type;
        sprite = new Sprite(Tables.cannons.get(type) == null ? Resources.cannon : Tables.cannons.get(type));
        w = (Tables.cannons.get(type) == null ? Resources.cannon : Tables.cannons.get(type)).getWidth();
        h = (Tables.cannons.get(type) == null ? Resources.cannon : Tables.cannons.get(type)).getHeight();
        this.x = gridlock(x - w / 2);
        this.y = gridlock(y - h / 2);
        sprite.setPosition(this.x, this.y);
    }

    void update(){
        counter++;
        if(counter > delay){
            if(!Main.zombies.isEmpty()) Main.bullets.add(new Bullet(type, x + w / 2, y + h / 2));
            counter = 0;
        }
        sprite.setRotation(calcangle());
    }

    void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    int gridlock(int n){
        return ((int)((n + 25) / 50) * 50);
    }

    float calcangle(){
        float zx = Main.zombies.get(0).x + (float)Main.zombies.get(0).w / 2;
        float zy = Main.zombies.get(0).y + (float)Main.zombies.get(0).h / 2;
        return (float)Math.toDegrees(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
