package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigInteger;
//Could've also used java.util.Comparator to sort

/**
 * 
 * This class uses plenty of methods to find various measurements used in Statistics. Such as mean, median, and mode. This class also includes different Distribution equations and a way to find permutations and combinations.
 * 
 * @author RyanMinneo
 * @version 1.0
 */
public class StatsLibrary {
	
	/**
	 * Finds the mean of an arrayList
	 * 
	 * @param inputArr - input Array, an arrayList of integers
	 * @return mean - the mean of the inputed arrayList.
	 */
	public double findMean(ArrayList<Integer> inputArr) {
		double mean = 0.0;
		
		for (int singleElement : inputArr) {
			mean += singleElement;
		}
		
		mean /= inputArr.size();
		
		return mean;
	}
	
	/**
	 * Sorts the arraylist to be in increasing order, then determines whether the size of the array is even or odd using modulo. Then finds the median of the arrayList
	 * 
	 * @param inputArr - inputed arrayList containing integers 
	 * @return median - the median of the arrayList
	 */
	public double findMedian(ArrayList<Integer> inputArr) {
		double median = 0.0;
		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		
		//adds all of the values from one arrayList to another.
		sortedArray.addAll(inputArr);
		
		//sorts the arrayList into ascending order.
		Collections.sort(sortedArray);
		
		if (sortedArray.size()%2 == 1) {
			median = sortedArray.get(sortedArray.size()/2);
		}
		else {
			median = (sortedArray.get(sortedArray.size()/2) + sortedArray.get(sortedArray.size()/2 + 1))/2;
		}
		
		return median;
	}
	
	/**
	 * Finds the mode of an inputed arrayList. If there are "multiple modes" then it should return null. If there is no mode, it will also return null.
	 * 
	 * @param inputArr - the inputed arrayList containing integers
	 * @return mode - the mode of the integers, will return null if there is no mode.
	 */
	public Integer findMode(ArrayList<Integer> inputArr) 
	{
		Integer mode = null;
		int highestCount = 0;
		
		for (int i = 0; i < inputArr.size(); i++) 
		{
			int count = 0;
			
			for (int j = 0; j < inputArr.size(); j++) 
			{
				if (inputArr.get(i) == inputArr.get(j))
					count++;	
			}
			
			//if the count is higher than the highestCount, highestCount will be redefined as the value of count. Then the mode is set to the value in the arrayList. If there are "multiple modes" then the mode will become null.
			if (count > highestCount) 
			{
				highestCount = count;
				mode = inputArr.get(i);
			}
			else if (count == highestCount && inputArr.get(i) != mode)
				mode = null;
		}
		return mode;
	}
	
	/**
	 * Finds the standard deviation of an ArrayList of Integers
	 * 
	 * @param inputArr - the inputed arrayList of integers
	 * @return deviation (AKA Standard Deviation)
	 */
	public double standardDeviation(ArrayList<Integer> inputArr)
	{
		double deviation = 0.0;
		//mean comes in handy
		double mean = findMean(inputArr);
		
		//Sum of (Xi - X-)^2
		for (int element : inputArr)
			deviation += Math.pow((element-mean), 2);
		
		//Divides by n-1
		deviation /= (inputArr.size()-1);
		
		//Takes the Square Root to finally determine the Standard Deviation
		deviation = Math.sqrt(deviation);
		
		return deviation;
	}
	
	/**
	 * First time I ever used BigInteger. I used it so that I can calculate factorials to a much higher value.
	 * 
	 * @param num - It is a String, but it could technically be an integer or BigInteger, String is just the easiest.
	 * @return strFactorial - returns the factorial of num as a String, since the values are too big for an int
	 */
	public String findFactorial(String num)
	{
		BigInteger factorial = new BigInteger("1");
		BigInteger numb = new BigInteger(num);
		String strFactorial = "";
		
		for (int i = numb.intValue(); i > 1; i--)
		{
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}
		
		strFactorial = factorial.toString();
		return strFactorial;
	}
	
	/**
	 * Finds the number of Permutations. Takes nInput and rInput to plug into the permutation equation. This class uses BigInteger, so I decided to go with String inputs and outputs rather than ints.
	 * 
	 * @param nInput - the number of elements
	 * @param rInput - how many elements are taken at a time
	 * @return strPermutations - the string version of the number of permutations.
	 */
	public String findPermutations(String nInput, String rInput)
	{
		BigInteger n = new BigInteger(nInput);
		BigInteger r = new BigInteger(rInput);
		BigInteger nFactorial = new BigInteger(findFactorial(nInput));
		BigInteger nMinusRFactorial = new BigInteger(findFactorial((n.subtract(r)).toString()));
		BigInteger permutations = new BigInteger("1");
		String strPermutations = "";
		
		permutations = nFactorial.divide(nMinusRFactorial);
		
		strPermutations = permutations.toString();
		return strPermutations;
	}
	
