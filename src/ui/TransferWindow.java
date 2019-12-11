package ui;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferWindow {
    public TransferWindow(Display display, Controller controller,
                          RecordsOnPage recordsOnPage, Composite composite, int key, List<String> inf) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(500, 60, 400, 320);
        shell.open();

        Label labelMain = new Label (shell, SWT.NONE);
        labelMain.setText("Введите информацию о переводе");
        labelMain.setBounds(85, 15, 250, 20);

        Label labelOldRoom = new Label (shell, SWT.NONE);
        labelOldRoom.setText("Старая палата:" + inf.get(12));
        labelOldRoom.setBounds(10, 47, 200, 20);

        Label labelOldTelephone = new Label (shell, SWT.NONE);
        labelOldTelephone.setText("Старый номер:" + inf.get(13));
        labelOldTelephone.setBounds(10, 77, 200, 20);

        Label labelNewRoom = new Label (shell, SWT.NONE);
        labelNewRoom.setText("Новая палата:");
        labelNewRoom.setBounds(10, 107, 120, 20);

        Text textNewRoom = new Text (shell, SWT.BORDER);
        textNewRoom.setBounds(135, 105, 70, 20);

        Label labelNewTelephone = new Label (shell, SWT.NONE);
        labelNewTelephone.setText("Новый телефон:");
        labelNewTelephone.setBounds(10, 137, 120, 20);

        Text textNewTelephone = new Text (shell, SWT.BORDER);
        textNewTelephone.setBounds(135, 135, 100, 20);

        Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
        combo.setBounds(240, 105, 120, 20);
        for(Map.Entry room : controller.getAllRooms().entrySet()) {
            combo.add(room.getKey() + " " + room.getValue());
        }

        combo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textNewRoom.setText(combo.getText().split(" ")[0]);
                textNewTelephone.setText(combo.getText().split(" ")[1]);
            }
        });

        Label labelDate = new Label (shell, SWT.NONE);
        labelDate.setText("Дата перевода:");
        labelDate.setBounds(10, 167, 120, 20);

        Text textDate = new Text (shell, SWT.BORDER);
        textDate.setBounds(135, 165, 40, 20);

        Button addButton = new Button (shell, SWT.PUSH);
        addButton.setText("Перевести");
        addButton.setBounds(165, 235, 70, 30);
        addButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {
                List<String> transfer = controller.getTransfer(key);
                String room = transfer.get(0);
                String date = transfer.get(1);
                String telephone = transfer.get(2);

                room = room + " " + inf.get(12);
                telephone = telephone + " " + inf.get(13);
                date = date + " " + textDate.getText();

                transfer.set(0, room);
                transfer.set(1, date);
                transfer.set(2, telephone);

                controller.updateRoom(key, transfer, Integer.parseInt(textNewRoom.getText()));

                recordsOnPage.refresh(composite);
                recordsOnPage.createTablePatients(composite);
                recordsOnPage.setRecords(controller.getAllPatients());

                //textName.setText("");
                //textSurname.setText("");
            }
        });
    }
}