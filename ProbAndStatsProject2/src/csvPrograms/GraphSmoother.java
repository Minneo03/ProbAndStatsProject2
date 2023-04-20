package csvPrograms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GraphSmoother
{
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
	
	public static void main(String[] args)
	{
		smooth(3, 3);
	}
}
