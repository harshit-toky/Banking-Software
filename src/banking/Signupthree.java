package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class Signupthree extends JFrame implements ActionListener{
    
    JRadioButton r1,r2;
    JCheckBox c1,c2,c3,c4;
    JButton submit,cancel;
    String formno;
    Signupthree(String formno){
        this.formno=formno;
        setLayout(null);
        
        JLabel l1=new JLabel("Page 3 : Account Details");
        l1.setFont(new Font("Raleway",Font.BOLD,22));
        l1.setBounds(280,40,400,40);
        add(l1);
        
        JLabel type=new JLabel("Account Type");
        type.setFont(new Font("Raleway",Font.BOLD,22));
        type.setBounds(100,140,250,30);
        add(type);
        
        r1=new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway",Font.BOLD,16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100,180,250,20);
        add(r1);
        
        r2=new JRadioButton("Current Account");
        r2.setFont(new Font("Raleway",Font.BOLD,16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(370,180,250,20);
        add(r2);
        
        ButtonGroup groupaccount=new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        
        JLabel acno=new JLabel("Account Number");
        acno.setFont(new Font("Raleway",Font.BOLD,22));
        acno.setBounds(100,250,200,30);
        add(acno);
        
        JLabel no=new JLabel("XXXXXXXXXX");
        no.setFont(new Font("Raleway",Font.BOLD,22));
        no.setBounds(330,250,300,30);
        add(no);
        
        JLabel detail=new JLabel("Your 10 Digit Account Number");
        detail.setFont(new Font("Raleway",Font.BOLD,12));
        detail.setBounds(100,290,300,20);
        add(detail);
        
        JLabel pass=new JLabel("Password");
        pass.setFont(new Font("Raleway",Font.BOLD,22));
        pass.setBounds(100,350,200,30);
        add(pass);
        
        JLabel passno=new JLabel("XXXX");
        passno.setFont(new Font("Raleway",Font.BOLD,22));
        passno.setBounds(330,350,300,30);
        add(passno);
        
        JLabel pdetail=new JLabel("Your 4 Digit Password");
        pdetail.setFont(new Font("Raleway",Font.BOLD,12));
        pdetail.setBounds(100,390,300,20);
        add(pdetail);
        
        JLabel services=new JLabel("Services Required :");
        services.setFont(new Font("Raleway",Font.BOLD,22));
        services.setBounds(100,450,300,30);
        add(services);
        
        c1=new JCheckBox("Debit Card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(100,500,200,30);
        add(c1);
        
        c2=new JCheckBox("SMS Alerts");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(100,550,200,30);
        add(c2);
        
        c3=new JCheckBox("Net Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(100,600,200,30);
        add(c3);
        
        c4=new JCheckBox("I hereby declares that the above entered details are correct to the best of my knowledge");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway",Font.BOLD,12));
        c4.setBounds(100,650,600,30);
        add(c4);
        
        submit=new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway",Font.BOLD,14));
        submit.setBounds(250,720,100,30);
        submit.addActionListener(this);
        add(submit);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBounds(420,720,100,30);
        cancel.addActionListener(this);
        add(cancel);
        
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850,820);
        setLocation(350,0);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            String accountType="";
            if(r1.isSelected()){
                accountType="Saving Account";
            }else if(r2.isSelected()){
                accountType="Current Account";
            }
            
            Random random = new Random();
            long randomNumber = (long) (random.nextDouble() * 9000000000L + 1000000000L);
            
            Random random1 = new Random();
            int randomNumber1 = random1.nextInt(9000) + 1000;
            
            Conn conn1=new Conn();
            try{
                ResultSet rs1=conn1.s.executeQuery("select * from signupthree where accountNumber = '"+randomNumber+"' ");
                if(rs1.next()){
                    random = new Random();
                    randomNumber = (long) (random.nextDouble() * 9000000000L + 1000000000L);
                }
                ResultSet rs2=conn1.s.executeQuery("select * from signupthree where Password = '"+randomNumber1+"' ");
                if(rs2.next()){
                    random1 = new Random();
                    randomNumber1 = random1.nextInt(9000) + 1000;
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
            String ran =""+randomNumber1;
            
            String facility="";
            if(c1.isSelected()){
                facility = facility + " Debit Card";
            }if(c2.isSelected()){
                facility= facility+ " SMS Alerts";
            }if(c3.isSelected()){
                facility= facility+ " Net Banking";
            }
            if(c4.isSelected()){
                try{
                    if(accountType.equals("")){
                        JOptionPane.showMessageDialog(null,"Account Type is Required");
                    }else{
                        Conn conn=new Conn();
                        String query1= "insert into signupthree values('"+formno+"', '"+accountType+"', '"+randomNumber+"', '"+randomNumber1+"', '"+facility+"')";
                        String query2= "insert into login values('"+formno+"', '"+randomNumber+"', '"+randomNumber1+"')";

                        conn.s.executeUpdate(query1);
                        conn.s.executeUpdate(query2);

                        JOptionPane.showMessageDialog(null, "Account Number :" + randomNumber +"\n" + "Password :" + randomNumber1);

                        setVisible(false);
                        new Deposit1(formno,""+randomNumber).setVisible(true);
                    }

                }catch(Exception e){
                    System.out.println(e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Declaration Checkbox is Required");
            }
        }else if(ae.getSource()==cancel){
            setVisible(false);
            new Banking().setVisible(true);
        }
        
    }
    
    public static void main(String args[]){
        new Signupthree("");
    }
}
