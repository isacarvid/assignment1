package lab1;
import java.lang.Math;

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
	 * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
	 * exactly G_PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
	 * condition is not met when NUMPOINTS < 3
	 * @param numpoints
	 * @param x
	 * @param y
	 * @param gPts
	 * @return
	 */
	boolean lic11(int numpoints, double[] x, double[] y, int gPts) {
		if(numpoints < 3) return false;
		for(int i = 0; i < numpoints - gPts; i++) {
			pt1x = x[i]; pt1y = y[i];
			pt2x = x[i+gPts+1]; pt2y = y[i+gPts+1];

			if(doublecompere(pt1x, pt2x) == Comptype.GT) return true;
		}
		return false;
	}
	
}
