
public class MessageDigest {
	private int length;
	private String digest;
	
	
	
	
	public MessageDigest(String digest){
		
		this.length=digest.length();
		this.digest=digest;
	}
	
	
	/*****************************************************************************************************/
	public String getDigest(){
		return this.digest;
	}
	/*****************************************************************************************************/
	
	public int getLength(){
		return this.length;
	}
	/*****************************************************************************************************/
	public void setDigest(String s){
		this.digest=s;
		this.length=s.length();
	}
	/*****************************************************************************************************/
		
	
		public boolean isBinary() 
	{			 
		    String b;  
            b= digest;
            if(b.length()== 0)
            	return false;
            while(b!= null && b.length() > 0) 
                 { 
                    if((b.charAt(b.length()-1)!='0') && b.charAt(b.length()-1)!='1') 
                      return false;
                       b= b.substring(0, b.length()-1);
                 } 
             return true;    
	}
		
	/*****************************************************************************************************/
		
		
	public boolean isHex()
	{
			
		    String b;  
            b= digest; 
            if(b.length()== 0)
            	return false;
            while(b!= null && b.length() > 0) 
                 { 
                    if((b.charAt(b.length()-1)!='0') && b.charAt(b.length()-1)!='1' && b.charAt(b.length()-1)!='2'&& b.charAt(b.length()-1)!='3'&& b.charAt(b.length()-1)!='4'&& b.charAt(b.length()-1)!='5'&& b.charAt(b.length()-1)!='6'&& b.charAt(b.length()-1)!='7'&& b.charAt(b.length()-1)!='8'&& b.charAt(b.length()-1)!='9'&& 
                    		    b.charAt(b.length()-1)!='a'&& b.charAt(b.length()-1)!='b'&& b.charAt(b.length()-1)!='c'&& b.charAt(b.length()-1)!='d'&& b.charAt(b.length()-1)!='e'&& b.charAt(b.length()-1)!='f'){ 
                      return false;
                    }
                       b= b.substring(0, b.length()-1);
                 } 
                     
             return true;  			
	}
	
	/*****************************************************************************************************/
	public String HowMuchOnes(){
		int i;
		int count=0;
		for(i=0 ; i<this.length ; i++){
			if(this.digest.charAt(i)=='1')
				count++;
		}
	String s=Integer.toString(count, 2);
		return s;
		
	}
	/*****************************************************************************************************/
	public String HowMuchOnesInDivI(){
		int i;
		int count=0;
		for(i=0 ; i<this.length ; i++){
			if((i&2)==0 && this.digest.charAt(i)=='1')
				count++;
		}
	String s=Integer.toString(count, 2);
		return s;
		
	}
	
	
	/*****************************************************************************************************/


	public MessageDigest[] createBlocks(int blocklen){
		int NumOfBlocks;
		NumOfBlocks=this.length/blocklen;
		String s1=this.digest;
		String s="";
		
		MessageDigest m[]= new MessageDigest[NumOfBlocks];
		for(int i=0; i<m.length; i++){
			s=s1.substring(blocklen*i, (blocklen)+i*blocklen);
			m[i]=new MessageDigest(s);
		}
		return m;
			
	}
	
	/*****************************************************************************************************/
	public MessageDigest Xor(MessageDigest m){
		MessageDigest result=this;
		char[] arr= new char[this.length];
		int i ;
		for(i=this.length-1;i>=0;i--){
			if(this.digest.charAt(i)==m.digest.charAt(i))
				arr[i]='0';	
			else
				arr[i]='1';	
		}
		result.digest=String.valueOf(arr);;
		return result;
	}
	/*****************************************************************************************************/
	
	public MessageDigest And(MessageDigest m){
		MessageDigest result=this;
		char[] arr= new char[this.length];
		int i ;
		for(i=this.length-1;i>=0;i--){
			if(this.digest.charAt(i)=='1' && m.digest.charAt(i)=='1')
				arr[i]='1';	
			else
				arr[i]='0';	
		}
		result.digest=String.valueOf(arr);;
		return result;
	}
	/*****************************************************************************************************/
	public MessageDigest Or(MessageDigest m){
		MessageDigest result=this;
		char[] arr= new char[this.length];
		int i ;
		for(i=this.length-1;i>=0;i--){
			if(this.digest.charAt(i)=='1' || m.digest.charAt(i)=='1')
				arr[i]='1';	
			else
				arr[i]='0';	
		}
		result.digest=String.valueOf(arr);;
		return result;
	}
	
