package com.sifcoapp.bussinessLogic;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.admin.to.UnityTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.*;


public interface UnityEJBRemote {
	public ResultOutTO Unity_mtto(UnityTO parameter,int action)throws Exception;
	 
	public List getUnity() throws Exception;
		
	public UnityTO getUnity_bykey(int code) throws Exception ;
}
