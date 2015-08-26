package com.kerwin.tm.njwjsqbyp.client.rcp.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Shell;

import com.kerwin.tm.njwjsqbyp.client.rcp.serviceretrieval.Services;
import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.domain.Account;

public class AddAccountDialog extends AbstractAccountDialog {

	public AddAccountDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void okPressed() {
		Account account = getAccountFromFields();
		AccountDBService accountDBService = Services.getAccountService();
		accountDBService.addAccount(account);
		super.okPressed();
	}

	@Override
	public void create() {
		super.create();
		setTitle("Add Account");
		setMessage("Enter account details", IMessageProvider.INFORMATION);
	}
}