	/*****************************************************************************************************/
	public MessageDigest Add(MessageDigest m){
		MessageDigest result=this;
		boolean carry=false;
		char[] arr= new char[this.length];
		int i ;
		for(i=this.length-1;i>=0;i--){
			
			if(this.digest.charAt(i)=='0' && m.digest.charAt(i)=='0' && !carry){
				arr[i]='0';
			}
			else if(this.digest.charAt(i)=='0' && m.digest.charAt(i)=='0' && carry){
				arr[i]='1';
				carry=false;
			}
			else if(this.digest.charAt(i)=='1' && m.digest.charAt(i)=='1' && carry){
				arr[i]='1';
			}
			else if(this.digest.charAt(i)=='1' && m.digest.charAt(i)=='1' && !carry){
				arr[i]='0';
				carry=true;
			}
			else if(((this.digest.charAt(i)=='1' && m.digest.charAt(i)=='0') || (this.digest.charAt(i)=='0' && m.digest.charAt(i)=='1')) && carry){
				arr[i]='0';
			}
			else if(((this.digest.charAt(i)=='1' && m.digest.charAt(i)=='0') || (this.digest.charAt(i)=='0' && m.digest.charAt(i)=='1')) && !carry){
				arr[i]='1';
			}
				
		}
		result.digest=String.valueOf(arr);;
		return result;
	}
	/*****************************************************************************************************/

	public MessageDigest Not(){
		MessageDigest result=this;
		char[] arr= new char[this.length];
		int i ;
		for(i=this.length-1;i>=0;i--){
			if(this.digest.charAt(i)=='0')
				arr[i]='1';	
			else
				arr[i]='0';	
		}
		result.digest=String.valueOf(arr);;
		return result;
	}
	/*****************************************************************************************************/

	public MessageDigest LS(int ls){
		
		String s=this.digest;  
		String result = s.substring(ls);

	    for (int i = 0; i < ls; i++) {

	        result += s.charAt(i);

	    }
		    return new MessageDigest(result);
	}
	/*****************************************************************************************************/

	public MessageDigest RS(int rs){
		
		
	return LS(this.length-rs);
	}
	/*****************************************************************************************************/
	public MessageDigest Pade(int padelen, int blocklen){
		MessageDigest result=this;
		if(result.length%blocklen==padelen%blocklen)
			return result;
		else{
			result.digest=result.digest+'1';
			result.length++;
			if(result.length%blocklen==padelen%blocklen)
				return result;
			else{
				while(result.length%blocklen!=padelen%blocklen){
					result.digest=result.digest+'0';
					result.length++;
				}
				return result;
			}
	      
		}
		
		
	}
	/*****************************************************************************************************/
	public MessageDigest LastBlock(int blocklen){
		String s=Integer.toString(this.length, 2);
		int count=blocklen-s.length();
		while(count>0){
			s='0'+s;
			count--;
		}
		MessageDigest result=new MessageDigest(s);
		return result;
	}
	/*****************************************************************************************************/
	public MessageDigest OnesBlock(int blocklen){
		String s=this.HowMuchOnes();
		int count=blocklen-s.length();
		while(count>0){
			s='0'+s;
			count--;
		}
		MessageDigest result=new MessageDigest(s);
		return result;
	}
	/*****************************************************************************************************/
	public MessageDigest OnesBlock1(int blocklen){
		String s=this.HowMuchOnesInDivI();
		int count=blocklen-s.length();
		while(count>0){
			s='0'+s;
			count--;
		}
		MessageDigest result=new MessageDigest(s);
		return result;
	}
	/*****************************************************************************************************/

	public String BinToHex(){
		String s1=this.digest;
		int div=this.length/4;
		String check;
		String s="";
		
		for(int i=0; i<div ; i++){
			check=s1.substring(4*i, 4+i*4);
		//	System.out.println(check);
			
			switch(check)
			{
			case "0000":
				s=s+'0';
				break;
			case "0001":
				s=s+'1';
				break;
			case "0010":
				s=s+'2';
				break;
			case "0011":
				s=s+'3';
				break;
			case "0100":
				s=s+'4';
				break;
			case "0101":
				s=s+'5';
				break;
			case "0110":
				s=s+'6';
				break;
			case "0111":
				s=s+'7';
				break;
			case "1000":
				s=s+'8';
				break;
			case "1001":
				s=s+'9';
				break;
			case "1010":
				s=s+'A';
				break;
			case "1011":
				s=s+'B';
				break;
			case "1100":
				s=s+'C';
				break;
			case "1101":
				s=s+'D';
				break;
			case "1110":
				s=s+'E';
				break;
			case "1111":
				s=s+'F';
				break;
			}

	}
		return s;
	
	}
	
	
		

}
