package com.kerwin.tm.njwjsqbyp.client.rcp.parts.toolitem;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.kerwin.tm.njwjsqbyp.client.rcp.dialog.AddAccountDialog;

public class AddAccount
{
    @Execute
    public void execute()
    {
        Dialog dialog = new AddAccountDialog( Display.getDefault().getActiveShell() );
        dialog.open();
    }
}
