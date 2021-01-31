package lab1;

import java.lang.Math;
import java.util.Arrays;

import static java.awt.geom.Point2D.distance;

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

	/**
	 * Return true if: exist 3 consecutive data pts separated
	 * by exactly C_PTS and D_PTS consecutive intervening pts, forming an angle s.t.
	 * angle < pi - epsilon or angle > pi+epsilon (2nd pt is always vertex)
	 * if numpoints < 5 : return false
	 * if first or 3rd point == vertex : cannot be true for those pts
	 */
	boolean LIC9(Paramenters_t parameters) {
		int i = 0;
		int j = i + parameters.cPts+1;
		int k = j + parameters.dPts+1;
		while(k < numpoints) {
			double x1 = coordinatex[i];
			double y1 = coordinatey[i];
			double x2 = coordinatex[j];
			double y2 = coordinatey[j];
			double x3 = coordinatex[k];
			double y3 = coordinatey[k];
			// checks that points 1 and 3 are not the same as 2
			if ((x1 != x2 || y1 != y2) && (x2 != x3 || y2 != y3)) {
				double angle = getAngle(x1, y1, x2, y2, x3, y3);
				if (angle < Math.PI - parameters.epsilon || angle > Math.PI + parameters.epsilon) {
					return true;
				}
			}
			i++;
			j++;
			k++;
		}
		return false;
	}

	/**
	 * There exists at least two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
	 * G_PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) 
	 * The condition is not met when NUMPOINTS < 3
	 * @return true if an earlier point i has a lower x coordinate than a latter point j
	 */
	boolean LIC11(Paramenters_t parameters) {
		double pt1x, pt2x;
		if(numpoints < 3) return false;
		for(int i = 0; i < numpoints - parameters.gPts - 1; i++) {
			pt1x = coordinatex[i];
			pt2x = coordinatex[i+parameters.gPts+1];

			if(doublecompere(pt1x, pt2x) == Comptype.GT) return true;
		}
		return false;
	}

	/**
	 * helper function to get the angle between three points
	 * https://math.stackexchange.com/questions/361412/finding-the-angle-between-three-points
	 */
	double getAngle(double x1, double y1, double x2, double y2, double x3,double y3) {
		double dist1 = distance(x1,y1,x2,y2);
		double dist2 = distance(x2,y2,x3,y3);
		double dist3 = distance(x1,y1,x3,y3);
		double angle = Math.acos((Math.pow(dist1,2)+Math.pow(dist3,2)-Math.pow(dist2,2))/(2*dist1*dist3));
		System.out.println(angle);
		return angle;
	}
}
