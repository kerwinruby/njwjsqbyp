package com.kerwin.tm.njwjsqbyp.client.rcp.parts;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.kerwin.tm.njwjsqbyp.client.rcp.serviceretrieval.Services;
import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.domain.Account;

public class AccountsContentProvider implements IStructuredContentProvider {
	@Override
	public Object[] getElements(Object arg0) {
		AccountDBService AccountService = Services.getAccountService();
		List<Account> accounts = AccountService.getAccounts();

		Account[] accountArray = new Account[accounts.size()];
		accountArray = accounts.toArray(accountArray);
		return accountArray;

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// do nothing
	}

	@Override
	public void dispose() {
		// do nothing
	}
}
