import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
	
	
	private Options options;
	private int gravity, airResistance, launchVel, mukLevel;
	
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
		
		options = new Options();
		getOptions();
		
		
		
		entities = new ArrayList<Entity>();
		entities.add(new Entity(Entity.Type.CAPTIAN, this));
		matt = new Matt(this, gravity, airResistance, launchVel);
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
				entities.add(getRandomEntity(mukLevel));
			}else if(matt.centered)
			{
				e.setX(e.getX() - matt.getVx());
			}
		}
	}
	
	private Entity getRandomEntity(int mukLevel)
	{
		if (mukLevel != 1)
		{
			int r = new Random().nextInt(6 - (mukLevel - 1));
			
			if (r == 0)
				return new Entity(Entity.Type.MUK, this);
		}
		
		return new Entity(Entity.Type.values()[(int)(Math.random()*Entity.Type.values().length)], this);
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
	
	
	
	private void getOptions()
	{
		final Object lock = new Object();

	    Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (options.isVisible())
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                System.out.println("Working now");
	            }
	        }
	    };
	    
	    t.start();
	    
	    options.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
	            synchronized (lock) {
	                options.setVisible(false);
	                lock.notify();
	                
	                gravity = options.getGravity();
	                airResistance = options.getAir();
	                launchVel = options.getLaunch();
	                mukLevel = options.getMukLevel();
	            }
	        }

	    });

	    try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
