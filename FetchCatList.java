package backend;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import front.CatList;
public class FetchCatList extends ConnectToServer {
	
	static String name = new String ();
	static String gstn = new String ();
	static String pan = new String ();
	static String email = new String ();
	public static void fetch()
	{
		
		CatList[] proList = new CatList[100];
		int i=0;
		try {
			Statement stmt0;//stm5,stmt12,stmt18,stmt28;
			stmt0 = con.createStatement();
			/*stmt5 = con.createStatement();
			stmt12 = con.createStatement();
			stmt18 = con.createStatement();
			stmt28 = con.createStatement();*/
		
			String qry0=  "select * from zero" ;
			ResultSet rs0=stmt0.executeQuery(qry0);
			while(rs0.next())
			{
				CatList.name[i]= rs0.getString(2);
				CatList.gstage[i] = 0;
				i++;
			}
			
			String qry5=  "select * from five" ;
			ResultSet rs5=stmt0.executeQuery(qry5);
			while(rs5.next())
			{
				CatList.name[i]= rs5.getString(2);
				CatList.gstage[i] = 5;
				i++;
			}
			
			String qry12=  "select * from twelve" ;
			ResultSet rs12=stmt0.executeQuery(qry12);
			while(rs12.next())
			{
				CatList.name[i]= rs12.getString(2);
				CatList.gstage[i] = 12;
				i++;
			}
			
			String qry18=  "select * from eighteen" ;
			ResultSet rs18=stmt0.executeQuery(qry18);
			while(rs18.next())
			{
				CatList.name[i]= rs18.getString(2);
				CatList.gstage[i] = 18;
				i++;
			}
			
			String qry28=  "select * from twentyeight" ;
			ResultSet rs28=stmt0.executeQuery(qry28);
			
			while(rs28.next())
			{
				CatList.name[i]= rs28.getString(2);
				CatList.gstage[i] = 28;
				i++;
			}
			CatList.name[i]= "stop";
			CatList.gstage[i] = 32000;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String[] fetchnotif()
	{
		String[] notif = new String[5];
		for(int k=0;k<5;k++)
			notif[k]=new String();
		int i=0;
		try {
			Statement stmt1 = con.createStatement();
			String qry= "select * from notification";
			ResultSet rs = stmt1.executeQuery(qry);
			while(rs.next()&&i<5)
			{
				notif[i] = rs.getString(1);
				i++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notif;
	}
	public static void fetchdetail(String uname)
	{
		name = new String ();
		gstn = new String ();
		pan = new String ();
		email = new String ();
		
		Statement stmt1;
		try {
			stmt1 = con.createStatement();
		
		String qry= "select * from users ";
		ResultSet rs = stmt1.executeQuery(qry);
		while(rs.next())
		{
			if(rs.getString(8).equals(uname)) {
			name = rs.getString(2)+ " "+rs.getString(3);
			gstn = rs.getString(5);
			pan = rs.getString(7);
			email = rs.getString(8);
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getname(){System.out.println("return "+name);return name;}
	public static String getgstn(){return gstn;}
	public static String getpan(){return pan;}
	public static String getemail(){return email;}
	
}