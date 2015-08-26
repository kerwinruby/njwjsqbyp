package com.kerwin.tm.njwjsqbyp.client.rcp.parts.toolitem;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.kerwin.tm.njwjsqbyp.client.rcp.parts.AccountPart;

public class AccountRefresh {
	@Execute
	public void execute(EPartService partService) {
		MPart part = partService.findPart(AccountPart.ID);
		AccountPart dataPart = (AccountPart) part.getObject();
		dataPart.refresh();
	}
}
