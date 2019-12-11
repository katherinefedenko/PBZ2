package ui;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.SAXException;

//import controller.*;

public class MainWindow {
	
	//private Controller controller;
	Display display = new Display();
	Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
	boolean firstIteration = true;
	private Controller controller = new Controller();
	private RecordsOnPage recordsOnPage = new RecordsOnPage(display, controller);
	//RecordsOnPage recordsOnPage;

	//public MainWindow(Controller controller) {
		//this.controller = controller;
	//}
	
	public void createMainWindow() {
		shell.setBounds(150, 100, 1200, 600);
		shell.setText("Lab Work №2");
		shell.forceFocus();

		Button openDatabase = new Button(shell, SWT.PUSH);
		openDatabase.setText("Открыть БД пациентов");
		openDatabase.setBounds(1050, 15, 133, 40);
		openDatabase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			    if (!firstIteration) {
                    recordsOnPage.refreshRooms(shell);
                }
                recordsOnPage.createTablePatients(shell);
                controller.openDatabase();
                recordsOnPage.setRecords(controller.getAllPatients());
                firstIteration = false;
			}
		});

		Button openDatabaseRoom = new Button(shell, SWT.PUSH);
		openDatabaseRoom.setText("Открыть БД палат");
		openDatabaseRoom.setBounds(1050, 55, 133, 40);
		openDatabaseRoom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
                if (!firstIteration) {
                    recordsOnPage.refresh(shell);
                }
				recordsOnPage.createTableRooms(shell);
				controller.openDatabase();
				recordsOnPage.setRooms(controller.getAllRooms());
                firstIteration = false;
			}
		});
		/*Button findByGradeAverage = new Button(shell, SWT.PUSH | SWT.WRAP);
		findByGradeAverage.setText("find by grade average and surname");
		findByGradeAverage.setBounds(1070, 55, 90, 60);

		findByGradeAverage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//new FindByAverageGrade(display, controller);
			}
		});

		Button findByNumberOfGroup = new Button(shell, SWT.PUSH | SWT.WRAP);
		findByNumberOfGroup.setText("find by number of group and surname");
		findByNumberOfGroup.setBounds(1070, 115, 90, 60);

		findByNumberOfGroup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//new FindByGroup(display, controller);
			}
		});
		
		Button findByGradeByDiscipline = new Button(shell, SWT.PUSH | SWT.WRAP);
		findByGradeByDiscipline.setText("find by grade by discipline and surname");
		findByGradeByDiscipline.setBounds(1070, 175, 90, 60);

		findByGradeByDiscipline.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				 //new FindByDiscipline(display, controller);
			}
		});
		
		Button deleteByGradeAverage = new Button(shell, SWT.PUSH | SWT.WRAP);
		deleteByGradeAverage.setText("delete by grade average and surname");
		deleteByGradeAverage.setBounds(1070, 235, 90, 60);

		deleteByGradeAverage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//new DeleteByAverageGrade(display, controller, recordsOnPage, shell);
			}
		});

		Button deleteByNumberOfGroup = new Button(shell, SWT.PUSH | SWT.WRAP);
		deleteByNumberOfGroup.setText("delete by number of group and surname");
		deleteByNumberOfGroup.setBounds(1070, 295, 90, 60);

		deleteByNumberOfGroup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//new DeleteByGroup(display, controller, recordsOnPage, shell);
			}
		});
		
		Button deleteByGradeByDiscipline = new Button(shell, SWT.PUSH | SWT.WRAP);
		deleteByGradeByDiscipline.setText("delete by grade by discipline and surname");
		deleteByGradeByDiscipline.setBounds(1070, 355, 90, 60);

		deleteByGradeByDiscipline.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//new DeleteByDiscipline(display, controller, recordsOnPage, shell);
			}
		});*/
				
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		controller.closeDatabase();
		display.dispose();
	}
		
	//public Controller getController() {
		//return controller;
	//}
	
}
