import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Entity {

	private double x, y;
	private int width, height;
	private Type type;
	private Game game;
	
	public enum Type {
		CAPTIAN, COW, FRIENDS, MUK, TWIN;
	}
	


	public Entity(Type t, Game game)
	{
		this.type = t;
		this.game = game;
		this.width = this.height = 150;
		this.x = (int)(Math.random()*500)+Game.WIDTH;
		this.y = Game.HEIGHT - height;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(Graphics2D g) {
		switch(type)
		{
		case CAPTIAN:
			g.drawImage(game.captian, (int)x, (int)y, width, height, null);
			break;
		case COW:
			g.drawImage(game.cow, (int)x, (int)y, width, height, null);
			break;
		case FRIENDS:
			g.drawImage(game.friends, (int)x, (int)y, width, height, null);
			break;
		case MUK:
			g.drawImage(game.muk, (int)x, (int)y, width, height, null);
			break;
		case TWIN:
			g.drawImage(game.twin, (int)x, (int)y, width, height, null);
			break;
		}
	}

	public Type getType() {
		return type;
	}
	
	
}
