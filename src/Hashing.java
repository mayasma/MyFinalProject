
public class Hashing {

	
	

	public MessageDigest getHashedValue(String inputData) {
		  MessageDigest m = new MessageDigest(inputData);
				// MessageDigest lastblock=m.LastBlock(64);
					 MessageDigest onesblock=m.OnesBlock(12);
					 MessageDigest paderesult=m.Pade(500, 512);
					 MessageDigest afterpadding=concatenation(paderesult,onesblock);
					 MessageDigest blocks[]=afterpadding.createBlocks(512);
						/**************************************IV***************************************************************/
					 MessageDigest A=new MessageDigest("11100111011100010101001001110010");
					 MessageDigest B=new MessageDigest("01010101110111000010001101100010");
					 MessageDigest C=new MessageDigest("11100010100111011000010111101100");
					 MessageDigest D=new MessageDigest("11110001010010100101010000001011");
					 MessageDigest E=new MessageDigest("10101110000101010111011010100010");
			/*****************************************************************************************************/	 
					 MessageDigest T1;
					 MessageDigest T2;
					 MessageDigest At=A;
					 MessageDigest Bt=B;
					 MessageDigest Ct=C;
					 MessageDigest Dt=D;
					 MessageDigest Et=E;
					 MessageDigest lAt=At;
					 MessageDigest lBt=Bt;
					 MessageDigest lCt=Ct;
					 MessageDigest lDt=Dt;
					 MessageDigest lEt=Et;
					 
					 String [] str={"11010111011010101010010001111000","11101000110001110110011101010110","00100100001000000111000011011011","11000001101111011100111011101110","11110101 011111000000111110101111","01000111100001111100011000101010","10011000001100000100011000010011","11111101010001101001010100000001","01101001100000001001100011011000","10000110010001001111011110101111","11111111111111110101101110110001","10001001010111001101011110111110","01101011100100000001000100100010","11111101101010000111000110010011","10100110011110010100001110001110","01001001011001000000100000100001","11110110000111100010010101100010","11000000010000000110001101000000","00100110010111100101101001010001","11101001101101101100011110101010","11010110001011110001000001011101","00000010010001000001010001010011","11011000101000011110011010000001","11100111110100111111101111001000","00100001111000011100110111101011","11000011001101110000011111010110","11110100110101010000110110000111","01000101010110100001010011101101","10101001111000111110100100000101","11111100111011111010001111111000","01100111011011110000001011011001","10001101001010100100110010001001"};
					 MessageDigest Kt = new MessageDigest("");
					 int j;
					 int i;
					 MessageDigest wt[]=blocks[4].createBlocks(32);
					 MessageDigest wt2[] = new MessageDigest[64];
					 for(i=0;i<wt.length;i++)
						 wt2[i]=wt[i];
					 for(i=16;i<wt2.length;i++)
						 wt2[i]=shift1(wt2[i-7]).Xor(shift0(wt2[i-13])).Xor(wt2[i-2]);
					 Kt.setDigest(str[3%32]);
					 T1=sigma1(lCt).Add(Kt);
					 return T1;
	 	}
	
	
	
	
	
	
	
	
	//lEt.Add((sigma1(lCt).Add(Kt))).Add((f(lCt,lDt,lEt).Add(wt2[15])))
	
	
	
	
	
	
	  private static MessageDigest concatenation(MessageDigest m1, MessageDigest m2) {
	    	String s=m1.getDigest()+m2.getDigest();
	    	MessageDigest result=new MessageDigest(s);
	    	return result;
	    	
	    }
	  public static MessageDigest f(MessageDigest m1, MessageDigest m2,MessageDigest m3){
		  return ((m1.Xor(m2)).Xor(m3)).And(m2);
	  }
	  public static MessageDigest shift0(MessageDigest m1){
		  return (m1.LS(17)).Xor((m1.LS(21))).Xor(m1.LS(3));
	  }
	  public static MessageDigest shift1(MessageDigest m1){
		  return (m1.RS(10)).Xor((m1.RS(13))).Xor(m1.RS(8));
	  }
	  public static MessageDigest sigma1(MessageDigest m1){
		  return (m1.RS(20)).Xor((m1.RS(19))).Xor(m1.RS(3));
	  }

}
