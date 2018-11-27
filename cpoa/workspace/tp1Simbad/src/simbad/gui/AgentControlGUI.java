package simbad.gui;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ex1.MyRobot;

import simbad.sim.Agent;
import simbad.sim.SimpleAgent;
import simbad.sim.Simulator;
import simbad.sim.World;

public class AgentControlGUI extends JPanel implements ActionListener
{
	private JButton left;
	private JButton right;
	
	private JButton more;
	private JButton less;
	private JButton def;
	
	private AgentFollower robotFollower;
	private World world;
	private Font smallFont;

	public AgentControlGUI()
	{
		
	}
	
	public AgentControlGUI(World world, Simulator simulator)
	{
		// controle de la direction du robot
		this.left  = new JButton("left" );
		this.right = new JButton("right");
		
		// controle de la vitesse du robot
		this.more = new JButton("more");
		this.less = new JButton("less");
		this.def  = new JButton("default");
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        // View Buttons
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Agent direction:"),BorderFactory.createEmptyBorder()));
        JPanel panel1_1 = new JPanel();
        panel1_1.setLayout(new BoxLayout(panel1_1, BoxLayout.X_AXIS));
        
        panel1_1.add(this.left);
        panel1_1.add(this.right);

        panel1.add(panel1_1);
             
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Agent Speed:"),
                BorderFactory.createEmptyBorder()));
        JPanel panel2_1 = new JPanel();
        panel2_1.setLayout(new BoxLayout(panel2_1, BoxLayout.X_AXIS));
        
        panel2_1.add(this.more);
        panel2_1.add(this.less);
        panel2_1.add(this.def );
        
        panel2.add(panel2_1);
        
        // ajout des deux groupes de boutons
        add(panel1);
        add(panel2);
		
		this.left.addActionListener(this);
		this.right.addActionListener(this);
		
		this.more.addActionListener(this);
		this.less.addActionListener(this);
		this.def.addActionListener(this);
		
        this.world = world;
        this.smallFont = new Font("Arial",Font.PLAIN,11);
        
        this.robotFollower = new AgentFollower(world, (SimpleAgent) simulator.getAgentList().get(0));
    }
	

	public AgentControlGUI(boolean arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AgentControlGUI(LayoutManager arg0, boolean arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{	
		if	   (evt.getSource() == left ) this.robotFollower.agent.rotateY(Math.PI/8);
		else if(evt.getSource() == right) this.robotFollower.agent.rotateY(-Math.PI/8);
		//else if(evt.getSource() == more ) 
	}

}
