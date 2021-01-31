package lab1;
import java.lang.Math;
import java.util.Arrays;

public class Decide {
	enum Connectors {
		  NOTUSED,
		  ORR,
		  ANDD
		}
	
	enum Comptype {
		LT, EQ, GT
	}
	class Paramenters_t{
		double length; // L e n gt h i n L ICs 0 , 7 , 12
		double radius ; // R a di u s i n L ICs 1 , 8 , 13
		double epsilon ; // D e v i a t i o n f r om P I i n L ICs 2 , 9
		double area1; // Area i n L ICs 3 , 1 0 , 14
		int qPts ; // No . o f c o n s e c u t i v e p o i n t s i n LIC 4
		int quads; // No . o f q u a d r a nt s i n LIC 4
		double dist ; // Di s t a n c e i n LIC 6
		int nPts ; // No . o f c o n s e c u t i v e p t s . i n LIC 6
		int kPts ; // No . o f i n t . p t s . i n L ICs 7 , 12
		int aPts ; // No . o f i n t . p t s . i n L ICs 8 , 13
		int bPts ; // No . o f i n t . p t s . i n L ICs 8 , 13
		int cPts ; // No . o f i n t . p t s . i n LIC 9
		int dPts ; // No . o f i n t . p t s . i n LIC 9
		int ePts ; // No . o f i n t . p t s . i n L ICs 1 0 , 14
		int fPts ; // No . o f i n t . p t s . i n L ICs 1 0 , 14
		int gPts ; // No . o f i n t . p t s . i n LIC 11
		double length2; // Maximum l e n g t h i n LIC 12
		double radius2 ; // Maximum r a d i u s i n LIC 13
		double area2; // Maximum a r e a i n LIC 14
	} 
	
	Paramenters_t parameters = new Paramenters_t();	
	double[] coordinatex;
	double[] coordinatey;
	int numpoints;

	public Connectors[][] lcm;
	
	public boolean[][] pum;
	
	public boolean[] cmv;

	public boolean[] fuv;
	
	boolean launch;

	/**
	 * If a < b return LT
	 */
	Comptype doublecompere(double a, double b) {
		if(Math.abs(a-b) < 0.000001) {
			return Comptype.EQ;
		}
		if(a < b) {
			return Comptype.LT;
		}
		return Comptype.GT;
	}
	
	boolean decide() {
		return false;
	}

	/**
	 * Returns true if there exists at least two consecutive
	 * data pts (xi yi) and (xj yj) where xj - xi < 0 => xj < xi
	 */
	// ideas for tests : list w consecutive data points which fulfill this and which does not fulfill this
	// remove parameter ?
	boolean LIC5(Paramenters_t parameters) {
		for(int i = 0; i < numpoints-1; i++) {
			// next nb is smaller than curr => true
			if(doublecompere(coordinatex[i+1], coordinatex[i]) == Comptype.LT) {
				return true;
			}
		}
		return false;
	}

	/**
	 * There exists at least two data points separated by K_PTS consecutive intervening
	 * points that are a distance greater than LENGTH1, apart. The condition
	 * is not met when NUMPOINTS < 3
	 * @return true if yes there exists such 2 pts
	 */
	boolean LIC7(Paramenters_t parameters) {
		double pt1x, pt1y, pt2x, pt2y;
		double someLength;

		if(numpoints < 3) return false;
		for(int i = 0; i < numpoints - parameters.kPts - 1; i++) {
			pt1x = coordinatex[i]; pt1y = coordinatey[i];
			pt2x = coordinatex[i+parameters.kPts+1]; pt2y = coordinatey[i+parameters.kPts+1];

			someLength = Math.sqrt((pt1x-pt2x)*(pt1x-pt2x) + (pt1y-pt2y)*(pt1y-pt2y));
			if(doublecompere(someLength, parameters.length) == Comptype.GT) return true;
		}
		return false;
	}
}
