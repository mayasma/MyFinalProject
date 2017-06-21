
public class HashFunc1 {
	  public HashFunc1() {
	    }
	  public MessageDigest getHashedValue(String inputData){
		  
		 MessageDigest m = new MessageDigest(inputData);
		  MessageDigest onesblock=m.OnesBlock(12);
		  MessageDigest lastblock=m.LastBlock(12);
	  	  MessageDigest paderesult=m.Pade(488, 512);
		  MessageDigest afterpadding=concatenation(paderesult,onesblock,lastblock);
		  MessageDigest blocks[]=afterpadding.createBlocks(512);
/**************************************IV***************************************************************/
		 MessageDigest A=new MessageDigest("01111001010001101011101010111000"); //7946BAB8 
		 MessageDigest B=new MessageDigest("01010100001111011100001100111111"); //543DC33F
		 MessageDigest C=new MessageDigest("11111000011110011010101010100011"); //F879AAA3
		 MessageDigest D=new MessageDigest("10000111101011111111011010001000"); //87AFF688
		 MessageDigest E=new MessageDigest("01010101001100001101011111110010"); //5530D7F2
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
		 MessageDigest wt2[] = new MessageDigest[80];
		 for(i=0;i<wt.length;i++)
			 wt2[i]=wt[i];
		 wt2[16]=createW(wt,3,14);
		 wt2[17]=createW(wt,1,15);
		 wt2[18]=createW(wt,13,6);
		 wt2[19]=createW(wt,0,12);
		 wt2[20]=createW(wt,5,4);
		 wt2[21]=createW(wt,2,10);
		 wt2[22]=createW(wt,8,11);
		 wt2[23]=createW(wt,7,9);
		 for(i=24;i<wt2.length;i++)
		 wt2[i]=((wt2[i-24].Xor(wt2[i-7])).Xor(wt2[i-8].Xor(wt2[i-3])));
/***************************************************/			 
		for(i=0;i<wt2.length;i++){
			if(i>=0 && i<=19)
				Kt.setDigest("10101011110010010001100111110111"); //ABC919F7
			else if(i>=20 && i<=39)
				Kt.setDigest("10011001100101110110101111010011"); //99976BD3
			else if(i>=40 && i<=59)
				Kt.setDigest("11011100111000110010111001010100"); //DCE32E54
			else if(i>=60 && i<=79)
				Kt.setDigest("00010010110011101001100011100011"); //12CE98E3
			lAt=At;
			lBt=Bt;
			lCt=Ct;
			lDt=Dt;
			lEt=Et;
		
			
			At=lEt.LS(5);
			Bt=(((lAt.Add(f(lAt,lCt,lDt,i))).Add(lBt.LS(5))).Add(wt2[i])).Add(Kt);
			Ct=lBt.LS(10);
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
	private static MessageDigest createW(MessageDigest[] arr,int i,int j){
		  String s1="";
		  String s2="";
		  String result;
		  int k,l;
		  for(k=0; k<arr.length;k++)
			  s1=s1+arr[k].getDigest().charAt(i);
		  for(l=0; l<arr.length;l++)
			  s2=s2+arr[l].getDigest().charAt(j);
		  result=s1+s2;
		  return new MessageDigest(result);
		  
	  }
	/**********************************************/
	  private static MessageDigest concatenation(MessageDigest m1, MessageDigest m2, MessageDigest m3) {
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
	  public static MessageDigest f(MessageDigest m1, MessageDigest m2,MessageDigest m3,int t){
		  if(t>=0 && t<=19)
		  return (m1.Xor(m2)).Or((m3.Not()).Xor(m2));
		  else if((t>=20 && t<=39) || (t>=60 && t<=79))
			  return (m1.Xor(m2)).And(m3);
		  else if(t>=40 && t<=59)
			  return ((m1.Not()).And(m2)).Or((m2.Not()).And(m3));
		  else 
			  return m1;
	  }
	
}
