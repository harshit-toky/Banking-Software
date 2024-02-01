package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Passchange extends JFrame implements ActionListener {
    
    JPasswordField otext,ntext,ctext;
    JButton change,clear;
    String pinnumber;
    Passchange(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Password Change");
        setLayout(null);
        
        JLabel text =new JLabel("CHANGE PASSWORD");
        text.setFont(new Font("Raleway",Font.BOLD,38));
        text.setBounds(200,50,600,40);
        add(text);
        
        JLabel text1 =new JLabel("Enter Old Password :");
        text1.setFont(new Font("Raleway",Font.BOLD,16));
        text1.setBounds(50,140,200,40);
        add(text1);
        
        otext=new JPasswordField();
        otext.setBounds(250,145,200,30);
        add(otext);
        
        JLabel text2 =new JLabel("Enter New Password :");
        text2.setFont(new Font("Raleway",Font.BOLD,16));
        text2.setBounds(50,200,400,40);
        add(text2);
        
        ntext=new JPasswordField();
        ntext.setBounds(250,205,200,30);
        add(ntext);
        
        JLabel text3 =new JLabel("Re-Enter New Password :");
        text3.setFont(new Font("Raleway",Font.BOLD,16));
        text3.setBounds(50,260,400,40);
        add(text3);
        
        ctext=new JPasswordField();
        ctext.setBounds(250,265,200,30);
        add(ctext);
        
        change=new JButton("SET");
        change.setBackground(Color.BLUE);
        change.setForeground(Color.WHITE);
        change.setBounds(300,350,100,30);
        change.addActionListener(this);
        add(change);
        
        clear=new JButton("CANCEL");
        clear.setBackground(Color.BLUE);
        clear.setForeground(Color.WHITE);
        clear.setBounds(100,350,100,30);
        clear.addActionListener(this);
        add(clear);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,500);
        setLocation(400,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==change){
            String pin="";
            try{
                Conn c=new Conn();
                String query="select * from login where accountNumber = '"+pinnumber+"' ";
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    pin = rs.getString("Password");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            try{
                String oldtext=otext.getText();
                String newtext=ntext.getText();
                String retext=ctext.getText();
                if(oldtext.equals(pin)){
                    if(newtext.equals(retext)){
                        Conn conn=new Conn();
                        String query2="update login set Password ='"+newtext+"' where Password = '"+pin+"' ";
                        String query3="update signupthree set Password ='"+newtext+"' where Password = '"+pin+"' ";
                        
                        conn.s.executeUpdate(query2);
                        conn.s.executeUpdate(query3);
                        JOptionPane.showMessageDialog(null,"Password Changed Successfuly");
                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Re-Enter Password is not Matching");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Enter Correct Old Password");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()==clear){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Passchange("");
    } 

}
