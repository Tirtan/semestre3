package tp3;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simbad.sim.Arch;
import simbad.sim.BlockWorldObject;
import simbad.sim.Box;
import simbad.sim.CherryAgent;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Simulator;
import simbad.sim.Wall;

public class MyEnv extends EnvironmentDescription
{	
	private HashMap<String, BlockWorldObject> envConfig;
	
	// ne sert à rien pour le moment, fait car précisé dans la consigne
	private ArrayList<Box> 	alBox ;
	private ArrayList<Arch> alArch;
	private ArrayList<Wall> alWall;
	
	/**
	 * liste de robots (10 max)
	 **/
	private ArrayList<MyRobot> listeRobots;
	
	/**
	 * Liste des robots ennemis
	 * */
	private ArrayList<RobotEnnemi> ennemis;
	
	/**
	 * Liste des cerises à attraper
	 * */
	private ArrayList<CherryAgent> cerises;
		
	/**
	 * Instance de Simulator afin que les objets de l'environnement puisse intéragir avec le simulateur
	 * */
	private Simulator simulator;
	
	/**
	 * Nombre de cerises attrapées par le joueur
	 * */
	private int nbCerise;
	

	public MyEnv( String nomFichier )
	{	
		this.envConfig = new HashMap<>();
		
		this.alArch = new ArrayList<>();
		this.alBox  = new ArrayList<>();
		this.alWall = new ArrayList<>();
		
		this.ennemis = new ArrayList<>();
		this.cerises = new ArrayList<>();
		
		this.nbCerise = 0;
		
		InputStream ips = this.getClass().getResourceAsStream("/" +nomFichier);
		InputStreamReader ipsr = new InputStreamReader(ips);
		Scanner sc = new Scanner(ipsr);
		
		while(sc.hasNextLine()){
			Scanner scLigne = new Scanner(sc.nextLine());
			
			char type = scLigne.next().charAt(0);
			
			if(type == 'R'){
				int nbRobots = Integer.parseInt(scLigne.next());
				
				this.listeRobots = new ArrayList<>(nbRobots);
				
				for(int cpt=0; cpt<nbRobots; cpt++){
					this.listeRobots.add(new MyRobot(new Vector3d((int)(Math.random()*cpt), 0, (int)(Math.random()*cpt)), "MyRobot " +cpt, simulator, this));
					add(this.listeRobots.get(cpt));
				}
				
				scLigne.close();
			}
			
			else{
			
    			double x = Double.parseDouble(scLigne.next());
    			double y = Double.parseDouble(scLigne.next());
    			double z = Double.parseDouble(scLigne.next());
    			
    			// walls
    			if(type == 'W'){
    				float length;
    				float height;
    				
    				Integer rotate = null;
    				
    				length = Float.parseFloat(scLigne.next());
    				height = Float.parseFloat(scLigne.next());
    				
    				if(scLigne.hasNext()) rotate = Integer.parseInt(scLigne.next());
    				
    				Wall w = new Wall(new Vector3d(x, y, z), length, height, this);
    				if(rotate != null) w.rotate90(rotate);
    				
    				this.envConfig.put("Wall", w);
    				this.alWall.add(w);
    				add(w);
    			}
    			
    			// boxs
    			else if(type == 'B'){
    				float xf = Float.parseFloat(scLigne.next());
    				float yf = Float.parseFloat(scLigne.next());
    				float zf = Float.parseFloat(scLigne.next());
    				
    				Box b = new Box(new Vector3d(x, y, z), new Vector3f(xf, yf, zf),this);
    				
    				this.envConfig.put("Box", b);
    				this.alBox.add(b);
    				add(b);
    			}
    			
    			// archs
    			else if(type == 'A'){
    				Arch a = new Arch(new Vector3d(x, y, z), this);

    				this.alArch.add(a);
    				this.envConfig.put("Arch", a);
    				//add(a); d'après les consignes les arch font planter le programme
    			}
    			
    			// robots ennemis
    			else if(type == 'E'){
    				RobotEnnemi ennemi = new RobotEnnemi(new Vector3d(x, y, z), "Ennemi ", this);
    				this.ennemis.add(ennemi);
    				add(ennemi);
				}
    			
    			// cherry agent
    			else if(type == 'C'){
    				setUsePhysics(false);
    				CherryAgent cerise = new CherryAgent(new Vector3d(x, y, z), "cherry", 0.15f);
    				
    				cerises.add(cerise);
    				add(cerise);
    			}
    			
    			scLigne.close();
    		}
		}
		
		sc.close();
	}
	
	/**
	 * Retourne le robot d'indice i de la liste de robots
	 * @param i entier indice dans la liste
	 * @return un MyRobot
	 * */
	public MyRobot getRobot(int i){
		return listeRobots.get(i);
	}
	
	/**
	 * Permet de définir le Simulator de l'environnement courant
	 * */
	public void setSimulator(Simulator simulator){
		this.simulator = simulator;
	}
	
	/**
	 * Incrémente le nombre de cerises que le joueur a trouvé
	 * Arrête le jeu et affiche un message si il a trouver toutes les cerises
	 * */
	public void attraperCerise(){
		nbCerise++;
		
		if(nbCerise == cerises.size()) stopSimulation("Gagné !");
	}
	
	/**
	 * Permet de stopper la simulation notament depuis un robot
	 * pour éviter les problème de synchro des threads si on veut
	 * arrêter la simulation depuis performBehavior()
	 * */
	public void stopSimulation(String message){
		try{
			Thread.sleep(500);
		}
		catch(InterruptedException evt){}
		
		simulator.stopSimulation();
		JOptionPane.showMessageDialog(null, message);
	}
}
