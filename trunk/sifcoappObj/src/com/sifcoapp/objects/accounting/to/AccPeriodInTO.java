package com.sifcoapp.objects.accounting.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class AccPeriodInTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6882410899828011622L;
	
    private String nombrePeriodo;
    private String subPeriodo;
    private String cantidadPeriodo;
    private String indicadorPeriodo;
    private String statusPeriodo;
    private String fechaConta;
    private String fechaVencimiento;
    private String fechaDocumento;
    private String inicioEjercicio;
    private String ejercicio;
    private String codigoPeriodo;
	public String getNombrePeriodo() {
		return nombrePeriodo;
	}
	public void setNombrePeriodo(String nombrePeriodo) {
		this.nombrePeriodo = nombrePeriodo;
	}
	public String getSubPeriodo() {
		return subPeriodo;
	}
	public void setSubPeriodo(String subPeriodo) {
		this.subPeriodo = subPeriodo;
	}
	public String getCantidadPeriodo() {
		return cantidadPeriodo;
	}
	public void setCantidadPeriodo(String cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}
	public String getIndicadorPeriodo() {
		return indicadorPeriodo;
	}
	public void setIndicadorPeriodo(String indicadorPeriodo) {
		this.indicadorPeriodo = indicadorPeriodo;
	}
	public String getStatusPeriodo() {
		return statusPeriodo;
	}
	public void setStatusPeriodo(String statusPeriodo) {
		this.statusPeriodo = statusPeriodo;
	}
	public String getFechaConta() {
		return fechaConta;
	}
	public void setFechaConta(String fechaConta) {
		this.fechaConta = fechaConta;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	public String getInicioEjercicio() {
		return inicioEjercicio;
	}
	public void setInicioEjercicio(String inicioEjercicio) {
		this.inicioEjercicio = inicioEjercicio;
	}
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
}
