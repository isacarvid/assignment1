package lab1;

import org.junit.Test;

import lab1.Decide.Connectors;

import static org.junit.Assert.assertArrayEquals; // remove ?
import static org.junit.Assert.assertEquals; // remove ?
import static org.junit.Assert.assertTrue;
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
		// Length in LICs 0 , 7 , 12 : test that dist > than LIC length 0
		program.parameters.length = 0;
		double[] trueCoorX1 = {1, 2, 3};
		double[] trueCoorY1 = {1, 2, 3};
		program.coordinatex = trueCoorX1;
		program.coordinatey = trueCoorY1;
		assertTrue(program.LIC0());

		//test that dist > than LIC length 7
		program.parameters.length = 7;
		double[] trueCoorX2 = {1, 1, 6};
		double[] trueCoorY2 = {4, 12, 12};
		program.coordinatex = trueCoorX2;
		program.coordinatey = trueCoorY2;
		assertTrue(program.LIC0());

		//test that dist > than LICE length 12
		program.parameters.length = 12;
		double[] trueCoorX3 = {1, 15, 7};
		double[] trueCoorY3 = {4, 18, 20};
		program.coordinatex = trueCoorX3;
		program.coordinatey = trueCoorY3;
		assertTrue(program.LIC0());

		//test that distances that are equal or less than length 0, 7 or 12 should fail
		program.parameters.length = 0;
		double[] falseCoorX = {1, 1, 1};
		double[] falseCoorY = {1, 1, 1};
		program.coordinatex = falseCoorX;
		program.coordinatey = falseCoorY;
		assertFalse(program.LIC0());
	}

	/**
	 * There exists at least one set of three consecutive data points
	 * than CANNOT all be contained in a circle of radius radius1
	 * */
	@Test
	public void testLIC1() {
		double[] x = {0, 4, 9};
		double[] y = {0, 4, 9};
		Decide decide = new Decide();
		decide.parameters.radius = 3;
		decide.numpoints = 3;
		decide.coordinatex = x;
		decide.coordinatey = y;
		assertTrue(decide.LIC1());

		double[] x2 = {0, 1, 2};
		double[] y2 = {0, 1, 2};
		Decide decide2 = new Decide();
		decide2.parameters.radius = 10;
		decide2.numpoints = 3;
		decide2.coordinatex = x2;
		decide2.coordinatey = y2;
		assertFalse(decide2.LIC1());
	}

	@Test
	public void testLIC2() {
		double[] x = {1, 3, 5};
		double[] y = {2, 2, 2};
		Decide decide = new Decide();
		decide.parameters.epsilon = 0.1;
		decide.numpoints = 3;
		decide.coordinatex = x;
		decide.coordinatey = y;
		assertFalse(decide.LIC2());

		double[] x2 = {1, 5, 7};
		double[] y2 = {2, 10, 2};
		Decide decide2 = new Decide();
		decide2.parameters.epsilon = 0.1;
		decide2.numpoints = 3;
		decide2.coordinatex = x2;
		decide2.coordinatey = y2;
		assertTrue(decide2.LIC2());
	}

	@Test
	/**
	 * Return true if the 3pts triangle area is greater than area1
	 * Return false otherwise
	 * Return false if numpoints < 3
	 */
	public void testLIC3() {
		Decide program = new Decide();

		program.numpoints = 3;
		double[] t1cx = {1, 2, 3};
		double[] t1cy = {1, 2, 3};
		double[] t2cx = {0, 4, 4};
		double[] t2cy = {0, 0, 4};
		program.parameters.area1 = 5;

		//test for a pts triangle with area 0
		program.coordinatex = t1cx;
		program.coordinatey = t1cy;
		assertFalse(program.LIC3(program.parameters));

		//test for a pts triangle with area 8
		program.coordinatex = t2cx;
		program.coordinatey = t2cy;
		assertTrue(program.LIC3(program.parameters));
	}

	/**
	 * Asserts true if LIC4 correctly checks that there exists a set of qPts that is greater than quads
	 */
	@Test
	public void testLIC4() {
		Decide program = new Decide();

		program.numpoints = 3;
		program.parameters.qPts = 3;
		program.parameters.quads = 2;
		double[] trueCoorX = {1, -2, -1};
		double[] trueCoorY = {3, 3, -1};
		program.coordinatex = trueCoorX;
		program.coordinatey = trueCoorY;
		assertTrue(program.LIC4());

		program.parameters.quads = 1;
		double[] falseCoorX = {1,2,3};
		double[] falseCoorY = {1,3,4};
		program.coordinatex = falseCoorX;
		program.coordinatey = falseCoorY;
		assertFalse(program.LIC4());
	}

	@Test
	//Returns true if there exists at least two consecutive
	//data pts (xi yi) and (xj yj) where xj - xi < 0
	public void testLIC5() {
		double[] LIC5Truex = new double[] {3, 2, 1};
		double[] LIC5Truey = new double[] {0, -2, 9};
		Decide decide = new Decide();
		decide.numpoints = 3;
		decide.coordinatex = LIC5Truex;
		decide.coordinatey = LIC5Truey;
		assertTrue(decide.LIC5());

		double[] LIC5Falsex = new double[] {1, 2, 3};
		double[] LIC5Falsey = new double[] {1, 2, 5};
		Decide decide2 = new Decide();
		decide2.numpoints = 3;
		decide2.coordinatex = LIC5Falsex;
		decide2.coordinatey = LIC5Falsey;
		assertFalse(decide2.LIC5());
	}

	@Test
	public void testLIC6() {
		double[] x = {1, 3, 5};
		double[] y = {2, 4, 2}; 
		Decide decide = new Decide();
		decide.numpoints = 3;
		decide.parameters.nPts = 3;
		decide.parameters.dist = 1;
		decide.coordinatex = x;
		decide.coordinatey = y;
		assertTrue(decide.LIC6());

		Decide decide2 = new Decide();
		decide2.numpoints = 3;
		decide2.parameters.nPts = 3;
		decide2.parameters.dist = 3;
		decide2.coordinatex = x;
		decide2.coordinatey = y;
		assertFalse(decide2.LIC6());
		
	}

	@Test
    /**
     * Returns true if two cons pts seperated by kPts
     * are at a distance greater than provided length
     * Returns false if equal or less than
     * Returns false if numpoints < 3
     */
	public void testLIC7() {
        Decide program = new Decide();

        program.numpoints = 3;
        double[] t1cx = {0, 0, 0};
        double[] t1cy = {0, 0, 0};
        double[] t2cx = {0, 2, 2};
        double[] t2cy = {0, 2, 0};
        double[] t3cx = {0, 4, 4};
        double[] t3cy = {0, 4, 0};
        program.parameters.kPts = 1;
        program.parameters.length = 2;

		// Less than: test for pts with length 0
        program.coordinatex = t1cx;
        program.coordinatey = t1cy;
		assertFalse(program.LIC7());

        // Equal to: test for pts with length 2
        program.coordinatex = t2cx;
        program.coordinatey = t2cy;
		assertFalse(program.LIC7());

        // Greater than: test for pts with length 4
        program.coordinatex = t3cx;
        program.coordinatey = t3cy;
        assertTrue(program.LIC7());
    }

	/**
	 * There exists at least one set of three consecutive data points
	 * with aPts and bPts distance between them
	 * that cannot all be contained in a circle of radius1
	 */
	@Test
	public void testLIC8() {
		double[] x = {0, 1, 4, 1, 9};
		double[] y = {0, 1, 4, 1, 9};
		Decide program = new Decide();
		program.numpoints = 5;
		program.parameters.radius = 3;
		program.coordinatex = x;
		program.coordinatey = y;
		assertTrue(program.LIC8());
		program.parameters.radius = 9;
		assertFalse(program.LIC8());
	}
	
	@Test
	/** Return true: exists 3 cons pts sep by exactly
	 * C_PTS and D_PTS cons intervening pts, forming an angle s.t.
	 * angle < pi - epsilon or angle > pi+epsilon (2nd pt is always vertex)
	 * if numpoints < 5 : return false
	 * if first or 3rd point == vertex : cannot be true for those pts
	 */
	public void testLIC9() {
		double[] LIC9Truex = new double[] {0, 2, 5, 6, 8, 1, 3};
		double[] LIC9Truey = new double[] {0, 1, 6, 8, 2, 4, 5};
		Decide decide = new Decide();
		decide.parameters.epsilon = 0.1;
		decide.parameters.cPts = 1; // 0,0 - 5,6 - 3,5
		decide.parameters.dPts = 3;
		decide.numpoints = 7;
		decide.coordinatex = LIC9Truex;
		decide.coordinatey = LIC9Truey;
		assertTrue(decide.LIC9());

		double[] LIC9Falsex = new double[] {1, 2, 3};
		double[] LIC9Falsey = new double[] {1, 2, 5};
		Decide decide2 = new Decide();
		decide2.parameters.epsilon = 0.1;
		decide2.parameters.cPts = 1;
		decide2.parameters.dPts = 3;
		decide2.numpoints = 3;
		decide2.coordinatex = LIC9Falsex;
		decide2.coordinatey = LIC9Falsey;
		assertFalse(decide2.LIC9());
	}

	/**
	 * calc area and checks if it is bigger than area1. The real area in both tests are 2.0.
	 * */
	@Test
	public void testLIC10() {
		double[] x = {1,2,3,4,4,3};
		double[] y = {1,1,3,3,3,1};
		Decide decide = new Decide();
		decide.numpoints = 6;
		decide.parameters.ePts = 1;
		decide.parameters.fPts = 2;
		decide.parameters.area1 = 1;
		decide.coordinatex = x;
		decide.coordinatey = y;
		assertTrue(decide.LIC10());

		double[] x2 =  {1,2,3,4,4,3};
		double[] y2 = {1,1,3,3,3,1};
		Decide decide2 = new Decide();
		decide2.numpoints = 6;
		decide2.parameters.ePts = 1;
		decide2.parameters.fPts = 2;
		decide2.parameters.area1 = 2;
		decide2.coordinatex = x2;
		decide2.coordinatey = y2;
		assertFalse(decide2.LIC10());
	}

    @Test
    /**
     * Returns true if two pts seperated by gPts
     * where i is a pt in the list earlier than j
     * such that x[i] > x[j]
     * Returns false if equal or less than
     * Returns false if numpoints < 3
     */
	public void testLIC11() {
        Decide program = new Decide();
        program.numpoints = 3;
        double[] t1cx = {0, 0, 1};
        double[] t1cy = {0, 0, 0};
        double[] t2cx = {1, 0, 1};
        double[] t2cy = {0, 0, 0};
        double[] t3cx = {1, 0, 0};
        double[] t3cy = {0, 0, 0};
        program.parameters.gPts = 1;

		// Less than: test for pts where i < j
        program.coordinatex = t1cx;
        program.coordinatey = t1cy;
		assertFalse(program.LIC11(program.parameters));

        // Equal to: test for pts where i = j
        program.coordinatex = t2cx;
        program.coordinatey = t2cy;
		assertFalse(program.LIC11(program.parameters));

        // Greater than: test for pts where i > j
        program.coordinatex = t3cx;
        program.coordinatey = t3cy;
        assertTrue(program.LIC11(program.parameters));
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

	@Test
	/**
     * Return true if some 3pts gapped by ePts and fPts
	 * dont fit in radius1 circle and some 3pts fit radius2
     * Return false otherwise
     * Return false if numpoints < 5
     */
	public void testLIC13() {
        Decide program = new Decide();

        program.numpoints = 5;
        double[] t1cx = {0, 0, 1, 0, 0};
        double[] t1cy = {0, 0, 0, 0, 1};
        double[] t2cx = {0, 0, 10, 0, 0};
		double[] t2cy = {0, 0, 0, 0, 10};
		double[] t3cx = {0, 0, 5, 0, 0};
		double[] t3cy = {0, 0, 0, 0, 5};
		program.parameters.aPts = 1;
		program.parameters.bPts = 1;
		program.parameters.radius = 2;
		program.parameters.radius2 = 4;

		// fits radius1 && fits radius2
        program.coordinatex = t1cx;
        program.coordinatey = t1cy;
		assertFalse(program.LIC13());
		
		// no fit radius1 && no fit radius2
        program.coordinatex = t2cx;
        program.coordinatey = t2cy;
		assertFalse(program.LIC13());

        // no fit radius1 && fits radius2
        program.coordinatex = t3cx;
        program.coordinatey = t3cy;
        assertTrue(program.LIC13());
    }

	@Test
	/**
     * Return true if some 3pts triangle area gapped by ePts and fPts
	 * is greater than area1 and some 3pts less than area2
     * Return false otherwise
     * Return false if numpoints < 5
     */
	public void testLIC14() {
        Decide program = new Decide();

        program.numpoints = 5;
        double[] t1cx = {0, 0, 5, 0, 5};
        double[] t1cy = {0, 0, 0, 0, 5};
        double[] t2cx = {0, 0, 0, 0, 0};
		double[] t2cy = {0, 0, 0, 0, 0};
		double[] t3cx = {0, 0, 4, 0, 4};
		double[] t3cy = {0, 0, 0, 0, 4};
		program.parameters.ePts = 1;
		program.parameters.fPts = 1;
		program.parameters.area1 = 5;
		program.parameters.area2 = 10;

		// 12.5: greater than area 1 && greater than area 2
        program.coordinatex = t1cx;
        program.coordinatey = t1cy;
		assertFalse(program.LIC14(program.parameters));
		
		// 0: less than area 1 && less than area 2
        program.coordinatex = t2cx;
        program.coordinatey = t2cy;
		assertFalse(program.LIC14(program.parameters));

        // 8: greater than area 1 && less than area 2
        program.coordinatex = t3cx;
        program.coordinatey = t3cy;
        assertTrue(program.LIC14(program.parameters));
    }
	
	/**
	 * Fills values ov cmv with true/false
	 * Fills values of the matrix lcm with ANDD
	 * one assert should be True and one should be False
	 * */
	@Test
	public void testPUM() {
		
		//checks if all values are true
		Decide program = new Decide();
		program.cmv[0] = true;
		program.cmv[1] = true;
		program.cmv[2] = true;
		program.cmv[3] = true;
		program.cmv[4] = true;
		program.cmv[5] = true;
		program.cmv[6] = true;
		program.cmv[7] = true;
		program.cmv[8] = true;
		program.cmv[9] = true;
		program.cmv[10] = true;
		program.cmv[11] = true;
		program.cmv[12] = true;
		program.cmv[13] = true;
		program.cmv[14] = true;
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				program.lcm[i][j] = Connectors.ANDD;
			}
		}
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				program.pum = program.createPUM();
			}
		}
		boolean result = true;
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if(program.pum[i][j] == false) {
					result = false;
					break;
				}
			}
		}
		
		//check if all values are false
		program.cmv[0] = false;
		program.cmv[1] = false;
		program.cmv[2] = false;
		program.cmv[3] = false;
		program.cmv[4] = false;
		program.cmv[5] = false;
		program.cmv[6] = false;
		program.cmv[7] = false;
		program.cmv[8] = false;
		program.cmv[9] = false;
		program.cmv[10] = false;
		program.cmv[11] = false;
		program.cmv[12] = false;
		program.cmv[13] = false;
		program.cmv[14] = false;
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				program.pum = program.createPUM();
			}
		}
		result = false;
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if(program.pum[i][j] == true) {
					result = true;
					break;
				}
			}
		}
		assertFalse(result);
		
		
	}
	
	@Test
	public void testFUV() {
		Decide program = new Decide();

		// Test FUV with PUM all false val
		// create CMV
		for(int i = 0; i < 15; i++) {
			program.cmv[i] = false;
		}
		// create LCM
		for(i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				program.lcm[i][j] = Connectors.ANDD;
			}
		}
		// create PUM
		program.pum = program.createPUM();
		// fill out PUV
		for(i = 0; i < 15; i++) {
			program.puv[i] = true; // true implies that the LIC impacts launch
		}

		// create FUV
		program.fuv = program.createFUV();

		// check assertion that all FUV are false
		boolean result = false;
		for(i = 0; i < 15; i++) {
			if(program.fuv[i] == true) {
				result = true;
				break;
			}
		}
		assertFalse(result);

		// Test FUV with PUM all true val
		// create CMV
		for(i = 0; i < 15; i++) {
			program.cmv[i] = true;
		}
		// create LCM
		for(i = 0; i < 15; i++) {
			for(j = 0; j < 15; j++) {
				program.lcm[i][j] = Connectors.ANDD;
			}
		}
		// create PUM
		program.pum = program.createPUM();
		// fill out PUV
		for(i = 0; i < 15; i++) {
			program.puv[i] = true; // true implies that the LIC impacts launch
		}

		// create FUV
		program.fuv = program.createFUV();

		// check assertion that all FUV are true
		result = true;
		for(i = 0; i < 15; i++) {
			if(program.fuv[i] == false) {
				result = false;
				break;
			}
		}
		assertTrue(result);

		// Test FUV with PUM all false val & all PUV are false
		// create CMV
		for(i = 0; i < 15; i++) {
			program.cmv[i] = false;
		}
		// create LCM
		for(i = 0; i < 15; i++) {
			for(j = 0; j < 15; j++) {
				program.lcm[i][j] = Connectors.ANDD;
			}
		}
		// create PUM
		program.pum = program.createPUM();
		// fill out PUV
		for(i = 0; i < 15; i++) {
			program.puv[i] = false; // false implies that the LIC doesnt impact launch
		}

		// create FUV
		program.fuv = program.createFUV();

		// check assertion that all FUV are true
		result = true;
		for(i = 0; i < 15; i++) {
			if(program.fuv[i] == false) {
				result = false;
				break;
			}
		}
		assertTrue(result);

	}
}