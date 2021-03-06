package zoneTerritoire.ihm;
import javax.swing.*;

import zoneTerritoire.Controleur;
import zoneTerritoire.metier.LectureXML;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.InputStreamReader;

public class PanelImage extends JPanel implements MouseListener, MouseMotionListener
{
	private Controleur ctrl;

	private String nomImage;
	private JLabel image;

	public PanelImage( Controleur ctrl, String nomImage )
	{
		this.ctrl = ctrl;
		this.nomImage = nomImage;
		
		InputStreamReader ipsr = new InputStreamReader(this.getClass().getResourceAsStream("/" +nomImage));
		//this.image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.nomImage))));
		//this.image = new JLabel(new ImageIcon(ipsr));

		this.add(this.image);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.nomImage));
		g.drawImage(img, 1024, 768, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		evt.getX();
		
	}
}