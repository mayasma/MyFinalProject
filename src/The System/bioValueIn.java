import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class bioValueIn extends JFrame
{
	
	JTextField bio_value;
    JButton check;
    JLabel lbl;
    int flag;
   static DynamicRegForm dynamic ;
    
    public bioValueIn(int num)
    {
    	
	setSize(600, 250);
    setLayout(null);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    lbl = new JLabel("insert your bio value :");
    lbl.setBounds(100, 40, 200, 40);
    
    bio_value = new JTextField(); 
    bio_value.setBounds(100, 80, 400, 30);
    
    check = new JButton("Scan");
    check.setBounds(250, 130, 100, 40);
    
    add(check);
    add(bio_value);
    add(lbl);
    
    check.addActionListener(new ActionListener() 
    {
		public void actionPerformed(ActionEvent e) 
		{
			      System.out.println(num);                    	
		MessageDigest res;
		if(num==1){
			HashFunc1 hash =new HashFunc1();
			 res = hash.getHashedValue(bio_value.getText().toString());
		}
		else if(num==2){
			HashFunc2 hash =new HashFunc2();
			 res = hash.getHashedValue(bio_value.getText().toString());
		}
		else if(num==3){
			HashFunc3 hash =new HashFunc3();
			 res = hash.getHashedValue(bio_value.getText().toString());
		}
		else if(num == 4){
			HashFunc4 hash =new HashFunc4();
			 res = hash.getHashedValue(bio_value.getText().toString());
		}
		else
		{
			res = null;
			return;
		}
		// bio_value.setText(res.BinToHex().toString());
		
		dynamic = new DynamicRegForm();
		dynamic.res=res.BinToHex().toString();
		// dynamic.flag = false;
		dispose();
		}
	});
    
    this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

    }
    
}
