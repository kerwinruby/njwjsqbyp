
package com.kerwin.tm.njwjsqbyp.client.rcp.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.kerwin.tm.njwjsqbyp.client.rcp.serviceretrieval.Services;
import com.kerwin.tm.njwjsqbyp.database.IAccountDBChangeObserver;
import com.kerwin.tm.njwjsqbyp.domain.Account;

public class AccountPart {

	public static final String ID = "com.kerwin.tm.njwjsqbyp.client.rcp.parts.accounts";

	@Inject
	private ESelectionService selectionService;

	private TableViewer viewer;
	  private AccountViewerComparator viewerComparator;

	@PostConstruct
	public void postConstruct(Composite parent) {
		viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createColumns();
		addSelectionListener();
		addDatabaseObserver();

		viewer.setContentProvider(new AccountsContentProvider());
		viewer.setInput(new Object());
		
        viewerComparator = new AccountViewerComparator();
        viewer.setComparator( viewerComparator );

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	private void addSelectionListener() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				selectionService.setSelection(selection.getFirstElement());
			}
		});
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final AccountViewerSortableColumns sortableColumn) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewerComparator.setColumn(sortableColumn);

				int dir = viewerComparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	private void addDatabaseObserver() {
		Services.getAccountService().registerAccountObserver(new IAccountDBChangeObserver() {
			@Override
			public void changed(String eventID, Account affectedPerson) {
				refresh();
			}
		});
	}

	@Focus
	public void onFocus() {
		viewer.getControl().setFocus();
	}

	public void refresh() {
		viewer.setInput(new Object());
	}

	private void createColumns() {
		createIDColumn();
		createUserNameColumn();
		createPasswordColumn();
		createSesssionIdColumn();
	}

	private void createSesssionIdColumn() {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(100);
		column.setText("SessionId");
		column.setAlignment(SWT.LEFT);
		column.addSelectionListener(getSelectionAdapter(column, AccountViewerSortableColumns.sessionId));

		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Account account = (Account) element;
				return account.getSessionId();
			}
		});
	}

	private void createPasswordColumn() {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(100);
		column.setText("Password");
		column.setAlignment(SWT.LEFT);
		column.addSelectionListener(getSelectionAdapter(column, AccountViewerSortableColumns.password));

		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Account account = (Account) element;
				return account.getPassword();
			}
		});
	}

	private void createUserNameColumn() {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(100);
		column.setText("UserName");
		column.setAlignment(SWT.LEFT);
		column.addSelectionListener(getSelectionAdapter(column, AccountViewerSortableColumns.username));

		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Account account = (Account) element;
				return account.getUserName();
			}
		});

	}

	private void createIDColumn() {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(100);
		column.setText("ID");
		column.setAlignment(SWT.RIGHT);
		column.addSelectionListener(getSelectionAdapter(column, AccountViewerSortableColumns.id));
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Account account = (Account) element;
				return Long.toString(account.getId());
			}
		});
	}

}