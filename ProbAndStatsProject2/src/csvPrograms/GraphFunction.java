package csvPrograms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will plot out 100 data points for an f(x) style equation. These data points will be outputted to a csv file, which will be created/updated in the project file path. I did follow along with "https://www.youtube.com/watch?v=dHZaqMmQNO4" this video to understand how to upload to a csv.
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
	 * Classic setter for X
	 * 
	 * @param xInput - the inputted X value
	 */
	public void setX(int xInput)
	{
		x = xInput;
	}
	
	/**
	 * Classic setter for Y
	 * 
	 * @param yInput - the inputted y value
	 */
	public void setY(int yInput)
	{
		y = yInput;
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
	public static void printToFile(int min, int max, int interval) throws FileNotFoundException
	{
		ArrayList<GraphFunction> points = new ArrayList<GraphFunction>();

	 	int funcX = 0;
	 	int funcY = 0;
		
	 	if (max < min || interval <= 0)
	 	{
	 		System.out.println("Parameters are not acceptable. The max has to be higher than the min and the interval has to be greater than 0.");
	 	}
	 	else
	 	{
			for(int i = min; i <= max; i = i + interval)
			{
				if (i == min)
				{
					funcX = min;
				}
				else
				{
					funcX += interval;
				}
				
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
	}
	
	/**
	 * Just calls the printToFile method in the main method. The method checks to see if the min/max/interval aren't out of bounds. Ie max being lower than the min, or interval being less than or equal to 0.
	 * 
	 * @throws FileNotFoundException - since the printToFile also has a FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		printToFile(-25, 25, 1);
	}
}
