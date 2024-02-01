package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmpDispUser extends JFrame implements ActionListener {
    
    String accno;
    JButton back;
    EmpDispUser(String accno){
        setLayout(null);
        setTitle(accno);
        this.accno=accno;
        String pin="",formno="",name="",fname="";
        int balance=0;
        Conn c1=new Conn();
        try{
            ResultSet rs1 = c1.s.executeQuery("select * from login where AccountNumber = '"+accno+"'");
            if(rs1.next()){
                formno = rs1.getString("formno");
            }
            
            rs1 = c1.s.executeQuery("select * from signup where formno = '"+formno+"' ");
            if(rs1.next()){
                name = rs1.getString("name");
                fname=rs1.getString("father_name");
            }
            rs1=c1.s.executeQuery("select * from bank where acno = '"+accno+"'");
            while(rs1.next()){
                balance = Integer.parseInt(rs1.getString("balance"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        JLabel appnumber=new JLabel("Account Number :"+ accno);
        appnumber.setBounds(50,20,600,50);
        appnumber.setFont(new Font("Raleway",Font.BOLD,28));
        add(appnumber);
        
        JLabel nameText = new JLabel("Customer Name :  " + name);
        nameText.setBounds(50,120,600,30);
        nameText.setFont(new Font("Raleway",Font.PLAIN,20));
        add(nameText);
        
        JLabel fnameText = new JLabel("Customer's Father Name :  " + fname);
        fnameText.setBounds(50,170,600,30);
        fnameText.setFont(new Font("Raleway",Font.PLAIN,20));
        add(fnameText);
        
        JLabel balanceT = new JLabel("Customer's Balance : Rs. " + balance);
        balanceT.setBounds(50,220,600,30);
        balanceT.setFont(new Font("Raleway",Font.PLAIN,20));
        add(balanceT);
        
        back = new JButton("Back");
        back.setBounds(50,300,100,40);
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,600);
        setLocation(400,100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new EmpAccountNumber().setVisible(true);          
        }
    }
    public static void main(String args[]){
        new EmpDispUser("");
    }
}
