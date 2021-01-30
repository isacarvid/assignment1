package lab1;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.assertFalse;


public class TestDecide {
    // here should probably put arrays which test like all the lics
    @Before
    public void setUp() {
        System.out.println("setup");
    }

	
	@Test
	public void testLIC0() {
		Decide program = new Decide();
		program.numpoints = 3;
		
		
		// Length in LICs 0 , 7 , 12
		//test that dist > than LIC length 0
		
				program.parameters.length = 0;
				double[] trueCoorX1 = {1, 2, 3};
				double[] trueCoorY1 = {1,2, 3};
				program.coordinatex = trueCoorX1;
				program.coordinatey = trueCoorY1;
				 assertTrue(program.LIC0(program.parameters));
				 
				
		//test that dist > than LICE length 7
				program.parameters.length = 7;
				double[] trueCoorX2 = {1, 1, 6};
				double[] trueCoorY2 = {4, 12, 12};
				program.coordinatex = trueCoorX2;
				program.coordinatey = trueCoorY2;
				assertTrue(program.LIC0(program.parameters));
		
		//test that dist > than LICE length 12
				program.parameters.length = 12;
				double[] trueCoorX3 = {1, 15, 7};
				double[] trueCoorY3 = {4, 18, 20};
				program.coordinatex = trueCoorX3;
				program.coordinatey = trueCoorY3;
				assertTrue(program.LIC0(program.parameters));
		//test that distances that are equal or less than length 0, 7 or 12 should fail
				program.parameters.length = 0;
				double[] falseCoorX = {1, 1, 1};
				double[] falseCoorY = {1, 1, 1};
				program.coordinatex = falseCoorX;
				program.coordinatey = falseCoorY;
				assertTrue(!program.LIC0(program.parameters));
	}
	

    @Test
    //Returns true if there exists at least two consecutive
    //data pts (xi yi) and (xj yj) where xj - xi < 0
    public void LIC5test() {
        double[] LIC5Truex = new double[] {3, 2, 1};
        double[] LIC5Truey = new double[] {0, -2, 9};
        Decide decide = new Decide();
        decide.numpoints = 3;
        decide.coordinatex = LIC5Truex;
        decide.coordinatey = LIC5Truey;
        assertTrue(decide.LIC5(decide.parameters));

        double[] LIC5Falsex = new double[] {1, 2, 3};
        double[] LIC5Falsey = new double[] {1, 2, 5};
        Decide decide2 = new Decide();
        decide2.numpoints = 3;
        decide2.coordinatex = LIC5Falsex;
        decide2.coordinatey = LIC5Falsey;
        assertFalse(decide2.LIC5(decide2.parameters));
    }

}




