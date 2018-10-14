//DrawingPanel
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.JPanel; //painting panel
import javax.swing.Timer; //timer
import java.awt.event.ActionListener; //event from timer going off
public class GraphPanel extends JPanel{

	private Graph gr;
	// Constructor sets up timer and reference to the array of figures
	public GraphPanel(Graph gr){
		this.setPreferredSize(new Dimension (1000,325));
		this.setBackground(Color.WHITE);
		this.gr = gr;
	}
	
	// This method paints everything in the graphics object g
	// and then moves the figures ready for the next paint
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gr.drawAxes(g);
		try {
			gr.drawGraph(g);
		}
		catch(Exception e) {
			;
		}
		
	}
}

