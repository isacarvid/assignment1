package lab1;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

public class TestDecide {

	
	@Test
	public void testLIC0() {
		Decide program = new Decide();
		program.numpoints = 3;
		
		// Length in LICs 0 , 7 , 12
		//test that dist > than LIC length 0
				double[] trueCoorX1 = {12, 13, 14};
				double[] trueCoorY1 = {12,13, 14};
				program.coordinatex = trueCoorX1;
				program.coordinatey = trueCoorY1;
				 assertTrue(program.LIC0());
				
		//test that dist > than LICE length 7
				double[] trueCoorX2;
				double[] trueCoorY2;
				assertTrue(false);
		
		//test that dist > than LICE length 12
				double[] trueCoorX3;
				double[] trueCoorY3;
				assertTrue(false);
		//test distances that are equal or less than length 0, 7 or 12, should fail
				double[] falseCoorX;
				double[] falseCoorY;
				assertTrue(false);
		
		
	}
	
}
