package com.kerwin.tm.njwjsqbyp.client.rcp.parts.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.kerwin.tm.njwjsqbyp.client.rcp.parts.AccountPart;
import com.kerwin.tm.njwjsqbyp.client.rcp.serviceretrieval.Services;
import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.domain.Account;

public class RemoveAccount {

	@Inject
	private ESelectionService selectionService;

	@Execute
	public void execute() {
		Account selectAccount = (Account) selectionService.getSelection(AccountPart.ID);
		if (selectAccount != null) {
			Shell shell = Display.getCurrent().getActiveShell();
			boolean confirmed = MessageDialog.openConfirm(shell, "Confirm delete",
					"Do you want to delete:" + selectAccount.getUserName());
			if (confirmed)
				delete(selectAccount);
		}
	}

	private void delete(Account selectAccount) {
		AccountDBService accountDBService = Services.getAccountService();
		accountDBService.removePersion(selectAccount);
	}

}
