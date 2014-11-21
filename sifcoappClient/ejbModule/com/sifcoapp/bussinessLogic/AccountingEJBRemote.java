package com.sifcoapp.bussinessLogic;
import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccPeriodInTO;
import com.sifcoapp.objects.accounting.to.AccPeriodOutTO;

@Remote
public interface AccountingEJBRemote {
	 public List getAccPeriods();
	 public AccPeriodOutTO AccAddPeriod(AccPeriodInTO parameters);
}
