package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pSmelter.*;

import data.Bar;

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
	main.setSize(175, 150);
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
	main.add(start);
	
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
