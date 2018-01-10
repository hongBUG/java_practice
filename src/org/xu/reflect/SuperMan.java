package org.xu.reflect;

public class SuperMan extends Person {
	
	private boolean fly;
	private float height;
	public SuperMan(boolean fly, float height, float weight) {
		super();
		this.fly = fly;
		this.height = height;
		this.weight = weight;
	}
	
	public void fly() {
		System.out.println("superMan can fly!");
	}
	
	public void walk(int m){
		System.out.println("superMan walked " + m + "kilometers");
	}
	public SuperMan() {
		super();
	}
	/**
	 * @return the fly
	 */
	public boolean isFly() {
		return fly;
	}
	/**
	 * @param fly the fly to set
	 */
	public void setFly(boolean fly) {
		this.fly = fly;
	}
	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
	private float weight;
}
