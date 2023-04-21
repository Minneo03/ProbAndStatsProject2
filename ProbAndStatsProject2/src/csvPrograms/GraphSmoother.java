package csvPrograms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class will take a csv determined by the file path, and then smooth the values and return a new csv.
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class GraphSmoother
{
	/**
	 * This method will read that file and load the values into an arrayList, after which it will be smoothed numTimes. After the arrayList values have been smoothed, it will convert the arrayList into a csv format. The 3 different parts are separated as different loops.
	 * 
	 * @param windowValue - A 'range' to determine how many values the program will look at to smooth.
	 * @param numTimes - Determines how many times the smoothing process will occur.
	 * Example - smooth(3, 3) means that every value will be replaced by the average of itself and the 3 numbers on either side of it. This process will then occur 3 times, according to numTimes.
	 */
	public static void smooth(int windowValue, int numTimes)
	{
		String file = "C:/Users/failg/git/ProbAndStatsProject2/ProbAndStatsProject2/SaltedGraphFunction.csv";
		BufferedReader reader = null;
		String line;
		int tmp = 0;
		int count = 0;
		
		
		try
		{
			File smoothedCsvFile = new File("SmoothedGraphFunction.csv");
			PrintWriter out = new PrintWriter(smoothedCsvFile);
			
			reader = new BufferedReader(new FileReader(file));
			
			ArrayList<Integer> tmpArr = new ArrayList<Integer>();
			
			String[] row;
			
			//reads the csv and adds it to an ArrayList
			while((line = reader.readLine()) != null) 
			{
				row = line.split(",");

				for(int i = 0; i < row.length; i++)
				{
					tmpArr.add(Integer.valueOf(row[i].trim()));
				}
				
			}
			
			//smooths the y values inside of the ArrayList, numTimes
			for(int k = 0; k < numTimes; k++)
			{
				for(int i = 1; i < tmpArr.size(); i+=2)
				{
					for(int j = i - (windowValue * 2); j <= i + (windowValue * 2); j+=2)
					{
						if(!(j < 0 || j > tmpArr.size()))
						{
							tmp += tmpArr.get(j);
							count++;
						}
					}
					tmpArr.set(i, (tmp/count));
					tmp = 0;
					count = 0;
				}
			}
			
			//Converts the ArrayList to a csv
			for(int i = 0; i < tmpArr.size(); i+=2)
			{
				out.printf("%d, %d\n", tmpArr.get(i), tmpArr.get(i+1));
			}
			
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				reader.close();
			} 
			catch (IOException f)
			{
				f.printStackTrace();
			}
		}
	}
}
