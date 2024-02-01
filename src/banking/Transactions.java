package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener {
    
    JButton logout,deposit,withdrawl,statement,pinchange,balanceenquiry,loanservices;
    String pinnumber,name,formno;
    Transactions(String pinnumber ){
        this.pinnumber=pinnumber;
        setLayout(null);
        setTitle("Customer Transactions");
        Conn c=new Conn();
        try{
            ResultSet rs=c.s.executeQuery("select * from login where AccountNumber = '"+pinnumber+"'");
            if (rs.next()) {
                formno = rs.getString("formno");
            }
            ResultSet rs1=c.s.executeQuery("select * from signup where formno = '"+formno+"'");
            if (rs1.next()) {
                    name = rs1.getString("name");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        JLabel text1 =new JLabel("Welcome "+name);
        text1.setBounds(70,50,700,35);
        text1.setFont(new Font("System",Font.BOLD,20));
        add(text1);
        
        JLabel text =new JLabel("Select Transaction");
        text.setBounds(70,110,700,35);
        text.setFont(new Font("System",Font.BOLD,38));
        text.setForeground(new Color(0xC75600));
        add(text);
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/logout.png")) ;
        Image I2=I1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon I3=new ImageIcon(I2);
        
        logout=new JButton(I3);
        logout.setBounds(600,85,50,50);
        logout.setBackground(new Color(0x87CEEB));
        logout.setBorderPainted(false);
        logout.addActionListener(this);
        add(logout);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/deposit.png")) ;
        I2=I1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        deposit=new JButton(I3);
        deposit.setBounds(70,180,100,100);
        deposit.setBackground(new Color(0x87CEEB));
        deposit.setBorderPainted(false);
        deposit.addActionListener(this);
        add(deposit);
        
        JLabel deposit_text = new JLabel("Deposit");
        deposit_text.setBounds(75,290,200,60);
        deposit_text.setFont(new Font("System",Font.BOLD,22));
        deposit_text.setForeground(new Color(0x0600A8));
        add(deposit_text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/withdraw.png")) ;
        I2=I1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        withdrawl=new JButton(I3);
        withdrawl.setBounds(460,195,100,100);
        withdrawl.setBackground(new Color(0x87CEEB));
        withdrawl.setBorderPainted(false);
        withdrawl.addActionListener(this);
        add(withdrawl);
        
        deposit_text = new JLabel("Withdraw");
        deposit_text.setBounds(460,290,200,60);
        deposit_text.setFont(new Font("System",Font.BOLD,22));
        deposit_text.setForeground(new Color(0x0600A8));
        add(deposit_text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/bank-statement.png")) ;
        I2=I1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        statement=new JButton(I3);
        statement.setBounds(70,385,100,100);
        statement.setBackground(new Color(0x87CEEB));
        statement.setBorderPainted(false);
        statement.addActionListener(this);
        add(statement);
        
        deposit_text = new JLabel("Statement");
        deposit_text.setBounds(70,495,200,60);
        deposit_text.setFont(new Font("System",Font.BOLD,22));
        deposit_text.setForeground(new Color(0x0600A8));
        add(deposit_text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/reset-password.png")) ;
        I2=I1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        pinchange=new JButton(I3);
        pinchange.setBounds(460,385,100,100);
        pinchange.setBackground(new Color(0x87CEEB));
        pinchange.setBorderPainted(false);
        pinchange.addActionListener(this);
        add(pinchange);
        
        deposit_text = new JLabel("Reset Password");
        deposit_text.setBounds(440,495,200,60);
        deposit_text.setFont(new Font("System",Font.BOLD,22));
        deposit_text.setForeground(new Color(0x0600A8));
        add(deposit_text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/wallet.png")) ;
        I2=I1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        balanceenquiry=new JButton(I3);
        balanceenquiry.setBounds(75,580,100,100);
        balanceenquiry.setBackground(new Color(0x87CEEB));
        balanceenquiry.setBorderPainted(false);
        balanceenquiry.addActionListener(this);
        add(balanceenquiry);
        
        deposit_text = new JLabel("Balance");
        deposit_text.setBounds(75,690,200,60);
        deposit_text.setFont(new Font("System",Font.BOLD,22));
        deposit_text.setForeground(new Color(0x0600A8));
        add(deposit_text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/bank-loan.png")) ;
        I2=I1.getImage().getScaledInstance(110,110,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        loanservices=new JButton(I3);
        loanservices.setBounds(460,580,110,110);
        loanservices.setBackground(new Color(0x87CEEB));
        loanservices.setBorderPainted(false);
        loanservices.addActionListener(this);
        add(loanservices);
        
        deposit_text = new JLabel("Loan");
        deposit_text.setBounds(475,690,200,60);
        deposit_text.setFont(new Font("System",Font.BOLD,22));
        deposit_text.setForeground(new Color(0x0600A8));
        add(deposit_text);
        
        new Interest(pinnumber);
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(850,810);
        setLocation(350,7);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==logout){
            JOptionPane.showMessageDialog(null,"Successfully Logout");
            setVisible(false);
            new Banking();
        }else if(ae.getSource()==deposit){
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        }else if(ae.getSource()==withdrawl){
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
        }else if(ae.getSource()==pinchange){
            setVisible(false);
            new Passchange(pinnumber).setVisible(true);
        }else if(ae.getSource()==balanceenquiry){
            setVisible(false);
            new Balanceenquiry(pinnumber).setVisible(true);
        }else if(ae.getSource()==statement){
            setVisible(false);
            new Statement(pinnumber).setVisible(true);
        }else if(ae.getSource() == loanservices){
            setVisible(false);
            new Loanservices(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Transactions("");
    }
}
