package front;
//https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.Locale;
import java.util.regex.*;
import javax.swing.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import backend.AddUser;
import backend.ConnectToServer;
/*"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";*/

class Signup extends GstM implements ActionListener
{
	public static final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern gstnPattern = Pattern.compile("/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/", Pattern.CASE_INSENSITIVE);
	public static final Pattern panPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}", Pattern.CASE_INSENSITIVE);
	JPanel mainPanel,leftPanel,rightPanel;
	JButton submit,reset;
	JDateChooser calendar;
	SimpleDateFormat sdf;
	//GstM copymainFrame;
	//JLabel lfirstname,llastname,lgstn,lpan,lgstcat,lpass,lconfpass,ldob;
	JTextField tfirstname,tlastname,tgstn,tpan,temail,tgstcat,tpass,tconfpass,tdob;
	Signup()
	   {
	   setDefaultCloseOperation(javax.swing.
	    WindowConstants.DISPOSE_ON_CLOSE);
	   setTitle("Sign up ");
	   setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
	   setLayout(new GridBagLayout());
	   
	   GridBagConstraints c = new GridBagConstraints();
	   GridBagConstraints b = new GridBagConstraints();
	   GridBagConstraints d = new GridBagConstraints();
	   setTitle("Welcome ");
	   setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
	   
	   leftPanel = new JPanel(new GridLayout(4,1));
	   //leftside bar configuration
	   b.weightx = 0.18;
	   b.weighty =1;
	   b.fill = GridBagConstraints.BOTH;
	   b.gridx = 0;
	   b.gridy = 0;
	   add(leftPanel,b);
	   
	   mainPanel = new JPanel();
	   //mainPanel configuration
	   mainPanel.setLayout(new GridLayout(10,2));
	   c.weightx = 0.64;
	   c.weighty =1;
	   c.fill = GridBagConstraints.BOTH;
	   c.gridx = 1;
	   c.gridy = 0;
	   mainPanel.setBackground(Color.cyan);
	   
	   mainPanel.add(new JLabel("First name : "));
	   tfirstname = new JTextField(10);
	   tfirstname.setSize(10, 50);
	   mainPanel.add(tfirstname);
	   
	   mainPanel.add(new JLabel("Last name : "));
	   tlastname = new JTextField(10);
	   mainPanel.add(tlastname);
	
	  calendar = new JDateChooser();
	  calendar.setDateFormatString("yyyy-MM-dd");
	  mainPanel.add(new JLabel("Date of birth"));
	  //calendar.setFocusable(true);
	  sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	     tdob = new JTextField();
	     tdob.add(calendar);
	     mainPanel.add(tdob);
	  //calendar.getin
	  
	  System.out.println(calendar.getDateFormatString());
	   
	   mainPanel.add(new JLabel("GSTN : "));
	   tgstn = new JTextField(10);
	   mainPanel.add(tgstn);
	   
	   mainPanel.add(new JLabel("GST CATEGORY : "));
	   tgstcat = new JTextField(10);
	   mainPanel.add(tgstcat);
	   
	   
	   mainPanel.add(new JLabel("Pan no. : "));
	   tpan = new JTextField(10);
	   mainPanel.add(tpan);
	   
	   temail = new JTextField(10);
	   mainPanel.add(new JLabel("Email : "));
	   mainPanel.add(temail);
	   
	   tpass = new JPasswordField(15);
	   mainPanel.add(new JLabel("Password : "));
	   mainPanel.add(tpass);
	   
	   tconfpass = new JPasswordField(15);
	   mainPanel.add(new JLabel("Confirm Password : "));
	   mainPanel.add(tconfpass);
	   reset = new JButton("Reset");
		submit = new JButton("Submit");
		reset.setEnabled(true);
		submit.setEnabled(true);
	   mainPanel.add(reset );//add(panel,BorderLayout.CENTER);
	   mainPanel.add(submit);
	   add(mainPanel,c);
	   
	   rightPanel = new JPanel(new GridLayout(4,1));
	   //leftside bar configuration
	   d.weightx = 0.18;
	   d.weighty =1;
	   d.fill = GridBagConstraints.BOTH;
	   d.gridx = 2;
	   d.gridy =0 ;
	   add(rightPanel,d);
	   addWindowListener(new WindowAdapter() {

		   public void windowClosing(WindowEvent e) {
			   ConnectToServer.disconnect();
			   System.exit(0);

		   }
		     });
	   
	   reset.addActionListener(this);
	   submit.addActionListener(this);
	   System.out.println("intialized");
	   setVisible(true);
	   fname = new String();
	   lname = new String();
	   dob = new String();
	   gstn = new String();
	   gstcategory = new String();
	   pan = new String();
	   email = new String();
	    }
	public void getDetail()
	{
		
	}
	public static boolean isValidEmail(String email) {

	    Matcher m = emailPattern.matcher(email);
	    System.out.println("eamil "+m.matches() +" " +!m.matches());
	    return !m.matches();

	}
	///^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/
	public static boolean isValidGstn(String gstn) {

	    Matcher m0 =gstnPattern.matcher(gstn);
	    System.out.println("gst "+m0.matches() +" " +!m0.matches());
	    return !m0.matches();

	}
	public static boolean isValidPan(String pan) {

	    Matcher m1 =panPattern.matcher(pan); //[A-Z]{5}[0-9]{4}[A-Z]{1}
	    System.out.println("pan "+m1.matches() +" " +!m1.matches());
	    return !m1.matches();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		//boolean flag=false;
		
		if(ae.getActionCommand().equals("Submit"))
		{
			System.out.println("submit button");
			if(		tfirstname.getText().equals("") ||
					tlastname.getText().equals("") ||
					tdob.getText().equals("")||
					tpass.getText().equals("")||
					tconfpass.getText().equals("")||
					temail.getText().equals("") ||
					tpan.getText().equals("")||
					tgstn.getText().equals("")||
					tgstcat.getText().equals("")
				)
			{
				//System.out.println("Some fields are empty");
				JOptionPane.showMessageDialog(null, "Please fill up the full form");
			}
			else if(isValidGstn(tgstn.getText())==false)
				
			{
				//System.out.println("gst is not valid");
				JOptionPane.showMessageDialog(null, "Invalid GSTN");
				tgstn.setText("");
				tpass.setText("");
				tconfpass.setText("");
				
				
			}
			/*else if(isValidPan(tpan.getText())==false)
				
			{
				//System.out.println("invalid pan");
				JOptionPane.showMessageDialog(null, "Invalid PAN");
				tpan.setText("");
				tpass.setText("");
				tconfpass.setText("");
				
			}*/
			else if(isValidEmail(temail.getText())==false)
			{
				//System.out.println("email is not valid");
				JOptionPane.showMessageDialog(null, "email is not valid");
				temail.setText("");
				tpass.setText("");
				tconfpass.setText("");
			}
			
			else if( tpass.getText().equals(tconfpass.getText())==false ) {
				
				//System.out.println("password not matched");
				JOptionPane.showMessageDialog(null, "Password are not matched");
				tpass.setText("");
				tconfpass.setText("");
			}
			
			
			
			else if(tgstn.getText().substring(2, 11).equals(tpan.getText()))
			{
				//System.out.println("email is not valid");
				JOptionPane.showMessageDialog(null, "Gst and Pan are not linked");
				tgstn.setText("");
				tpan.setText("");
				tpass.setText("");
				tconfpass.setText("");
			}
			
			
			else
			{
				System.out.println("else");
				fname = tfirstname.getText();
				lname = tlastname.getText();
				//dob = calendar.toString()
				dob = tdob.getText();
				gstn =tgstn.getText();
				gstcategory = tgstcat.getText();
				pan = tpan.getText();
				email = temail.getText();
				AddUser.addUser(fname,lname,dob,gstn,gstcategory,pan,email,tpass.getText());
				clearFields();
				 Login frame=new Login();
				   frame.setSize(300,120);
				   frame.setResizable(false);
				   frame.setLocationByPlatform(true);
				   frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
				   frame.setVisible(true);
				   frame.label3.setText("");
				   frame.text1.setEditable(true);
				   frame.text2.setEditable(true);
				   frame.blogin.setEnabled(true);
				   frame.bsignup.setEnabled(true);
				setVisible(false);
			}
			
			
		}
		else if(ae.getActionCommand().equals("Reset"))
		{
			clearFields();
		}
		
	}
	void clearFields()
	{
		tfirstname.setText("");
		tlastname.setText("");
		tdob.setText("");
		tgstn.setText("");
		tgstcat.setText("");
		tpan.setText("");
		temail.setText("");
		tpass.setText("");
		tconfpass.setText("");
	}
}