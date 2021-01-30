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
	 * Check if 3 points gapped by aPts and bPts can fit or not in radius1 and radius2 
	 * @return true if the 3 points do not fit in radius1 and 3 pts fit in radius2
	 */
	boolean lic13(int numpoints, double[] x, double[] y, int aPts, int bPts, int radius1, int radius2) {
		if(numpoints < 5) return false;
		double someRadius;
		double length12, length13, length23;
		boolean r1, r2 = false;
		for(int i = 0; i < numpoints - (aPt + bPts) - 2; i++) {
			pt1x = x[i]; pt1y = y[i];
			pt2x = x[i+aPts+1]; pt2y = y[i+aPts+1];
			pt3x = x[i+bPts+1]; pt3y = y[i+bPts+1];

			length12 = Math.sqrt((pt1x-pt2x)*(pt1x-pt2x) + (pt1y-pt2y)*(pt1y-pt2y));
			length13 = Math.sqrt((pt1x-pt3x)*(pt1x-pt3x) + (pt1y-pt3y)*(pt1y-pt3y));
			length23 = Math.sqrt((pt2x-pt3x)*(pt2x-pt3x) + (pt2y-pt3y)*(pt2y-pt3y));
			
			// check if the pts do NOT fit in circle of radius 1
			if(doublecompere(length12, 2*radius1) == Comptype.GT) {
				r1 = true;
				if(r2) return true;
			}
			else if(doublecompere(length13, 2*radius1) == Comptype.GT) {
				r1 = true;
				if(r2) return true;
			}
			else if(doublecompere(length23, 2*radius1) == Comptype.GT) {
				r1 = true;
				if(r2) return true;
			}

			// check if the pts do fit in circle of radius 2
			if(doublecompere(length12, 2*radius2) != Comptype.GT) {
				if(doublecompere(length13, 2*radius2) != Comptype.GT) {
					if(doublecompere(length23, 2*radius2) != Comptype.GT) {
						r2 = true;
						if(r1) return true;
					}
				}
			}
		}
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
}
