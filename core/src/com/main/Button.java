package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    int x, y, w, h;
    boolean active = true;
    boolean selected, locked;
    String type;
    Tooltip t;

    Button(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = (Tables.buttons.get(type) == null ? Resources.button_cannon : Tables.buttons.get(type)).getWidth();
        h = (Tables.buttons.get(type) == null ? Resources.button_cannon : Tables.buttons.get(type)).getHeight();
        selected = false;
        locked = true;
        t=new Tooltip(type,this);
    }

    void update(){

    }

    void draw(SpriteBatch batch){
        batch.draw((Tables.buttons.get(type) == null ? Resources.button_cannon : Tables.buttons.get(type)), x, y);
        if(locked) batch.draw(Resources.locked, x, y);
        if(selected) batch.draw(Resources.selected, x - 7, y - 7);
        t.draw(batch);
    }

    Rectangle gethitbox() { return new Rectangle(x, y, w, h); }
}
