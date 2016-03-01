import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options extends JFrame implements ChangeListener
{
	private JLabel gravityLabel = new JLabel("Gravity");
	private JSlider gravity = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
	
	private JLabel airLabel = new JLabel("Air resistance");
	private JSlider air = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
	
	private JLabel launchLabel = new JLabel("Launch velocity");
	private JSlider launch = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
	
	private JLabel difficulty = new JLabel("Difficulty: ");
	private JLabel pic = new JLabel();

	public Options()
	{
	    setSize(330, 300);
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    setVisible(true);
	    this.setLayout(new FlowLayout());
	    
	    add(gravityLabel);
	    add(gravity);
	    gravity.setMajorTickSpacing(1);
	    gravity.setPaintTicks(true);
	    gravity.setPaintLabels(true);
	    gravity.setLabelTable(getLabelTable());
	    
	    add(airLabel);
	    add(air);
	    air.setMajorTickSpacing(1);
	    air.setPaintTicks(true);
	    air.setPaintLabels(true);
	    air.setLabelTable(getLabelTable());
	    
	    add(launchLabel);
	    add(launch);
	    launch.setMajorTickSpacing(1);
	    launch.setPaintTicks(true);
	    launch.setPaintLabels(true);
	    launch.setLabelTable(getLabelTable());
	    
	    gravity.addChangeListener(this);
	    air.addChangeListener(this);
	    launch.addChangeListener(this);
	    
	    difficulty.setFont(new Font("Verdana", Font.BOLD, 24));
	    add(difficulty);
	    pic.setIcon(getImage("src/medium.jpg"));
	    add(pic);
	    
	    validate(); repaint();
	}
	
	private Hashtable getLabelTable()
	{
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 1 ), new JLabel("Low") );
		labelTable.put( new Integer( 3 ), new JLabel("Average") );
		labelTable.put( new Integer( 5 ), new JLabel("High") );
		return labelTable;
	}
	
	public int getDifficulty()
	{
		return gravity.getValue() + air.getValue() + (6 - launch.getValue());
	}
	
	public int getGravity()
	{
		return gravity.getValue();
	}
	
	public int getAir()
	{
		return air.getValue();
	}
	
	public int getLaunch()
	{
		return launch.getValue();
	}
	
	private ImageIcon getImage(String path)
	{
		ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return new ImageIcon(newimg);  // transform it back
	}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
		int difficulty = getDifficulty();
		System.out.println(difficulty);
		
		ImageIcon img = null;
		if (difficulty >= 13)
			img = getImage("src/olivia.png");
		else if (difficulty >= 11)
			img = getImage("src/brendan.png");
		else if (difficulty >= 9)
			img = getImage("src/medium.jpg");
		else if (difficulty >= 7)
			img = getImage("src/joey.png");
		else
			img = getImage("src/jared.jpg");
		
		pic.setIcon(img);
		
		validate(); repaint();
	}
}
