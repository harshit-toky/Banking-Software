package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener{
    
    JTextField amount;
    JButton deposit,back,clear;
    String pinnumber,type,formno;
    
    Deposit(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Money Deposit Page");
        setLayout(null);
        
        JLabel text=new JLabel("Enter Amount");
        text.setFont(new Font("Raleway",Font.PLAIN,38));
        text.setBounds(280,100,400,40);
        add(text);
        
        JLabel rs=new JLabel("Rs.");
        rs.setFont(new Font("Raleway",Font.PLAIN,38));
        rs.setBounds(150,190,100,50);
        add(rs);
        
        amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,28));
        amount.setBounds(240,190,320,50);
        add(amount);
        
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/wallet1.png")) ;
        Image I2=I1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon I3=new ImageIcon(I2);
        
        deposit=new JButton(I3);
        deposit.setBounds(500,290,50,50);
        deposit.setBackground(new Color(0x87CEEB));
        deposit.setBorderPainted(false);
        deposit.addActionListener(this);
        add(deposit);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/back.png")) ;
        I2=I1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        back=new JButton(I3);
        back.setBounds(105,105,30,30);
        back.setBackground(new Color(0x87CEEB));
        back.setBorderPainted(false);
        back.addActionListener(this);
        add(back);
        
        clear=new JButton("Clear");
        clear.setBounds(240,290,100,50);
        clear.setBackground(new Color(0x87CEEB));
        clear.setFont(new Font("Raleway",Font.PLAIN,20));
        clear.setBorderPainted(false);
        clear.addActionListener(this);
        add(clear);
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(800,500);
        setLocation(400,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        int balance = 0;
        
        if(ae.getSource()==deposit){
            String number=amount.getText();
            Date date=new Date();
            
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where acno = '"+pinnumber+"'");
                while(rs.next()){
                    balance = Integer.parseInt(rs.getString("balance"));
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
            Conn c1=new Conn();
            try{
              ResultSet rs1=c1.s.executeQuery("select * from login where AccountNumber = '"+pinnumber+"' ");
                   if(rs1.next()){
                            formno=rs1.getString("formno");
                    }
            }catch(Exception e){
                System.out.println(e);
            }
            
            Conn c2=new Conn();
            try{
              ResultSet rs2=c2.s.executeQuery("select * from signupthree where formno = '"+formno+"' ");
                   if(rs2.next()){
                            type=rs2.getString("accountType");
                    }
            }catch(Exception e){
                System.out.println(e);
            }
            
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter Amount");
     
            }else if("Current Account".equals(type) && Integer.parseInt(number) + balance >50000){
                JOptionPane.showMessageDialog(null, "Maximum Balance should be less than Rs.50000");
            }
            else{
                try{
                    String value = ""+(Integer.parseInt(number)+balance);
                    Conn conn=new Conn();
                    String query = "insert into bank values('"+pinnumber+"' , '"+date+"' , 'Deposit' , '"+number+"' , '"+value+"' )";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs. "+number+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }catch(Exception e){
                    System.out.println(e);
                }    
            }
        }else if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else if(ae.getSource() == clear){
            amount.setText("");
        }
            
    }       
    
    public static void main(String args[]){
        new Deposit("");
    }
}
