import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelImage extends JPanel implements MouseListener, MouseMotionListener
{
	private Controleur ctrl;

	private String nomImage;
	private JLabel image;

	private Integer x1;
	private Integer y1;
	private Integer x2;
	private Integer y2;

	private boolean deuxiemeClick; //passe à true au deuxieme click

	public PanelImage( Controleur ctrl, String nomImage )
	{
		this.ctrl = ctrl;
		this.nomImage = nomImage;
		this.deuxiemeClick = false;

		this.x1 = null;
		this.y1 = null;
		this.x2 = null;
		this.y2 = null;

		//this.image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.nomImage))));
		this.image = new JLabel(new ImageIcon(this.nomImage));

		this.image.addMouseListener(this);
		this.image.addMouseMotionListener(this);

		this.add(this.image);
	}

	public void paintChildren(Graphics g)
	{
		super.paintChildren(g);

		g.setColor(Color.RED);

		if(this.x1 != null && this.y1 !=null)
			g.fillOval(this.x1, this.y1, 10,10);

		if(this.x2 != null && this.y2 !=null)
			g.fillOval(this.x2, this.y2, 10,10);

		if(this.x1 != null && this.y1 !=null && this.x2 != null && this.y2 !=null)
		{
			g.setColor(Color.BLUE);

			if(this.x1 < this.x2 && this.y1 < this.y2)
				g.drawRect(this.x1, this.y1, this.x2-this.x1, this.y2-this.y1);

			else if(this.x1 > this.x2 && this.y1 < this.y2)
					g.drawRect(this.x1, this.y1, this.x2-this.x1, this.y2+this.y1);
		}
	}

	public void reset()
	{
		this.x1 = null;
		this.y1 = null;
		this.x2 = null;
		this.y2 = null;
	}

	// gestion de la souris
	@Override
	public void mouseClicked(MouseEvent evt) {
		if(!deuxiemeClick)
		{
			this.x1 = evt.getX();
			this.y1 = evt.getY();
			this.deuxiemeClick = true;
			//this.repaint();
		}

		else
		{
			this.x2 = evt.getX();
			this.y2 = evt.getY();
			this.deuxiemeClick = false;

			System.out.println("x1: " +this.x1+ " y1: " +this.y1+ " x2: " +this.x2+ " y2: " +this.y2);
			//this.repaint();
			//this.reset();
		}
		this.repaint();
		//this.reset();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("entre");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		this.reset();
		this.repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		System.out.println("x= " +evt.getX()+ " y= " +evt.getY());
		
	}
}