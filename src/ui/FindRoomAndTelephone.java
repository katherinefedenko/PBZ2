package ui;

import controller.Controller;
import modelP.Patient;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class FindRoomAndTelephone {
    private RecordsOnPage recordsOnPage;
    private List<Patient> search;

    public FindRoomAndTelephone(Display display, Controller controller) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(50, 100, 1090, 580);
        shell.open();
        recordsOnPage = new RecordsOnPage(display, controller);
        Label labelSurname = new Label (shell, SWT.NONE);
        labelSurname.setText("Surname:");
        labelSurname.setBounds(740, 425, 60, 20);

        Text textSurname = new Text (shell, SWT.BORDER);
        textSurname.setBounds(810, 425, 150, 20);

        Label labelName = new Label (shell, SWT.NONE);
        labelName.setText("Name:");
        labelName.setBounds(740, 455, 70, 20);

        Text textName = new Text (shell, SWT.BORDER);
        textName.setBounds(810, 455, 150, 20);

        Label labelSecondName = new Label (shell, SWT.NONE);
        labelSecondName.setText("SecondName:");
        labelSecondName.setBounds(740, 485, 70, 20);

        Text textSecondName = new Text (shell, SWT.BORDER);
        textSecondName.setBounds(810, 485, 150, 20);

        Button findButton = new Button (shell, SWT.PUSH);
        findButton.setText("Find");
        findButton.setBounds(740, 510, 120, 30);

        recordsOnPage.createTableRoomAndTelephone(shell);

        findButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {
                search = controller.findRoom(textSurname.getText(), textName.getText(), textSecondName.getText());
                if (search.isEmpty()) {
                    MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
                    messageError.setText("ERROR!");
                    messageError.setMessage("No match found");
                    messageError.open();
                } else if (search.size() == 1) {
                    recordsOnPage.refreshTasksTable(shell);
                    recordsOnPage.createTableRoomAndTelephone(shell);
                    recordsOnPage.setRecordsRoom(search, false);
                } else {
                    recordsOnPage.refreshTasksTable(shell);
                    recordsOnPage.createTableRoomAndTelephone(shell);
                    recordsOnPage.setRecordsRoom(search, true);
                }
            }
        });
    }
}
