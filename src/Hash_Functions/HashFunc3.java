
public class HashFunc3 {
	  public MessageDigest getHashedValue(String inputData){
		  MessageDigest m = new MessageDigest(inputData);
		  MessageDigest onesblock=m.OnesBlock(12);
		  MessageDigest lastblock=m.LastBlock(12);
	  	  MessageDigest paderesult=m.Pade(488, 512);
		  MessageDigest afterpadding=concatenation(paderesult,onesblock,lastblock);
		  MessageDigest blocks[]=afterpadding.createBlocks(512);
/**************************************IV***************************************************************/
			 MessageDigest A=new MessageDigest("10011111100010101011110100011011"); //9F8ABD1B
			 MessageDigest B=new MessageDigest("00000101011100011110111010101100"); //0571EEAC
			 MessageDigest C=new MessageDigest("01010101100101010110101010101011"); //55956AAB
			 MessageDigest D=new MessageDigest("11111110100000001000011101101010"); //FE80876A
			 MessageDigest E=new MessageDigest("00010010110010101110111100110110"); //12CAEF36
/*****************************************************************************************************/	 
			 MessageDigest T1;
			 MessageDigest T2;
			 MessageDigest At=A;
			 MessageDigest Bt=B;
			 MessageDigest Ct=C;
			 MessageDigest Dt=D;
			 MessageDigest Et=E;
			 MessageDigest lAt;
			 MessageDigest lBt;
			 MessageDigest lCt;
			 MessageDigest lDt;
			 MessageDigest lEt;
			 
			 String [] str={"11010111011010101010010001111000","11101000110001110110011101010110","00100100001000000111000011011011","11000001101111011100111011101110","11110101 011111000000111110101111","01000111100001111100011000101010","10011000001100000100011000010011","11111101010001101001010100000001","01101001100000001001100011011000","10000110010001001111011110101111","11111111111111110101101110110001","10001001010111001101011110111110","01101011100100000001000100100010","11111101101010000111000110010011","10100110011110010100001110001110","01001001011001000000100000100001","11110110000111100010010101100010","11000000010000000110001101000000","00100110010111100101101001010001","11101001101101101100011110101010","11010110001011110001000001011101","00000010010001000001010001010011","11011000101000011110011010000001","11100111110100111111101111001000","00100001111000011100110111101011","11000011001101110000011111010110","11110100110101010000110110000111","01000101010110100001010011101101","10101001111000111110100100000101","11111100111011111010001111111000","01100111011011110000001011011001","10001101001010100100110010001001"};
			 MessageDigest Kt = new MessageDigest("");
			 int j;
			 int i;
			 
			
/***********************************************************************************/			 		
		for(j=0; j<blocks.length; j++){
			 MessageDigest wt[]=blocks[j].createBlocks(32);
			 MessageDigest wt2[] = new MessageDigest[64];
			 for(i=0;i<wt.length;i++)
				 wt2[i]=wt[i];
			 for(i=16;i<wt2.length;i++)
				 wt2[i]=shift1(wt2[i-7]).Xor(shift0(wt2[i-13])).Xor(wt2[i-2]);
/***************************************************/
				for(i=0;i<wt2.length;i++){
				Kt.setDigest(str[i%32]);
				lAt=At;
				lBt=Bt;
				lCt=Ct;
				lDt=Dt;
				lEt=Et;
				
				T1=lEt.Add((f(lCt,lDt,lEt).Add(wt2[i]))).Add((sigma1(lCt).Add(Kt)));
				T2= sigma0(lBt).Add(g(lAt,lBt));
				At=T1.Add(T2);
				Bt=lAt.Add(Kt).Xor(wt2[i]);
				Ct=lBt.Add(T1); 
				Dt=lCt;
				Et=lDt;
				
				
					
				} 
/***************************************************/				
 		
		}
/***********************************************************************************/
		
		A=At;
		B=Bt;
		C=Ct;
		D=Dt;
		E=Et;
		
		return concatenation(A,B,C,D,E)	;  
	  }
	  
	  
	  
	  
/**************************************StaticFunc's**********************************************************/	
	  
	  public static MessageDigest f(MessageDigest m1, MessageDigest m2,MessageDigest m3){
		  return ((m1.Xor(m2)).Xor(m3)).And(m2);
	  }
/**********************************************/
	  public static MessageDigest g(MessageDigest m1, MessageDigest m2){
		  return (m1.Add(m2)).Xor(m1);
	  }
/**********************************************/
	  public static MessageDigest shift0(MessageDigest m1){
		  return (m1.LS(17)).Xor((m1.LS(21))).Xor(m1.LS(3));
	  }
/**********************************************/
	  public static MessageDigest shift1(MessageDigest m1){
		  return (m1.RS(10)).Xor((m1.RS(13))).Xor(m1.RS(8));
	  }
/**********************************************/
	  public static MessageDigest sigma0(MessageDigest m1){
		  return (m1.RS(5)).Xor((m1.RS(16))).Xor(m1.RS(8));
	  }
/**********************************************/
	  public static MessageDigest sigma1(MessageDigest m1){
		  return (m1.RS(20)).Xor((m1.RS(19))).Xor(m1.RS(3));
	  }
/**********************************************/
	  private static MessageDigest concatenation(MessageDigest m1, MessageDigest m2,MessageDigest m3, MessageDigest m4,MessageDigest m5) {
	    	String s=m1.getDigest()+m2.getDigest()+m3.getDigest()+m4.getDigest()+m5.getDigest();
	    	MessageDigest result=new MessageDigest(s);
	    	return result;
	    }
	  private MessageDigest concatenation(MessageDigest m1, MessageDigest m2, MessageDigest m3) {
		  String s=m1.getDigest()+m2.getDigest()+m3.getDigest();
	    	MessageDigest result=new MessageDigest(s);
	    	return result;
		}
	  

}
