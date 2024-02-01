package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Deposit1 extends JFrame implements ActionListener{
    
    JTextField amount;
    JButton deposit,back,clear;
    String pinnumber,formno,type;
    
    Deposit1(String formno,String pinnumber){
        this.pinnumber=pinnumber;
        this.formno=formno;
        setTitle("Money Deposit Page");
        setLayout(null);
        
        JLabel text=new JLabel("Enter Opening Amount");
        text.setFont(new Font("Raleway",Font.PLAIN,38));
        text.setBounds(220,100,400,50);
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
        
        Conn c=new Conn();
        try{
          ResultSet rs1=c.s.executeQuery("select * from signupthree where formno = '"+formno+"' ");
               if(rs1.next()){
                        type=rs1.getString("accountType");
                }
        }catch(Exception e){
            System.out.println(e);
        }
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(800,500);
        setLocation(400,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==deposit){
            String number=amount.getText();
            Date date=new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter Amount");
     
            }else{
                if(type.equals("Saving Account")){
                    if(Integer.parseInt(number)<100){
                        JOptionPane.showMessageDialog(null, "Amount should be greater than Rs.100");
                    }else{
                        try{
                            Conn conn=new Conn();
                            String query = "insert into bank values('"+pinnumber+"' , '"+date+"' , 'Deposit' , '"+number+"' ,'"+number+"' )";
                            conn.s.executeUpdate(query);
                            JOptionPane.showMessageDialog(null,"Rs. "+number+" Deposited Successfully");
                            setVisible(false);
                            new Banking().setVisible(true);
                        }catch(Exception e){
                            System.out.println(e);
                        }    
                    }
                }else{
                    if(Integer.parseInt(number)<5000 || Integer.parseInt(number)>=50000){
                        JOptionPane.showMessageDialog(null, "Amount should be greater than Rs.5000 and less than Rs.50000");
                    }else{
                        try{
                            Conn conn=new Conn();
                            String query = "insert into bank values('"+pinnumber+"' , '"+date+"' , 'Deposit' , '"+number+"','"+number+"' )";
                            conn.s.executeUpdate(query);
                            JOptionPane.showMessageDialog(null,"Rs. "+number+" Deposited Successfully");
                            setVisible(false);
                            new Banking().setVisible(true);
                        }catch(Exception e){
                            System.out.println(e);
                        }    
                    }
                }
            }
        }else if(ae.getSource()==back){
            setVisible(false);
            new Banking().setVisible(true);
        }else if(ae.getSource() == clear){
            amount.setText("");
        }
            
    }       
    
    public static void main(String args[]){
        new Deposit("");
    }
}
