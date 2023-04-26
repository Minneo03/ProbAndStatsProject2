package apiCsvPrograms;

/**
 * The Tester class for my API CSV Program.
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class ApiCsvTester
{
	/**
	 * Classic main method. All it is doing is constructing an ApiPSS object and then using it to call the generateGraphs method and the generateCSVs method.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		ApiPSS tester = new ApiPSS();
		tester.generateGraphs();
		tester.generateCSVs();
	}

}