	/**
	 * Finds the number of combinations given nInput and rInput, where n and r are the variables in the Combination equation. Uses BigInteger to allows for bigger inputs and outputs. Because I use BigInteger, the inputs and outputs are strings
	 * 
	 * @param nInput - the number of elements
	 * @param rInput - number of elements taken at a time
	 * @return strCombinations - a string containing the number of combinations
	 */
	public String findCombinations(String nInput, String rInput)
	{
		BigInteger n = new BigInteger(nInput);
		BigInteger r = new BigInteger(rInput);
		BigInteger nFactorial = new BigInteger(findFactorial(nInput));
		BigInteger rFactorial = new BigInteger(findFactorial(rInput));
		BigInteger nMinusRFactorial = new BigInteger(findFactorial((n.subtract(r)).toString()));
		BigInteger combinations = new BigInteger("1");
		String strCombinations = "";
		
		combinations = nFactorial.divide(rFactorial.multiply(nMinusRFactorial));
		
		strCombinations = combinations.toString();
		return strCombinations;
	}
	
	/**
	 * Finds the Binomial Distribution of an exact number
	 * Example, find the probability of exactly 7 out of 10 successes.
	 * This method will not work if the number of combinations exceeds the max int value of 2.147 billion.
	 * 
	 * @param n - total number of cases
	 * @param y - number of "successes"
	 * @param p - chance of success
	 * @return finalAnswer - the finalAnswer of the binomialDistribution
	 */
	public double binomialDistributionExact(int n, int y, double p)
	{
		double finalAnswer = 0.0;
		double q = 1.0 - p;
		
		int combos = Integer.parseInt(findCombinations(String.valueOf(n), String.valueOf(y)));
		
		finalAnswer = combos * Math.pow(p, y) * Math.pow(q, (n-y));
		
		return finalAnswer;
	}
	
	/**
	 * Finds the Binomial Distribution of numbers greater than y.
	 * Example, find the probability of at least 8 out of 10 successes. (y would be 7)
	 * 
	 * @param n - total number of cases
	 * @param y - Greater than y successes
	 * @param p - chance of success per trial
	 * @return finalAnswer = sum of each individual binomialDistributionExact.
	 */
	public double binomialDistributionGreater(int n, int y, double p)
	{
		double finalAnswer = 0.0;
		
		for (int i = y + 1; i <= n; i++)
		{
			finalAnswer += binomialDistributionExact(n, i, p);
		}
		
		return finalAnswer;
	}
	
	/**
	 * This method will find the geometric distribution given the probability of success and the number of trials up to and including the first success
	 * 
	 * @param p - the probability of a success
	 * @param y - the number of trails up to and including the first success.
	 * @return finalAnswer - the geometric distribution in decimal form.
	 */
	public double geometricDistribution(double p, int y)
	{
		double finalAnswer = 0.0;
		
		double q = 1.0-p;
		
		finalAnswer = Math.pow(q, y-1) * p;
		
		return finalAnswer;
	}
	
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
		
		double numerator = Double.valueOf(findCombinations(r + "", y + "")) * Double.valueOf(findCombinations(NminusR + "", nMinusY + ""));
		double denominator = Double.valueOf(findCombinations(N + "", n + ""));
		
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
		int denominator = Integer.valueOf(findFactorial(y + ""));
		
		result = ((double)numerator/(double)denominator) * Math.pow(Math.E, 0-lambda);
		
		return result;
	}
	
	/**
	 * Tests every method from this class and prints out those trials into the console. This method is much longer than it needs to be, but it allows only 2 lines in the main method.
	 */
	public void testEverything()
	{
		StatsLibrary tester = new StatsLibrary();
		//For Mean, median, mode, standard deviation
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> sortedArr = new ArrayList<Integer>();
		
		//For Factorial Test (big number)
		String number = "5";
		
		//randomizing the arrayList cause I didn't want to create multiple arrayLists, and I wanted to test out how using ThreadLocalRandom worked. "i < ThreadLocal..." randomizes the length of the arrayList. ...nextInt(min, max + 1) 
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(10, 21); i++)
		{
			arr.add(ThreadLocalRandom.current().nextInt(0, 21));
		}
		
		sortedArr.addAll(arr);
		Collections.sort(sortedArr);
		
		//ArrayListMethods
		System.out.println("The unsorted arrayList is: " + arr.toString());
		System.out.println("The sorted arrayList is: " + sortedArr.toString() + "\n");
		System.out.println("The mean of this arrayList is: " + tester.findMean(arr));
		System.out.println("The median of this arrayList is: " + tester.findMedian(arr));
		System.out.println("The mode of this arrayList is: " + tester.findMode(arr)+ "\n");
		System.out.println("The Standard Deviation of this arrayList is: " + tester.standardDeviation(arr)+ "\n");
		
		//Permutations/Combinations Methods (These use BigInteger, and Strings are inputs to allows insanely big number)
		System.out.println("The factorial of " + number + " is: " + tester.findFactorial(number) + "\n");
		
		System.out.println("The number of Permutations is: " + tester.findPermutations("52", "2"));
		System.out.println("The number of Combinations is: " + tester.findCombinations("52", "2") + "\n");
		
		//Distributions
		System.out.println("The binomial distribution is " + tester.binomialDistributionExact(5, 3, 0.75));
		System.out.println("The binomial distribution is " + tester.binomialDistributionGreater(5, 3, 0.75) + "\n");
		
		System.out.println("The geometric distribution is " + tester.geometricDistribution(0.45, 3));
		System.out.println("The hypergeometric distribution is " + tester.hypergeometricDistribution(20, 5, 6, 4) + "\n");
		
		System.out.println("The poisson distribution is " + tester.poissonDistribution(0, 1));
	}
}
