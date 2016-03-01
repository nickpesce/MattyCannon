import java.awt.Color;
import java.awt.Graphics2D;


public class Matt{
	public static final double g = .3;
	double y, x, vx, vy, ax;
	int width, height;
	private Game game;
	boolean centered;
	public int distance;
	
	public Matt(Game game)
	{
		x = 10;
		y = 600;
		vy = -25;
		width = height = 200;
		vx = 30;
		ax = -.05;
		this.game = game;
		centered = false;
	}
	
	public void update() {

		if(y + height >= Game.HEIGHT - 100)
		{
			Entity.Type entity = game.getEntityAtLocation(x);
			if(entity != null)
			{
				switch(entity){
				case CAPTIAN:
					vy = -30;
					vx = vx + 10;
					break;
				case COW:
					vy = -30;
					vx = vx + 10;
					break;
				case FRIENDS:
					vy = -30;
					vx = vx + 10;
					break;
				case MUK:
					game.gameOver("Oh no!! You ran into Mukund!");
					break;
				case TWIN:
					game.gameOver("Oh no!! You were caught twinning!");
					break;
				}
			}
		}
		if(y + height >= Game.HEIGHT)
		{
			y = Game.HEIGHT - height;
			vy = -vy/2;
			if(vy >= -1)
				game.gameOver("Game Over! You stopped moving!");
		}
		if(!centered && vx > 0)
		{
			x += vx;
			if(x > (Game.WIDTH/2)-width/2)
			{
				x = (Game.WIDTH/2)-width/2;
				centered = true;
			}
		}
		vy += g;
		y += vy;
		if(vx + ax > 10)
		{
			vx += ax;
		}
		distance += vx;
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(game.mattImage, (int)x, (int)y, width, height, null);
	}

	public double getVx() {
		return vx;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getX()
	{
		return x;
	}

	public void boost() {
		if(game.boosts > 0)
		{
			game.boosts--;
			vy += -30;
			vx += 10;
		}
	}

}
