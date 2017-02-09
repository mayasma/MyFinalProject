import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleHashFunc extends JPanel implements ActionListener
{
	private static final int  cut = 10; // limit 
	private JLabel lbl_result;
	private JButton btnChooseFile,btnProcess;
	private JTextField txt_xor;
	public SimpleHashFunc()
	{
		setLayout(new GridLayout(3,1,2,2));
		btnProcess= new JButton("generate");
		lbl_result = new JLabel("");
		txt_xor= new JTextField();
		this.add(txt_xor);
		this.add(btnProcess);
		this.add(lbl_result);
		btnProcess.addActionListener(this);
		lbl_result.setText(" * * * * * ");

	}
	protected void paintComponent(Graphics g) // method that sets the background color and handles draw on the screen
	{
		super.paintComponent(g);
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==btnProcess)
		{
				lbl_result.setText(simple_hash_func(txt_xor.getText()));
		}

	}

	public static boolean check_input(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i)!= '0' && str.charAt(i)!= '1')
				return true;
		}
		return false;
	}

	public static String xor(String s1 ,String s2)
	{
		String result="";
		for (int i = 0; i < s1.length(); i++) 
		{
			result+= s1.charAt(i)==s2.charAt(i) ? 0 : 1;
		}
		return result;

	}

	public static String simple_hash_func(String hash_input)
	{

		//		final int  cut = 5; // limit 
		//		Scanner scan = new Scanner(System.in);
		//		String hash_input = scan.nextLine(); // input

		if(check_input(hash_input))
		{
			System.out.println("worng input");
			return "worng input";
		}

		List<String> list = new ArrayList<String>();
		String str = "";
		String temp = hash_input;
		
		while(!temp.isEmpty())
		{
			for (int i = 0; i < cut ; i++) 
			{
				if(i>=temp.length())
					str+=0;
				else
					str += temp.charAt(i);
			}
			list.add(str);
			if(cut>temp.length())
				break;
			temp = temp.substring(cut);
			str = "";
		}

		System.out.println(list);


		String result , s2;

		Iterator itr = list.iterator();
		Object element = itr.next();
		result = element.toString();
		while(itr.hasNext())
		{
			element = itr.next();
			s2 = element.toString();
			result = xor(result,s2);
		}
		System.out.println("the result is : "+result);
		return result;

	}



}
