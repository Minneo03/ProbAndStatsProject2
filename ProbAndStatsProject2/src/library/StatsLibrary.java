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
	
	
}
