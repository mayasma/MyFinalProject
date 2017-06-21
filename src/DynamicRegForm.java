import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
import java.awt.event.*;
import java.sql.*;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class DynamicRegForm extends RegistrationFormGUI{
	String gender = "";
	ResultSet rst,rstLast;
	Object[][] data;
	int serialNo; 
	String SHOW = "Show";
	RegistrationFormGUI formGUIObject;

	// Defining Constructor
	DynamicRegForm(){

		connect();
		
		nameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(nameField.getText().length()>=15)
					e.consume();
			}
		});
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					con.close();
					System.exit(0);
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});

		//=====
		//*********************	Register With Hash Function*********************
		//=====
		registerButton.addActionListener(new AbstractAction(SHOW){
			public void actionPerformed(ActionEvent ae){
				try{
					if (ae.getSource() == registerButton) {
						if (idField.getText().equals(""))
							JOptionPane.showMessageDialog(idField, 
									"Please provide Name_Field");
						else if(nameField.getText().equals(""))
							JOptionPane.showMessageDialog(idField, 
									"Please provide Address_Field");
						else if(Password.getText().equals(""))
							JOptionPane.showMessageDialog(idField, "Please provide Contact_Field");
						else {
							//Fetching column values from Database


							//Using Face Hash
							String insertTableSQL = "INSERT INTO UserFace1"
									+ "(ID, UserName,UserPassword,UserFace ) VALUES"
									+ "(?,?,?,?)";

							st=con.prepareStatement(insertTableSQL);
							
							st.setInt(1,Integer.parseInt(idField.getText()));
							st.setString(2,nameField.getText());
							st.setInt(3, Integer.parseInt(Password.getText()));
							st.setString(4, "khalid123");
							int i = st.executeUpdate();




							//Executing MySQL Update Query						+++++++++++++++++++++++
							if(i==1){
								JOptionPane.showMessageDialog(panel, 
										"Successfully Registered");
							}

							// showing last row 
							rstLast = stmt.executeQuery("select * from UserFace1");
							rstLast.next();


							String string = String.valueOf(rstLast.getInt(1))+","+rstLast.getString(2)+","+rstLast.getString(3)+","+rstLast.getString(4);
							Object[] row = null;
							row = string.split(",");
							model.addRow(row);
							panel.revalidate();

							// fields are blank
							blankFields();
						}
					}
				}catch(Exception ex){
					System.out.println(ex.getMessage()); 
				}
			}
		});


		//Registering Anonymous Listener Class
		deleteButton.addActionListener(new AbstractAction(SHOW){ 
			public void actionPerformed(ActionEvent e){
				try{ 
					//Getting Selected Row No
					int r = table.getSelectedRow(); 
					if(r>=0){ 
						if (JOptionPane.showConfirmDialog(panel,
								"Are you sure want to Delete this 'RECORD' ?","WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							String id = (String)table.getModel().getValueAt(r,1);

							// Executing MySQL Update Command
							int i = stmt.executeUpdate("delete from UserFace1 where id="+id);
							if(i==1){
								model.removeRow(r);

								// fields are blank
								blankFields();
								registerButton.setEnabled(true);
								deleteButton.setEnabled(false);
								updateButton.setEnabled(false);
							}
						}
					}
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});

		//Registering Anonymous Listener Class
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// calling method resetFields()
				resetFields();
				registerButton.setEnabled(true);
				updateButton.setEnabled(false);
				deleteButton.setEnabled(false);
				resetButton.setEnabled(false);
			}
		});

		// Registering Anonymous Listener Class
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calling  refresh() method
				refreshTable();
			}
		});

		//Registering Anonymous Listener Class
		table.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0){ 
				//Getting Selected Row No
				int r = table.getSelectedRow();
				if(r>=0){ 
					deleteButton.setEnabled(true);
					registerButton.setEnabled(false); 

					//Fetching records from Table on Fields
					idField.setText(""+table.getModel().getValueAt(r,0));
					nameField.setText(""+table.getModel().getValueAt(r,1));
					Password.setText(""+table.getModel().getValueAt(r,3));

				}
			}

			//                @Override
			public void mouseReleased(MouseEvent arg0) {}
			//                @Override
			public void mousePressed(MouseEvent arg0) {}
			//                @Override 
			public void mouseExited(MouseEvent arg0) {}
			//                @Override 
			public void mouseEntered(MouseEvent arg0) {}
		});

		// Displaying rows into the Frame table
		addRows();
	}
	// addRows method





	public void connect(){
		try{

			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database = "jdbc:mysql://localhost:3306/MySQL?autoReconnect=true&useSSL=false";
			con =DriverManager.getConnection(database,"root", "khalid123");
			stmt = con.createStatement();
			//     con.prepareStatement("insert into regForm(ID,Name,Password,Faces) values(?,?,?,?)");


		}catch(Exception e){
			System.out.print(e.getMessage());
		}
	}

	private void addRows(){
		try{
			Object[] row = null;
			//Generating Serial No
			serialNo=1;

			rst = stmt.executeQuery("select * from UserFace1");
			while(rst.next()){ 
				String string = String.valueOf(rst.getInt(0))+","
						+rst.getString(1) + "," +rst.getString(2)+ "," + rst.getString(3);

				row = string.split(",");


				// Adding records in table model 
				model.addRow(row);
			}
			panel.revalidate();
		}catch(Exception ex){ System.out.println(ex.getMessage()); }
	}

	private void resetFields(){ 

		//calling method blankfields() 
		blankFields();
	}

	// refresh method
	private void refreshTable(){

		// removing all the rows of the table
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		dm.getDataVector().removeAllElements();
		System.out.println("Refresh Table");

		//calling method addRows
		addRows();
	}

	private void blankFields(){
		// fields will be blank
		idField.setText("");
		nameField.setText("");
		bg.clearSelection();
		Password.setText("");

	}

	// main() method
	public static void main(String[] args) {
		new DynamicRegForm();
	}     
}