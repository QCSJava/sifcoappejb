package com.sifcoapp.objects.catalogos;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.objects.admin.to.DocStatusTO;

public class Common {
	public static final int VALID = 1;
	public static final int INVALID = 0;
	public static final int C_TRUE = 1;
	public static final int C_FALSE = 0;
	public static final int RESP_OK_DB = 0;
	public static final String TYPESTRING = "STRING";
	public static final String TYPESARRAYTRING = "ARRAYSTRING";
	public static final String TYPEINT = "INT";
	public static final String TYPELONG = "LONG";
	public static final String TYPEFLOAT = "FLOAT";
	public static final String TYPEDOUBLE = "DOUBLE";
	public static final String TYPEDATE = "DATE";
	public static final String TYPEBOOL = "BOOL";
	public static final String TYPEBIGDECIMAL = "BIGDECIMAL";
	public static final String TYPERETURN_INT = "INT";
	public static final String TYPERETURN_RESULTSET = "RESULTSET";
	public static final String TYPERETURN_CURSOR = "CURSOR";
	public static final int MTTOINSERT = 1;
	public static final int MTTOUPDATE = 2;
	public static final int MTTODELETE = 3;

	public static final String DocStatusOpen = "O";
	public static final String DocStatusClose = "C";
	public static final String DocStatusCancelled = "S";

	private static List DocStatusList;

	public static Date getPrimerDiaDelMes(int anno, int mes) {

		Calendar cal = Calendar.getInstance();
		cal.set(anno, mes - 1, 1);

		cal.set(cal.get(Calendar.YEAR),

		cal.get(Calendar.MONTH),

		cal.getActualMinimum(Calendar.DAY_OF_MONTH),

		cal.getMaximum(0),

		cal.getMaximum(0),

		cal.getMaximum(0));

		Date fecha = new Date(cal.getTime().getTime());

		return fecha;
	}

	public static Date getUltimoDiaDelMes(int anno, int mes) {

		Calendar cal = Calendar.getInstance();
		cal.set(anno, mes - 1, 1);

		cal.set(cal.get(Calendar.YEAR),

		cal.get(Calendar.MONTH),

		cal.getActualMaximum(Calendar.DAY_OF_MONTH),

		cal.getMaximum(0),

		cal.getMaximum(0),

		cal.getMaximum(0));

		Date fecha = new Date(cal.getTime().getTime());

		return fecha;
	}

	public static List getDocStatusList() {
		List _return = new Vector();
		// Open
		DocStatusTO statusO = new DocStatusTO();
		statusO.setCode(DocStatusOpen);
		statusO.setValue("Abierto");
		_return.add(statusO);
		// Open
		DocStatusTO statusC = new DocStatusTO();
		statusC.setCode(DocStatusClose);
		statusC.setValue("Cerrado");
		_return.add(statusC);
		// Open
		DocStatusTO statusS = new DocStatusTO();
		statusS.setCode(DocStatusCancelled);
		statusS.setValue("Cancelado");
		_return.add(statusS);

		return _return;

	}

}
