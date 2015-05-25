package com.sifcoapp.objects.catalog.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class BusinesspartnerAcountTO extends CommonTO{

	
	public BusinesspartnerAcountTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String cardcode;
	  private int acctype; 
	  private String acctcode; 
	  private Double balance; 
	  private String objtype;
	  
	  public BusinesspartnerAcountTO(String cardcode, int acctype,
				String acctcode, Double balance, String objtype) {
			super();
			
			this.cardcode = cardcode;
			this.acctype = acctype;
			this.acctcode = acctcode;
			this.balance = balance;
			this.objtype = objtype;
		}
	  
	  
	  
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public int getAcctype() {
		return acctype;
	}
	public void setAcctype(int acctype) {
		this.acctype = acctype;
	}
	public String getAcctcode() {
		return acctcode;
	}
	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getObjtype() {
		return objtype;
	}
	public void setObjtype(String objtype) {
		this.objtype = objtype;
	} 
	
}
