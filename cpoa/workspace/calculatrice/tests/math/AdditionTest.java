package math;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdditionTest
{
	private Addition add;
	private Multiplication mult;
	private Soustraction sous;
	
	//initialisation des ressources utiles aux tests
	@Before
	public void setUp() throws Exception
	{
		this.add  = new Addition	  ();
		this.mult = new Multiplication();
		this.sous = new Soustraction  ();
	}
	
	//libération des resources utilisées
	@After
	public void tearDown() throws Exception
	{
		this.add  = null;
		this.mult = null;
		this.sous = null;
	}

	@Test
	public void testCalculer() throws Exception
	{
	   assertEquals(new Long(4), add.calculer(new Long(1), new Long(3)));
	   assertEquals(new Integer(8), this.mult.calculer(new Integer(4), new Integer(2)));
	   assertEquals(123, this.sous.calculer(126, 3));
	}
	 
	@Test
	public void testLireSymbole() throws Exception
	{
	    assertEquals((Character)'-', add.lireSymbole());
	}

}
