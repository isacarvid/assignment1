package lab1;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.assertFalse;


public class TestDecide {
	@Test
	public void LIC2test() {
		double[] x = {1, 3, 5};
		double[] y = {2, 2, 2};
		Decide decide = new Decide();
		decide.parameters.epsilon =0.1;
		decide.numpoints = 3;
		decide.coordinatex = x;
		decide.coordinatey = y;
		boolean tmp = decide.lic2();
		assertFalse(decide.lic2()); // använda tmp här?

		double[] x2 = {1, 5, 7};
		double[] y2 = {2, 10, 2};
		Decide decide2 = new Decide();
		decide2.parameters.epsilon =0.1;
		decide2.numpoints = 3;
		decide2.coordinatex = x2;
		decide2.coordinatey = y2;
		assertTrue(decide2.lic2()); // använda tmp här?
	}
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
		double[] trueCoorY1 = {1, 2, 3};
		program.coordinatex = trueCoorX1;
		program.coordinatey = trueCoorY1;
		assertTrue(program.LIC0(program.parameters));
				
		//test that dist > than LIC length 7
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

	@Test
	// Return true: exists 3 cons pts sep by exactly
	// C_PTS and D_PTS cons intervening pts, forming an angle s.t.
	// angle < pi - epsilon or angle > pi+epsilon (2nd pt is always vertex)
	// if numpoints < 5 : return false
	// if first or 3rd point == vertex : cannot be true for those pts
	public void LIC9test() {
		double[] LIC9Truex = new double[] {0, 2, 5, 6, 8, 1, 3};
		double[] LIC9Truey = new double[] {0, 1, 6, 8, 2, 4, 5};
		Decide decide = new Decide();
		decide.parameters.epsilon = 0.1;
		decide.parameters.cPts = 1; // 0,0 - 5,6 - 3,5
		decide.parameters.dPts = 3;
		decide.numpoints = 7;
		decide.coordinatex = LIC9Truex;
		decide.coordinatey = LIC9Truey;
		assertTrue(decide.LIC9(decide.parameters));

		double[] LIC9Falsex = new double[] {1, 2, 3};
		double[] LIC9Falsey = new double[] {1, 2, 5};
		Decide decide2 = new Decide();
		decide2.parameters.epsilon = 0.1;
		decide2.parameters.cPts = 1;
		decide2.parameters.dPts = 3;
		decide2.numpoints = 3;
		decide2.coordinatex = LIC9Falsex;
		decide2.coordinatey = LIC9Falsey;
		assertFalse(decide2.LIC9(decide2.parameters));
	}
	
	
	@Test
	
	public void testLIC12() {
		Decide program = new Decide();
		program.numpoints = 5;
		program.parameters.kPts = 2;
		double[] trueCoorX = {1,3,5,8, 9};
		double[] trueCoorY = {6,8,5,8, 6};
		program.coordinatex = trueCoorX;
		program.coordinatey = trueCoorY;
		program.parameters.length = 6;
		program.parameters.length2 = 10;
		assertTrue(program.LIC12());
		program.parameters.kPts = 3;
		//should fail on the below
		program.parameters.length = 10;
		assertFalse(program.LIC12());
		
		
	}

	
	
	
	
	
	
	
}




