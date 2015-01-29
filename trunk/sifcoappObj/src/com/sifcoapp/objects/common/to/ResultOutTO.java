package com.sifcoapp.objects.common.to;

public class ResultOutTO extends CommonTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 380892981572094281L;
	private int codigoError;
	private int docentry;
	private int linenum;
	private String mensaje;
	public ResultOutTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResultOutTO(int codigoError, int docentry, int linenum,
			String mensaje) {
		super();
		this.codigoError = codigoError;
		this.docentry = docentry;
		this.linenum = linenum;
		this.mensaje = mensaje;
	}
	public int getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}
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
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
