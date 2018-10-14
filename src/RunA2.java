import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Arrays;
public class RunA2 implements Runnable {

	public void run() {
		A2 c = new A2();
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunA2());

		/*
		String[] sList = {};
		sList = Data.addItem(sList, "a");
		sList = Data.addItem(sList, "b");
		sList = Data.addItem(sList, "c");
		sList = Data.addItem(sList, "d");
		System.out.println(sList[0]);
		System.out.println(sList[1]);
		System.out.println(sList[2]);
		System.out.println(sList[3]);
		*/
	}

} 