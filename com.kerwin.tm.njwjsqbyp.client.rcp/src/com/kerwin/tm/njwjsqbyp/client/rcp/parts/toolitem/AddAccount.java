package com.kerwin.tm.njwjsqbyp.client.rcp.parts.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

import com.kerwin.tm.njwjsqbyp.client.rcp.dialog.AddAccountDialog;
import com.kerwin.tm.njwjsqbyp.database.AccountDBService;

public class AddAccount {

	@Inject
	private AccountDBService accountDBService;

	@Execute
	public void execute() {
		AddAccountDialog dialog = new AddAccountDialog(Display.getDefault().getActiveShell());
		dialog.setAccountDBService(accountDBService);
		dialog.open();
	}
}
