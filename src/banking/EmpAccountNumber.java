
package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmpAccountNumber  extends JFrame implements ActionListener{
    JTextField amount;
    JButton deposit,back;
    EmpAccountNumber(){
        setTitle("Account Number");
        setLayout(null);
        
        JLabel text=new JLabel("Enter User Account Number");
        text.setFont(new Font("Raleway",Font.PLAIN,38));
        text.setBounds(150,100,600,40);
        add(text);
        
        amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,28));
        amount.setBounds(200,190,320,50);
        add(amount);
        
        deposit=new JButton("Next");
        deposit.setBounds(180,290,100,30);
        deposit.setBackground(Color.BLUE);
        deposit.setForeground(Color.WHITE);
        deposit.addActionListener(this);
        add(deposit);
        
        back=new JButton("Back");
        back.setBounds(460,290,100,30);
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,500);
        setLocation(400,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new EmployeeTransactions().setVisible(true);
        }else if(ae.getSource() == deposit){
            String accno = amount.getText();
            Conn c=new Conn();
            try{
                ResultSet rs  = c.s.executeQuery("select * from login where AccountNumber = '"+accno+"' ");
                if(rs.next()){
                    setVisible(false);
                    new EmpDispUser(accno).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Enter Correct Account Number");
                }
                    
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    }
    public static void main(String args[]){
        new EmpAccountNumber();
    }
}
