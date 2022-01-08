package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

/*
	- button logic (?)
 */

public class Main extends ApplicationAdapter {
	// Game Variables
	SpriteBatch batch;
	static Random r = new Random();

	// Control Variables
	static String current_type = "ccc";

	// Game Lists
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	static ArrayList<EFFECT> effects = new ArrayList<EFFECT>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		setup();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		update();
		batch.begin();
		batch.draw(Resources.bg, 0, 0);
		UI.draw(batch);
		for(Zombie z : zombies) z.draw(batch);
		for(Cannon c : cannons) c.draw(batch);
		for(Button b : buttons) b.draw(batch);
		for(Bullet b : bullets) b.draw(batch);
		for(EFFECT e : effects) e.draw(batch);
		batch.end();
	}

	void update(){
		//calls
		tap();
		spawn_zombies();

		//loops
		for(Zombie z : zombies) z.update();
		for(Cannon c : cannons) c.update();
		for(Button b : buttons) b.update();
		for(Bullet b : bullets) b.update();

		//remove inactive objects
		housekeeping();
	}

	void housekeeping() {

		for (Zombie z : zombies)
			if (!z.active) {
				zombies.remove(z);
				break;

			}
		for (EFFECT e : effects)
			if (!e.active) {
				effects.remove(e);
				break;
			}
	}


	void tap(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();
			effects.add(new EFFECT("boom",x,y));

			for(Button b: buttons){
				if(!b.t.hidden&&b.t.hitbox().contains(x,y))return;
				if(b.gethitbox().contains(x, y)){

					if(b.locked){
						if (b.t.hidden){hidett(); b.t.hidden=false;}
						else { hidett();b.locked=false;}
					}


					else {
						deselect();
						hidett();
						b.selected = true;
						current_type = b.type;
					}
					return;
				}
				if(!b.t.hidden&&!b.t.hitbox().contains(x,y)){b.t.hidden=true;return;}
			}

			//leave cannons last
			for(Cannon c : cannons) if(c.hitbox().contains(x, y)) return;
			if(buildable(x, y)) cannons.add(new Cannon(current_type, x, y));
		}
	}
	void hidett() {for(Button b: buttons) b.t.hidden=true;}

	boolean anybutton(int x, int y){
		for (Button b:buttons)if(b.gethitbox().contains(x,y)) return true;
		return false;
	}
	boolean buildable(int x, int y){
		return ((y>0&&y<200)||(y>300&&y<500)) && x<1000;

	}

	void deselect(){
		for(Button b : buttons) b.selected = false;
	}

	void setup(){
		Tables.init();
		current_type="cannon";
		buttons.add(new Button("bbb", 225 + buttons.size() * 75, 525));
		buttons.get(buttons.size()-1).locked=false;
		buttons.get(buttons.size()-1).locked=true;
		buttons.add(new Button("fire", 225 + buttons.size() * 75, 525));
		buttons.add(new Button("super", 225 + buttons.size() * 75, 525));
		buttons.add(new Button("double", 225 + buttons.size() * 75, 525));
		buttons.add(new Button("laser", 225 + buttons.size() * 75, 525));
	}

	void spawn_zombies() {
		if(!zombies.isEmpty()) return;
		for(int i = 0; i < 5; i++) zombies.add(new Zombie("zzz", 1055 + i * 90, r.nextInt(450), 5));
	}

	// TODO: Don't go below this line
	@Override
	public void dispose () {
		batch.dispose();
	}
}
