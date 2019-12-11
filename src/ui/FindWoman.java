package ui;

import controller.Controller;
import modelP.Patient;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class FindWoman {
    private RecordsOnPage recordsOnPage;
    private List<Patient> search;

    public FindWoman(Display display, Controller controller) {
        Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
        shell.setBounds(50, 100, 1090, 580);
        shell.open();

        recordsOnPage = new RecordsOnPage(display, controller);

        Label labelAge = new Label (shell, SWT.NONE);
        labelAge.setText("Возраст:");
        labelAge.setBounds(740, 425, 60, 20);

        Text textAge = new Text (shell, SWT.BORDER);
        textAge.setBounds(810, 425, 150, 20);

        Button findButton = new Button (shell, SWT.PUSH);
        findButton.setText("Найти");
        findButton.setBounds(740, 455, 120, 30);

        recordsOnPage.createTableWoman(shell);

        findButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {
                search = controller.findWoman(Integer.parseInt(textAge.getText()));
                if (search.isEmpty()) {
                    MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
                    messageError.setText("ERROR!");
                    messageError.setMessage("No match found");
                    messageError.open();
                } else {
                    recordsOnPage.refreshTasksTable(shell);
                    recordsOnPage.createTableWoman(shell);
                    recordsOnPage.setRecordsWoman(search);
                }
            }
        });
    }
}
