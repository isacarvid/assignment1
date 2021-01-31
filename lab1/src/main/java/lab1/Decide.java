package lab1;

import java.lang.Math;
import java.util.Arrays;

public class Decide {
	enum Connectors {
		NOTUSED, ORR, ANDD
	}

	enum Comptype {
		LT, EQ, GT
	}

	class Paramenters_t {
		double length; // L e n gt h i n L ICs 0 , 7 , 12
		double radius; // R a di u s i n L ICs 1 , 8 , 13
		double epsilon; // D e v i a t i o n f r om P I i n L ICs 2 , 9
		double area1; // Area i n L ICs 3 , 1 0 , 14
		int qPts; // No . o f c o n s e c u t i v e p o i n t s i n LIC 4
		int quads; // No . o f q u a d r a nt s i n LIC 4
		double dist; // Di s t a n c e i n LIC 6
		int nPts; // No . o f c o n s e c u t i v e p t s . i n LIC 6
		int kPts; // No . o f i n t . p t s . i n L ICs 7 , 12
		int aPts; // No . o f i n t . p t s . i n L ICs 8 , 13
		int bPts; // No . o f i n t . p t s . i n L ICs 8 , 13
		int cPts; // No . o f i n t . p t s . i n LIC 9
		int dPts; // No . o f i n t . p t s . i n LIC 9
		int ePts; // No . o f i n t . p t s . i n L ICs 1 0 , 14
		int fPts; // No . o f i n t . p t s . i n L ICs 1 0 , 14
		int gPts; // No . o f i n t . p t s . i n LIC 11
		double length2; // Maximum l e n g t h i n LIC 12
		double radius2; // Maximum r a d i u s i n LIC 13
		double area2; // Maximum a r e a i n LIC 14
	}

	Paramenters_t parameters = new Paramenters_t();
	double[] coordinatex;
	double[] coordinatey;
	int numpoints;

	public Connectors[][] lcm = new Connectors[15][15];

	public boolean[][] pum = new boolean[15][15];

	public boolean[] cmv = new boolean[15];

	public boolean[] fuv = new boolean[15];

	boolean launch;

	/**
	 * If a < b return LT
	 */
	Comptype doublecompere(double a, double b) {
		if (Math.abs(a - b) < 0.000001) {
			return Comptype.EQ;
		}
		if (a < b) {
			return Comptype.LT;
		}
		return Comptype.GT;
	}

	// if the angle of three consecutive points are within PI +- some margin epsilon
	// return true
	boolean lic2() {
		for (int i = 1; i < numpoints - 1; i++) {
			if (!((coordinatex[i - 1] == coordinatex[i] && coordinatey[i - 1] == coordinatey[i])
					|| (coordinatex[i + 1] == coordinatex[i] && coordinatey[i + 1] == coordinatey[i]))) {
				
				double xDiff1 = coordinatex[i - 1] - coordinatex[i];
				double yDiff1 = coordinatey[i - 1] - coordinatey[i];
				double xDiff2 = coordinatex[i + 1] - coordinatex[i];
				double yDiff2 = coordinatey[i + 1] - coordinatey[i];
				
				double dotProduct = xDiff1 * xDiff2 + yDiff1 * yDiff2; 
				
				double normA = Math.sqrt(Math.pow(xDiff1,2) + Math.pow(yDiff1,2));
				double normB = Math.sqrt(Math.pow(xDiff2,2) + Math.pow(yDiff2,2));
				double angle = Math.acos(dotProduct/(normA * normB));
				if (angle < (Math.PI - parameters.epsilon) || angle > (Math.PI + parameters.epsilon)) {
					cmv[2] = true; 
					return cmv[2];
				}
			}
		}
		cmv[2] = false;
		return false;
	}
	
	/**
	 * Check if 3 points gapped by aPts and bPts can fit or not in radius1 and radius2 
	 * @return true if the 3 points do not fit in radius1 and 3 pts fit in radius2
	 */
	boolean LIC13(Paramenters_t parameters) {
		double pt1x, pt1y, pt2x, pt2y, pt3x, pt3y;
		double someRadius;
		double length12, length13, length23;
		boolean r1, r2 = false;
		
		if(numpoints < 5) return false;
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


	//check if there exists to consecutive data points with a distance greater than length parameter
	boolean LIC0(Paramenters_t param) {
		
		cmv[0] = false;
		
		for(int i = 0; i < numpoints-1; i++) {
			double dist = Math.sqrt(Math.pow((coordinatex[i+1]-coordinatex[i]),2) + Math.pow((coordinatey[i+1]-coordinatey[i]),2));
			if(dist > param.length) {
				
				cmv[0] = true;
			}
		}
		if(cmv[0] == true) {
			return true;
		}else {
			return false;
		}
	}
	
	


	/**
	 * Returns true if there exists at least two consecutive data pts (xi yi) and
	 * (xj yj) where xj - xi < 0 => xj < xi
	 */
	// ideas for tests : list w consecutive data points which fulfill this and which
	// does not fulfill this
	// remove parameter ?
	boolean LIC5(Paramenters_t parameters) {
		for (int i = 0; i < numpoints - 1; i++) {
			// next nb is smaller than curr => true
			if (doublecompere(coordinatex[i + 1], coordinatex[i]) == Comptype.LT) {
				return true;
			}
		}
		return false;
	}


	boolean decide() {
		return false;
	}

}
