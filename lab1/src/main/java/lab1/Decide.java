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

	/**
	 * There exists at least one set of three consecutive data points
	 * than CANNOT all be contained in a circle of radius radius1
	 * */
	boolean LIC1(Paramenters_t parameters) {
		double radius1 = parameters.radius;
		for (int i = 0; i < numpoints - 2; i++) {
			double x1 = coordinatex[i];
			double y1 = coordinatey[i];
			double x2 = coordinatex[i + 1];
			double y2 = coordinatey[i + 1];
			double x3 = coordinatex[i + 2];
			double y3 = coordinatey[i + 2];

			double dist1 = distance(x1, y1, x2, y2);
			double dist2 = distance(x2, y2, x3, y3);
			double dist3 = distance(x1, y1, x3, y3);

			// distance bigger than diameter => cannot fit
			if (doublecompere(dist1, 2 * radius1) == Comptype.GT) {
				return true;
			} else if (doublecompere(dist2, 2 * radius1) == Comptype.GT) {
				return true;
			} else if (doublecompere(dist3, 2 * radius1) == Comptype.GT) {
				return true;
			}

			// points with max dist between them
			double maxDist = 0;
			double maxDist1x = 0;
			double maxDist1y = 0;
			double maxDist2x = 0;
			double maxDist2y = 0;
			double extraPointx = 0;
			double extraPointy = 0;
			if(dist1 > dist2 && dist1 > dist3) {
				maxDist1x = x1;
				maxDist1y = y1;
				maxDist2x = x2;
				maxDist2y = y2;
				extraPointx = x3;
				extraPointy = y3;
			}
			else if(dist2 > dist1 && dist2 > dist3) {
				maxDist1x = x2;
				maxDist1y = y2;
				maxDist2x = x3;
				maxDist2y = y3;
				extraPointx = x1;
				extraPointy = y1;
			}
			if(dist3 > dist1 && dist3 > dist1) {
				maxDist1x = x1;
				maxDist1y = y1;
				maxDist2x = x3;
				maxDist2y = y3;
				extraPointx = x2;
				extraPointy = y2;
			}
			//distance between extra point and the others less than r :
			// return false because it can be contained
			if(distance(extraPointx, extraPointy, maxDist1x, maxDist1y) < radius1 ||
					distance(extraPointx, extraPointy, maxDist2x, maxDist2y) < radius1
			) {
				return false;
			}
		}
		return true;
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

	//check if there exists a set of Q_PTS consecutive data points lie in more than quads quadrants
	boolean LIC4() {
		boolean[] inhabitedQuads = new boolean[3];
		if(parameters.qPts < 2 || parameters.qPts > numpoints || parameters.quads < 1 || parameters.quads > 3) {
			return false;
		}
		for(int i= 0; i < numpoints; i++) {
		if((i + parameters.qPts) <= numpoints ){
			
			for(int j = 0; j < parameters.qPts; j++) {
				if(coordinatex[i + j] >= 0 && coordinatey[i + j] >= 0 && inhabitedQuads[0] != true) {
					inhabitedQuads[0] = true;
					
				} else if(coordinatex[i + j] < 0 && coordinatey[i + j] >= 0 && inhabitedQuads[1] != true) {
					inhabitedQuads[1] = true;
				} else if(coordinatex[i + j] <= 0 && coordinatey[i + j] < 0 && inhabitedQuads[2] != true) {
					inhabitedQuads[2] = true;
				}
			}
			
			int counter = 0;
			for(int j = 0; j < 3; j++) {
				if(inhabitedQuads[j] == true) {
					counter++;
				}
			}
			
			if(counter > parameters.quads) {
				return true;
			} else {
				Arrays.fill(inhabitedQuads,Boolean.FALSE);
				
			}	
		}
		}
		
		return false;
	
		
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
	
	boolean lic10() {
		if(!(parameters.ePts >= 1) || !(parameters.fPts >= 1) || !(parameters.ePts + parameters.fPts <= numpoints-2) || numpoints <= 5) {
			return false;
		}
		int i = 0;
		int j = i + parameters.ePts+1;
		int k = j + parameters.fPts+1;
		
		while(k < numpoints) {
			double x1 = coordinatex[i];
			double y1 = coordinatey[i];
			double x2 = coordinatex[j];
			double y2 = coordinatey[j];
			double x3 = coordinatex[k];
			double y3 = coordinatey[k];
			
			if ((x1 != x2 || y1 != y2) && (x2 != x3 || y2 != y3)) {
				double area = Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2);
				if(area > parameters.area1) {
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
	 * Check if 3 points gapped by ePts and fPts for a triangle with area less or more than area1 and area2
	 * @return true if 3 pts triangle area is more than area1 and some 3 pts less than area2
	 */
	boolean LIC14(Paramenters_t parameters) {
		double pt1x, pt1y, pt2x, pt2y, pt3x, pt3y;
		double someArea;
		boolean a1 = false, a2 = false;
		if(numpoints < 5) return false;
		for(int i = 0; i < numpoints - (parameters.ePts + parameters.fPts) - 2; i++) {
			pt1x = coordinatex[i]; pt1y = coordinatey[i];
			pt2x = coordinatex[i+parameters.ePts+1]; pt2y = coordinatey[i+parameters.ePts+1];
			pt3x = coordinatex[i+parameters.ePts+parameters.fPts+2]; pt3y = coordinatey[i+parameters.ePts+parameters.fPts+2];

			someArea = 0.5 * (pt1x * (pt2y-pt3y) + pt2x * (pt3y-pt1y) + pt3x * (pt1y-pt2y));
			
			// check if the pts triangle area is more than area1
			if(doublecompere(someArea, parameters.area1) == Comptype.GT) {
				a1 = true;
				if(a2) return true;
			}

			// check if the pts triangle area is less than area2
			if(doublecompere(someArea, parameters.area2) == Comptype.LT) {
				a2 = true;
				if(a1) return true;
			};
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
