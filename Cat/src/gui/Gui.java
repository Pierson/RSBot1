package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import cat.Main;

import enums.Pet;

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
	
	final JLabel label = new JLabel("Choose Pet");

	final JComboBox<Pet> scroll = new JComboBox<Pet>(new DefaultComboBoxModel<>(Pet.values()));
	scroll.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		Main.selected = scroll.getSelectedItem().toString();
		Main.pet = (Pet) scroll.getSelectedItem();
	    }
	});
	
	JLabel lab = new JLabel("Drops cats and shiz");
	lab.setFont(new Font("Serif", Font.BOLD, 14));
	raised = BorderFactory.createRaisedBevelBorder();
	title = BorderFactory.createTitledBorder(raised, "Le trolles");
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
