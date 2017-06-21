import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4491121978535661188L;
	allfunctions all;
	Connection con;
	ResultSet  rst4 ;
    Statement stmt;
    java.sql.PreparedStatement st;
    tryto tt = new tryto();
     PreparedStatement preStatement,updatePreStmt;
    JLabel Passwordlabel;
    JButton loginbtn;
    JTextField Passfield;
    int WIDTH=600;
    int HEIGHT=220;
    public Login(){
        super("LOGIN FORM");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        
        Passwordlabel = new JLabel("Insert Your ID Face");
        Passwordlabel.setBounds((WIDTH/2)-60,HEIGHT-200,200,10);
        
        Passfield=new JTextField();
        Passfield.setBounds(100, HEIGHT-150,400, 20);
        
        add(Passwordlabel);
        add(Passfield);
        
        loginbtn=new JButton("login to system");
        loginbtn.setBounds((WIDTH/2)-75, HEIGHT-100, 150, 30);
        add(loginbtn);
        
        
        //Displaying Frame in Center of the Screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                         dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        //******************************************************************************************************************
        
        loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
			//	Passfield.setText("012121351351655465");
				
				try 
				{
					all  = new allfunctions();

					int num;
					String rand = null;
					connect();
					rst4 =stmt.executeQuery("Select * From rand");
					if( rst4.next() )
					{
						rand  = rst4.getString(1);
						
					}
					num=all.randFun(rand);
					
					
					System.out.println(num + " log in flag");
					
					if(num == -1)
					{
						JOptionPane.showMessageDialog(null ,
								"Embty, need to run the db");
					}
					else
					{
						
						MessageDigest res;
						
						 
						if(num==1){
							HashFunc1 hash =new HashFunc1();
							 res = hash.getHashedValue(Passfield.getText().toString());
						}
						else if(num==2){
							HashFunc2 hash =new HashFunc2();
							 res = hash.getHashedValue(Passfield.getText().toString());
						}
						else if(num==3){
							HashFunc3 hash =new HashFunc3();
							 res = hash.getHashedValue(Passfield.getText().toString());
						}
						else{
							HashFunc4 hash =new HashFunc4();
							 res = hash.getHashedValue(Passfield.getText().toString());
						}
						
						//stmt.executeUpdate("DELETE From rand");
						String str  = res.BinToHex().toString();
						rst4=stmt.executeQuery("SELECT UserName FROM UserFace1 WHERE UserFace = '"+str+"'");
						str = rst4.next()? rst4.getString(1): "fucked up";
						System.out.println(str);
						//flag = false;
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
        

    }
    //connect with database
    public void connect(){
        try{
        	
             // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              String database = "jdbc:mysql://localhost:3306/MySQL?autoReconnect=true&useSSL=false";
               con = DriverManager.getConnection(database,"root", "aaaa");
               stmt = con.createStatement();
         //     con.prepareStatement("insert into regForm(ID,Name,Password,Faces) values(?,?,?,?)");
              
              
        }catch(Exception e){
              System.out.print(e.getMessage());
        }
  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
