package apiCsvPrograms;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

/**
 * This is the Plotter, Salter, and Smoother done using common API's such as JFreeCharts and Apache. This class has a method for plotting, salting, smoothing, and then generating the graph for all three of the data objects created from plotting, salting, and smoothing.
 * 
 * @author Ryan Minneo
 * @version 1.0
 */
public class ApiPSS
{
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private XYSeries data;
	private XYSeries saltedData;
	private XYSeries smoothedData;
	
	/**
	 * This method will create a set of (x,y) values, the length of which will be determined by the parameters. The function that the (x,y) values will be based on is (x^2 + 2x + 1). The x values will start at 'min' and end at 'max', moving at intervals of 'interval'.
	 * 
	 * @param min - the starting point. The first x value you want in your graph.
	 * @param max - the ending point. The last x value you want in your graph.
	 * @param interval - the interval at which the x values will increase.
	 */
	public void plot(int min, int max, int interval)
	{
		if (max < min || interval <= 0)
	 	{
	 		System.out.println("Parameters are not acceptable. The max has to be higher than the min and the interval has to be greater than 0.");
	 	}
	 	else
	 	{
			double[] coefficients = { 1, 2, 1};
			PolynomialFunction equation = new PolynomialFunction(coefficients);
			
			data = new XYSeries("Plotted Data");
			for (int x = min; x <= max; x = x + interval)
			{
				int y = (int) equation.value(x);
				data.add(x, y);
			}
			
			dataset.addSeries(data);
	 	}
	}
	
	/**
	 * This method will go through the plotted data and salt it. Effectively it will add a random number from -range to +range to each y value in the XYSeries. This method adds more 'noise' to the graph.
	 * 
	 * @param range - the maximum amount of 'noise' that can be added to each y value.
	 */
	public void salt(int range)
	{
		saltedData = new XYSeries("Salted Data");
		for (int i = 0; i < data.getItemCount(); i++)
		{
			int x = (int) (data.getDataItem(i).getXValue());
			int y = (int) (data.getDataItem(i).getYValue() + ThreadLocalRandom.current().nextInt(-range, range+1));
			saltedData.add(x, y);
		}
		
		dataset.addSeries(saltedData);
	}
	
	/**
	 * This method will use a windowValue to give a moving average to the salted data set. It will also generate a new XYSeries named 'smoothedData'.
	 * 
	 * @param windowValue - the windowValue of the smoother.
	 */
	public void smooth(int windowValue)
	{
		smoothedData = new XYSeries("Smoothed Data");
		double[] xValues = new double[saltedData.getItemCount()];
		double[] yValues = new double[saltedData.getItemCount()];
		for (int i = 0; i < saltedData.getItemCount(); i++)
		{
			yValues[i] = saltedData.getDataItem(i).getYValue();
		}
		
		DescriptiveStatistics stat = new DescriptiveStatistics(windowValue);
		
		for(int i = 0; i < xValues.length-1; i++)
		{
			int x = (int) (saltedData.getDataItem(i).getXValue());
			stat.addValue(yValues[i]);
			int y = (int) stat.getMean();
			
			smoothedData.add(x,y);
		}
		
		dataset.addSeries(smoothedData);
	}
	
	/**
	 * This method will call the plot, salt, and smooth method and then produce a graph with all of the values.
	 */
	public void generateGraphs()
	{
		plot(-25, 25, 1);
		salt(50);
		smooth(3);
		JFreeChart chart = ChartFactory.createXYLineChart("PSS Chart", "X Axis", "Y Axis", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		ChartFrame frame = new ChartFrame("PSS Plot", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method will go through all of the data formed by the plot, salt, and smooth methods and place that data into CSV files. If there is no data for any reason, example (salt and smooth aren't called) then the ApiSaltedData.csv file will become an empty CSV.
	 * The formula I used for converting the data into CSVs is using a for loop to go through every iteration of the data, and using printf to print the data neatly into the CSV.
	 */
	public void generateCSVs()
	{
		try
		{
			//Plotted Data CSV
			File plottedCsvFile = new File("ApiPlottedData.csv");
			PrintWriter out1 = new PrintWriter(plottedCsvFile);
			
			for(int i = 0; i < data.getItemCount(); i++)
			{
				out1.printf("%d, %d\n", (int) data.getDataItem(i).getXValue(), (int) data.getDataItem(i).getYValue());
			}
			
			out1.close();
			
			//Salted Data CSV
			File saltedCsvFile = new File("ApiSaltedData.csv");
			PrintWriter out2 = new PrintWriter(saltedCsvFile);
			
			for(int i = 0; i < saltedData.getItemCount(); i++)
			{
				out2.printf("%d, %d\n", (int) saltedData.getDataItem(i).getXValue(), (int) saltedData.getDataItem(i).getYValue());
			}
			
			out2.close();
			
			//Smoothed Data CSV
			File smoothedCsvFile = new File("ApiSmoothedData.csv");
			PrintWriter out3 = new PrintWriter(smoothedCsvFile);
			
			for(int i = 0; i < smoothedData.getItemCount(); i++)
			{
				out3.printf("%d, %d\n", (int) smoothedData.getDataItem(i).getXValue(), (int) smoothedData.getDataItem(i).getYValue());
			}
			
			out3.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}