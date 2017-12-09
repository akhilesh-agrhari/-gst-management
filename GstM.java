package front;
import backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Date;
import java.awt.color.*;
 class Login extends GstM implements ActionListener
 {
  JButton blogin,bsignup;
  JPanel panel,panel2;
  JLabel label1,label2,label3;
  final JTextField  text1,text2;
   Login()
   {
   label1 = new JLabel();
   label1.setText("Username:");
   text1 = new JTextField(15);
 
   label2 = new JLabel();
   label2.setText("Password:");
   
   label3= new JLabel("");
   text2 = new JPasswordField(15);
  
   blogin=new JButton("Login");
   bsignup = new JButton("Sign up");
   
   panel=new JPanel(new GridLayout(2,1));
   panel2 = new JPanel(new FlowLayout());
   text1.setEditable(false);
   text2.setEditable(false);
   blogin.setEnabled(false);
   bsignup.setEnabled(false);
   //panel.setBackground(Color.LIGHT_GRAY);
   //panel2.setBackground(Color.LIGHT_GRAY);
   panel.add(label1);
   panel.add(text1);
   panel.add(label2);
   panel.add(text2);
   panel2.add(blogin);
   panel2.add(bsignup);
   panel2.add(label3);
   add(panel,BorderLayout.CENTER);
   add(panel2,BorderLayout.SOUTH);
   blogin.addActionListener(this);
   bsignup.addActionListener(this);
   addWindowListener(new WindowAdapter() {

	   public void windowClosing(WindowEvent e) {
		   ConnectToServer.disconnect();
		   System.exit(0);

	   }
	     });
   setTitle("LOGIN FORM");
   }
  public void actionPerformed(ActionEvent ae)
   {
	  if(ae.getActionCommand().equals("Login"))
	  {
		  boolean flag;
		  uname=text1.getText();
		  upass=text2.getText();
		  try
		  {
			  	flag = ConnectToServer.usercheck(uname,upass);
			  	if(flag)
			  	{
			  		UserHome homePage=new UserHome();
			  		setVisible(false);
			  		homePage.setVisible(true);
			  		   
			  
			  	}
			  	else
			  	{
			  		JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
			  	}
		  }
		  catch (HeadlessException | SQLException e)
		  {
			  JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
		  }
	  }         //end of if
	  else if (ae.getActionCommand().equals("Sign up"))
	  {
		  setVisible(false); 
		  	new Signup();
	  		                 //login form closing indirectly
	  		//JLabel label = new JLabel("Welcome:");
	  		//signupPage.add(label);
	  }
   }
 }
public  class GstM extends JFrame
 {
	public static String uname = new String();
	public static String upass = new String();
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	// user variables
	String fname,lname,gstcategory,pan,email;
	String gstn;
	String dob;
	
   public static void main(String args[])
   {
   try
   {
	   Login frame=new Login();
   frame.setSize(300,120);
   frame.setResizable(false);
   frame.setLocationByPlatform(true);
   frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
   frame.setVisible(true);
   frame.label3.setText("Connecting...");
   ConnectToServer.connect();
   
   frame.label3.setText("");
   frame.text1.setEditable(true);
   frame.text2.setEditable(true);
   frame.blogin.setEnabled(true);
   frame.bsignup.setEnabled(true);
   
   }
   catch(Exception e)
   {
	   JOptionPane.showMessageDialog(null, "Can't connect. Please retry !");
	   System.exit(0);
   }
   }
   void clearLoginForm(Login obj)
   {
	   obj.text1.setText("");
	   obj.text2.setText("");
   }
 }
