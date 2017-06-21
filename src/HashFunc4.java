
public class HashFunc4 {
	public MessageDigest getHashedValue(String inputData){
		  MessageDigest m = new MessageDigest(inputData);
		  MessageDigest onesblock=m.OnesBlock(12);
		  MessageDigest lastblock=m.LastBlock(12);
	  	  MessageDigest paderesult=m.Pade(488, 512);
		  MessageDigest afterpadding=concatenation(paderesult,onesblock,lastblock);
		  MessageDigest blocks[]=afterpadding.createBlocks(512);
/**************************************IV***************************************************************/
			 MessageDigest A=new MessageDigest("01010101010101011110110101111000"); //5555ED78
			 MessageDigest B=new MessageDigest("10000000000010010101011110100111"); //800957A7
			 MessageDigest C=new MessageDigest("11001101110110101110011101101101"); //CDDAE76D
			 MessageDigest D=new MessageDigest("01110111101011101011110101011011"); //77AEBD5B
			 MessageDigest E=new MessageDigest("01011010110001011100101101101000"); //5AC5CB68
/*****************************************************************************************************/	 
			
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
				 int j;
				 int i;
/***********************************************************************************/
				 
					for(j=0; j<blocks.length; j++){
						 MessageDigest wt[]=blocks[j].createBlocks(32);
						 MessageDigest wt2[] = new MessageDigest[64];
						 for(i=0;i<wt.length;i++)
							 wt2[i]=wt[i];
						 for(i=16;i<wt2.length;i++)
							 wt2[i]= wt2[i]=shift1(wt2[i-7],wt2[i-6],wt2[i-8]).Xor(shift0(wt2[i-13],wt2[i-12],wt2[i-14])).Xor(wt2[i-2]);
/***************************************************/
							for(i=0;i<64;i++){
								lAt=At;
								lBt=Bt;
								lCt=Ct;
								lDt=Dt;
								lEt=Et;
							/**************/
								if(i%2==0){
								At=lEt;
								Bt=(lAt).Add(f(lCt,lBt,lEt,i)).Add(wt2[i]).Add(Kt(i));
								Ct=lBt;
								Dt=lCt.RS(10);
								Et=lDt;		
								}
								
							/**************/
								
								else{
								At=lDt.LS(20);
								Bt=lAt;
								Ct=lBt;
								Dt=lCt;
								Et=g(lAt,lBt,lCt,lDt,i).Add(wt2[i].Xor(Kt(i)));	
								}
							/**************/
								
						
/***************************************************/
							}
							
							At=At.Add(lAt);
							Bt=Bt.Add(lBt);
							Ct=Ct.Add(lCt);
							Dt=Dt.Add(lDt);
							Et=Et.Add(lEt);	
							
/***********************************************************************************/						
					}
					A=A.Add(At);
					B=B.Add(Bt);
					C=C.Add(Ct);
					D=D.Add(Dt);
					E=E.Add(Et);
						 
				    MessageDigest lastresult=concatenation(A, B,C,D,E);
					
						  return lastresult;
			 
	}
	
	
	

	
	
	
	
	
	
	
/**************************************StaticFunc's**********************************************************/	

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
	 
/**********************************************/
	  public static MessageDigest f(MessageDigest m1, MessageDigest m2,MessageDigest m3,int t){
		  if(t<32)
			  return ((m1.Xor(m2)).Xor(m3));
		  else
			  return (m1.Or(m2)).Xor(m1.Not().Or(m3));
	  }
/**********************************************/
	  public static MessageDigest g(MessageDigest m1, MessageDigest m2, MessageDigest m3,MessageDigest m4,int t){
		  if(t<32)
			  return ((m2.Not().And(m3)).Or(m4)).Xor(m1) ;
		  else
			  return m1.Xor(m2).Xor(m3).Xor(m4);
	  }
/**********************************************/
	  public static MessageDigest Kt(int t){
		  MessageDigest Kt0=new MessageDigest("");
		  MessageDigest Kt1=new MessageDigest("");
		  MessageDigest Kt;
		  int shift=t%32;
		  if(t<32){
			  Kt0.setDigest("01100000101101001101001101101011"); //60B4D36B
		  	  Kt1.setDigest("01111000010110100111111111001111"); //785A7FCF
		  }
		  else{
			  Kt0.setDigest("10101111111111000011101101101001"); //AFFC3B69
		  	  Kt1.setDigest("10010011001101000100101010001101"); //93344A8D
		  }
		  Kt=(Kt0.LS(shift)).Xor(Kt1);
		  
		 return Kt;
	  }
/**********************************************/
	  
	  /**********************************************/
	  public static MessageDigest shift0(MessageDigest m1,MessageDigest m2,MessageDigest m3){
		  return (m1.LS(17)).Xor((m2.LS(21))).Xor(m3.LS(3));
	  }
/**********************************************/
	  public static MessageDigest shift1(MessageDigest m1,MessageDigest m2,MessageDigest m3){
		  return (m1.RS(10)).Xor((m2.RS(13))).Xor(m3.RS(8));
	  }  
	  
	  
	
}
