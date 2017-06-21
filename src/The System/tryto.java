import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.mysql.jdbc.PreparedStatement;
public class tryto {
	//public static int ran_number;
	public static int saved ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//login.dispose();
		Connection con;
		java.sql.PreparedStatement st;
		Statement stmt;
		ResultSet rst2;
		HashFunc1 hashing = new HashFunc1();

		try{
			Random ran = new Random();
			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database = "jdbc:mysql://localhost:3306/MySQL?autoReconnect=true&useSSL=false";
			con =DriverManager.getConnection(database,"root", "aaaa");
			stmt = con.createStatement();


			//                
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////           	

			stmt.executeUpdate("DELETE From rand");

			String insertTableSQL1 = "INSERT INTO rand"
					+ "(ID) VALUES"
					+ "(?)";
			st=con.prepareStatement(insertTableSQL1);
			
			
			String str = "";
			switch(ran.nextInt(4))
			{
			case 0: 
				str ="0000";
				break;
			case 1: 
				str ="0001";
				break;
			case 2:
				str = "0010";
				break;
			case 3:
				str = "0011";
				break;
			}
				st.setString(1, hashing.getHashedValue("str").BinToHex().toString());
				st.executeUpdate();
				System.out.println("insert into ");

				System.out.println("done");

				rst2=stmt.executeQuery("Select * From rand");

				if (rst2.next()) 
				{
					//Print one row          
					System.out.println(rst2.getString(1));
					System.out.println();//Move to the next line to print the next row.  
				}

				//////////////////////////////////////////////////////////////////////////////////////////////////////////         

//				              rst = stmt.executeUpdate("create table UserFace1("
//				        		+ "ID int,"
//				        		+ "UserName varchar(45),"
//				        		+ "UserPassword int,"
//				        		+ "UserFace varchar(45));"); 
//
//
//				             String insertTableSQL2 = "INSERT INTO UserFace1"
//				            		    + "(ID, UserName,UserPassword,UserFace ) VALUES"
//				            		    + "(?,?,?,?)";
//				              
//				             st=con.prepareStatement(insertTableSQL2);
//				             st.setInt(1, 11);
//				             st.setString(2, "jaleo");
//				             st.setString(3, "2525");
//				             st.setString(4, "");
//				             st.executeUpdate();
//				                         
//				             System.out.println("done");
//				                
//				                rst2=stmt.executeQuery("Select * From UserFace1");
//				               
//				                while (rst2.next()) 
//				                {
//				                	//Print one row          
//				                	System.out.println(rst2.getString(2));
//				
//				                	  System.out.println();//Move to the next line to print the next row.           
//				
//				                }

				con.close();
				System.out.println("halsnaa");

			}catch(Exception e){
				System.out.print(e.getMessage());
			}




		}

	}
