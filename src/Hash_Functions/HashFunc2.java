
public class HashFunc2 {
	  public HashFunc2() {
	    }
	  public MessageDigest getHashedValue(String inputData){
		  
		  MessageDigest m = new MessageDigest(inputData);
		  MessageDigest onesblock=m.OnesBlock(12);
		  MessageDigest lastblock=m.LastBlock(12);
	  	  MessageDigest paderesult=m.Pade(488, 512);
		  MessageDigest afterpadding=concatenation(paderesult,onesblock,lastblock);
		  MessageDigest blocks[]=afterpadding.createBlocks(512);
/**************************************IV***************************************************************/
		 MessageDigest A=new MessageDigest("11100111011100010101001001110010"); //E7715272
		 MessageDigest B=new MessageDigest("01010101110111000010001101100010"); //55DC2362
		 MessageDigest C=new MessageDigest("11100010100111011000010111101100"); //E29D85EC
		 MessageDigest D=new MessageDigest("11110001010010100101010000001011"); //F14A540B
		 MessageDigest E=new MessageDigest("10101110000101010111011010100010"); //AE1576A2
/*****************************************************************************************************/	 
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
		 MessageDigest Kt = new MessageDigest("");
		 int j;
		 int i;
		 
/***********************************************************************************/
	for(j=0; j<blocks.length; j++){
		 MessageDigest wt[]=blocks[j].createBlocks(32);
		 
/***************************************************/
		for(i=0;i<64;i++){
			if(i%8==0)
				Kt.setDigest("01000111100001111100011000100101"); //4787C625
			else if(i%4==0)
				Kt.setDigest("11111100100100111010000000111001"); //FC93A039
			else if(i%2==0)
				Kt.setDigest("11000100101011000101011001100101"); //C4AC5665
			else if(i%2==1)
				Kt.setDigest("00100110010111100101101001010001"); //265E5A51
				
			lAt=At;
			lBt=Bt;
			lCt=Ct;
			lDt=Dt;
			lEt=Et;
			
			
			At=((f(lAt,lBt,lCt,lEt,i).Add(sigma(wt,i))).Add(Kt));
			Bt=lAt;
			Ct=lBt.LS(5);
			Dt=lCt;
			Et=lDt;
		}
/***************************************************/
	
		
	}
/***********************************************************************************/
	
	
	A=A.Add(At);
	B=B.Add(Bt);
	C=C.Add(Ct);
	D=D.Add(Dt);
	E=E.Add(Et);
		 
		 
		 
		 MessageDigest lastresult=concatenation(A, B,C,D,E);
	
		  return lastresult;
	  }
	  
	  
	  
	  
	  
	  

/**************************************StaticFunc's**********************************************************/	
	  
	  private MessageDigest concatenation(MessageDigest m1, MessageDigest m2, MessageDigest m3) {
		  String s=m1.getDigest()+m2.getDigest()+m3.getDigest();
	    	MessageDigest result=new MessageDigest(s);
	    	return result;
		}
	  private static MessageDigest concatenation(MessageDigest m1, MessageDigest m2,MessageDigest m3, MessageDigest m4,MessageDigest m5) {
	    	String s=m1.getDigest()+m2.getDigest()+m3.getDigest()+m4.getDigest()+m5.getDigest();
	    	MessageDigest result=new MessageDigest(s);
	    	return result;
	    }
/**********************************************/
	  public static MessageDigest f(MessageDigest m1, MessageDigest m2,MessageDigest m3,MessageDigest m4 ,int t){
		  if(t>=0 && t<=15)
		  return (m1.Xor(m2)).Xor(m3.Xor(m4));
		  else if((t>=16 && t<=31))
			  return (m1.Or(m2)).And(m3.Or(m4));
		  else if(t>=32 && t<=47)
			  return (m1.And(m2)).Or(m3.And(m4));
		  else if(t>=48 && t<=63)
			  return (m1.Not().Xor(m2.Not())).Xor((m3.Not()).Xor(m4.Not()));
		  else 
			  return m1;
	  }
/**********************************************/
   public static MessageDigest sigma(MessageDigest []ms,int i){
		  int[] x = {0,2,1,3,4,5,6,7,8,9,10,11,12,13,14,15,11,2,6,9,10,3,15,1,8,14,4,13,7,5,0,12,2,5,8,11,14,3,6,9,12,15,1,4,7,10,13,0,15,10,5,0,1,4,8,12,6,7,3,9,11,14,2,13};

		  if(i<62)
			  return ms[x[i]].Xor(ms[x[i+1]]).Xor(ms[x[i+2]]);
		  
		  else if(i==62)
			  return ms[x[62]].Xor(ms[x[63]]).Xor(ms[x[0]]);
		  else
			  return  ms[x[63]].Xor(ms[x[0]]).Xor(ms[x[1]]);
			  
	  }
	  
	
}
