package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.*;

public class Loanapply extends JFrame implements ItemListener,ChangeListener,ActionListener  {
    
    String pinnumber;
    JRadioButton r1,r2,r3,r4;
    JLabel interest,tenureLabel1;
    JSlider tenureslider;
    JTextField amount,salary;
    JButton submit,back;
    Loanapply(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Loan Application Form");
        setLayout(null);
        
        JLabel application = new JLabel("Loan Application Form");
        application.setFont(new Font("Raleway",Font.BOLD,38));
        application.setBounds(200,30,600,50);
        add(application);
        
        JLabel type= new JLabel("Select Type of Loan :");
        type.setFont(new Font("Raleway",Font.BOLD,22));
        type.setBounds(50,100,600,40);
        add(type);
        
        r1=new JRadioButton("Car Loan");
        r2=new JRadioButton("Two-Wheeler Loan");
        r3=new JRadioButton("Home Loan");
        r4=new JRadioButton("Personal Loan");
        r1.setBackground(Color.WHITE);
        r2.setBackground(Color.WHITE);
        r3.setBackground(Color.WHITE);
        r4.setBackground(Color.WHITE);
        r1.setFont(new Font("Raleway",Font.PLAIN,18));
        r2.setFont(new Font("Raleway",Font.PLAIN,18));
        r3.setFont(new Font("Raleway",Font.PLAIN,18));
        r4.setFont(new Font("Raleway",Font.PLAIN,18));
        
        r1.setBounds(50,150,200,40);
        r2.setBounds(350,150,200,40);
        r3.setBounds(50,210,200,40);
        r4.setBounds(350,210,200,40);
        
        r1.addItemListener(this);
        r2.addItemListener(this);
        r3.addItemListener(this);
        r4.addItemListener(this);
        
        add(r1);
        add(r2);
        add(r3);
        add(r4);
        ButtonGroup typeLoan = new ButtonGroup();
        typeLoan.add(r1);
        typeLoan.add(r2);
        typeLoan.add(r3);
        typeLoan.add(r4);
        
        JLabel rate= new JLabel("Rate of Interest :");
        rate.setFont(new Font("Raleway",Font.PLAIN,22));
        rate.setBounds(50,280,600,40);
        add(rate);
        
        interest= new JLabel("");
        interest.setFont(new Font("Raleway",Font.PLAIN,22));
        interest.setBounds(250,280,600,40);
        add(interest);
        
        JLabel amountLabel = new JLabel("Enter Loan Amount : Rs.");
        amountLabel.setFont(new Font("Raleway",Font.BOLD,22));
        amountLabel.setBounds(50,340,600,40);
        add(amountLabel);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(320,340,400,40);
        add(amount);
        
        JLabel tenureLabel = new JLabel("Enter Loan Months :");
        tenureLabel.setFont(new Font("Raleway",Font.BOLD,22));
        tenureLabel.setBounds(50,400,600,40);
        add(tenureLabel);
        
        tenureLabel1 = new JLabel("Selected Tenure: ");
        tenureLabel1.setBounds(320,450,400,30);
        tenureLabel1.setFont(new Font("Raleway",Font.PLAIN,16));
        add(tenureLabel1);
        
        JLabel salaryLabel = new JLabel("Enter per-Month Salary : Rs.");
        salaryLabel.setFont(new Font("Raleway",Font.BOLD,22));
        salaryLabel.setBounds(50,520,600,40);
        add(salaryLabel);
        
        salary = new JTextField();
        salary.setFont(new Font("Raleway",Font.BOLD,22));
        salary.setBounds(320,520,400,40);
        add(salary);
        
        submit=new JButton("Submit");
        submit.setBounds(200,650,130,50);
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
        back=new JButton("Back");
        back.setBounds(430,650,130,50);
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850,820);
        setLocation(350,0);
        setVisible(true);
    }
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == r1 && r1.isSelected()) {
            interest.setText("6% P.A.");
            tenureslider = new JSlider(JSlider.HORIZONTAL, 12, 60, 36);
            tenureslider.setBounds(320,400,400,40);
            tenureslider.setBackground(Color.WHITE);
            tenureslider.addChangeListener(this);
            add(tenureslider);
            tenureLabel1.setText("Selected Tenure: " + tenureslider.getValue() + " Months");
        } else if (e.getSource() == r2 && r2.isSelected()) {
            interest.setText("4% P.A.");
            tenureslider = new JSlider(JSlider.HORIZONTAL, 12, 60, 36);
            tenureslider.setBounds(320,400,400,40);
            tenureslider.setBackground(Color.WHITE);
            tenureslider.addChangeListener(this);
            add(tenureslider);
            tenureLabel1.setText("Selected Tenure: " + tenureslider.getValue() + " Months");
        } else if (e.getSource() == r3 && r3.isSelected()) {
            interest.setText("8% P.A.");
            tenureslider = new JSlider(JSlider.HORIZONTAL, 12, 300, 36);
            tenureslider.setBounds(320,400,400,40);
            tenureslider.setBackground(Color.WHITE);
            tenureslider.addChangeListener(this);
            add(tenureslider);
            tenureLabel1.setText("Selected Tenure: " + tenureslider.getValue() + " Months");
        } else if (e.getSource() == r4 && r4.isSelected()) {
            interest.setText("10% P.A.");
            tenureslider = new JSlider(JSlider.HORIZONTAL, 12, 60, 36);
            tenureslider.setBounds(320,400,400,40);
            tenureslider.setBackground(Color.WHITE);
            tenureslider.addChangeListener(this);
            add(tenureslider);
            tenureLabel1.setText("Selected Tenure: " + tenureslider.getValue() + " Months");
        }
    }
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tenureslider) {
            tenureLabel1.setText("Selected Tenure: " + tenureslider.getValue() + " Months");
        }
    }
    public void actionPerformed(ActionEvent ae){
        String amt="",sal="",type="",month,roi="";
        if(ae.getSource()==back){
            setVisible(false);
            new Loanservices(pinnumber).setVisible(true);
        }else if(ae.getSource() == submit){
            
            amt=amount.getText();
            sal=salary.getText();
            if(amt.equals("") || sal.equals("")){
                JOptionPane.showMessageDialog(null, "Required Fields are empty");
            }else{
                Random random = new Random();
                long randomNumber = (long) (random.nextDouble() * 9000000000L + 1000000000L);
                if(r1.isSelected()){
                    type="Car Loan";
                    roi="6";
                }else if(r2.isSelected()){
                    type="Two-Wheeler Loan";
                    roi="4";
                }else if(r3.isSelected()){
                    type="Home Loan";
                    roi="8";
                }else if(r4.isSelected()){
                    type="Personal Loan";
                    roi="10";
                }
                month ="" + tenureslider.getValue();
                Conn c=new Conn();
                try{
                    String query="insert into loan values ('"+pinnumber+"', '"+randomNumber+"' ,'"+type+"','"+amt+"','"+sal+"','"+month+"','"+roi+"','No','' )";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Your Application Number :"+ randomNumber);
                    setVisible(false);
                    new Loanservices(pinnumber).setVisible(true);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]){
        new Loanapply("");
    }
}