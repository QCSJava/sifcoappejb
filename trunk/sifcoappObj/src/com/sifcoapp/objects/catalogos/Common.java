package com.sifcoapp.objects.catalogos;

import java.sql.Date;
import java.util.Calendar;

public class Common {
	public static final int VALID = 1;
	public static final int INVALID = 0;
	public static final int C_TRUE = 1;
	public static final int C_FALSE = 0;
	public static final int RESP_OK_DB = 0;
	public static final String TYPESTRING = "STRING";
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

	public static Date getPrimerDiaDelMes(int anno, int mes) {

		Calendar cal = Calendar.getInstance();
		cal.set(anno, mes-1, 1);

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
		cal.set(anno, mes-1, 1);

		cal.set(cal.get(Calendar.YEAR),

		cal.get(Calendar.MONTH),

		cal.getActualMaximum(Calendar.DAY_OF_MONTH),

		cal.getMaximum(0),

		cal.getMaximum(0),

		cal.getMaximum(0));

		Date fecha = new Date(cal.getTime().getTime());

		return fecha;
	}
}
