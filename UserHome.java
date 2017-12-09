package front;
//https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import backend.ConnectToServer;
import backend.FetchCatList;
class UserHome extends GstM implements ActionListener
{
	static JPanel menubar,infobar,notifyPan,profilePan,calPan,billPan;
	static JButton bNotification,bProfile,bCalculate,bGenerate;
	final static String notification = "notification";
	final static String profile = "profile";
	final static String calculator = "calculate";
	final static String bill = "bill";
	JLabel notifylab = new JLabel("notification");
	JLabel profilelab = new JLabel("profile");
	JLabel callab = new JLabel("calculator");
	JLabel billlab = new JLabel("billing");
	CardLayout cl = new CardLayout();
	JTextField tproname = new JTextField(15);
	JTextField tproam = new JTextField(15);
	float rate,incprice,exlprice,gstam;
	JRadioButton r1=new JRadioButton("Inclusive GST");
	JRadioButton r2=new JRadioButton("Exclusive Gst");
	JComboBox cb=new JComboBox();
	JPanel bottom = new JPanel(new GridLayout(2,6));
	
	JLabel proname = new JLabel();
	JLabel lcategory = new JLabel();
	JLabel lexlprice = new JLabel();
	JLabel lrate = new JLabel();
	JLabel lgstam = new JLabel();
	JLabel lincprice = new JLabel();
	
	//-------------billing GUI variables--------------
	
	final static String billcard = "billing card";
	final static String invoicecard = "invoice card";
	JTextField nameField = new JTextField(15);
	JTextField qtyField  = new JTextField(3);
	JTextField price = new JTextField(15);
	JTextField snField = new JTextField(3);
	JTextField qtyField2 = new JTextField(3);
	JComboBox billcb=new JComboBox();
	JLabel errorlabel = new JLabel("              ");
	int prosn=1;
	static int itemno = 0;
	JPanel[] panelarr = new JPanel[12];
	JLabel[][] alllab = new JLabel[12][5];
	 BillPro[] prolist = new BillPro[10];
	 JPanel row4 = new JPanel(); 
	 float total=0;
	
	UserHome()
	   {
		//--------allocating memeory to the array of objects;
		
		for(int i =0 ;i<12;i++)
			{
			panelarr[i] = new JPanel(new GridLayout(1,5));
			if(i<10)
			prolist[i]= new BillPro();
			for(int k = 0 ;k<5;k++)
			{
				alllab[i][k]= new JLabel("");
				alllab[i][k].setBackground(Color.black);
			}
			
			}
		
	   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	   setLayout(new GridBagLayout());
	   
	   GridBagConstraints c = new GridBagConstraints();
	   GridBagConstraints b = new GridBagConstraints();
	   
	   setTitle("Welcome ");
	   setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
	   
	   menubar = new JPanel(new GridLayout(4,1));
	   //menu bar configuration
	   b.weightx = 0.15;
	   b.weighty =1;
	   b.fill = GridBagConstraints.BOTH;
	   b.gridx = 0;
	   b.gridy = 0;
	   menubar.setBackground(Color.green);
	   //menu bar items
	   bNotification = new JButton("Notification");
	   bProfile = new JButton("Profile");
	   bCalculate = new JButton("Calculate GST");
	   bGenerate = new JButton("Generate bill");
	   menubar.add(bNotification);
	   menubar.add(bProfile);
	   menubar.add(bCalculate);
	   menubar.add(bGenerate);
	   add(menubar,b);
	   
	   infobar = new JPanel();
	   infobar.setLayout(cl);
	   //infobar configuration
	   c.weightx = 0.85;
	   c.weighty =1;
	   c.fill = GridBagConstraints.BOTH;
	   c.gridx = 1;
	   c.gridy = 0;
	   infobar.setBackground(Color.cyan);
	   //infobar items
	   notifyPan = new JPanel(new FlowLayout());
	   profilePan = new JPanel(new FlowLayout());
	   calPan = new JPanel (new GridLayout(3,1));
	   billPan =  new JPanel (new FlowLayout());
	   //notifyPan.add(notifylab);
	  // profilePan.add(profilelab);
	   notifyGUI();
	   profileGUI();
	   calGUI();
	   
	  // calPan.add(callab);
	   //billPan.add(billlab);
	   billGUI();
	   infobar.add(notification, notifyPan);
	   infobar.add(profile,profilePan);
	   infobar.add(calculator ,calPan);
	   infobar.add(bill, billPan);
	   add(infobar,c);
	   bNotification.addActionListener(this);
	   bProfile.addActionListener(this);
	   bCalculate.addActionListener(this);
	   bGenerate.addActionListener(this);
	   setVisible(true);
	   addWindowListener(new WindowAdapter() {

		   public void windowClosing(WindowEvent e) {
			   ConnectToServer.disconnect();
			   System.exit(0);

		   }
		     });
	    }
	
	
	
