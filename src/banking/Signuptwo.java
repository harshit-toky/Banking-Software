package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signuptwo extends JFrame implements ActionListener{
    
    JTextField aadhar,pan;
    JButton next;
    JRadioButton eyes,eno,syes,sno;
    JComboBox religion,category,occupation,education,income;
    String formno;
    Signuptwo(String formno){
        this.formno=formno;
        setLayout(null);
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        
        JLabel additionaldetails = new JLabel("PAGE 2 - ADDITIONAL DETAILS ");
        additionaldetails.setFont(new Font("Raleway",Font.BOLD ,28));
        additionaldetails.setBounds(230,80,600,40);
        add(additionaldetails);
        
        JLabel name = new JLabel("Nationality:");
        name.setFont(new Font("Raleway",Font.BOLD ,20));
        name.setBounds(100,140,200,30);
        add(name);
        
        String valReligion[]={"Indian","Nepali","Other"};
        religion=new JComboBox(valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        add(religion);
        
        
        JLabel fname = new JLabel("Category:");
        fname.setFont(new Font("Raleway",Font.BOLD ,20));
        fname.setBounds(100,190,200,30);
        add(fname);
        
        String valCategory[]={"General","OBC","SC","ST","Other"};
        category=new JComboBox(valCategory);
        category.setBounds(300,190,400,30);
        category.setBackground(Color.WHITE);
        add(category);
        
        
        JLabel dob = new JLabel("Income :");
        dob.setFont(new Font("Raleway",Font.BOLD ,20));
        dob.setBounds(100,240,200,30);
        add(dob);
        
        String valIncome[]={"Null","<1,50,000","< 2,50,000","<5,00,000","Upto 10,00,000"};
        income=new JComboBox(valIncome);
        income.setBounds(300,240,400,30);
        income.setBackground(Color.WHITE);
        add(income);
               
        JLabel gender = new JLabel("Educational");
        gender.setFont(new Font("Raleway",Font.BOLD ,20));
        gender.setBounds(100,290,200,30);
        add(gender);
        
        JLabel email = new JLabel("Qualification :");
        email.setFont(new Font("Raleway",Font.BOLD ,20));
        email.setBounds(100,315,200,30);
        add(email);
        
        String valEducation[]={"Non-Graduate","Graduate","Post-Graduate","Doctorate"};
        education=new JComboBox(valEducation);
        education.setBounds(300,315,400,30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel marital = new JLabel("Occupation :");
        marital.setFont(new Font("Raleway",Font.BOLD ,20));
        marital.setBounds(100,390,200,30);
        add(marital);
        
        String valOccupation[]={"Salaried","Self-Employed","Buisness","Student","Other"};
        occupation=new JComboBox(valOccupation);
        occupation.setBounds(300,390,400,30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel address = new JLabel("PAN No. :");
        address.setFont(new Font("Raleway",Font.BOLD ,20));
        address.setBounds(100,440,200,30);
        add(address);
        
        pan=new JTextField();
        pan.setFont(new Font("Raleway",Font.BOLD,14));
        pan.setBounds(300,440,400,30);
        add(pan);
        
        JLabel city = new JLabel("Aadhar No. :");
        city.setFont(new Font("Raleway",Font.BOLD ,20));
        city.setBounds(100,490,200,30);
        add(city);
        
        aadhar=new JTextField();
        aadhar.setFont(new Font("Raleway",Font.BOLD,14));
        aadhar.setBounds(300,490,400,30);
        add(aadhar);
        
        JLabel state = new JLabel("Senior Citizen :");
        state.setFont(new Font("Raleway",Font.BOLD ,20));
        state.setBounds(100,540,200,30);
        add(state);
        
        syes=new JRadioButton("Yes");
        syes.setBounds(300,540,100,30);
        syes.setBackground(Color.WHITE);
        add(syes);
        
        sno=new JRadioButton("No");
        sno.setBounds(450,540,100,30);
        sno.setBackground(Color.WHITE);
        add(sno);
        
        ButtonGroup seniorgroup=new ButtonGroup();
        seniorgroup.add(syes);
        seniorgroup.add(sno);
        
        JLabel pin = new JLabel("Existing Account :");
        pin.setFont(new Font("Raleway",Font.BOLD ,20));
        pin.setBounds(100,590,200,30);
        add(pin);
        
        eyes=new JRadioButton("Yes");
        eyes.setBounds(300,590,100,30);
        eyes.setBackground(Color.WHITE);
        add(eyes);
        
        eno=new JRadioButton("No");
        eno.setBounds(450,590,100,30);
        eno.setBackground(Color.WHITE);
        add(eno);
        
        ButtonGroup eagroup=new ButtonGroup();
        eagroup.add(eyes);
        eagroup.add(eno);
        
        next=new JButton("NEXT");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Rleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        String sreligion=(String)religion.getSelectedItem();
        String scategory=(String)category.getSelectedItem();
        String sincome=(String)income.getSelectedItem();
        String seducation=(String)education.getSelectedItem();
        String soccupation=(String)occupation.getSelectedItem();
        String seniorcitizen=null;
        if(syes.isSelected()){
            seniorcitizen="Yes";
        }else if(sno.isSelected()){
            seniorcitizen="No";
        }
        String existingaccount="";
        if(eyes.isSelected()){
            existingaccount="Yes";
        }else if(eno.isSelected()){
            existingaccount="No";
        }
        String span=pan.getText();
        String saadhar=aadhar.getText();
        
        try{ 
            Conn c=new Conn();
            String query= "insert into signuptwo values('"+formno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+span+"','"+saadhar+"','"+seniorcitizen+"','"+existingaccount+"')";
            c.s.executeUpdate(query);
            
            setVisible(false);
            new Signupthree(formno).setVisible(true);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void main(String[] args){
        new Signuptwo("");
    }
}
