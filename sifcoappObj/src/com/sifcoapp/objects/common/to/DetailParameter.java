package com.sifcoapp.objects.common.to;

public class DetailParameter {
	private int position;
	private String colName;
	private Object colValue;
	private Object colType;
	
	public DetailParameter(int position,String colName,Object colValue){
		this.position=position;
		this.colName=colName;
		this.colValue=colValue;
		this.colType=colType;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Object getColValue() {
		return colValue;
	}

	public void setColValue(Object colValue) {
		this.colValue = colValue;
	}
}
