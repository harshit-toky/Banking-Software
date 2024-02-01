package banking;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class EmpLoanApplication extends JFrame implements ActionListener {
    JButton ob[]=new JButton[50],back;
    int i=0;
    
    EmpLoanApplication() {
        
        setLayout(null);
        setTitle("Pending Loan Application");
        
        JLabel text = new JLabel("Pending Loan Application");
        text.setBounds(160,50,500,50);
        text.setFont(new Font("Raleway",Font.BOLD,38));
        add(text);
        Conn c=new Conn();
        try{
            ResultSet rs=c.s.executeQuery("select * from loan where status = 'No' ");
            while(rs.next()){
                ob[i] = new JButton();
                ob[i].setText("Loan Application Number " +rs.getString("applicationNumber"));
                ob[i].setForeground(Color.WHITE);
                ob[i].setBackground(Color.BLUE);
                ob[i].setBounds(100,120 + 60 *i , 300 ,40);
                ob[i].addActionListener(this);
                add(ob[i]);
                i=i+1;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        back=new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(Color.RED);
        back.setBounds(500,160 + 60 *i , 200 ,40);
        back.addActionListener(this);
        add(back);
        
        
        getContentPane().setBackground(Color.WHITE);
        setSize(800,700);
        setVisible(true);
        setLocation(350,50);

    }
    public void actionPerformed(ActionEvent ae){
        int j;
        for(j=0;j<i;j++){
            if(ae.getSource() == ob[j]){
                setVisible(false);
                new EmpLoanDisplay(ob[j].getText()).setVisible(true);
            }
        }
        if(ae.getSource()==back){
            setVisible(false);
            new EmployeeTransactions().setVisible(true);
        }
    }
    
    
    public static void main(String args[]){
        new EmpLoanApplication();
    }
}
