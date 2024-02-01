package banking;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DecimalFormat;

public class EmpBalanceCheckLoan extends JFrame implements ActionListener {
    
    int balance=0;
    String date;
    JComboBox text,text1;
    JLabel bal;
    String pinnumber,type,appno;
    JButton submit,approve1,reject,back;
    JCheckBox c1,c2,c3,c4,c5,c6;
    EmpBalanceCheckLoan(String pinnumber,String appno,String type){
        setTitle("Loan Approval Page");
        setLayout(null);
        
        this.pinnumber=pinnumber;
        this.type=type;
        this.appno=appno;
        JLabel approve = new JLabel("APPROVE LOAN");
            approve.setBounds(220,20,600,50);
            approve.setFont(new Font("Raleway",Font.BOLD,38));
            approve.setForeground(Color.RED);
            add(approve);
        
        if(type.equals("Car Loan")){
            JLabel monthText = new JLabel("Select Month :");
            monthText.setBounds(30,100,200,50);
            monthText.setFont(new Font("Raleway",Font.BOLD,28));
            
            add(monthText);
            
            String months[] = {"Jan" , "Feb" ,"Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec" };
            text = new JComboBox(months);
            text.setBackground(Color.WHITE);
            text.setBounds(250,110,400,30);
            text.addActionListener(this);
            add(text);
            
            JLabel yearText = new JLabel("Select Year :");
            yearText.setBounds(30,150,200,50);
            yearText.setFont(new Font("Raleway",Font.BOLD,28));
            
            add(yearText);
            
            String years[] = {"2022" , "2023" ,"2024" };
            text1 = new JComboBox(years);
            text1.setBackground(Color.WHITE);
            text1.setBounds(250,160,400,30);
            text1.addActionListener(this);
            add(text1);
            
            submit=new JButton("Submit");
            submit.setBounds(450,220,200,30);
            submit.addActionListener(this);
            add(submit);
            
            bal =new JLabel("Balance is :Rs. ");
            bal.setFont(new Font("Raleway",Font.PLAIN,22));
            bal.setBounds(30,250,400,50);
            add(bal);
            
            JLabel monthText1 = new JLabel("Select Checkbox for last 6 months Balance(More Than Rs.20000) :");
            monthText1.setBounds(30,300,650,50);
            monthText1.setFont(new Font("Raleway",Font.BOLD,20));
            add(monthText1);
            
            c1=new JCheckBox("Month 1");
            c1.setBounds(30,350,200,30);
            c1.setBackground(Color.WHITE);
            add(c1);
            
            c2=new JCheckBox("Month 2");
            c2.setBounds(30,400,200,30);
            c2.setBackground(Color.WHITE);
            add(c2);
            
            c3=new JCheckBox("Month 3");
            c3.setBounds(30,450,200,30);
            c3.setBackground(Color.WHITE);
            add(c3);
            
            c4=new JCheckBox("Month 4");
            c4.setBounds(230,350,200,30);
            c4.setBackground(Color.WHITE);
            add(c4);
            
            c5=new JCheckBox("Month 5");
            c5.setBounds(230,400,200,30);
            c5.setBackground(Color.WHITE);
            add(c5);
            
            c6=new JCheckBox("Month 6");
            c6.setBounds(230,450,200,30);
            c6.setBackground(Color.WHITE);
            add(c6);
    
        }else if(type.equals("Two-Wheeler Loan")){
            JLabel monthText = new JLabel("Select Month :");
            monthText.setBounds(30,100,200,50);
            monthText.setFont(new Font("Raleway",Font.BOLD,28));
            
            add(monthText);
            
            String months[] = {"Jan" , "Feb" ,"Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec" };
            text = new JComboBox(months);
            text.setBackground(Color.WHITE);
            text.setBounds(250,110,400,30);
            text.addActionListener(this);
            add(text);
            
            JLabel yearText = new JLabel("Select Year :");
            yearText.setBounds(30,150,200,50);
            yearText.setFont(new Font("Raleway",Font.BOLD,28));
            
            add(yearText);
            
            String years[] = {"2022" , "2023" ,"2024" };
            text1 = new JComboBox(years);
            text1.setBackground(Color.WHITE);
            text1.setBounds(250,160,400,30);
            text1.addActionListener(this);
            add(text1);
            
            submit=new JButton("Submit");
            submit.setBounds(450,220,200,30);
            submit.addActionListener(this);
            add(submit);
            
            bal =new JLabel("Balance is :Rs. ");
            bal.setFont(new Font("Raleway",Font.PLAIN,22));
            bal.setBounds(30,250,400,50);
            add(bal);
            
            JLabel monthText1 = new JLabel("Select Checkbox for last 6 months Balance(More Than Rs.5000) :");
            monthText1.setBounds(30,300,650,50);
            monthText1.setFont(new Font("Raleway",Font.BOLD,20));
            add(monthText1);
            
            c1=new JCheckBox("Month 1");
            c1.setBounds(30,350,200,30);
            c1.setBackground(Color.WHITE);
            add(c1);
            
            c2=new JCheckBox("Month 2");
            c2.setBounds(30,400,200,30);
            c2.setBackground(Color.WHITE);
            add(c2);
            
            c3=new JCheckBox("Month 3");
            c3.setBounds(30,450,200,30);
            c3.setBackground(Color.WHITE);
            add(c3);
            
            c4=new JCheckBox("Month 4");
            c4.setBounds(230,350,200,30);
            c4.setBackground(Color.WHITE);
            add(c4);
            
            c5=new JCheckBox("Month 5");
            c5.setBounds(230,400,200,30);
            c5.setBackground(Color.WHITE);
            add(c5);
            
            c6=new JCheckBox("Month 6");
            c6.setBounds(230,450,200,30);
            c6.setBackground(Color.WHITE);
            add(c6);
        }else if(type.equals("Home Loan")){
            String salary="",amount="";
            Conn conn=new Conn();
            try{
                ResultSet rs=conn.s.executeQuery("select * from loan where applicationNumber = '"+appno+"' ");
                if(rs.next()){
                    salary=rs.getString("salary");
                    amount=rs.getString("amount");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            JLabel amountT = new JLabel("Amount asked for :"+amount);
            amountT.setBounds(30,100,600,50);
            amountT.setFont(new Font("Raleway",Font.BOLD,25));
            add(amountT);
            
            JLabel salaryT = new JLabel("Per-Month Salary :"+salary);
            salaryT.setBounds(30,150,260000,50);
            salaryT.setFont(new Font("Raleway",Font.BOLD,25));
            add(salaryT);
            double calculatedAmount = 0.5 * Integer.parseInt(salary) * 12 * 15;
            String formattedAmount = String.format("%.2f", calculatedAmount);
            JLabel salaryT1 = new JLabel("Amount that can be Issued: " + formattedAmount);
            salaryT1.setBounds(30,200,260000,50);
            salaryT1.setFont(new Font("Raleway",Font.BOLD,25));
            add(salaryT1);
        }else if(type.equals("Personal Loan")){
            String salary="",amount="";
            Conn conn=new Conn();
            try{
                ResultSet rs=conn.s.executeQuery("select * from loan where applicationNumber = '"+appno+"' ");
                if(rs.next()){
                    salary=rs.getString("salary");
                    amount=rs.getString("amount");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            JLabel amountT = new JLabel("Amount asked for :"+amount);
            amountT.setBounds(30,100,600,50);
            amountT.setFont(new Font("Raleway",Font.BOLD,25));
            add(amountT);
            
            JLabel salaryT = new JLabel("Per-Month Salary :"+salary);
            salaryT.setBounds(30,150,260000,50);
            salaryT.setFont(new Font("Raleway",Font.BOLD,25));
            add(salaryT);
            double calculatedAmount = 0.2 * Integer.parseInt(salary) * 12 * 15;
            String formattedAmount = String.format("%.2f", calculatedAmount);
            JLabel salaryT1 = new JLabel("Amount that can be Issued: " + formattedAmount);
            
            salaryT1.setBounds(30,200,260000,50);
            salaryT1.setFont(new Font("Raleway",Font.BOLD,25));
            add(salaryT1);
        }
        
        approve1=new JButton("Approve");
            approve1.setBounds(450,500,200,30);
            approve1.setBackground(Color.BLUE);
            approve1.setForeground(Color.WHITE);
            approve1.addActionListener(this);
            add(approve1);
            
            reject=new JButton("Reject");
            reject.setBounds(100,500,200,30);
            
            reject.setBackground(Color.BLUE);
            reject.setForeground(Color.WHITE);
            reject.addActionListener(this);
            add(reject);
            
            back=new JButton("Back");
            back.setBounds(100,580,200,30);
            back.setBackground(Color.RED);
            back.setForeground(Color.WHITE);
            back.addActionListener(this);
            add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,700);
        setLocation(350,50);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(type.equals("Car Loan") || type.equals("Two-Wheeler Loan")){
            if(ae.getSource() == submit ){
                Conn c= new Conn();
                try{
                    ResultSet rs = c.s.executeQuery("select * from bank where acno = '"+pinnumber+"' ");
                    while(rs.next()){
                        date = rs.getString("date");
                        if(date.indexOf((String)text.getSelectedItem() )!=-1 && date.indexOf((String)text1.getSelectedItem() )!=-1){
                            balance = Integer.parseInt(rs.getString("balance"));
                        }
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                bal.setText("Balance is :Rs. "+balance);
                balance = 0;
            }
        }
        boolean ok = false;
        if(type.equals("Car Loan") || type.equals("Two-Wheeler Loan")){
            if(c1.isSelected() && c2.isSelected() &&c3.isSelected() &&c4.isSelected() &&c5.isSelected() &&c6.isSelected() ){
                ok = true;
            }
        }
        if(ae.getSource()==approve1 && (type.equals("Car Loan") || type.equals("Two-Wheeler Loan") )){
            if(ok){    
                String amount="",roi="",tenure="";
                double installment;
                int balance=0;
                JOptionPane.showMessageDialog(null,"Loan is Approved");
                String query1 = "update loan set status = 'Approved' where applicationNumber = '"+appno+"' ";
                Conn c=new Conn();
                try{
                   c.s.executeUpdate(query1);
                   ResultSet rs =c.s.executeQuery("select * from loan where applicationNumber ='"+appno+"' ");
                   if(rs.next()){
                       amount = rs.getString("amount");
                       roi=rs.getString("roi");
                       tenure=rs.getString("tenure");
                   }
                }catch(Exception e){
                    System.out.println(e);
                }
                Conn c1=new Conn();
                try{
                   query1= "select * from bank where acno ='"+pinnumber+"' " ;
                   ResultSet r = c1.s.executeQuery(query1);
                   while(r.next()){
                       balance = Integer.parseInt(r.getString("balance"));
                   }
                }catch(Exception e){
                    System.out.println(e);
                }
                Date date = new Date();
                String s = "Loan Approved" + appno;
                String s1= "" + (balance + Integer.parseInt(amount));
                query1="insert into bank values ('"+pinnumber+"' ,'"+date+"' ,'"+s+"' ,'"+amount+"' , '"+s1+"' )";
                try{
                    c.s.executeUpdate(query1);
                }catch(Exception e){
                    System.out.println(e);
                }

                installment = Double.parseDouble(amount) * (Double.parseDouble(roi) / 12 / 100) * Math.pow(1 + (Double.parseDouble(roi) / 12 / 100), Integer.parseInt(tenure)) / (Math.pow(1 + (Double.parseDouble(roi) / 12 / 100), Integer.parseInt(tenure)) - 1);
                DecimalFormat df = new DecimalFormat("#.##");
                double formattedInstallment = Double.parseDouble(df.format(installment));

                Conn c2=new Conn();
                try{
                    query1="update loan set installment = '"+formattedInstallment+"' where applicationNumber = '"+appno+"' ";
                    c2.s.executeUpdate(query1);
                }catch(Exception e){
                    System.out.println(e);
                }

                Conn c3=new Conn();
                String date1=""+date;
                date1 = date1.substring(4,7);
                query1="insert into installment values ('"+pinnumber+"' ,'"+appno+"' , '"+date1+"' ,'"+tenure+"' ,'"+formattedInstallment+"' )";
                try{
                    c3.s.executeUpdate(query1);
                }catch(Exception e){
                    System.out.println(e);
                }

                setVisible(false);
                new EmpLoanApplication().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Confirm all Months CheckBox");
            }
        }else if(ae.getSource() == reject){
            JOptionPane.showMessageDialog(null,"Loan is Rejected");
            String query1 = "update loan set status = 'Rejected' where applicationNumber = '"+appno+"' ";
            Conn c=new Conn();
            try{
               c.s.executeUpdate(query1);
            }catch(Exception e){
                System.out.println(e);
            }
            setVisible(false);
            new EmpLoanApplication().setVisible(true);
        }else if(ae.getSource() == back){
            setVisible(false);
            new EmpLoanApplication();
        }else if(ae.getSource()==approve1){
            String amount="",roi="",tenure="";
            double installment;
            int balance=0;
            JOptionPane.showMessageDialog(null,"Loan is Approved");
            String query1 = "update loan set status = 'Approved' where applicationNumber = '"+appno+"' ";
            Conn c=new Conn();
            try{
               c.s.executeUpdate(query1);
               ResultSet rs =c.s.executeQuery("select * from loan where applicationNumber ='"+appno+"' ");
               if(rs.next()){
                   amount = rs.getString("amount");
                   roi=rs.getString("roi");
                   tenure=rs.getString("tenure");
               }
            }catch(Exception e){
                System.out.println(e);
            }
            Conn c1=new Conn();
            try{
               query1= "select * from bank where acno ='"+pinnumber+"' " ;
               ResultSet r = c1.s.executeQuery(query1);
               while(r.next()){
                   balance = Integer.parseInt(r.getString("balance"));
               }
            }catch(Exception e){
                System.out.println(e);
            }
            Date date = new Date();
            String s = "Loan Approved" + appno;
            String s1= "" + (balance + Integer.parseInt(amount));
            query1="insert into bank values ('"+pinnumber+"' ,'"+date+"' ,'"+s+"' ,'"+amount+"' , '"+s1+"' )";
            try{
                c.s.executeUpdate(query1);
            }catch(Exception e){
                System.out.println(e);
            }
            
            installment = Double.parseDouble(amount) * (Double.parseDouble(roi) / 12 / 100) * Math.pow(1 + (Double.parseDouble(roi) / 12 / 100), Integer.parseInt(tenure)) / (Math.pow(1 + (Double.parseDouble(roi) / 12 / 100), Integer.parseInt(tenure)) - 1);
            DecimalFormat df = new DecimalFormat("#.##");
            double formattedInstallment = Double.parseDouble(df.format(installment));
            
            Conn c2=new Conn();
            try{
                query1="update loan set installment = '"+formattedInstallment+"' where applicationNumber = '"+appno+"' ";
                c2.s.executeUpdate(query1);
            }catch(Exception e){
                System.out.println(e);
            }
            
            Conn c3=new Conn();
            String date1=""+date;
            date1 = date1.substring(4,7);
            query1="insert into installment values ('"+pinnumber+"' ,'"+appno+"' , '"+date1+"' ,'"+tenure+"' ,'"+formattedInstallment+"' )";
            try{
                c3.s.executeUpdate(query1);
            }catch(Exception e){
                System.out.println(e);
            }
            
            setVisible(false);
            new EmpLoanApplication().setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new EmpBalanceCheckLoan("","","");
    }
}
