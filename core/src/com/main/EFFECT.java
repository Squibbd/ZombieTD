package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EFFECT {
    int x,y,w,h;
    boolean active=true;
    String type;
    int rows = 1, cols ;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f; //1 seconds = 1.0f
    EFFECT(String type,int x, int y){
        this.type=type;
        cols=(Tables.values.get("columns_"+type)==null?1:Tables.values.get("columns_"+type));
        w=(Tables.resources.get("effect_"+type)==null?Resources.click:Tables.resources.get("effect_"+type)).getWidth()/cols;
        h=(Tables.resources.get("effect_"+type)==null?Resources.click:Tables.resources.get("effect_"+type)).getHeight()/rows;
        this.x=x-w/2;
        this.y=y-h/2;
        init_animations();


    }
    void draw(SpriteBatch b){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        b.draw(frame, x, y);
        active=!anim.isAnimationFinished(frame_time);
    }

    void init_animations(){
        //split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split((Tables.resources.get("effect_"+type)==null?Resources.click:Tables.resources.get("effect_"+type)),w,h);

        //init number of frames to maximum number possible (all rows * all cols)
        frames = new TextureRegion[rows * cols];

        //loop through the sheet and fill frames array with cells (in order)
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

        //initialize animation
        anim = new Animation(frame_time, frames);
    }

}
