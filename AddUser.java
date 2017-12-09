package backend;

//import java.sql.Date;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddUser extends ConnectToServer
{
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	static Date d ;
	static java.sql.Date sqldate;
	
	public static void addUser(String fname,String lname,String dob,String gstn,String gstcategory, String pan,String email,String pass)
	{
		int maxUser=0;
		try {
			d = new Date();
			d = (Date) df.parse(dob);
			System.out.println("util date "+d);
			sqldate = new java.sql.Date(d.getYear(), d.getMonth(), d.getDate());
			System.out.println("sql date "+ sqldate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt1;
		try {
			stmt1 = con.createStatement();
			String qry = "select max(userno) from users" ;
			ResultSet rs=stmt1.executeQuery(qry);
			
			if(rs.next())
			{
				maxUser = rs.getInt(1);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		maxUser =maxUser+1;
		System.out.println(""+maxUser);
		
		
		String query = " insert into users values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, maxUser);
			stmt.setString(2,fname);
			stmt.setString(3, lname);
			stmt.setDate(4, sqldate);
			//stmt.setDate(4, new java.sql.Date(d));
			stmt.setString(5,gstn );
			stmt.setString(6, gstcategory);
			stmt.setString(7, pan);
			stmt.setString(8, email);
			stmt.setString(9, pass);
			stmt.executeUpdate();
			System.out.println("uploaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong !!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}//ps.setDate(6, new java.sql.Date(affiliate.getDate().getTime()));
