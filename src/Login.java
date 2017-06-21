import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4491121978535661188L;

	Connection con;
    
    Statement stmt;
    java.sql.PreparedStatement st;

     PreparedStatement preStatement,updatePreStmt;
    JLabel Passwordlabel;
    JButton loginbtn;
    JTextField Passfield;
    
    int WIDTH=550;
    int HEIGHT=220;
    public Login(){
        super("LOGIN FORM");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        
        Passwordlabel = new JLabel("Your ID face");
        Passwordlabel.setBounds(WIDTH/2-WIDTH/8,HEIGHT-200,100,10);
        
        Passfield=new JTextField();
        Passfield.setBounds(WIDTH-400, HEIGHT-150,200, 20);
        
        add(Passwordlabel);
        add(Passfield);
        
        loginbtn=new JButton("login to system");
        loginbtn.setBounds(WIDTH/2, HEIGHT-100, 150, 30);
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
			public void actionPerformed(ActionEvent e) {
				Passfield.setText("012121351351655465");
				
				
				
				//<<<<<<<<<======================WORK HERE KHALID
				//<<=====================================
				//<<<<====================================
			}
		});
        

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
