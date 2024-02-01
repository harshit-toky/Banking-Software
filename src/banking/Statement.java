package banking;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Statement extends JFrame implements ActionListener {
    
    String pinnumber;
    JButton back;
    
    Statement(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        setTitle("Monthly Statement");
        
        
        
        JLabel bank= new JLabel("Monthly Statement");
        bank.setFont(new Font("Raleway",Font.BOLD,30));
        bank.setBounds(250,50,500,40);
        add(bank);
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/back.png")) ;
        Image I2=I1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon I3=new ImageIcon(I2);
        
        back=new JButton(I3);
        back.setBounds(50,55,30,30);
        back.setBorderPainted(false);
        back.setBackground(new Color(0x87CEEB));
        back.addActionListener(this);
        add(back);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        int balance=0,i=0;
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from bank where acno ='"+pinnumber+"' ");
            while(rs.next()){
                
                
                JLabel text=new JLabel();
                text.setText("<html>" + rs.getString("date") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("type") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +rs.getString("amount"));
                text.setBounds(50,80+(i*40),600,100);
                text.setFont(new Font("Raleway",Font.PLAIN,20));
                panel.add(text);
                //add(text);
                i++;          
                
                balance = Integer.parseInt(rs.getString("balance"));
          
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 120, 600, 500);
        add(scrollPane);
        
        JLabel showbal=new JLabel("Net Balance :Rs. "+balance);
        showbal.setFont(new Font("Raleway",Font.BOLD,18));
        showbal.setBounds(50,650,600,40);
        add(showbal);
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(800,800);
        setLocation(400,10);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]){
        new Statement("");
    }
}
