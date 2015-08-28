package com.sifcoapp.objects.catalog.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class BusinesspartnerAcountTO extends CommonTO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8455457895546529126L;

	public BusinesspartnerAcountTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String cardcode;
	  private int acctype; 
	  private String acctcode; 
	  private String acctcode2; 
	  private String acctcode3;
	  private String acctcode4;	  
	  private Double balance; 
	  private String objtype;

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
	public String getAcctcode2() {
		return acctcode2;
	}
	public void setAcctcode2(String acctcode2) {
		this.acctcode2 = acctcode2;
	}
	public String getAcctcode3() {
		return acctcode3;
	}
	public void setAcctcode3(String acctcode3) {
		this.acctcode3 = acctcode3;
	}
	public String getAcctcode4() {
		return acctcode4;
	}
	public void setAcctcode4(String acctcode4) {
		this.acctcode4 = acctcode4;
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
