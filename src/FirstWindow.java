import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FirstWindow extends JFrame {

	JLabel removeUser, Register;
	JButton rm,reg;
	
	public FirstWindow(){
		super("Welcome");
		setSize(500, 300);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        
        
        
        rm=new JButton("Remove User");
        rm.setBounds(5,150,200,50);
        reg=new JButton("Register New");
        reg.setBounds(220, 150, 200, 50);
        
        removeUser=new JLabel("Remove User");
        removeUser.setBounds(5,5,100,30);
        Register=new JLabel("Register");
        Register.setBounds(220, 6, 100, 25);
        
        
        add(removeUser);
        add(Register);
        add(rm);
        add(reg);
        
        
        reg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
					setVisible(false);
					
					DynamicRegForm.main(null);
				
			}
		});
        
//        rm.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				RemovingUser.main(null);
//				
//			}
//		});

        
        
        this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FirstWindow();

	}

}
