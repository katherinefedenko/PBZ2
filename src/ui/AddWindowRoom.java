package ui;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class AddWindowRoom {

    private List<String> informationAboutRoom;

    public AddWindowRoom(Display display, Controller controller, RecordsOnPage recordsOnPage, Composite composite) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(500, 160, 400, 200);
        shell.open();

        Label labelMain = new Label (shell, SWT.NONE);
        labelMain.setText("Enter room info");
        labelMain.setBounds(85, 15, 250, 20);

        Label labelRoom = new Label (shell, SWT.NONE);
        labelRoom.setText("Room number:");
        labelRoom.setBounds(10, 47, 70, 20);

        Text textRoom = new Text (shell, SWT.BORDER);
        textRoom.setBounds(85, 45, 180, 20);

        Label labelTelephone = new Label (shell, SWT.NONE);
        labelTelephone.setText("Room phone:");
        labelTelephone.setBounds(10, 77, 70, 20);

        Text textTelephone = new Text (shell, SWT.BORDER);
        textTelephone.setBounds(85, 75, 180, 20);

        Button addButton = new Button (shell, SWT.PUSH);
        addButton.setText("Add room");
        addButton.setBounds(175, 115, 70, 30);
        addButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {
                informationAboutRoom = new ArrayList<>();
                informationAboutRoom.add(textRoom.getText());
                informationAboutRoom.add(textTelephone.getText());

                controller.addRoom(informationAboutRoom);

                recordsOnPage.refreshRooms(composite);
                recordsOnPage.createTableRooms(composite);
                recordsOnPage.setRooms(controller.getAllRooms());
            }
        });
    }
}
