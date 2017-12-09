package backend;

import java.sql.*;  
public class ConnectToServer{  
	static Connection con;
	static Statement stmt;
public static void connect() throws Exception {  
	
	Class.forName("com.mysql.jdbc.Driver"); 
	 con=DriverManager.getConnection(  
	"jdbc:mysql://your link","id","pass");
	 stmt=con.createStatement(); 
		stmt.executeQuery("use project5th2");
	
}
public static void disconnect()
{
	try {
		con.close();
		System.out.println("CLOSED");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static boolean usercheck(String uname,String upass) throws SQLException
{
	boolean flag = false;
	
	String qry= new String();
	//System.out.println("executing");
	qry = "select * from users" ;
	ResultSet rs=stmt.executeQuery(qry);
	//System.out.println("executed");
	//System.out.println("inputed username = "+uname);
	//System.out.println("inputed upass =  "+upass);
	while(rs.next())
	{
		System.out.println("checking");
		System.out.println(rs.getString(1));
		if(rs.getString(8).equals(uname) && rs.getString(9).equals(upass))
			{
			//System.out.println("flag true means user found");
			flag=true;
			}
	}
	return flag;
}
}
