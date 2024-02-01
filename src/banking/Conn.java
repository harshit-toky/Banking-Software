package banking;

import java.sql.*;

public class Conn {
    
    Connection c;
    java.sql.Statement s;
    public Conn(){
        try{
            c=DriverManager.getConnection("jdbc:mysql:///onlinebankingsystem","root","534227628@hH");
            s = c.createStatement();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
