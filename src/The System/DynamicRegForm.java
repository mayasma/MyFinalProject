import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("serial")
public class DynamicRegForm extends RegistrationFormGUI{
	String gender = "";
	ResultSet rst,rstLast;
	Object[][] data;
	int serialNo; 
	//public static boolean flag;
	String SHOW = "Show";
	RegistrationFormGUI formGUIObject;
	ResultSet  rst3 ;
	String res = null;
	bioValueIn bios;
	// Defining Constructor
    tryto tt = new tryto();
allfunctions all;
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
		face_btn.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				try{
					all = new allfunctions();
					
					String randomNum;
					rst3 =stmt.executeQuery("Select * From rand");
					if( rst3.next()) {
						randomNum  =  rst3.getString(1);
					 bios = new bioValueIn(all.randFun(randomNum));
					 dispose();
					}
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}

			}
		});

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
						else if(res.equals(""))
						  JOptionPane.showMessageDialog(idField, "Please provide Contact_Field");
						else {
								

							String insertTableSQL = "INSERT INTO UserFace1"
									+ "(ID, UserName,UserPassword,UserFace ) VALUES"
									+ "(?,?,?,?)";

							st=con.prepareStatement(insertTableSQL);
							st.setInt(1,Integer.parseInt(idField.getText()));
							st.setString(2,nameField.getText() );
							st.setInt(3, Integer.parseInt("111"));
							st.setString(4, res);
							int i = st.executeUpdate();




							//Executing MySQL Update Query						+++++++++++++++++++++++
							if(i==1){
								res = "";
								JOptionPane.showMessageDialog(panel, 
										"Successfully Registered");
							}


							refreshTable();
							// fields are blank
							blankFields();
						}
					}
				}catch(Exception ex){
					System.out.println(ex.getMessage()); 
				}
			}
		});


		backbutn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				FirstWindow.main(null);
			}
		});




		//Registering Anonymous Listener Class
		table.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0){ 
				//Getting Selected Row No
				int r = table.getSelectedRow();
				if(r>=0){ 
					//                  deleteButton.setEnabled(true);
					registerButton.setEnabled(false); 

					//Fetching records from Table on Fields
					idField.setText(""+table.getModel().getValueAt(r,0));
					nameField.setText(""+table.getModel().getValueAt(r,1));
					// Password.setText(""+table.getModel().getValueAt(r,3));

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






	//@method read from database to the table
	private void addRows(){
		try{
			Object[] row = null;
			//Generating Serial No
			serialNo=1;

			rst = stmt.executeQuery("select ID,UserName,UserFace from UserFace1");
			while(rst.next()){ 
				String string = String.valueOf(rst.getInt(1))+","
						+rst.getString(2) + "," +rst.getString(3);

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