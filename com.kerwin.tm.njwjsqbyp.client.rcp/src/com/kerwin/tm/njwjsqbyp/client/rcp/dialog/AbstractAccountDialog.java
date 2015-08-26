package com.kerwin.tm.njwjsqbyp.client.rcp.dialog;


import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.kerwin.tm.njwjsqbyp.domain.Account;

public abstract class AbstractAccountDialog extends TitleAreaDialog {

	protected Text userNameText;
	protected Text passwordText;

	public AbstractAccountDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, false);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(layout);

		createUserName(container);
		createPassword(container);

		return area;
	}

	private void createPassword(Composite container) {
		Label lblUserName = new Label(container, SWT.NONE);
		lblUserName.setText("UserName");
		
		GridData dataUserName = new GridData();
		dataUserName.grabExcessHorizontalSpace = true;
		dataUserName.horizontalAlignment = GridData.FILL;
		
		userNameText = new Text(container, SWT.BORDER);
		userNameText.setLayoutData(dataUserName);
	}

	private void createUserName(Composite container) {
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setText("Password");
		
		GridData dataPassword = new GridData();
		dataPassword.grabExcessHorizontalSpace = true;
		dataPassword.horizontalAlignment = GridData.FILL;
		
		passwordText = new Text(container, SWT.BORDER);
		passwordText.setLayoutData(dataPassword);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
	
	protected Account getAccountFromFields(){
		String userName = userNameText.getText();
		String password = passwordText.getText();
		return new Account(userName, password);
	}

}
