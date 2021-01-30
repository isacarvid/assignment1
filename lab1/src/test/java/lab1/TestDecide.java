package lab1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

class TestDecide {

    // here should probably put arrays which test like all the lics
    @Before
    public void setUp() {

    }

    @Test
    //Returns true if there exists at least two consecutive
    //data pts (xi yi) and (xj yj) where xj - xi < 0
    public void LIC5test() {
        double[] LIC5Truex = new double[] {1, 5, 3.5};
        double[] LIC5Truey = new double[] {0, -2, 9};
        double[] LIC5Falsex = new double[] {7, 5, 3};
        double[] LIC5Falsey = new double[] {0, 1, -5};

        Decide decide = new Decide();
        decide.coordinatex = LIC5Truex;
        decide.coordinatey = LIC5Truey;
        assertTrue(decide.LIC5(decide.parameters));

        decide = new Decide();
        decide.coordinatex = LIC5Falsex;
        decide.coordinatey = LIC5Falsey;
        assertFalse(decide.LIC5(decide.parameters));
    }
}