package ui;

import controller.Controller;
import modelP.Patient;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class FindDate {
    private RecordsOnPage recordsOnPage;
    private List<Patient> search;

    public FindDate(Display display, Controller controller) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(50, 100, 1090, 580);
        shell.open();

        recordsOnPage = new RecordsOnPage(display, controller);

        Label labelDate = new Label (shell, SWT.NONE);
        labelDate.setText("Дата:");
        labelDate.setBounds(740, 425, 60, 20);

        Text textDate = new Text (shell, SWT.BORDER);
        textDate.setBounds(810, 425, 150, 20);

        Button findButton = new Button (shell, SWT.PUSH);
        findButton.setText("Найти");
        findButton.setBounds(740, 455, 120, 30);

        recordsOnPage.createTableDate(shell);

        findButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {
                search = controller.findDate(textDate.getText());
                if (search.isEmpty()) {
                    MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
                    messageError.setText("ERROR!");
                    messageError.setMessage("No match found");
                    messageError.open();
                } else {
                    recordsOnPage.refreshTasksTable(shell);
                    recordsOnPage.createTableDate(shell);
                    recordsOnPage.setRecordsDate(search);
                }
            }
        });
    }
}
