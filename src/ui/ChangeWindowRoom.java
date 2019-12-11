package ui;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class ChangeWindowRoom {

    public ChangeWindowRoom(Display display, Controller controller,
                        RecordsOnPage recordsOnPage, Composite composite, int key, List<String> inf) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(500, 160, 400, 200);
        shell.open();

        Label labelMain = new Label (shell, SWT.NONE);
        labelMain.setText("Enter new info about the room " + inf.get(0));
        labelMain.setBounds(85, 15, 250, 20);

        Label labelTelephone = new Label (shell, SWT.NONE);
        labelTelephone.setText("Room phone:");
        labelTelephone.setBounds(10, 47, 70, 20);

        Text textTelephone = new Text (shell, SWT.BORDER);
        textTelephone.setBounds(85, 45, 180, 20);
        textTelephone.setText(inf.get(1));

        Button addButton = new Button (shell, SWT.PUSH);
        addButton.setText("Update room");
        addButton.setBounds(165, 75, 70, 30);
        addButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {
                controller.updateInformationAboutRoom(textTelephone.getText(), key);
                recordsOnPage.refreshRooms(composite);
                recordsOnPage.createTableRooms(composite);
                recordsOnPage.setRooms(controller.getAllRooms());
            }
        });
    }
}