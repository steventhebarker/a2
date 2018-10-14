import java.awt.*;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;

public class Data {
	private File f;
    public ArrayList<Entry> allEntries = new ArrayList<Entry>(); //This is the real one
    public Set<String> uniqueDestinationIPs = new HashSet<String>();
    public Set<String> uniqueSourceIPs = new HashSet<String>();
    public ArrayList<String> sortedDestinationIPs = new ArrayList<String>();
    public ArrayList<String> sortedSourceIPs = new ArrayList<String>();
	public Data(File f) {
		this.f = f;
	}
	
	public String getAbsolutePath() {
		return f.getAbsolutePath();
	}

	public int getLineNumber() {
		Scanner input = null;
		try {
		input = new Scanner(Paths.get(f.getAbsolutePath()));
		}
		catch (IOException ioExc) {
		// Nothing to do: File doesn't exist or isn't readable
		return -1;
		}
		
		int count=0;
		while (input.hasNext()) { // While there is data left..
			input.nextLine(); // … read a line
			count++;
		}
		input.close();
		return count;
	}
	
	public void getData() {
		int lineNumber = this.getLineNumber();			
		System.out.println(lineNumber);
				
		Scanner input = null;
		try {
		input = new Scanner(Paths.get(f.getAbsolutePath()));
		}
		catch (IOException ioExc) {
		// Nothing to do: File doesn't exist or isn't readable
		return;
		}
		
		int i = 0;
		while (input.hasNext()) { // While there is data left..
			String currentLine = input.nextLine(); // … read a line
			Entry entry = new Entry(currentLine);
			//entry.printEntry();
			if (entry.numberOfBytes != -1) {
				uniqueDestinationIPs.add(entry.destinationIP); 
				uniqueSourceIPs.add(entry.sourceIP); 
				allEntries.add(entry);
			}
		}
		input.close();
		sortedDestinationIPs = sortDestinationIPs();
		sortedSourceIPs = sortSourceIPs();
		System.out.println("\n DONEDONE");
	}

	public ArrayList<String> sortSourceIPs() {
		ArrayList<String> al = new ArrayList<String>(uniqueSourceIPs);

		Collections.sort(al, new Comparator<String>() {	
			public int compare(String str1, String str2) {
		    	String[] parts1 = str1.split("\\.");
		    	String[] parts2 = str2.split("\\.");
		    	
		    	//check the segments in order
		    	if (!parts1[0].equals(parts2[0])) {
		    		return Integer.valueOf(parts1[0]).compareTo(Integer.valueOf(parts2[0]));
		    	}
		    	if (!parts1[1].equals(parts2[1])) {
		    		return Integer.valueOf(parts1[1]).compareTo(Integer.valueOf(parts2[1]));
		    	}
		    	if (!parts1[2].equals(parts2[2])) {
		    		return Integer.valueOf(parts1[2]).compareTo(Integer.valueOf(parts2[2]));
		    	}
		    	if (!parts1[3].equals(parts2[3])) {
		    		return Integer.valueOf(parts1[3]).compareTo(Integer.valueOf(parts2[3]));
		    	}
		    	return 0;
			}
		});
		return al;
	}
	
	public ArrayList<String> sortDestinationIPs() {
		ArrayList<String> al = new ArrayList<String>(uniqueDestinationIPs);

		Collections.sort(al, new Comparator<String>() {	
			public int compare(String str1, String str2) {
		    	String[] parts1 = str1.split("\\.");
		    	String[] parts2 = str2.split("\\.");
		    	
		    	//check the segments in order
		    	if (!parts1[0].equals(parts2[0])) {
		    		return Integer.valueOf(parts1[0]).compareTo(Integer.valueOf(parts2[0]));
		    	}
		    	if (!parts1[1].equals(parts2[1])) {
		    		return Integer.valueOf(parts1[1]).compareTo(Integer.valueOf(parts2[1]));
		    	}
		    	if (!parts1[2].equals(parts2[2])) {
		    		return Integer.valueOf(parts1[2]).compareTo(Integer.valueOf(parts2[2]));
		    	}
		    	if (!parts1[3].equals(parts2[3])) {
		    		return Integer.valueOf(parts1[3]).compareTo(Integer.valueOf(parts2[3]));
		    	}
		    	return 0;
			}
		});
		return al;
	}





}
