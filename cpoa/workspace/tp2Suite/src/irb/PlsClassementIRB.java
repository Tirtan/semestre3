package irb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class PlsClassementIRB
{
	private ClassementIRB irb2007;
	private ClassementIRB irb2008;
	private ClassementIRB irb2009;
	private ClassementIRB irb2010;
	
	private ClassementIRB irb2011;
	
	private ClassementIRB irbMoy78910;
	
	// associer le nom d'un pays avec toutes ses instances dans les alPays des différents classements
	private HashMap<String, ArrayList<Pays>> mapPays;
	
	// liens entre l'écart max de rang et liste des codes pays ayant obtenu cet écart
	private HashMap<Integer, ArrayList<String>> mapRang;

	public PlsClassementIRB()
	{
		this.irb2007 = new ClassementIRB("i2007.txt");
		this.irb2008 = new ClassementIRB("i2008.txt");
		this.irb2009 = new ClassementIRB("i2009.txt");
		this.irb2010 = new ClassementIRB("i2010.txt");
		this.irb2011 = new ClassementIRB("i2011.txt");
		
		this.irbMoy78910 = new ClassementIRB(this.irb2007, "moy78910");
		
		/* tp2 fin */
		this.mapPays = new HashMap<String, ArrayList<Pays>>();
		this.mapRang = new HashMap<Integer, ArrayList<String>>();
		
		// classements de 2007 à 2010: les pays sont les mêmes donc on ne fait qu'une liste de code pays
		for(String code: this.irb2007.getEnsembleCodePays())
		{
			ArrayList<Pays> instancesPaysParAn = new ArrayList<>();
			Pays pCourant = this.irb2007.getPaysByCode(code); // pour éviter d'avoir à appeler la méthode une nouvelle fois la méthode
															  // au moment de l'ajout dans la hash map
			
			instancesPaysParAn.add(pCourant);
			instancesPaysParAn.add(this.irb2008.getPaysByCode(code));
			instancesPaysParAn.add(this.irb2009.getPaysByCode(code));
			instancesPaysParAn.add(this.irb2010.getPaysByCode(code));
			
			this.mapPays.put(pCourant.getNomPays(), instancesPaysParAn);
		}
		
		// Objectif 1 dernirèe partie
		for(String code: this.irbMoy78910.getEnsembleCodePays())
		{
			int ecartMax = this.ecartMax(code);
			
			if(!this.mapRang.containsKey(ecartMax))
				this.mapRang.put(ecartMax, new ArrayList<String>());
			
			this.mapRang.get(ecartMax).add(code);
		}
	}
	
	/**
	 * *
	 * @param code d'un Pays en String
	 * @return moyenne de points d'un pays, renvoit la même chose que le nombre de point de moyIrb78910
	 */
	public double moyPoints(String codeP)
	{
		double moy 	    = 0.0;
		double sommePts = 0.0;
		
		String nomPays = this.irb2007.getPaysByCode(codeP).getNomPays();
		ArrayList<Pays> paysCourant = this.mapPays.get(nomPays);
		System.out.println(this.mapPays.get(nomPays));
		
		for(Pays p: paysCourant)
		{
			sommePts += p.getNbPoint();
		}
		
		moy = sommePts / paysCourant.size();
		
		return moy;
	}
	
	/**
	 * @param  2 ClassementsIRB pour comparer leurs Pays
	 * @return HashSet de Pays qui existent l'année 1 mais pas l'année 2
	 * */
	public HashSet <Pays> nAppartientPas(ClassementIRB an1, ClassementIRB an2)
	{
		HashSet<String> codesAn1 = an1.getEnsembleCodePays();
		HashSet<String> codesAn2 = an2.getEnsembleCodePays();
		
		HashSet<String> hsCodesDiff = new HashSet<String>();
		HashSet<Pays> 	hsRet		= new HashSet<Pays>	 ();
		
		for(String codeAn1: codesAn1)
			if(!codesAn2.contains(codeAn1)) hsCodesDiff.add(codeAn1);
			
		for(String code: hsCodesDiff)
			hsRet.add(an1.getPaysByCode(code));
			
		return hsRet;
	}
	
	/**
	 * @param  code d'un Pays en String
	 * @return l'écart maximal de rang d'un Pays entre une année de 2007 à 2010 et la moyenne de rang de moyIrb78910
	 * */
	public Integer ecartMax(String code)
	{
		int rangIrbMoy = this.irbMoy78910.getPaysByCode(code).getRang();
		int[] tabRang = new int[4];
		
		tabRang[0] = this.irb2007.getPaysByCode(code).getRang();
		tabRang[1] = this.irb2008.getPaysByCode(code).getRang();
		tabRang[2] = this.irb2009.getPaysByCode(code).getRang();
		tabRang[3] = this.irb2010.getPaysByCode(code).getRang();
		
		int ecartMax = rechercherMax(rangIrbMoy, tabRang);
		
		return ecartMax;
	}
	
	/**
	 * @return méthode privée pour rechercher le plus grand écart, utilisée dans ecartMax(String code)
	 * */
	private int rechercherMax(int rangMoy, int[] tab)
	{
		int ecartRet = 0;
		
		for(int cpt=0; cpt<tab.length; cpt++)
		{
			int ecartTemp = tab[cpt] - rangMoy;
			
			if(ecartTemp > ecartRet) ecartRet = ecartTemp;
		}
		return ecartRet;
	}
	
	/**
	 * affiche la liste des pays
	 * */
	public void deltaPays()
	{
		  System.out.println("Pays en 2007 qui ne sont pas en 2008 " + nAppartientPas(irb2007, irb2008));		
		  System.out.println("Pays en 2008 qui ne sont pas en 2009 " + nAppartientPas(irb2008, irb2009));		
		  System.out.println("Pays en 2009 qui ne sont pas en 2010 " + nAppartientPas(irb2009, irb2010));	
		  System.out.println("Pays en 2010 qui ne sont pas en 2011 " + nAppartientPas(irb2010, irb2011));
		  
		  System.out.println(this.irbMoy78910);
	}
	
	public void afficheRangsMax()
	{
		StringBuilder result = new StringBuilder();
		
		for(Integer key: this.mapRang.keySet())
		{	
			result.append(key +": ");
			
			for(String values: this.mapRang.get(key)) result.append(values +" ");
			
			result.append("\n");
		}
		
		System.out.println(result);
	}
	

	@Override
	public String toString()
	{
		return "PlsClassementIRB [irbMoy78910= " + irbMoy78910 + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PlsClassementIRB pls = new PlsClassementIRB();
		pls.deltaPays();
		pls.afficheRangsMax();

	}

}
