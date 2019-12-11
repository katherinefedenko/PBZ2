package ui;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import java.util.List;

public class InfAboutTransferWindow {
    public InfAboutTransferWindow(Display display, Controller controller, int key) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(500, 60, 400, 420);
        shell.open();

        Label labelMain = new Label (shell, SWT.NONE);
        labelMain.setText("Transfers");
        labelMain.setBounds(155, 15, 250, 20);

        Label labelTransfers = new Label (shell, SWT.NONE);
        labelTransfers.setBounds(10, 47, 70, 400);

        List<String[]> inf = controller.findTransfers(key);
        String[] rooms = inf.get(0);
        String[] dates = inf.get(1);
        String[] telephones = inf.get(2);
        String transfers = "";

        for (int i = 0; i < rooms.length; i++) {
            transfers = transfers + "Room:" + rooms[i] + "\n" + "Дата:" + dates[i] + "\n" + "Телефон:" +telephones[i] + ";" + "\n";
        }
        labelTransfers.setText(transfers);
    }
}