	void notifyGUI() {
		String[] n=new String[5];
		JLabel[] l = new JLabel[5];
		JLabel heading = new JLabel("Notification");
		heading.setFont(new Font("Serif", Font.BOLD, 36));
		notifyPan.setLayout(new GridLayout(6,1));
		notifyPan.add(heading);
		n= FetchCatList.fetchnotif();
		for(int i = 0;i<5;i++) {
			l[i]= new JLabel();
		l[i].setText(n[i]);
		l[i].setFont(new Font("Serif", Font.ITALIC,18));
		notifyPan.add(l[i]);
		}
		
	}
	void profileGUI() {
		String name= new String();
		String gstn =new String();
		String pan =new String();
		String email =new String();
		JLabel heading = new JLabel("Profile");
		heading.setFont(new Font("Serif", Font.BOLD, 36));
		profilePan.setLayout(new GridLayout(5,1));
		profilePan.add(heading);
		JLabel [] details = new  JLabel[4];
		FetchCatList.fetchdetail(GstM.uname);
		name = FetchCatList.getname();
		gstn = FetchCatList.getgstn();
		pan = FetchCatList.getpan();
		email = FetchCatList.getemail();
		for(int i =0;i<4;i++)
			{
			details[i] = new JLabel();
			details[i].setFont(new Font("Serif", Font.ITALIC,18));
			profilePan.add(details[i]);
			}
		details[0].setText("NAME : "+name);
		details[1].setText("GST Number : "+gstn);
		details[2].setText("PAN no. : "+pan);
		details[3].setText("Email id : "+ email);
		
		
		
		
	}
	void calGUI() {
		//-----top
		FetchCatList.fetch();
		int i=0;
		
		while(CatList.gstage[i]<29)
				{
			
		    cb.addItem(CatList.name[i]);
		    i++;
			
				}
		JPanel top=new JPanel(new FlowLayout());
		JLabel heading = new JLabel("GST Calculator");
		
		heading.setFont(new Font("Serif", Font.BOLD, 36));
		top.add(heading);
		calPan.add(top);
		
		//----mid 
		JPanel mid = new JPanel(new GridLayout(2,1));
		JPanel productPan = new JPanel(new GridLayout(3,1));
		JPanel pronamePan = new JPanel(new FlowLayout());
		JPanel procatPan = new JPanel (new FlowLayout());
		JPanel proamPan = new JPanel (new FlowLayout());
		pronamePan.add(new JLabel("Product Name : "));
		
		pronamePan.add(tproname);
		productPan.add(pronamePan);
		
		procatPan.add(new JLabel("Product Category"));    
	    
	    procatPan.add(cb);
		productPan.add(procatPan);
		
		proamPan.add(new JLabel("Your amount : "));
	    //JTextField tproam = new JTextField(15);
	    proamPan.add(tproam);
	    productPan.add(proamPan);
	    mid.add(productPan);
		
		JPanel rAb = new JPanel(new GridLayout(2,1));
		JPanel radio = new JPanel(new FlowLayout());
		//radio button
		r1.setSelected(true);
		ButtonGroup bg=new ButtonGroup();    
		bg.add(r1);bg.add(r2);
		radio.add(r1);radio.add(r2);
		rAb.add(radio);
		
		JPanel button = new JPanel(new FlowLayout());
		JButton calculate = new JButton("Calculate");
		button.add(calculate);
		rAb.add(button);
		mid.add(rAb);
		
		
		calPan.add(mid);
		//JPanel middle = new JPanel(new GridLayout());
		//JTextField proname = new JTextField();
		
		
		bottom.add(new JLabel("Product name"));
		bottom.add(new JLabel("Category"));
		bottom.add(new JLabel("Exclusive GST"));
		bottom.add(new JLabel("GST %"));
		bottom.add(new JLabel("GST amount "));
		bottom.add(new JLabel("Price"));
		bottom.add(proname);
		bottom.add(lcategory);
		bottom.add(lexlprice);
		bottom.add(lrate);
		bottom.add(lgstam);
		bottom.add(lincprice);
		//bottom.add(lincprice);
		
		bottom.setVisible(false);
		calPan.add(bottom);
		
		
		calculate.addActionListener(this);
		
	}
	
	
	void calculate()
	{
		int i = cb.getSelectedIndex();
		System.out.println("select item = "+ CatList.name[i]);
		rate = CatList.gstage[i];
		 
		if(r1.isSelected()) {
			exlprice = Float.parseFloat(tproam.getText());
			incprice = exlprice * (1 + rate/100);
		}
			
		else {
			incprice = Float.parseFloat(tproam.getText());
			exlprice = incprice/ (1 + rate/100);
		}
		gstam = incprice - exlprice;
		
		proname.setText(tproname.getText());
		proname.setFont(new Font("Serif", Font.ITALIC, 15));
		lcategory.setText( CatList.name[i]);
		lcategory.setFont(new Font("Serif", Font.ITALIC, 15));
		lexlprice.setText(""+exlprice);
		lexlprice.setFont(new Font("Serif", Font.ITALIC, 15));
		lrate.setText(""+rate+"%");
		lrate.setFont(new Font("Serif", Font.ITALIC, 15));
		 lgstam.setText(""+ gstam);
		 lgstam.setFont(new Font("Serif", Font.ITALIC, 15));
		lincprice.setText(""+incprice);
		lincprice.setFont(new Font("Serif", Font.ITALIC, 15));
		bottom.setVisible(true);
		
	}
	
void billGUI() {
	JPanel main = new JPanel();
	JPanel billpan = new JPanel(new GridLayout(4,1));
	//JPanel invoicepan = new JPanel(new GridLayout(5,1)); //this need to be update in future *********
	CardLayout cards = new CardLayout();
	main.setLayout(cards);
	
	//billpan GUI
		//row1
	JPanel row1 = new JPanel (new FlowLayout());
	JLabel heading = new JLabel("Billing");
	heading.setFont(new Font("Serif", Font.BOLD, 36));
	row1.add(heading);
	billpan.add(row1);
		//row2
	JPanel row2 = new JPanel (new FlowLayout());
	JLabel namelabel = new JLabel ("Item name");
	JLabel catlabel = new JLabel ("Category");
	JLabel qtylabel2 = new JLabel ("Quantity");
	
	int i=0;
	while(CatList.gstage[i]<29)
	{
		billcb.addItem(CatList.name[i]);
		i++;
	}
	
	JButton add = new JButton("Add");
	row2.add(namelabel);
	row2.add(nameField);
	row2.add(qtylabel2);
	row2.add(qtyField);
	row2.add(new JLabel("Price"));
	row2.add(price);
	row2.add(catlabel);
	row2.add(billcb);
	
	row2.add(add);
	billpan.add(row2);
	add.addActionListener(this);
	
		//row3
	JPanel row3 = new JPanel(new FlowLayout());
	JLabel snlabel = new JLabel("SN");
	
	
	JButton remove = new JButton("Remove");
	
	row3.add(snlabel);
	row3.add(snField);
	row3.add(new JLabel("Quantity"));
	row3.add(qtyField2);
	row3.add(remove);
	row3.add(errorlabel);
	//billpan.add(row3);
	remove.addActionListener(this);
	
	//row4
	row4.setLayout(new GridLayout(12,1));
	
	for(int j=0;j<12;j++)
	{
		
		for(int k = 0 ;k<5;k++)
		{
			panelarr[j].add(alllab[j][k]);
			
		}
		row4.add(panelarr[j]);
	}
	billpan.add(row4);
	
	
	
	main.add(billcard,billpan); // main panel having card layout , 1st billpan card...to calculate bill
	//main.add(invoicecard,invoicepan);   //.......................,2nd invoice card after calculating the bill
	billPan.add(main); //billPan is the panel which is holding all items of generate bill button
	
	}
void createLine(int itemno)

{
	System.out.println("inside create line and itemno = "+ itemno);
	if(itemno==0)
	{
		alllab[0][0].setText("SN");
		alllab[0][1].setText("NAME");
		alllab[0][2].setText("QTY");
		alllab[0][3].setText("GST");
		alllab[0][4].setText("PRICE");
		alllab[1][0].setText(""+prolist[itemno].getSn());
		alllab[1][1].setText(""+prolist[itemno].getProname());
		alllab[1][2].setText(""+prolist[itemno].getQty());
		alllab[1][3].setText(""+prolist[itemno].getGstam());
		alllab[1][4].setText(""+prolist[itemno].getPrice());
		alllab[7][0].setText("total");
		alllab[7][1].setText("");
		alllab[7][2].setText("");
		alllab[7][3].setText("");	
		total = prolist[itemno].getPrice();
		alllab[7][4].setText(""+total);
		
	}
	else if(itemno<7)
	{
		alllab[itemno+1][0].setText(""+prolist[itemno].getSn());
		alllab[itemno+1][1].setText(prolist[itemno].getProname());
		alllab[itemno+1][2].setText(""+prolist[itemno].getQty());
		alllab[itemno+1][3].setText(""+prolist[itemno].getGstam());
		alllab[itemno+1][4].setText(""+prolist[itemno].getPrice());
		total = total +  prolist[itemno].getPrice();
		alllab[7][4].setText(""+total);
	}
	else {
		System.out.println("ELSE CONDTION");
		JOptionPane.showMessageDialog(null, "no more item can not be added");
	}
	
}
void removeitem(int itemno,int qty)
{
	
	if(qty>=prolist[itemno].qty)
	{
		total =total - prolist[itemno].getPrice();
		alllab[7][4].setText(""+total);
		
	}
	else
	{
		
	}
}
void clearFields()
{
	nameField.setText("");
	qtyField.setText("");
	price.setText("");
	billcb.setSelectedIndex(0);
}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getActionCommand().equals("Notification")) {
		cl.show(infobar, notification); //cl.show(infobar is the cardlayout panel, notification is the identifier of the card)
		bNotification.setEnabled(false);
		   bProfile.setEnabled(true);
		   bCalculate.setEnabled(true);
		   bGenerate.setEnabled(true);
		
	}
	else if(e.getActionCommand().equals("Profile")) {
		cl.show(infobar, profile);
		bNotification.setEnabled(true);
		   bProfile.setEnabled(false);
		   bCalculate.setEnabled(true);
		   bGenerate.setEnabled(true);
	}
	
	else if(e.getActionCommand().equals("Calculate GST")) {
		cl.show(infobar, calculator);
		bNotification.setEnabled(true);
		   bProfile.setEnabled(true);
		   bCalculate.setEnabled(false);
		   bGenerate.setEnabled(true);
	}
	
	else if(e.getActionCommand().equals("Generate bill")) {
		cl.show(infobar, bill);
		bNotification.setEnabled(true);
		   bProfile.setEnabled(true);
		   bCalculate.setEnabled(true);
		   bGenerate.setEnabled(false);
	}
	else if(e.getActionCommand().equals("Calculate")) {
		calculate();
	}
	else if (e.getActionCommand().equals("Add")) {
		System.out.println("add button clicked ,category = "+CatList.gstage[billcb.getSelectedIndex()]);
		prolist[itemno].setData(itemno+1,nameField.getText(),qtyField.getText(),CatList.name[billcb.getSelectedIndex()],CatList.gstage[billcb.getSelectedIndex()],price.getText());//billcb.getSelectedIndex();CatList[billcb.setSelectIndex()]
		createLine(itemno);
		clearFields();
		itemno++;
		
	}
	else if(e.getActionCommand().equals("Remove")) {
		removeitem(Integer.parseInt(snField.getText()),Integer.parseInt(qtyField2.getText()));
	}
		
	
	
	
}
}

