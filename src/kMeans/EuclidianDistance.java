package kMeans;

/**
 * @author vravic2
 * Class that implements to calculate the Euclidian distance.
 */
public class EuclidianDistance {

	public EuclidianDistance() {

	}
	
	public static double calcEuclidianDistance(double[] cent, double[] dp){
		double dist = Math.sqrt(
		Math.pow(cent[0]-dp[0], 2) + Math.pow((cent[1]-dp[1]), 2)+
		Math.pow(cent[2]-dp[2], 2) + Math.pow(cent[3]-dp[3], 2)+
		Math.pow(cent[4]-dp[4], 2) + Math.pow(cent[5]-dp[5], 2)+
		Math.pow(cent[6]-dp[6], 2) + Math.pow(cent[7]-dp[7], 2)+
		Math.pow(cent[8]-dp[8], 2) + Math.pow(cent[9]-dp[9], 2));
		return dist;
		
	}

}
