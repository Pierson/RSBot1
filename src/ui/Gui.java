package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pSmelter.*;

import data.Bar;
import data.Location;

public class Gui {
    
    public static boolean started = false;

public Gui() {
    components();
}

private void components() {
    Border raised;
    TitledBorder title;
    
	final JFrame main = new JFrame();
	main.setLayout(new FlowLayout());
	main.setSize(220, 200);
	main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	main.setResizable(false);
	
	final JLabel label = new JLabel("Choose Bar Type:");

	final JComboBox<Bar> scroll = new JComboBox<Bar>(new DefaultComboBoxModel<>(Bar.values()));
	scroll.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		Smelter.selected = scroll.getSelectedItem().toString();
		Smelter.bar = (Bar) scroll.getSelectedItem();
	    }
	});
	
	final JComboBox<Location> scroll2 = new JComboBox<Location>(new DefaultComboBoxModel<>(Location.values()));
	scroll2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		Smelter.location = (Location) scroll2.getSelectedItem();
		Smelter.selectedLocation = scroll2.getSelectedItem().toString();
	    }
	});
	
	final JCheckBox coalbag = new JCheckBox("Use Coalbag");
	coalbag.setEnabled(false);
	final JLabel need = new JLabel("Need account to test Coalbag. PM ME");
	
	final JLabel location = new JLabel("Choose Location: (Edge untested)");
	
	JLabel lab = new JLabel("Your Elite AIO Experience");
	lab.setFont(new Font("Serif", Font.BOLD, 14));
	raised = BorderFactory.createRaisedBevelBorder();
	title = BorderFactory.createTitledBorder(raised, "pSmelter");
	title.setTitlePosition(TitledBorder.CENTER);
	lab.setBorder(title);
	
	JButton start = new JButton("Start");
	start.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		main.dispose();
		started = true;
	    }
	});
	
	main.add(lab);
	main.add(label);
	main.add(scroll);
	main.add(location);
	main.add(coalbag);
	main.add(scroll2);
	main.add(start);
	main.add(need);
	
	main.setVisible(true);
}	

    public static void main(String args[]) {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		new Gui();
	    }
	});
    }
}
