package csvPrograms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This program takes a function (f(x) = 5x + 5) and finds the y value for x values 1 through 100.
 */
public class OutputFunction 
{
	public void function()
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();
		//int[][] arr = new int[2][100];
	 	int x = 0;
	 	int y = 0;
		
		for(int i = 1; i <= 100; i++)
		{
			x++;
			y = 5 * x + 5;
			arr.add(x);
			arr.add(y);
		}
		
		System.out.println(arr.toString());
	}
	
	public void csvFunction()
	{
		File csv = new File("D:\\OutputJavaFiles");
		
		try
		{
			FileWriter outputCSV = new FileWriter(csv);
			
			ArrayList<int[]> data = new ArrayList<int[]>();
			
			for(int i = 1; i <= 100; i++)
			{
				data.add(new int[] {i, (5 * i + 5)});
			}
			
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();	
		}
	}
}
