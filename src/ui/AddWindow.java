package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddWindow {

    private List<String> informationAboutPatient;
	
	public AddWindow(Display display, Controller controller, RecordsOnPage recordsOnPage, Composite composite) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(500, 60, 400, 530);
		shell.open();
		
		Label labelMain = new Label (shell, SWT.NONE);
		labelMain.setText("Enter patient information");
		labelMain.setBounds(85, 15, 250, 20);

        Label labelSurname = new Label (shell, SWT.NONE);
        labelSurname.setText("Surname: ");
        labelSurname.setBounds(10, 47, 70, 20);

        Text textSurname = new Text (shell, SWT.BORDER);
        textSurname.setBounds(85, 45, 180, 20);

		Label labelName = new Label (shell, SWT.NONE);
		labelName.setText("Name :");
		labelName.setBounds(10, 77, 70, 20);
		
		Text textName = new Text (shell, SWT.BORDER);
		textName.setBounds(85, 75, 180, 20);
		
		Label labelSecondName = new Label (shell, SWT.NONE);
        labelSecondName.setText("SecondName:");
        labelSecondName.setBounds(10, 107, 70, 20);
		
		Text textSecondName = new Text (shell, SWT.BORDER);
        textSecondName.setBounds(85, 105, 180, 20);
		
		Label labelSex = new Label (shell, SWT.NONE);
        labelSex.setText("Sex (f/m):");
        labelSex.setBounds(10, 137, 70, 20);
		
		Text textSex = new Text (shell, SWT.BORDER);
        textSex.setBounds(85, 135, 180, 20);
		
		Label labelAge = new Label (shell, SWT.NONE);
        labelAge.setText("Age:");
        labelAge.setBounds(10, 167, 70, 20);
		
		Text textAge = new Text (shell, SWT.BORDER);
        textAge.setBounds(85, 165, 40, 20);
		
		Label labelDiagnosis = new Label (shell, SWT.NONE);
        labelDiagnosis.setText("Diagnosis:");
        labelDiagnosis.setBounds(10, 197, 70, 20);
		
		Text textDiagnosis = new Text (shell, SWT.BORDER);
        textDiagnosis.setBounds(85, 195, 180, 20);

        Label labelHowArrive = new Label (shell, SWT.NONE);
        labelHowArrive.setText("Arriving:");
        labelHowArrive.setBounds(10, 227, 90, 20);

        Text textHowArrive = new Text (shell, SWT.BORDER);
        textHowArrive.setBounds(105, 225, 70, 20);

        Label labelDateOfArrival = new Label (shell, SWT.NONE);
        labelDateOfArrival.setText("Arrival date:");
        labelDateOfArrival.setBounds(10, 257, 105, 20);

        Text textDateOfArrival = new Text (shell, SWT.BORDER);
        textDateOfArrival.setBounds(115, 255, 100, 20);

        Label labelGrowth = new Label (shell, SWT.NONE);
        labelGrowth.setText("Growth:");
        labelGrowth.setBounds(10, 287, 70, 20);

        Text textGrowth = new Text (shell, SWT.BORDER);
        textGrowth.setBounds(85, 285, 40, 20);

        Label labelHairColor = new Label (shell, SWT.NONE);
        labelHairColor.setText("Hair color:");
        labelHairColor.setBounds(10, 317, 70, 20);

        Text textHairColor = new Text (shell, SWT.BORDER);
        textHairColor.setBounds(85, 315, 70, 20);

        Label labelSpecialSings = new Label (shell, SWT.NONE);
        labelSpecialSings.setText("Special signs:");
        labelSpecialSings.setBounds(10, 347, 100, 20);

        Text textSpecialSings = new Text (shell, SWT.BORDER);
        textSpecialSings.setBounds(115, 345, 180, 20);

        Label labelPossibleAge = new Label (shell, SWT.NONE);
        labelPossibleAge.setText("Possible Age:");
        labelPossibleAge.setBounds(10, 377, 120, 20);

        Text textPossibleAge = new Text (shell, SWT.BORDER);
        textPossibleAge.setBounds(135, 375, 40, 20);

        Label labelRoom = new Label (shell, SWT.NONE);
        labelRoom.setText("Room:");
        labelRoom.setBounds(10, 407, 70, 20);

        Text textRoom = new Text (shell, SWT.BORDER);
        textRoom.setBounds(85, 405, 40, 20);

        Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
        combo.setBounds(140, 405, 120, 20);
        for(Map.Entry room : controller.getAllRooms().entrySet()) {
            combo.add(Integer.toString((Integer) room.getKey()));
        }

        combo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textRoom.setText(combo.getText());
            }
        });
		
		Button addButton = new Button (shell, SWT.PUSH);
		addButton.setText("Add patient");
		addButton.setBounds(175, 455, 70, 30);
		addButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
			    informationAboutPatient = new ArrayList<>();
			    informationAboutPatient.add(textSurname.getText());
                informationAboutPatient.add(textName.getText());
                informationAboutPatient.add(textSecondName.getText());
                informationAboutPatient.add(textSex.getText());
                informationAboutPatient.add(textAge.getText());
                informationAboutPatient.add(textDiagnosis.getText());
                informationAboutPatient.add(textHowArrive.getText());
                informationAboutPatient.add(textDateOfArrival.getText());
                informationAboutPatient.add(textGrowth.getText());
                informationAboutPatient.add(textHairColor.getText());
                informationAboutPatient.add(textSpecialSings.getText());
                informationAboutPatient.add(textPossibleAge.getText());
                informationAboutPatient.add(textRoom.getText());

                controller.addPatient(informationAboutPatient);

    			recordsOnPage.refresh(composite);
				recordsOnPage.createTablePatients(composite);
				recordsOnPage.setRecords(controller.getAllPatients());
			}
		});
	}
}
