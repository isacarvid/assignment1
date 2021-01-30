package lab1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		assertFalse(decide.lic2());
		
		double[] x2 = {1, 5, 7};
		double[] y2 = {2, 10, 2}; 
		Decide decide2 = new Decide();
		decide2.parameters.epsilon =0.1;
		decide2.numpoints = 3;
		decide2.coordinatex = x2;
		decide2.coordinatey = y2;
		assertTrue(decide2.lic2());
	}
    // here should probably put arrays which test like all the lics
    @Before
    public void setUp() {
        System.out.println("setup");
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