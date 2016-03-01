import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;



public class Game {

	public static final int WIDTH = 1600, HEIGHT = 900;
	public Frame frame; 
	private Loop loop;
	private Canvas canvas;
	public BufferedImage background, captian, cow, friends, muk, mattImage, arrow, twin;
	
	int boosts;
	private ArrayList<Entity> entities;
	private Matt matt;
	public int backgroundOffset;
	public static void main(String[] args)
	{
		new Game();
	}
	
	public Game()
	{
		frame = new Frame();
		canvas = new Canvas(this);
		frame.add(canvas);
		frame.pack();
		canvas.requestFocus();
		canvas.displayLoading();
		entities = new ArrayList<Entity>();
		entities.add(new Entity(Entity.Type.CAPTIAN, this));
		matt = new Matt(this);
		boosts = 3;
		//load all textures
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
			captian = ImageIO.read(getClass().getResourceAsStream("/captian.png"));
			cow = ImageIO.read(getClass().getResourceAsStream("/cow.png"));
			friends = ImageIO.read(getClass().getResourceAsStream("/friends.png"));
			muk = ImageIO.read(getClass().getResourceAsStream("/muk.jpg"));
			mattImage = ImageIO.read(getClass().getResourceAsStream("/matt.png"));
			arrow = ImageIO.read(getClass().getResourceAsStream("/arrow.png"));
			twin = ImageIO.read(getClass().getResourceAsStream("/twin.jpg"));


		} catch (IOException e) {
			e.printStackTrace();
		}
		loop = new Loop(this);
		loop.run();
	}

	public void update() {
		updateEntities();
		matt.update();
		canvas.render();
		if(matt.centered)
		{
			backgroundOffset -= matt.getVx();
			backgroundOffset %= Game.WIDTH;
		}
	}

	private void updateEntities()
	{
		for(Entity e : entities)
		{
			if(e.getX() < 0 - e.getWidth())
			{
				entities.remove(e);
				entities.add(new Entity(Entity.Type.values()[(int)(Math.random()*Entity.Type.values().length)], this));
			}else if(matt.centered)
			{
				e.setX(e.getX() - matt.getVx());
			}
		}
	}

	public void gameOver(String reason) {
		loop.stop();
		JOptionPane.showMessageDialog(frame, reason);
		System.exit(0);
	}

	public Matt getMatt() {
		return matt;
	}
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}

	public Entity.Type getEntityAtLocation(double x) {
		for(Entity e : entities)
		{
			if(e.getX() - 200 < x && e.getX() + e.getWidth() > x)
				return e.getType();
		}
		return null;
	}

}
