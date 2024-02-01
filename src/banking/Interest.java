package banking;

import java.sql.*;
import java.util.Date;

public class Interest {
    
    String type,formno;
    int balance=0,interest=0,charge=0,surcharge = 0;
    
    Interest(String pinnumber){
        Date d=new Date();
        String date=""+d;
        String date1=date.substring(4, 7);
        String date2=date.substring(23);
        date=date1+date2;
        
        int flag=0;
        Conn c3=new Conn();
        try{
            ResultSet rs3=c3.s.executeQuery("select * from interest where acno = '"+pinnumber+"'");
            while(rs3.next()){
                if(rs3.getString("month").equals(date)){
                    flag=1;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(flag==0){
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

            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where acno = '"+pinnumber+"'");
                while(rs.next()){
                    if(rs.getString("type").equals("Deposit")){
                        balance+=Integer.parseInt(rs.getString("amount"));

                    }else if(rs.getString("type").equals("Withdrawl")){
                        balance-=Integer.parseInt(rs.getString("amount"));
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
            Conn c4=new Conn();
            String facility = "";
            try{
                ResultSet rs4=c4.s.executeQuery("select * from signupthree where formno = '"+formno+"'");
                if(rs4.next()){
                        facility = rs4.getString("facility");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            String sub1="Debit Card",sub2="SMS Alerts";
            if(facility.indexOf(sub1) != -1){
                charge += 10/3;
            }if(facility.indexOf(sub2) != -1){
                charge += 5/3;
            }
            
            if("Current Account".equals(type)){
               if(balance < 0){
                   surcharge += balance * 0.06 / 12; 
               }else{
                   interest += balance * 0.04/12;
               }       
            }else{
                if(balance < 50){
                    charge += balance * 0.1 ;
                }else{
                    interest += balance * (1 + 0.05/12);
                }
            }   
            Conn c5=new Conn();
            try{
                if(interest != 0 ){
                    String query1="insert into interest values( '"+pinnumber+"' , 'interest' , '"+interest+"' , '"+date+"') ";
                    c5.s.executeUpdate(query1);
                }if(surcharge != 0){
                    String query2="insert into interest values( '"+pinnumber+"' , 'surcharge' , '"+surcharge+"' , '"+date+"') ";
                    c5.s.executeUpdate(query2);
                }if(charge != 0){
                    String query3="insert into interest values( '"+pinnumber+"' , 'charge' , '"+charge+"' , '"+date+"') ";
                    c5.s.executeUpdate(query3);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }    
    public static void main(String args[]){
        new Interest("");
    }
}
