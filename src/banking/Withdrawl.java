package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField amount;
    JButton deposit,back,clear;
    String pinnumber;
    
    Withdrawl(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Withdrawl Page");
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
        
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/wallet2.png")) ;
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
        Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where acno = '"+pinnumber+"'");
                while(rs.next()){
                    balance = Integer.parseInt(rs.getString("balance"));
                }
            }catch(Exception e){
                System.out.println(e);
            }
        if(ae.getSource()==deposit){
            String number=amount.getText();
            Date date=new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter Amount");
     
            }else if(balance >= Double.parseDouble(number)){
                try{
                    String value = ""+(balance-Integer.parseInt(number));
                    Conn conn=new Conn();
                    
                    String query = "insert into bank values('"+pinnumber+"' , '"+date+"' , 'Withdrawl' , '"+number+"' , '"+value+"' )";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs. "+number+" Withdraw Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }catch(Exception e){
                    System.out.println(e);
                }    
            }else{
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
            }
        }else if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else if(ae.getSource() == clear){
            amount.setText("");
        }
            
    }       
    
    public static void main(String args[]){
        new Withdrawl("");
    }
}
