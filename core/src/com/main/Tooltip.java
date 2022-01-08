package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tooltip {
    int x,y,w,h;
    boolean hidden=true;
    String type;
    BitmapFont font = new BitmapFont();
    GlyphLayout layout=new GlyphLayout();
    Tooltip(String type, Button p){
        w=200;
        h=100;
        x=(p.x+p.w/2)-w/2;
        y=p.y-h-2;
    }
    void draw(SpriteBatch batch){
        if (hidden) return;
        batch.draw(Resources.tooltip_bg,x,y,w,h);
        int rtx =25, rty=3;
        String[] words ="Fires cannon balls, what did you expect?".split(" ");
        for (String s : words){
            if (rtx + layout.width > w - 26) {
            rtx=25;
            rty+=layout.height+3;

            }
            font.setColor(Color.BLACK);
            font.draw(batch,s,x+rtx,y+h-rty);
            layout.setText(font," "+s);
            rtx+=layout.width;
            }
            font.setColor(Color.GRAY);
            font.getData().setScale(2.0f);
            font.draw(batch,"Unlock : 5000", x+12, y+50);
            font.getData().setScale(1.0f);
            font.draw(batch,"tap to unlock", x+37, y+15);
        }


    Rectangle hitbox(){return new Rectangle(x,y,w,h);}
}
