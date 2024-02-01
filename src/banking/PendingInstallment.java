package banking;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import java.time.*;
import java.util.Locale;
import java.text.DateFormatSymbols;

public class PendingInstallment extends JFrame implements ActionListener {
    
    String appno,pin,month,pending,amount,accno;
    JButton pay,back;
    PendingInstallment(String appno){
        this.appno=appno;
        setTitle("Pay Installment");
        setLayout(null);
        
        Conn c= new Conn();
        try{
            ResultSet rs=c.s.executeQuery("select * from installment where applicationNumber = '"+appno+"' ");
            if(rs.next()){
                pin=rs.getString("acno");
                month=rs.getString("month");
                pending=rs.getString("pending");
                amount=rs.getString("amount");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        Conn c1=new Conn();
        try{
            ResultSet rs1=c.s.executeQuery("select * from login where Password = '"+pin+"' ");
            if(rs1.next()){
                accno=rs1.getString("AccountNumber");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        JLabel appnoText = new JLabel ("Application Number : "+appno);
        appnoText.setBounds(50,20,500,50);
        appnoText.setFont(new Font("Raleway",Font.BOLD,28));
        add(appnoText);
        
        JLabel amountT = new JLabel ("Installment :Rs.  "+amount);
        amountT.setBounds(50,90,300,30);
        amountT.setFont(new Font("Raleway",Font.PLAIN,24));
        add(amountT);
        
        JLabel monthT = new JLabel ("Next Installment :  "+month);
        monthT.setBounds(50,140,300,30);
        monthT.setFont(new Font("Raleway",Font.PLAIN,24));
        add(monthT);
        
        Date d=new Date();
        String date = ""+d;
        date=date.substring(4,7);
        if(date.equals(month)){
            JLabel payT = new JLabel ("Select Account to Pay");
            payT.setBounds(50,250,300,30);
            payT.setFont(new Font("Raleway",Font.PLAIN,24));
            add(payT);
            
            String array[]={pin};
            JComboBox accnoC=new JComboBox(array);
            accnoC.setBackground(Color.WHITE);
            accnoC.setBounds(300,250,400,30);
            add(accnoC);
            
            pay=new JButton("PAY");
            pay.setBackground(Color.BLUE);
            pay.setForeground(Color.WHITE);
            pay.setBounds(400,370,100,30);
            pay.addActionListener(this);
            add(pay);
        
            
            
        }
        back=new JButton("BACK");
            back.setBackground(Color.RED);
            back.setForeground(Color.WHITE);
            back.setBounds(100,370,100,30);
            back.addActionListener(this);
            add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,700);
        setLocation(400,50);
        setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent ae){
        int balance = 0;
        if(ae.getSource() == back){
            setVisible(false);
            new DispApproveLoan(pin).setVisible(true);
        }else if(ae.getSource() == pay){
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where acno = '"+pin+"'");
                while(rs.next()){
                    balance = Integer.parseInt(rs.getString("balance"));
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
            if(balance >= Double.parseDouble(amount)){
                Conn c2=new Conn();
                Date date=new Date();
                String s= ""+(balance - Double.parseDouble(amount));
                try{
                    String query1 = "insert into bank values ('"+pin+"','"+date+"','Withdrawl','"+amount+"','"+s+"') ";
                    c2.s.executeUpdate(query1);
                }catch(Exception e){
                    System.out.println(e);
                }
                int currentMonthValue = java.time.LocalDate.now().getMonthValue();
                String currentMonthName = new DateFormatSymbols(Locale.ENGLISH).getShortMonths()[currentMonthValue - 1];
                int nextMonthValue = currentMonthValue % 12 + 1;
                String nextMonthName = new DateFormatSymbols(Locale.ENGLISH).getShortMonths()[nextMonthValue - 1];
                Conn c1=new Conn();
                try{
                   String query1 = "update installment set month = '"+nextMonthName+"' where applicationNumber = '"+appno+"' ";
                   c1.s.executeUpdate(query1);
                   int month = Integer.parseInt(pending) - 1;
                   String m=""+month;
                   query1 = "update installment set pending = '"+m+"' where applicationNumber = '"+appno+"' ";
                   c1.s.executeUpdate(query1);
                }catch(Exception e){
                    System.out.println(e);
                }
                JOptionPane.showMessageDialog(null,"Installment Successfuly Paid");
                setVisible(false);
                new DispApproveLoan(pin).setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
            }
        }
    }
    
    
    public static void main(String args[]){
        new PendingInstallment("");
    }
}
