package com.kerwin.tm.njwjsqbyp.client.rcp.parts.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

import com.kerwin.tm.njwjsqbyp.client.rcp.dialog.AddAccountDialog;
import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.service.Http;

public class AddAccount {

	@Inject
	private AccountDBService accountDBService;
	@Inject
	private Http http;

	@Execute
	public void execute() {
		test();

		AddAccountDialog dialog = new AddAccountDialog(Display.getDefault().getActiveShell());
		dialog.setAccountDBService(accountDBService);
		dialog.open();
	}

	private void test() {
		if (http != null) {
			System.out.println("inject ok!");
		}
	}
}
