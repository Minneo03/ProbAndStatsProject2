package library;

/**
 * This class has a few methods relating to the non-calculus distributions/equations talked about during the second half of the semester in Probability and Applied Statistics.
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class StatsLibraryProject2
{
	StatsLibrary stat = new StatsLibrary();
	
	
	/**
	 * Finds the hypergeometric distribution of a problem given numbers, r, y, N, and n.
	 * 
	 * @param r - the total amount of the group we want
	 * @param y - how many of (r) we want
	 * @param N - total amount of everything
	 * @param n - total amount of everything we selected
	 * @return result - returns the result of the hypergeometric distribution.
	 */
	public double hypergeometricDistribution(int N, int n, int r, int y)
	{
		double result = 0.0;
		
		int NminusR = N-r;
		int nMinusY = n-y;
		
		double numerator = Double.valueOf(stat.findCombinations(r + "", y + "")) * Double.valueOf(stat.findCombinations(NminusR + "", nMinusY + ""));
		double denominator = Double.valueOf(stat.findCombinations(N + "", n + ""));
		
		result = numerator/denominator;
		
		return result;
	}
	
	/**
	 * This will find the result of a poissonDistribution problem given the y and lambda
	 * 
	 * @param y - given number of events happening.
	 * @param lambda - the specified time period where 1 event happens y times.
	 * @return result - the result of the distribution.
	 */
	public double poissonDistribution(int y, int lambda)
	{
		double result = 0.0;
		
		int numerator = (int) Math.pow(lambda, y);
		int denominator = Integer.valueOf(stat.findFactorial(y + ""));
		
		result = ((double)numerator/(double)denominator) * Math.pow(Math.E, 0-lambda);
		
		return result;
	}
	
	/**
	 * This will deploy tchebysheffs theorem (3.14)
	 * 
	 * @param k - any constant greater than 0
	 * @param isMax - Since there are two equations for tchebysheffs, this boolean will determine which is used. If you would like to find the maximum proportion of observations or the minimum proportion of observations that are more than k standard deviations from the mean.
	 * @return result - the result of tchebysheffs theorem
	 */
	public double tchebysheffs(int k, boolean isMax)
	{
		double result = 0.0;
		
		//1-(1/k^2) or 1/k^2
		if(isMax)
		{
			result = 1-(1/Math.pow(k, 2));
		}
		else
		{
			result = 1/Math.pow(k, 2);
		}
		
		return result;
	}
	
	/**
	 * Tests every method from this class and prints out those trials into the console.
	 */
	public void testEverything()
	{
		StatsLibraryProject2 tester = new StatsLibraryProject2();
		
		//Distributions
		System.out.println("The hypergeometric distribution is " + tester.hypergeometricDistribution(20, 5, 6, 4));
		System.out.println("The poisson distribution is " + tester.poissonDistribution(0, 1) + "\n");
		System.out.println("Tchebysheff's Maximum is " + tester.tchebysheffs(3, true));
		System.out.println("Tchebysheff's Minimum is " + tester.tchebysheffs(3, false));
	}
}
