import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 7552526367108577562L;	
	public Frame() 
	{
		setVisible(true);
		setSize(1600, 910);
		setTitle("Super Matt");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}