import java.io.File;

public class Entry {
	double time;
	String sourceIP;
	String destinationIP;
	int numberOfBytes;		
	String[] l;
	
	public Entry(String line) {
		l = line.split("\t");
		this.time = Double.parseDouble(l[1]);
		this.sourceIP = l[2];
		this.destinationIP = l[4];
		
		try {
			this.numberOfBytes = Integer.valueOf(l[7]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			this.numberOfBytes = -1;
		}
	}
	
	public void printEntry() {
		System.out.println("==========================");
		
		System.out.print("time:		");
		System.out.println(this.time);
		
		System.out.print("sourceIP:	");
		System.out.println(this.sourceIP);
		
		System.out.print("destinationIP:	");
		System.out.println(this.destinationIP);
		
		System.out.print("numberOfBytes:	");
		System.out.println(this.numberOfBytes);
		
		System.out.println("==========================");
	}
	
}
