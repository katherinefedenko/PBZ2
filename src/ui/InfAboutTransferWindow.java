package ui;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.util.List;

public class InfAboutTransferWindow {
	public InfAboutTransferWindow(Display display, Controller controller, int key) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(500, 60, 400, 420);
		shell.open();

		Label labelMain = new Label(shell, SWT.NONE);
		labelMain.setText("Transfers");
		labelMain.setBounds(155, 15, 250, 20);

		Label labelTransfers = new Label(shell, SWT.NONE);
		labelTransfers.setBounds(10, 47, 200, 400);

		List<String[]> inf = controller.findTransfers(key);
		try {
			String[] rooms = inf.get(0);
			String[] dates = inf.get(1);
			String transfers = "";

			for (int i = 0; i < rooms.length; i++) {
				transfers = transfers + "Room: " + rooms[i] + "\n" + "Date: " + dates[i] + "\n";
			}
			labelTransfers.setText(transfers);
		} catch (Exception ex) {
			if (inf.isEmpty()) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
				messageBox.setText("Info");
				messageBox.setMessage("There is no transfer for selected patient!");
				messageBox.open();
			}
		}
	}
}
