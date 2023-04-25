package csvPrograms;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This program will take a csv file and salt it, essentially adding/subtracting an inputted value (range) to the y values in the csv.
 * I followed along with this video "https://www.youtube.com/watch?v=zKDmzKaAQro" to understand how to read files.
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class GraphSalter
{
	/**
	 * This method will filter through every line of the inputted csv file and adds/subtracts a random number determined by the range to the y values. Then the program will take those new x,y values and outputs them to a new csv.
	 * 
	 * @param range - Determines the max of what can be added/subtracted
	 */
	public static void salt(int range)
	{
		String file = "C:/Users/failg/git/ProbAndStatsProject2/ProbAndStatsProject2/GraphPlot.csv";
		BufferedReader reader = null;
		String line;
		int tmp;
		
		
		try
		{
			File saltedCsvFile = new File("SaltedGraphPlot.csv");
			PrintWriter out = new PrintWriter(saltedCsvFile);
			
			reader = new BufferedReader(new FileReader(file));
			
			ArrayList<Integer> tmpArr = new ArrayList<Integer>();
			
			while((line = reader.readLine()) != null) 
			{
				String[] row = line.split(",");
				
				
				for (int i = 1; i < row.length; i += 2)
				{
					tmp = Integer.valueOf(row[i].trim()) + ThreadLocalRandom.current().nextInt(-range, range+1);
					
					row[i] = String.valueOf(tmp);
				}
				
				for(int i = 0; i < row.length; i++)
				{
					tmpArr.add(Integer.valueOf(row[i]));
					
				}
				
				out.printf("%d, %d\n", tmpArr.get(0), tmpArr.get(1));
				tmpArr.removeAll(tmpArr);
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
