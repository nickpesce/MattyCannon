
public class Loop {

	private Game game;
	private boolean running;
	public Loop(Game game){
		this.game = game;
	}
	
	public void run()
	{
		running = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long start;
				while(running)
				{
					start = System.currentTimeMillis();
					Loop.this.game.update();
					try {
						Thread.sleep(Math.max(0, 16 - (System.currentTimeMillis() - start)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void stop()
	{
		running = false;
	}
}
