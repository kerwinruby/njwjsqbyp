package com.kerwin.tm.njwjsqbyp.client.rcp.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Shell;

import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.domain.Account;

public class AddAccountDialog extends AbstractAccountDialog {

	private AccountDBService accountDBService;

	public AddAccountDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void okPressed() {
		Account account = getAccountFromFields();
		accountDBService.addAccount(account);
		super.okPressed();
	}

	@Override
	public void create() {
		super.create();
		setTitle("Add Account");
		setMessage("Enter account details", IMessageProvider.INFORMATION);
	}

	public AccountDBService getAccountDBService() {
		return accountDBService;
	}

	public void setAccountDBService(AccountDBService accountDBService) {
		this.accountDBService = accountDBService;
	}

}
