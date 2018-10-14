//import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Vector;
public class A2 extends JFrame {// implements ComponentListener, ItemListener {
    
	//private ArrayList<Image> images;
	//private ArrayList<String> imageNames;
	//private ArrayList<String> captions;
	//private JComboBox<String> imagesComboBox;
	//private JLabel iconLabel;
	//private JLabel textFieldLabel;
	//private JTextField nameTextField;
	//private JButton saveButton;
	//private JButton nextButton;
	//private JTextArea captionTextArea;
	//private int currentImageIndex = -1; 
	private Font font;
    private final JPanel radioButtonPanel;
    private final GraphPanel p;
    private final ButtonGroup radioButtons;
    private final JRadioButton radioButtonSourceHosts;
    private final JRadioButton radioButtonDestinationHosts;
    private final Graph gr;
    private int comboBoxAlreadySetUp = 0;
	private String[] petArray = new String[]{"a", "b", "c"};
	private JComboBox IPComboBox;
	private Data data;
	public A2() {
    	super("Photo Album");
    	setLayout(new FlowLayout(FlowLayout.LEFT));
    	setSize(1030,500);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	font = new Font("Sans-serif", Font.PLAIN, 20);
    	setupMenu();

    	//Setup radio buttons
    	radioButtonPanel = new JPanel();
    	radioButtonPanel.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = 0;
    	c.gridy = GridBagConstraints.RELATIVE;
    	c.anchor = GridBagConstraints.WEST;
    	// Group the buttons so only one of them can be checked
        radioButtons = new ButtonGroup();
    	
        //create source host button
    	radioButtonSourceHosts = new JRadioButton("Source Hosts");
    	radioButtonSourceHosts.setFont(font);
    	radioButtonSourceHosts.setSelected(true);
        radioButtons.add(radioButtonSourceHosts);
        radioButtonPanel.add(radioButtonSourceHosts, c);
        
        //create destination host button
    	radioButtonDestinationHosts = new JRadioButton("Destination hosts");
    	radioButtonDestinationHosts.setFont(font);   	
        radioButtons.add(radioButtonDestinationHosts);
    	radioButtonPanel.add(radioButtonDestinationHosts, c);

    	
    	add(radioButtonPanel);
    	
    	//TODO: THIS
    	

    	
    	//Adding graph JPanel
    	
    	gr = new Graph();
    	p = new GraphPanel(gr);
		add(p);
    	setVisible(true);
    	
    	
	}
	private void redrawGraph(Data data) {
		System.out.println("updating yValues");
		gr.yValues = gr.filterData(data, (String)IPComboBox.getSelectedItem()); //TODOO
		//setSize(1031,500);
		//setSize(1030,500);
	}
	private void setupMenu() {
		
		//Menu and MenuBar
    	JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileMenu.setFont(font);
		menuBar.add(fileMenu);
		JMenuItem fileMenuOpen = new JMenuItem("Open trace file");
		fileMenuOpen.setFont(font);
		fileMenuOpen.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser(".");
    					int retval = fileChooser.showOpenDialog(A2.this);
    					if (retval == JFileChooser.APPROVE_OPTION) {
    						File f = fileChooser.getSelectedFile();
    						// Create image icon from image file
    						System.out.println(f.getAbsolutePath());
    						data = new Data(f);
    						data.getData();
    						
    						////ADD JCOMBO////
    						if(comboBoxAlreadySetUp==0) {
    						setupComboBox(data);
    						comboBoxAlreadySetUp=1;
    						//redrawGraph(data);
    						}
    						else {
    							updateComboBox(data);
    						}
    						
    						//add Jbutton ActionListeners
    						for( ActionListener al : radioButtonDestinationHosts.getActionListeners() ) {
    							radioButtonDestinationHosts.removeActionListener( al );
    					    }
    						radioButtonDestinationHosts.addActionListener(new ActionListener() {
    				            @Override
    				            public void actionPerformed(ActionEvent e) {
    				                updateComboBox(data);
    				            }
    				        });
    						for( ActionListener al : radioButtonSourceHosts.getActionListeners() ) {
    							radioButtonSourceHosts.removeActionListener( al );
    					    }
    					   	radioButtonSourceHosts.addActionListener(new ActionListener() {
    				            @Override
    				            public void actionPerformed(ActionEvent e) {
    				                updateComboBox(data);
    				            }
    				        });
    						
    					}
					}
				}
        );
		fileMenu.add(fileMenuOpen);
		JMenuItem fileMenuQuit = new JMenuItem("Quit");
		fileMenuQuit.setFont(font);
		fileMenu.add(fileMenuQuit);
		fileMenuQuit.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}
				);
	}
	
	
	private void setupComboBox(Data data) {
        if(radioButtonSourceHosts.isSelected()) {
        	ArrayList<String> al = new ArrayList<String>(data.sortedSourceIPs);
        	IPComboBox = new JComboBox(new Vector<String>(al));
        }
        else {
        	ArrayList<String> al = new ArrayList<String>(data.sortedDestinationIPs);
        	IPComboBox = new JComboBox(new Vector<String>(al));
        }
        IPComboBox.setSelectedIndex(0);
        IPComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redrawGraph(data);
                p.repaint();
            }
        });
        //IPComboBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        IPComboBox.setBorder(new EmptyBorder(0,200,0,0));
        add(IPComboBox, 1);
        setVisible(true);
        redrawGraph(data);
        p.repaint();
        
	}
	
	private void updateComboBox(Data data) {
		IPComboBox.removeAllItems();
        if(radioButtonSourceHosts.isSelected()) {
        	for (String item : data.sortedSourceIPs) {
        		IPComboBox.addItem(item);
        		}
        }
        else {
        	for (String item : data.sortedDestinationIPs) {
        		IPComboBox.addItem(item);
        		}
        }
        
		for( ActionListener al : IPComboBox.getActionListeners() ) {
			IPComboBox.removeActionListener( al );
	    }
        IPComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redrawGraph(data);
                p.repaint();
            }
        });
        redrawGraph(data);
        p.repaint();
	}

}