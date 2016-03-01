
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
				while(running)
				{
					Loop.this.game.update();
					try {
						Thread.sleep(10);
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
