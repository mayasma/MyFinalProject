import java.awt.Color; 
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RegistrationFormGUI extends JFrame{   

      Connection con;
      
      Statement stmt;
      java.sql.PreparedStatement st;

       PreparedStatement preStatement,updatePreStmt;
      JLabel Passwordlabel, idLabel, nameLabel;
      JTextField idField, nameField, Password;
      JButton registerButton, exitButton,backbutn,face_btn;
      ButtonGroup bg;
      JPanel panel;
      JTable table;

      DefaultTableModel model;

      JScrollPane scrollpane;
      
      
      
      
      public RegistrationFormGUI() {
           // TODO Auto-generated constructor stub
           super("REGISTRATION FORM");
            setSize(770, 420);
            setLayout(null);
            // Calling connect method, this will connect us to database
            
            // Defining Labels 
            idLabel = new JLabel("ID");
            idLabel.setBounds(30, 50, 60, 30);
            nameLabel = new JLabel("Name"); 
            nameLabel.setBounds(30, 85, 60, 30);

            // Defining ID field
            idField = new JTextField(); 
            idField.setBounds(95, 50, 130, 30);

            // Defining Name field
            nameField = new JTextField(); 
            nameField.setBounds(95, 85, 130, 30);         

            // Defining Gender Passwords
            Passwordlabel=new JLabel("Password");
            Passwordlabel.setBounds(30, 120, 60, 30);
            
            
            
            Password=new JTextField();
            Password.setBounds(95, 120, 130, 30);
            	

            bg = new ButtonGroup(); 

            // fixing all Label,TextField,RadioButton
            //add(Passwordlabel);
            add(idLabel);
            add(nameLabel);
            add(idField);
            add(nameField);
          //  add(Password);
            
            // Defining Exit Button
            exitButton = new JButton("Exit"); 
            exitButton.setBounds(125, 320, 80, 25);  
            
         // Defining Update Button
            backbutn = new JButton("Back");
            backbutn.setBounds(25, 320, 80, 25);

            // Defining Register Button
            registerButton = new JButton("Register");
            registerButton.setBounds(110, 125, 100, 30);

            
            
            face_btn = new JButton("face sccanner");
            face_btn.setBounds(60, 200, 120, 40);
            
            // fixing all Buttons
            add(exitButton);
            add(registerButton);
            add(backbutn);
            add(face_btn);
            // Defining Panel
            panel = new JPanel();
            panel.setLayout(new GridLayout());
            panel.setBounds(250, 20, 480, 330);
            panel.setBorder(BorderFactory.createDashedBorder(Color.blue));
            add(panel);

            // Defining Refresh Button

            //Defining Model for table
            model = new DefaultTableModel();

            //Adding object of DefaultTableModel into JTable
            table = new JTable(model);

            //Fixing Columns move
            table.getTableHeader().setReorderingAllowed(false);

            // Defining Column Names on model
            model.addColumn("ID");
            model.addColumn("UserName");
        //    model.addColumn("UserPassword");
            model.addColumn("UserFace");
 
            // Enable Scrolling on table
           scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(scrollpane);

            //Displaying Frame in Center of the Screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setVisible(true);
            
      }
      
     //=====================================================================================
    //======================================================================================
    //======================================================================================
      // Connection with Database
      
      
      //==================================================================================
      //============================Connect to database ==================================
      public void connect(){
          try{
          	
               // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                String database = "jdbc:mysql://localhost:3306/MySQL?autoReconnect=true&useSSL=false";
                con =DriverManager.getConnection(database,"root", "aaaa");
                stmt = con.createStatement();
           //     con.prepareStatement("insert into regForm(ID,Name,Password,Faces) values(?,?,?,?)");
                
                
          }catch(Exception e){
                System.out.print(e.getMessage());
          }
    }

}