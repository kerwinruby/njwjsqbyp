package com.kerwin.tm.njwjsqbyp.database;

import com.kerwin.tm.njwjsqbyp.domain.Account;

public interface IAccountDBChangeObserver {
	void changed(String eventID, Account affectedAccount);
}
