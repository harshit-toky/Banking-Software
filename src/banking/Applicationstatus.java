package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Applicationstatus extends JFrame implements ActionListener{
    
    JTextField amount;
    JButton deposit,back;
    String pinnumber,type,formno;
    
    Applicationstatus(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Application Status");
        setLayout(null);
        
        JLabel text=new JLabel("Enter Application Number");
        text.setFont(new Font("Raleway",Font.PLAIN,38));
        text.setBounds(150,100,500,40);
        add(text);
        
        
        amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,28));
        amount.setBounds(200,190,320,50);
        add(amount);
        
        deposit=new JButton("Submit");
        deposit.setBounds(200,290,100,30);
        deposit.addActionListener(this);
        add(deposit);
        
        back=new JButton("Back");
        back.setBounds(420,290,100,30);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,500);
        setLocation(400,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        String app="",stat="";
        if(ae.getSource()==deposit){
            app=amount.getText();
            if(app.equals("")){
                JOptionPane.showMessageDialog(null, "Enter Application Number");
            }else{
                Conn c=new Conn();
                try{
                    ResultSet rs=c.s.executeQuery("select * from loan where applicationNumber = '"+app+"' ");
                    if(rs.next()){
                        stat = rs.getString("status");
                    }
                    if(stat.equals("")){
                        JOptionPane.showMessageDialog(null, "Incorrect Application Number");
                    }else if(stat.equals("No")){
                        JOptionPane.showMessageDialog(null, "Application in Queue");
                    }else if(stat.equals("Approved")){
                        JOptionPane.showMessageDialog(null, "Application is Accepted");
                    }else if(stat.equals("Rejected")){
                        JOptionPane.showMessageDialog(null, "Application is Rejected");
                    }
                    amount.setText("");
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }else if(ae.getSource()==back){
            setVisible(false);
            new Loanservices(pinnumber).setVisible(true);
        }
            
    }       
    
    public static void main(String args[]){
        new Applicationstatus("");
    }
}
