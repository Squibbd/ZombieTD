package com.main;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class Tables {
    static HashMap<String, Texture> cannons = new HashMap<String, Texture>();
    static HashMap<String, Texture> buttons = new HashMap<String, Texture>();
    static HashMap<String, Texture> zombies = new HashMap<String, Texture>();

    static void init(){
        cannons.put("fire", Resources.cannon_fire);
        cannons.put("double", Resources.cannon_double);
        cannons.put("super", Resources.cannon_super);
        cannons.put("laser", Resources.cannon_laser);
        cannons.put("mounted", Resources.cannon_mounted);

        buttons.put("fire", Resources.button_cannon_fire);
        buttons.put("double", Resources.button_cannon_double);
        buttons.put("super", Resources.button_cannon_super);
        buttons.put("laser", Resources.button_cannon_laser);
        buttons.put("mounted", Resources.button_cannon_mounted);

        zombies.put("dif", Resources.zombie_dif);
        zombies.put("fast", Resources.zombie_fast);
        zombies.put("speedy", Resources.zombie_speedy);
        zombies.put("riot", Resources.zombie_riot);
    }
}
