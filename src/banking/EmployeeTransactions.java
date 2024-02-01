package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class EmployeeTransactions extends JFrame implements ActionListener {
    JButton logout,deposit,loan;
    EmployeeTransactions(){
        setLayout(null);
        setTitle("Employee Transactions");
        
        JLabel text =new JLabel("Select Transaction");
        text.setBounds(100,55,700,40);
        text.setFont(new Font("System",Font.BOLD,38));
        add(text);
        
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/logout.png")) ;
        Image I2=I1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon I3=new ImageIcon(I2);
        
        logout=new JButton(I3);
        logout.setBounds(600,55,100,50);
        logout.setBackground(new Color(0x87CEEB));
        logout.setBorderPainted(false);
        logout.addActionListener(this);
        add(logout);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/bank-account.png")) ;
        I2=I1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        deposit=new JButton(I3);
        deposit.setBounds(100,150,90,90);
        deposit.setBackground(new Color(0x87CEEB));
        deposit.setBorderPainted(false);
        deposit.addActionListener(this);
        add(deposit);
        
        text =new JLabel("Account Information");
        text.setBounds(100,260,300,40);
        text.setFont(new Font("System",Font.BOLD,20));
        add(text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/loan-approve.png")) ;
        I2=I1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        loan=new JButton(I3);
        loan.setBounds(100,320,90,90);
        loan.setBackground(new Color(0x87CEEB));
        loan.setBorderPainted(false);
        loan.addActionListener(this);
        add(loan);
        
        text =new JLabel("Loan Approve");
        text.setBounds(100,420,300,40);
        text.setFont(new Font("System",Font.BOLD,20));
        add(text);
        
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == logout){
            JOptionPane.showMessageDialog(null, "Logout Successfully");
            setVisible(false);
            new Banking().setVisible(true);
        }else if(ae.getSource()==deposit){
            setVisible(false);
            new EmpAccountNumber();
            
        }else if(ae.getSource() == loan){
            setVisible(false);
            new EmpLoanApplication().setVisible(true);
        }
    }
    public static void main(String args[]){
        new EmployeeTransactions();
    }
}
