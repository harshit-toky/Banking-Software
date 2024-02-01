package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Balanceenquiry extends JFrame implements ActionListener {
    String pinnumber;
    JButton back;
    Balanceenquiry(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        int balance=0;
        
        JLabel text=new JLabel("Balance Amount");
        text.setFont(new Font("Raleway",Font.PLAIN,38));
        text.setBounds(280,100,400,40);
        add(text);
        Conn c=new Conn();
        try{
            ResultSet rs=c.s.executeQuery("select * from bank where acno = '"+pinnumber+"'");
            while(rs.next()){
                    balance = Integer.parseInt(rs.getString("balance"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        JLabel rs1=new JLabel("Rs.  "+ balance);
        rs1.setFont(new Font("Raleway",Font.PLAIN,38));
        rs1.setBounds(220,190,500,50);
        add(rs1);
        
        back=new JButton("Back");
        back.setBounds(460,290,100,30);
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        setSize(800,500);
        setLocation(400,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Balanceenquiry("");
    }
}
