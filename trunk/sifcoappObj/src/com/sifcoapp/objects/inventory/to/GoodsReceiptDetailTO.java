/**
 * 
 */
package com.sifcoapp.objects.inventory.to;

import com.sifcoapp.objects.common.to.CommonTO;

/**
 * @author USER
 * 
 */
public class GoodsReceiptDetailTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4806267667210251219L;
	private int docentry;
	private int linenum;
	private int targettype;
	private int trgetentry;
	private String baseref;
	private int basetype;
	private String linestatus;
	private String itemcode;
	private String dscription;
	private Double quantity;
	private Double openqty;
	private Double price;
	private Double linetotal;
	private String whscode;
	private String acctcode;
	private String usebaseun;
	private String objtype;
	private String unitmsr;

	public int getDocentry() {
		return docentry;
	}

	public void setDocentry(int docentry) {
		this.docentry = docentry;
	}

	public int getLinenum() {
		return linenum;
	}

	public void setLinenum(int linenum) {
		this.linenum = linenum;
	}

	public int getTargettype() {
		return targettype;
	}

	public void setTargettype(int targettype) {
		this.targettype = targettype;
	}

	public int getTrgetentry() {
		return trgetentry;
	}

	public void setTrgetentry(int trgetentry) {
		this.trgetentry = trgetentry;
	}

	public String getBaseref() {
		return baseref;
	}

	public void setBaseref(String baseref) {
		this.baseref = baseref;
	}

	public int getBasetype() {
		return basetype;
	}

	public void setBasetype(int basetype) {
		this.basetype = basetype;
	}

	public String getLinestatus() {
		return linestatus;
	}

	public void setLinestatus(String linestatus) {
		this.linestatus = linestatus;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getDscription() {
		return dscription;
	}

	public void setDscription(String dscription) {
		this.dscription = dscription;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getOpenqty() {
		return openqty;
	}

	public void setOpenqty(Double openqty) {
		this.openqty = openqty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLinetotal() {
		return linetotal;
	}

	public void setLinetotal(Double linetotal) {
		this.linetotal = linetotal;
	}

	public String getWhscode() {
		return whscode;
	}

	public void setWhscode(String whscode) {
		this.whscode = whscode;
	}

	public String getAcctcode() {
		return acctcode;
	}

	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}

	public String getUsebaseun() {
		return usebaseun;
	}

	public void setUsebaseun(String usebaseun) {
		this.usebaseun = usebaseun;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public String getUnitmsr() {
		return unitmsr;
	}

	public void setUnitmsr(String unitmsr) {
		this.unitmsr = unitmsr;
	}

	/**
	 * 
	 */
	public GoodsReceiptDetailTO() {
		// TODO Auto-generated constructor stub
	}

	public GoodsReceiptDetailTO(int docentry, int linenum, int targettype,
			int trgetentry, String baseref, int basetype, String linestatus,
			String itemcode, String dscription, Double quantity,
			Double openqty, Double price, Double linetotal, String whscode,
			String acctcode, String usebaseun, String objtype, String unitmsr) {
		super();
		this.docentry = docentry;
		this.linenum = linenum;
		this.targettype = targettype;
		this.trgetentry = trgetentry;
		this.baseref = baseref;
		this.basetype = basetype;
		this.linestatus = linestatus;
		this.itemcode = itemcode;
		this.dscription = dscription;
		this.quantity = quantity;
		this.openqty = openqty;
		this.price = price;
		this.linetotal = linetotal;
		this.whscode = whscode;
		this.acctcode = acctcode;
		this.usebaseun = usebaseun;
		this.objtype = objtype;
		this.unitmsr = unitmsr;
	}

}
