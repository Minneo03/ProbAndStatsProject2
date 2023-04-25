package csvPrograms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will plot out 100 data points for an f(x) style equation. These data points will be outputted to a csv file, which will be created/updated in the project file path. I did follow along with "https://www.youtube.com/watch?v=dHZaqMmQNO4" this video to understand how to upload to a csv.
 * 
 * @author Ryan Minneo
 * @version 1.1
 */
public class GraphPlotter
{
	/**
	 * This method will create an arraylist of GraphFunctions, where x is minimum to maximum at intervals of "interval". Then it will output those GraphFunctions (more accurately 'data points') to a csvFile using printwriter. 
	 * 
	 * @param - min - the minimum x value, or the first x value you want to be plotted
	 * @param - max - the maximum x value, or the last x value you want to be plotted
	 * @param - interval - the interval at which the x values should be plotted.
	 * Example - printToFile(0, 100, 5) will plot x values 0, 5, 10, 15, ... , 95, 100.
	 * @throws FileNotFoundException - The File should always be found though, as it is also creating a file in this method.
	 */
	public static void printToFile(int min, int max, int interval)
	{
		ArrayList<Integer> points = new ArrayList<Integer>();

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
				
				points.add(funcX);
				points.add(funcY);
			}
			try
			{
				File csvFile = new File("GraphPlot.csv");
				PrintWriter out = new PrintWriter(csvFile);
				
				for(int i = 0; i < points.size()-1; i+=2)
				{
					out.printf("%d, %d\n", points.get(i), points.get(i+1));
				}
				
				out.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 	}
	}
}