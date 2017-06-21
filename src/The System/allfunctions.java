import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class allfunctions 
{

    
	public allfunctions()
	{
		
		
	}
	public static int randFun(String num)
	{

		HashFunc1 hash =new HashFunc1();
		String a,b,c,d;
		a = hash.getHashedValue("0000").BinToHex();
		b = hash.getHashedValue("0001").BinToHex();
		c = hash.getHashedValue("0010").BinToHex();
		d = hash.getHashedValue("0011").BinToHex();
		
		if(a.equals(num))
			return 0;
		else if(b.equals(num))
			return 1;
		else if(c.equals(num))
			return 2;
		else if(d.equals(num))
			return 3;
		else return -1;
		
		
	}
	
	
	
}
