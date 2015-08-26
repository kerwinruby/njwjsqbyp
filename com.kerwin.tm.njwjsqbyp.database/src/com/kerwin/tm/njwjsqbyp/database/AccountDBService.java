package com.kerwin.tm.njwjsqbyp.database;

import java.util.List;

import com.kerwin.tm.njwjsqbyp.domain.Account;

public interface AccountDBService {
	void addAccount(Account account);

	void modifyAccount(Account account);

	void removePersion(Account account);

	List<Account> getAccounts();

	void registerAccountObserver(IAccountDBChangeObserver observer);

	void unregisterAccountObserver(IAccountDBChangeObserver observer);
}
