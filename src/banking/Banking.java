package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.*;
import java.awt.geom.RoundRectangle2D;


class RoundTextField extends JTextField {

    private int borderRadius;


    public RoundTextField(int columns, int borderRadius) {
        super(columns);
        this.borderRadius = borderRadius;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Optional: Add some padding
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a rounded rectangle as the background
        Shape border = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
        g2.setColor(getBackground());
        g2.fill(border);
        g2.setColor(getForeground());
        g2.draw(border);

        super.paintComponent(g2);
        g2.dispose();
    }
}

class RoundTextField1 extends JPasswordField {

    private int borderRadius;


    public RoundTextField1(int columns, int borderRadius) {
        super(columns);
        this.borderRadius = borderRadius;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Optional: Add some padding
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a rounded rectangle as the background
        Shape border = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
        g2.setColor(getBackground());
        g2.fill(border);
        g2.setColor(getForeground());
        g2.draw(border);

        super.paintComponent(g2);
        g2.dispose();
    }
}

public class Banking extends JFrame implements ActionListener , ItemListener {

    JButton login,clear,signup,exit,emplogin;
    JTextField acTextField;
    JPasswordField passTextField;
    JRadioButton r1,r2;
    JLabel ACno;
    Banking(){
        setUndecorated(true);
        
        setLayout(null);
        
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icons/logo1.png")) ;
        Image I2=I1.getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT);
        ImageIcon I3=new ImageIcon(I2);
        
        JLabel label=new JLabel(I3);
        label.setBounds(20,0,200,200);
        label.setBackground(new Color(0x87CEEB));
        add(label);
        
        JLabel text=new JLabel("RK PUBLIC BANK");
        text.setFont(new Font("Osward",Font.BOLD, 38));
        text.setBounds(230,50,400,40);
        text.setForeground(new Color(0x001F3F));
        add(text);
        
        I1=new ImageIcon(ClassLoader.getSystemResource("icons/exit.png")) ;
        I2=I1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        I3=new ImageIcon(I2);
        
        exit=new JButton(I3);
        exit.setBounds(630,45,50,50);
        exit.setBackground(new Color(0x87CEEB));
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        add(exit);
        
        r1=new JRadioButton("Employee Login");
        r2=new JRadioButton("Customer Login");
        r1.setBackground(new Color(0x87CEEB));
        r2.setBackground(new Color(0x87CEEB));
        r1.setFont(new Font("Raleway",Font.PLAIN,20));
        r1.setBounds(230,120,200,30);
        r2.setFont(new Font("Raleway",Font.PLAIN,20));
        r2.setBounds(430,120,200,30);
        add(r1);
        add(r2);
        ButtonGroup loginGroup = new ButtonGroup();
        loginGroup.add(r1);
        loginGroup.add(r2);
        r1.addItemListener(this);
        r2.addItemListener(this);
        
        ACno=new JLabel("A/C No.     :");
        ACno.setFont(new Font("Raleway",Font.BOLD, 25));
        ACno.setBounds(100,175,400,30);
        add(ACno);
        
        acTextField = new RoundTextField(20, 35);
        acTextField.setBounds(250,180 ,230,30 );
        acTextField.setFont(new Font("Arial",Font.BOLD, 14));
        add(acTextField);
        
        JLabel Pass=new JLabel("Password :");
        Pass.setFont(new Font("Raleway",Font.BOLD, 25));
        Pass.setBounds(100,240,400,30);
        add(Pass);
        
        passTextField = new RoundTextField1(20, 35);
        passTextField.setBounds(250,240 ,230,30 );
        passTextField.setFont(new Font("Arial",Font.BOLD, 16));
        passTextField.setEchoChar('*');
        add(passTextField);
        
        login=new JButton("SIGN IN");
        login.setBounds(240,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        clear=new JButton("CLEAR");
        clear.setBounds(370,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        signup=new JButton("SIGN UP");
        signup.setBounds(240,350,230,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        
        
        getContentPane().setBackground(new Color(0x87CEEB));
        
        setSize(800,480);
        setVisible(true);
        setLocation(300,150);
        
    }
    public void itemStateChanged(ItemEvent e){
        if(e.getSource() == r1 && r1.isSelected()){
            ACno.setText("Username :");
            signup.setVisible(false);
        }else if(e.getSource() == r2 && r2.isSelected()){
            ACno.setText("A/C No.     :");
            signup.setVisible(true);
        }
    }
    
    public void actionPerformed(ActionEvent ae ){
        if(ae.getSource()== clear){
            acTextField.setText("");
            passTextField.setText("");
        }else if(ae.getSource() == login){
            String accountnumber=acTextField.getText();
            String pinnumber=passTextField.getText();
            if(r2.isSelected()){
                Conn conn=new Conn();
                
                String query="select * from login where AccountNumber = '"+accountnumber+"' and Password = '"+pinnumber+"' ";
                try{
                    ResultSet rs = conn.s.executeQuery(query);
                    if(rs.next()){
                        setVisible(false);
                        new Transactions(accountnumber).setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrect AC No. or Password");
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }else if(r1.isSelected()){
                if("admin".equals(accountnumber) && "admin".equals(pinnumber)){
                        setVisible(false);
                        new EmployeeTransactions().setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrect  Username or Password");
                    }
            }else{
                JOptionPane.showMessageDialog(null,"Select User or Employee Login");
            }
        }else if(ae.getSource()==signup){
            setVisible(false);
            new Signupone().setVisible(true);
        }else if(ae.getSource()==exit){
            System.exit(0);
        }
        
    }
    public static void main(String[] args) {
        new Banking();
    }
    
}
