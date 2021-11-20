package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie {
    int x, y, w, h, speed;
    String type;
    boolean active = true;

    //Animation Variables
    int rows = 1, cols = 4;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f; //1 seconds = 1.0f

    Zombie(String type, int x, int y, int speed){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;

        w = (Tables.zombies.get(type) == null ? Resources.zombie : Tables.zombies.get(type)).getWidth() / cols;
        h = (Tables.zombies.get(type) == null ? Resources.zombie : Tables.zombies.get(type)).getHeight() / rows;
        init_animations();
    }

    void draw(SpriteBatch b){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        b.draw(frame, x, y);
    }

    void update(){
        x -= speed;
        active = x > -50;
    }

    void init_animations(){
        //split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split((Tables.zombies.get(type) == null ? Resources.zombie : Tables.zombies.get(type)), w, h);

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
