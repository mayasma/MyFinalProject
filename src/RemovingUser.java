import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RemovingUser extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel UserId;
    JTextField idField;
    JButton deletebtn,search;
    
    DefaultTableModel model;
    JTable table;
    JScrollPane scrollpane;
    
    JPanel panel;
    
    Connection con;
    
    Statement stmt;
    java.sql.PreparedStatement st;
    ResultSet rst,rstLast;

    
    public RemovingUser(){
    	
    	connect();
    	setSize(770, 420);
        setLayout(null);
    	//for removing users
    	model = new DefaultTableModel();
    	table = new JTable(model);
    	table.getTableHeader().setReorderingAllowed(false);
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Faces");
        
        scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        
        // Defining Panel
        panel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.setBounds(250, 20, 480, 330);
        panel.setBorder(BorderFactory.createDashedBorder(Color.blue));
        add(panel);

        panel.add(scrollpane);
        
        
        UserId=new JLabel("User Id:");
        UserId.setBounds(60, 25, 200, 30);
        add(UserId);
        
        idField=new JTextField();
        idField.setBounds(60, 55, 130, 30);
        add(idField);
        
        deletebtn=new JButton("delete User");
        deletebtn.setBounds(60, 320, 130, 30);
        add(deletebtn);
        deletebtn.setEnabled(false);
        
        
        search=new JButton("Search User");
        search.setBounds(60,90,130,30);
        add(search);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        addRows();
        
        table.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent arg0){ 
                  //Getting Selected Row No
            int r = table.getSelectedRow();
            if(r>=0){ 
//                  deletebtnButton.setEnabled(true);
                  deletebtn.setEnabled(true); 

                  //Fetching records from Table on Fields
                  idField.setText(""+table.getModel().getValueAt(r,0));
            	}
            }

//          @Override
            public void mouseReleased(MouseEvent arg0) {}
//          @Override
            public void mousePressed(MouseEvent arg0) {}
//          @Override 
            public void mouseExited(MouseEvent arg0) {}
//          @Override 
            public void mouseEntered(MouseEvent arg0) {}
      });

        
        deletebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String RemoveOrder="Delete from UserFace1 where (ID=?)";
				try {
					st=con.prepareStatement(RemoveOrder);
					st.setInt(1,Integer.parseInt(idField.getText()));
					int i = st.executeUpdate();
                    //Executing MySQL Update Query						+++++++++++++++++++++++
                      if(i==1){
                       JOptionPane.showMessageDialog(panel,"Successfully Removed");
                      }
                      
                      refreshTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        

        search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        DefaultTableModel dm = (DefaultTableModel)table.getModel();
		        dm.getDataVector().removeAllElements();
		        System.out.println("Refresh Table");

				
				
				
				String commandSsql="Select * From UserFace1 Where (ID=?)";
				try {
					st=con.prepareStatement(commandSsql);
					st.setInt(1,Integer.parseInt(idField.getText()));
					ResultSet rst = st.executeQuery();
			        Object[] row = null;
			        while(rst.next()){ 
			        	String string = String.valueOf(rst.getInt(1))+","
			                    +rst.getString(2) + "," +rst.getString(3)+ "," + rst.getString(4);
			        	
			              row = string.split(",");
			              

			              // Adding records in table model 
			              model.addRow(row);
			        }
			        panel.revalidate();

					
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

    }
    private void addRows(){
        try{
        Object[] row = null;
        //Generating Serial No
        
        rst = stmt.executeQuery("select * from UserFace1");
        while(rst.next()){ 
        	String string = String.valueOf(rst.getInt(1))+","
                    +rst.getString(2) + "," +rst.getString(3)+ "," + rst.getString(4);
        	
              row = string.split(",");
              

              // Adding records in table model 
              model.addRow(row);
        }
        panel.revalidate();
        }catch(Exception ex){ System.out.println(ex.getMessage()); }
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

	
    private void refreshTable(){
    	 
        // removing all the rows of the table
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        dm.getDataVector().removeAllElements();
        System.out.println("Refresh Table");

        //calling method addRows
        addRows();
  }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RemovingUser();

	}
	
	
	
	

}
