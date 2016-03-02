import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.text.AttributedCharacterIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Canvas extends java.awt.Canvas implements KeyListener{
	private static final long serialVersionUID = -1354251777507926593L;

	private Game game;
	BufferStrategy bufferStrategy;
	Font font;
	DecimalFormat format;
	public Canvas(Game game)
	{
		this.game = game;
		setPreferredSize(new Dimension((int)(Game.WIDTH*Game.SCALE), (int)(Game.HEIGHT*Game.SCALE)));
		setVisible(true);
		setIgnoreRepaint(true);
		addKeyListener(this);
		font = new Font("ARIAL", Font.PLAIN, 15);
		format = new DecimalFormat("00000000");
	}
	
	public void render()
	{
		if(bufferStrategy == null)
		{
			createBufferStrategy(2);
			bufferStrategy = getBufferStrategy();
		}
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.scale(Game.SCALE,  Game.SCALE);


		g.drawImage(game.background, game.backgroundOffset, 0, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(game.background, game.backgroundOffset + Game.WIDTH, 0, Game.WIDTH, Game.HEIGHT, null);
		
		for(Entity e : game.getEntities())
		{
			e.draw(g);
		}


		if(game.getMatt().getY() < 0)
		{
			g.drawImage(game.arrow, (int)game.getMatt().getX(), 0, 100, 100, null);
			g.drawString((int)((-game.getMatt().getY())/10.0) + " ft", (int)game.getMatt().getX() + 32, 120);
		}
		else
			game.getMatt().draw(g);
		
		g.drawString(format.format((int)(game.getMatt().distance/10.0))+" ft", 0, 25);
		
		g.setColor(Color.RED);
		for(int i = 0; i < game.boosts; i++)
		{
			g.fillOval(Game.WIDTH - 30, i * 30 + 5, 25, 25);
		}
		g.dispose();

		bufferStrategy.show();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
		{
			game.getMatt().boost();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	public void displayLoading() {
		if(bufferStrategy == null)
		{
			createBufferStrategy(2);
			bufferStrategy = getBufferStrategy();
		}
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		g.setColor(Color.GREEN);getBounds();
		g.scale(Game.SCALE, Game.SCALE);
		g.setFont(new Font("ARIAL", Font.BOLD, 72));
		g.drawString("HAPPY BIRTHDAY MATT!", Game.WIDTH/2 - 400, Game.HEIGHT/2 - 20);
		g.dispose();

		bufferStrategy.show();
	}
}
