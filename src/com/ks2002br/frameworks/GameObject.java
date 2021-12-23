package com.ks2002br.frameworks;

/*
 * By Elisandro
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected ObjectId id;
	protected float x, y;
	protected float spdX, spdY;
	
	protected boolean falling = true; //CAINDO
	protected boolean jumping = false; // PULO
	
	protected int type;

	// construtor
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public GameObject(float x, float y, int type, ObjectId id) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> obj);

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public ObjectId getId() {
		return id;
	}

//	public void setId(ObjectId id) {
//		this.id = id;
//	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpdX() {
		return spdX;
	}

	public void setSpdX(float spdX) {
		this.spdX = spdX;
	}

	public float getSpdY() {
		return spdY;
	}

	public void setSpdY(float spdY) {
		this.spdY = spdY;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}
