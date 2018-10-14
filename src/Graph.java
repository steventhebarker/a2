// StickFigure.java
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph {
	private int interval;
	private int xInterval;
	private int yTickNum = 8;
	private int xTickNum = 13;
	private int height; 
	private Point zero;
	public ArrayList<Integer> yValues;
	private float width;
	public Graph() {
	zero = new Point(60,275);
	}
	
	public int roundUpNicely(int num) {
		ArrayList<Integer> niceNumbers = new ArrayList<>(Arrays.asList(
				10,20,50,100,200,250,500,1000,2000,2500,5000,10000,20000,25000,50000,100000,200000,250000,500000,1000000,2000000,5000000,10000000));
		while(true) {
			if(niceNumbers.contains(num)) {
				return num;
			}
			num++;
			
		}
	}
	public void drawAxes(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; //creates new graphic
		g2d.setStroke(new BasicStroke());
		g2d.setPaint(Color.BLACK);
		g2d.drawString("Volume [bytes]", 0, 15);
		g2d.drawString("Time [s]", zero.x+450, zero.y+45);
		//y axis
		g2d.drawLine(zero.x, zero.y+5, zero.x, zero.y-250);
		int maxYAxis = 0;
		try {
			maxYAxis = Collections.max(yValues);
		}
		catch(Exception e) {
			;
		}
		if(maxYAxis!=0) {
			interval = roundUpNicely((int)(maxYAxis)/yTickNum);
			int heightDifference = (int)(250/yTickNum);
			for(int i=0; i<= 250; i+=heightDifference) {
				g2d.drawLine(zero.x-5, zero.y-((i*250)/maxYAxis),zero.x, zero.y-((i*250)/maxYAxis));
				g2d.drawString(String.valueOf(interval*(i/heightDifference)), zero.x-60,zero.y+10-i);
			}
		}
		System.out.printf("MAX HEIGHT: %d", maxYAxis);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//x axis
		g2d.drawLine(zero.x-5, zero.y, zero.x+900, zero.y);
		int maxXAxis = 500;
		try {
			maxXAxis = 2*yValues.size()+4;
			System.out.printf("MAX: %d", maxXAxis);
		}
		catch(Exception e) {}
		xInterval = roundUpNicely((int)(maxXAxis)/xTickNum);
		int widthDifference = (int)(900/xTickNum);
		for(int i=0; i<=xTickNum; i++) {
			g2d.drawLine(zero.x+(i*widthDifference),zero.y,zero.x+(i*widthDifference),zero.y+5);
			g2d.drawString(String.valueOf(xInterval*i), zero.x+(i*widthDifference),zero.y+20);
		}
	}
	
	public void drawGraph(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; //creates new graphic
		g2d.setStroke(new BasicStroke());
		g2d.setPaint(Color.RED);
		int num = yValues.size();
		width = 900.0f/num; //(int)Math.round(900.0/num);
		double i = 0;

		//int maxHeight = Collections.max(yValues);;
		try {
			for(int height: yValues) {
				//g2d.fillRect(zero.x+i, zero.y, 4, -500);
				g2d.drawRect(zero.x+(int)i, zero.y, (int)width, -((height*250)/(interval*yTickNum)));
				i+=width;
			}
		}
		catch(Exception e){}
	}
	
	
	public ArrayList<Integer> filterData(Data data, String ip) {
		ArrayList<Entry> filteredEntries = new ArrayList<Entry>();
		for (Entry item : data.allEntries) {
			if(item.sourceIP.equals(ip) ||  item.destinationIP.equals(ip)) {
				filteredEntries.add(item);
			}
		}
		return dataToPlot(filteredEntries);
	}
	
	public ArrayList<Integer> dataToPlot(ArrayList<Entry> entries){
		ArrayList<Integer> yValues = new ArrayList<Integer>();
		int i = 0;
		int total = 0;
		for (Entry item : entries) {
			if (item.time < i) {
				total+=item.numberOfBytes;
			}
			else {
				yValues.add(total);
				i+=2;
				total = 0;
			}
		}
		return yValues;
	}
	
	
}
