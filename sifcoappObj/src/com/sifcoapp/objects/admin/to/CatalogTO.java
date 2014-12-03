package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class CatalogTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3400353028558278833L;
	
	private int codeTable;
	private String codeCatlg;
	private String valueCatlg;
	
	public CatalogTO(){
		this.setCodeCatlg(null);
		this.setCodeTable(0);
		this.setValueCatlg(null);
	}
	
	public CatalogTO(int codeTable,String codeCatlg,String valueCatlg){
		this.setCodeCatlg(codeCatlg);
		this.setCodeTable(codeTable);
		this.setValueCatlg(valueCatlg);
		
	}
	public String getCodeCatlg() {
		return codeCatlg;
	}
	public void setCodeCatlg(String codeCatlg) {
		this.codeCatlg = codeCatlg;
	}
	public String getValueCatlg() {
		return valueCatlg;
	}
	public void setValueCatlg(String valueCatlg) {
		this.valueCatlg = valueCatlg;
	}
	public int getCodeTable() {
		return codeTable;
	}
	public void setCodeTable(int codeTable) {
		this.codeTable = codeTable;
	}


}
