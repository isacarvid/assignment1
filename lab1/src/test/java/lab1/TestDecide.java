package lab1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDecide {
    // here should probably put arrays which test like all the lics
    @Before
    public void setUp() {
        System.out.println("setup");
    }

    @Test
	public void testLIC3() {
        Decide program = new Decide();
        
        program.numpoints = 3;
        double[] t1cx = {1, 2, 3};
        double[] t1cy = {1, 2, 3};
        double[] t2cx = {0, 2, 2};
        double[] t2cy = {0, 0, 2};
        program.parameters.area1 = 5;
		
		//test for a pts triangle with area 0
        program.coordinatex = t1cx;
        program.coordinatey = t1cy;
        assertTrue(!program.LIC0(program.parameters));

        //test for a pts triangle with area 8
        program.coordinatex = t2cx;
        program.coordinatey = t2cy;
        assertTrue(program.LIC0(program.parameters));
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