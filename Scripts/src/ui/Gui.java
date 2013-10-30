package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pSmelter.*;

import data.Bar;
import data.Item;
import data.Location;

public class Gui {
    
    public static boolean started = false;

public Gui() {
    components();
}

private JPanel smelting() {
    Border raised;
    TitledBorder title;
    
    	JPanel main = new JPanel();
    	main.setLayout(new BorderLayout());
	
	final JLabel label = new JLabel("Choose Bar Type:");
	final JLabel location = new JLabel("Choose Location: (Edge untested)");

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
	final JLabel need = new JLabel("Need account to test Coalbag + Edgeville. PM ME");
	
	JLabel lab = new JLabel("Your Elite AIO Experience");
	lab.setFont(new Font("Serif", Font.BOLD, 14));
	raised = BorderFactory.createRaisedBevelBorder();
	title = BorderFactory.createTitledBorder(raised, "pSmelter");
	title.setTitlePosition(TitledBorder.CENTER);
	lab.setBorder(title);
	
	main.add(lab, BorderLayout.NORTH);
	main.add(label, BorderLayout.EAST);
	main.add(scroll, BorderLayout.EAST);
	main.add(location, BorderLayout.EAST);
	main.add(coalbag, BorderLayout.EAST);
	main.add(scroll2, BorderLayout.EAST);
	main.add(need, BorderLayout.SOUTH);
	
	return main;
}

@SuppressWarnings({ "rawtypes", "unchecked" })
public JPanel smithing() {
    JPanel top = new JPanel();
    top.setLayout(new BorderLayout());
    JPanel bot = new JPanel();
    bot.setLayout(new BorderLayout());
    final JComboBox<Item> scroll3 = new JComboBox<Item>(Item.values());
    final DefaultListModel listModel = new DefaultListModel();;
    JList list = new JList(listModel);
    
    final JTextField input = new JTextField(10);
    input.addActionListener(new ActionListener() {
	public void actionPerformed( ActionEvent aActionEvent ) {
	    String text = scroll3.getSelectedItem().toString();
	    if (text.length() > 0) {
		listModel.addElement(text);
		input.setText( "" );
	    }
	}
    });

    JButton remove = new JButton("Clear");
    remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
        	listModel.clear();
            }
    });
    
    JButton add = new JButton("Add");
    add.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    try {
		int[] item = ((Item) scroll3.getSelectedItem()).getItem();
		String id = (String) scroll3.getSelectedItem().toString() +"("+item+")";
		if(!listModel.contains(id)) {
		    listModel.addElement(id);
		}
	    } catch(Exception a) {
		return;
	    }
	}
    });

    top.add(list, BorderLayout.CENTER);
    bot.add(input, BorderLayout.SOUTH);
    bot.add(add);
    bot.add(remove);
    
    top.add(scroll3, BorderLayout.NORTH);
    top.add(bot, BorderLayout.SOUTH);
    
    return top;
    
}

public void components() {
    final JFrame main = new JFrame("pSmither AIO");
    main.setLayout(new FlowLayout());
    main.setSize(250, 400);
    main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("Smelting", smelting());
    tabs.addTab("Smithing", smithing());
    
    JButton start = new JButton("Start");
    start.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
	    main.dispose();
	    started = true;
	}
    });
    
    main.add(tabs);
    main.add(panel);
    main.add(start);
    main.setVisible(true);
    main.pack();
    }

public static void main(String args[]) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new Gui();
        }
    });
}
}
