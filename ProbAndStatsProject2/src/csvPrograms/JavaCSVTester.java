package csvPrograms;

import java.io.FileNotFoundException;

/**
 * This is the tester class for the Java CSV Plotter, Salter, and Smoother Programs. 
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class JavaCSVTester
{
	/**
	 * The main method. Though nothing will be outputted to the console, there will be files that are created in the src folder.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		//This method will create a CSV with x values between -25 and 25, increments of 1. With the determined function X^2 + 2x + 1.
		GraphFunction.printToFile(-25, 25, 1);
		
		//This method will salt the data of the CSV produced in the previous function. The amount of salt is determined by the parameter.
		GraphSalter.salt(50);
		
		//This method will then smooth the data of the saltedCSV, with a windowValue of 3, and it will smooth 3 times over.
		GraphSmoother.smooth(3, 3);
	}
}
