import javax.swing.JDialog;
import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 7552526367108577562L;	
	public Frame() 
	{
		setVisible(true);
		setSize((int)(Game.WIDTH*Game.SCALE), (int)(Game.HEIGHT*Game.SCALE));
		setTitle("Super Matt");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}