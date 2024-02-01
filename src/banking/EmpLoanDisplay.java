package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmpLoanDisplay extends JFrame implements ActionListener {
    String appno,pinnumber,type,amount,salary,tenure,roi,accno,formno,fname,name;
    JButton next,back;
    EmpLoanDisplay(String appno){
        
        setLayout(null);
        setTitle(appno);
        
        appno=appno.substring(24);
        this.appno=appno;
        Conn c=new Conn();
        try{
            ResultSet rs = c.s.executeQuery("select * from loan where applicationNumber = '"+appno+"'");
            if(rs.next()){
                pinnumber = rs.getString("acno");
                type = rs.getString("type");
                amount = rs.getString("amount");
                salary = rs.getString("salary");
                tenure = rs.getString("tenure");
                roi = rs.getString("roi");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        Conn c1=new Conn();
        try{
            ResultSet rs1 = c1.s.executeQuery("select * from login where AccountNumber = '"+pinnumber+"'");
            if(rs1.next()){
                formno = rs1.getString("formno");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        Conn c2=new Conn();
        try{
            ResultSet rs2 = c2.s.executeQuery("select * from signup where formno = '"+formno+"'");
            if(rs2.next()){
                name = rs2.getString("name");
                fname = rs2.getString("father_name");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        JLabel appnumber=new JLabel("Application Number :"+ appno);
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
        
        JLabel ac = new JLabel("Account Number :  " + pinnumber);
        ac.setBounds(50,220,600,30);
        ac.setFont(new Font("Raleway",Font.PLAIN,20));
        add(ac);
        
        JLabel typet = new JLabel("Loan Type :  " + type);
        typet.setBounds(50,270,600,30);
        typet.setFont(new Font("Raleway",Font.PLAIN,20));
        add(typet);
        
        JLabel amountt = new JLabel("Loan Amount :  " + amount);
        amountt.setBounds(50,320,600,30);
        amountt.setFont(new Font("Raleway",Font.PLAIN,20));
        add(amountt);
        
        JLabel roit = new JLabel("Rate of Interest :  " + roi);
        roit.setBounds(50,370,600,30);
        roit.setFont(new Font("Raleway",Font.PLAIN,20));
        add(roit);
        
        JLabel tenuret = new JLabel("Loan Tenure :  " + tenure + " months");
        tenuret.setBounds(50,420,600,30);
        tenuret.setFont(new Font("Raleway",Font.PLAIN,20));
        add(tenuret);
        
        JLabel salaryt = new JLabel("Customer's Salary : Rs. " + salary);
        salaryt.setBounds(50,470,600,30);
        salaryt.setFont(new Font("Raleway",Font.PLAIN,20));
        add(salaryt);
        
        next = new JButton("Next");
        next.setBounds(400,550,200,30);
        next.setBackground(Color.BLUE);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);
        
        back = new JButton("Back");
        back.setBounds(100,550,200,30);
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,700);
        setLocation(350,50);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new EmpLoanApplication().setVisible(true);
        }else if(ae.getSource() == next){
            setVisible(false);
            new EmpBalanceCheckLoan(pinnumber,appno,type).setVisible(true);
        }
    }
    
    
    
    public static void main(String args[]){
        new EmpLoanDisplay("");
    }
}
