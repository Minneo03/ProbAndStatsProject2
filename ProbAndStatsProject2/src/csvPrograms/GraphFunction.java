package csvPrograms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will plot out 100 data points for an f(x) style equation. These data points will be outputted to a csv file, which will be created/updated in the project file path.
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class GraphFunction
{
	private int x;
	private int y;
	
	/**
	 * Constructor for a GraphFunction. I should've labeled this class as 'points' as that would probably be more accurate to what it is actually simulating.
	 * 
	 * @param xInput - the inputted x value
	 * @param yInput - the inputted y value
	 */
	public GraphFunction(int xInput, int yInput)
	{
		x = xInput;
		y = yInput;
	}
	
	/**
	 * Classic Getter for X
	 * 
	 * @return x - the x value of the GraphFunction
	 */
	public int getX()
	{
		return x;
	}
	
	
	/**
	 * Classic Getter for Y
	 * 
	 * @return y - the y value of the GraphFunction
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * This method will create an arraylist of GraphFunctions, where x is 1 to 100. Then it will output those GraphFunctions (more accurately 'data points') to a csvFile using printwriter. 
	 * 
	 * @throws FileNotFoundException - The File should always be found though, as it is also creating a file in this method.
	 */
	public static void printToFile() throws FileNotFoundException
	{
		ArrayList<GraphFunction> points = new ArrayList<GraphFunction>();

	 	int funcX = 0;
	 	int funcY = 0;
		
		for(int i = 1; i <= 100; i++)
		{
			funcX++;
			
			//Change this line to make a new f(x)
			funcY = (int) (Math.pow(funcX, 2) + 2 * funcX + 1);
			
			points.add(new GraphFunction(funcX, funcY));
		}
		
		File csvFile = new File("GraphFunction.csv");
		PrintWriter out = new PrintWriter(csvFile);
		
		for(GraphFunction graph : points)
		{
			out.printf("%d, %d\n", graph.getX(), graph.getY());
		}
		
		out.close();
	}
	
	/**
	 * Just calls the printToFile method in the main method.
	 * 
	 * @throws FileNotFoundException - since the printToFile also has a FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		printToFile();
	}
}
