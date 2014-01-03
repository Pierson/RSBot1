package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import jobs.Create;
import jobs.banking.Deposit;
import jobs.banking.OpenBank;
import jobs.banking.Withdraw;
import jobs.interfaces.InterfaceHandler;
import jobs.movement.Reverse;
import jobs.movement.Traverse;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

import core.JobContainer;
import data.Familiar;
import data.Teleport;


import pSummoner.*;

public class Gui extends MethodProvider {
    
private Summoner summon;

public Gui(MethodContext arg0, Summoner summon) {
    super(arg0);
    this.summon = summon;
    components();
}

private void components() {
    Border raised;
    TitledBorder title;
    
    final JPanel panel1 = new JPanel();
    final JPanel panel2 = new JPanel();
    final JPanel panel3 = new JPanel();
    
    JLabel summ = new JLabel("Your Elite AIO Experience");
    summ.setFont(new Font("Serif", Font.BOLD, 14));
    raised = BorderFactory.createRaisedBevelBorder();
    title = BorderFactory.createTitledBorder(raised, "pSummoner");
    title.setTitlePosition(TitledBorder.CENTER);
    summ.setBorder(title);
    
    panel1.add(summ);
    
    final JLabel fam = new JLabel("Choose Familiar Type:");
    final JLabel tele = new JLabel("Choose Tele Method:");
	
    final JComboBox<Familiar> scroll = new JComboBox<Familiar>(Familiar.values());
    scroll.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
	    Summoner.familiar = (Familiar) scroll.getSelectedItem();
	    summon.selected = scroll.getSelectedItem().toString();
	}
    });
    
    final JComboBox<Teleport> option = new JComboBox<Teleport>(Teleport.values());
    option.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
	    summon.option = (Teleport) option.getSelectedItem();
	    summon.teleport = option.getSelectedItem().toString();
	}
    });
    
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
    panel2.add(fam);
    panel2.add(scroll);
    panel2.add(tele);
    panel2.add(option);
    panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    
    final JFrame main = new JFrame("pSummoner AIO");
    main.setLayout(new FlowLayout());
    main.setSize(250, 275);
    main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    main.setResizable(false);
	
    JButton start = new JButton("START");
    start.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
	    main.dispose();
	    summon.container = new JobContainer(new Create(ctx), new InterfaceHandler(ctx), new Withdraw(ctx), new Deposit(ctx), new OpenBank(ctx), new Traverse(ctx), new Reverse(ctx));
	}
    });
    
    final JLabel warning = new JLabel("<html>-CHARMS <br>-SHARDS <br>-POUCHES <br>MUST BE IN INVENTORY TO START");
    
    panel3.add(start);
    panel3.add(warning);
	
    main.add(panel1);
    main.add(panel2);
    main.add(panel3);
	
    main.setVisible(true);
}
}
