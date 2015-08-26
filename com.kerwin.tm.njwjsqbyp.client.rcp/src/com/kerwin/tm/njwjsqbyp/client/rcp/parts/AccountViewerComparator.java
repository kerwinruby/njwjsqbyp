package com.kerwin.tm.njwjsqbyp.client.rcp.parts;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import com.kerwin.tm.njwjsqbyp.domain.Account;

public class AccountViewerComparator extends ViewerComparator {
	private static final int DESCENDING = 1;
	private static final int ASCENDING = 0;

	private AccountViewerSortableColumns propertyIndex;
	private int direction;

	public AccountViewerComparator() {
		this.propertyIndex = AccountViewerSortableColumns.id;
		direction = ASCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(AccountViewerSortableColumns sortableColumn) {
		if (sortableColumn == this.propertyIndex)
			direction = 1 - direction;
		else {
			this.propertyIndex = sortableColumn;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Account o1 = (Account) e1;
		Account o2 = (Account) e2;
		int rc = 0;
		switch (propertyIndex) {
		case id:
			rc = Integer.compare(o1.getId(), o2.getId());
			break;
		case username:
			rc = o1.getUserName().compareTo(o2.getUserName());
			break;
		case password:
			rc = o1.getPassword().compareTo(o2.getPassword());
			break;
		case sessionId:
			rc = o1.getSessionId().compareTo(o2.getSessionId());
			break;
		default:
			rc = 0;
			break;
		}

		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}

}
