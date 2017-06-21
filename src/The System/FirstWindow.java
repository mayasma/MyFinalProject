import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FirstWindow extends JFrame {

	JLabel removeUser, Register,welcome_screen;
	JButton rm,reg;
	
	
	
	public FirstWindow(){
		
		setSize(400, 300);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        
        
        
        rm=new JButton("Remove User");
        rm.setBounds(5,150,200,30);
        reg=new JButton("Register New");
        reg.setBounds(220, 150, 200, 30);
        
        removeUser=new JLabel("Remove User");
        removeUser.setBounds(5,100,100,30);
        Register=new JLabel("Register");
        Register.setBounds(220, 6, 100, 25);
        
        welcome_screen=new JLabel("Welcome To My Program");
        welcome_screen.setBounds(100, 10, 200, 25);
        
//        add(removeUser);
//        add(Register);
        add(rm);
        add(reg);
        add(welcome_screen);
        
        reg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
					setVisible(false);
					
					DynamicRegForm.main(null);
				
			}
		});
        
        rm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				RemovingUser.main(null);
				
			}
		});

        
        this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FirstWindow();

	}

}
