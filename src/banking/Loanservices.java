package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Loanservices extends JFrame implements ActionListener {
    JButton apply,pay,view,status,back;
    String pinnumber;
    Loanservices(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Loan Sevices");
        setLayout(null);
        
        JLabel text = new JLabel("Loan Services");
        text.setFont(new Font("Raleway",Font.BOLD,38));
        text.setBounds(130,20,400,50);
        add(text);
        
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/loan-apply.png")) ;
        Image I2=I1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        ImageIcon I3=new ImageIcon(I2);
        
        apply=new JButton(I3);
        apply.setBounds(130,90,90,90);
        apply.setBackground(new Color(0x87CEEB));
        apply.setBorderPainted(false);
        apply.addActionListener(this);
        add(apply);
        text = new JLabel("Apply Loan");
        text.setBounds(130,190,200,40);
        text.setFont(new Font("Raleway",Font.BOLD,18));
        add(text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/emi.png")) ;
        I2=I1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        pay=new JButton(I3);
        pay.setBounds(440,90,90,90);
        pay.setBackground(new Color(0x87CEEB));
        pay.setBorderPainted(false);
        pay.addActionListener(this);
        add(pay);
        
        text = new JLabel("Pay Installment");
        text.setBounds(430,190,200,40);
        text.setFont(new Font("Raleway",Font.BOLD,18));
        add(text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/view.png")) ;
        I2=I1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        view=new JButton(I3);
        view.setBounds(130,300,90,90);
        view.setBackground(new Color(0x87CEEB));
        view.setBorderPainted(false);
        view.addActionListener(this);
        add(view);
        
        text = new JLabel("View Loan");
        text.setBounds(130,410,200,40);
        text.setFont(new Font("Raleway",Font.BOLD,18));
        add(text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/status.png")) ;
        I2=I1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        status=new JButton(I3);
        status.setBounds(440,300,90,90);
        status.setBackground(new Color(0x87CEEB));
        status.setBorderPainted(false);
        status.addActionListener(this);
        add(status);
        
        text = new JLabel("Application Status");
        text.setBounds(430,410,200,40);
        text.setFont(new Font("Raleway",Font.BOLD,18));
        add(text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/back.png")) ;
        I2=I1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        back=new JButton(I3);
        back.setBounds(40,35,30,30);
        back.setBackground(new Color(0x87CEEB));
        back.setBorderPainted(false);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(750,600);
        setLocation(400,100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else if(ae.getSource()==apply){
            setVisible(false);
            new Loanapply(pinnumber).setVisible(true);
        }else if(ae.getSource()==status){
            setVisible(false);
            new Applicationstatus(pinnumber).setVisible(true);
        }else if(ae.getSource() == view){
            setVisible(false);
            new AllLoan(pinnumber).setVisible(true);
        }else if(ae.getSource() == pay){
            setVisible(false);
            new DispApproveLoan(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Loanservices("");
    }
}
