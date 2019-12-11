package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import modelP.*;

public class RecordsOnPage {

	private Table table;
	private Button delete;
	private Button change;
	private Button add;
    private Button deleteRoom;
    private Button changeRoom;
    private Button addRoom;
	private Button findRoom;
	private Button findDate;
	private Button findFemale;
	private Button transfer;
	private Button informationAboutTransfer;
	private Controller controller;
	private Display display;
	private int keyOfItem;
	private List<String> informationAboutPatient;
	private List<String> informationAboutRoom;
	private Shell shellHelp;

	public RecordsOnPage(Display display, Controller controller) {
		this.display = display;
		this.controller = controller;
	}

	private void createTable(Composite shell) {
		table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 15, 1030, 530);
	}

	public void createTablePatients(Composite shell) {

		createTable(shell);

		TableColumn idColumn = new TableColumn(table, SWT.DEFAULT);
		idColumn.setText("ID");
		idColumn.setWidth(30);

		TableColumn FIOColumn = new TableColumn(table, SWT.DEFAULT);
		FIOColumn.setText("Р¤Р�Рћ");
		FIOColumn.setWidth(200);

		TableColumn sexColumn = new TableColumn(table, SWT.DEFAULT);
		sexColumn.setText("РџРѕР»");
		sexColumn.setWidth(40);

		TableColumn ageColumn = new TableColumn(table, SWT.DEFAULT);
		ageColumn.setText("Р’РѕР·СЂР°СЃС‚");
		ageColumn.setWidth(80);

		TableColumn diagnosisColumn = new TableColumn(table, SWT.DEFAULT);
		diagnosisColumn.setText("Р”РёР°РіРЅРѕР·");
		diagnosisColumn.setWidth(100);

		TableColumn howArriveColumn = new TableColumn(table, SWT.DEFAULT);
		howArriveColumn.setText("РљР°Рє РїСЂРёР±С‹Р»");
		howArriveColumn.setWidth(200);

		TableColumn dateOfArrivalColumn = new TableColumn(table, SWT.DEFAULT);
		dateOfArrivalColumn.setText("Р”Р°С‚Р° РїСЂРёР±С‹С‚РёСЏ");
		dateOfArrivalColumn.setWidth(100);

		TableColumn growthColumn = new TableColumn(table, SWT.DEFAULT);
		growthColumn.setText("Р РѕСЃС‚");
		growthColumn.setWidth(40);

		TableColumn hairColorColumn = new TableColumn(table, SWT.DEFAULT);
		hairColorColumn.setText("Р¦РІРµС‚ РІРѕР»РѕСЃ");
		hairColorColumn.setWidth(80);

		TableColumn specialSingsColumn = new TableColumn(table, SWT.DEFAULT);
		specialSingsColumn.setText("РћСЃРѕР±С‹Рµ РїСЂРёРјРµС‚С‹");
		specialSingsColumn.setWidth(120);

		TableColumn possibleAgeColumn = new TableColumn(table, SWT.DEFAULT);
		possibleAgeColumn.setText("Р’РѕР·РјРѕР¶РЅС‹Р№ РІРѕР·СЂР°СЃС‚");
		possibleAgeColumn.setWidth(130);

		TableColumn numberOfRoomColumn = new TableColumn(table, SWT.DEFAULT);
		numberOfRoomColumn.setText("РџР°Р»Р°С‚Р°");
		numberOfRoomColumn.setWidth(60);

		TableColumn telephoneNumberColumn = new TableColumn(table, SWT.DEFAULT);
		telephoneNumberColumn.setText("РўРµР»РµС„РѕРЅ");
		telephoneNumberColumn.setWidth(80);

		/*TableColumn transferColumn = new TableColumn(table, SWT.DEFAULT);
		transferColumn.setText("РџРµСЂРµРІРѕРґ");
		transferColumn.setWidth(130);*/

		TableColumn dateOfDischargeColumn = new TableColumn(table, SWT.DEFAULT);
		dateOfDischargeColumn.setText("Р”Р°С‚Р° РІС‹РїРёСЃРєРё");
		dateOfDischargeColumn.setWidth(100);

		TableColumn causeOfDischargeColumn = new TableColumn(table, SWT.DEFAULT);
		causeOfDischargeColumn.setText("РџСЂРёС‡РёРЅР° РІС‹РїРёСЃРєРё");
		causeOfDischargeColumn.setWidth(130);

		initiateListeners(shell);
	}

    public void createTableRooms(Composite shell) {

		createTable(shell);

        TableColumn roomColumn = new TableColumn(table, SWT.DEFAULT);
        roomColumn.setText("РџР°Р»Р°С‚Р°");
        roomColumn.setWidth(100);

        TableColumn telephoneColumn = new TableColumn(table, SWT.DEFAULT);
        telephoneColumn.setText("РўРµР»РµС„РѕРЅ");
        telephoneColumn.setWidth(100);


        initiateListenersRoom(shell);
    }

    private void initiateListenersRoom(Composite shell) {
        table.addListener( SWT.Selection, event -> {
            informationAboutRoom = new ArrayList<>();
            TableItem i = (TableItem) event.item;
            keyOfItem = Integer.parseInt(i.getText());
			informationAboutRoom.add(i.getText(0));
			informationAboutRoom.add(i.getText(1));
        });

        addRoom = new Button(shell, SWT.PUSH);
        addRoom.setText("Р”РѕР±Р°РІРёС‚СЊ");
        addRoom.setBounds(1050, 95, 133, 40);

        addRoom.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                new AddWindowRoom(display, controller, RecordsOnPage.this, shell);
            }
        });

        deleteRoom = new Button(shell, SWT.PUSH);
        deleteRoom.setText("РЈРґР°Р»РёС‚СЊ");
        deleteRoom.setBounds(1050, 135, 133, 40);
		shellHelp = (Shell) shell;
        deleteRoom.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
				if (keyOfItem != 0) {
					List<Patient> help = controller.getAllPatients();
					for (Patient patient : help) {
						if (patient.getNumberOfRoom().equals(Integer.toString(keyOfItem))) {
							errorAlarm(shellHelp);
							return;
						}
					}
					controller.deleteRoom(keyOfItem);
					refreshRooms(shell);
					createTableRooms(shell);
					setRooms(controller.getAllRooms());
				}
			}
        });

        changeRoom = new Button(shell, SWT.PUSH);
        changeRoom.setText("Р�Р·РјРµРЅРёС‚СЊ");
        changeRoom.setBounds(1050, 175, 133, 40);
        changeRoom.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
				if (keyOfItem != 0) {
					new ChangeWindowRoom(display, controller, RecordsOnPage.this, shell, keyOfItem, informationAboutRoom);
				}
			}
        });
    }

	private void initiateListeners(Composite shell) {
		table.addListener( SWT.Selection, event -> {
            informationAboutPatient = new ArrayList<>();
			TableItem i = (TableItem) event.item;
			keyOfItem = Integer.parseInt(i.getText());
			String[] help = i.getText(1).split(" ");
            for (int k = 0; k < 3; k++){
                if (help.length - k - 1 >= 0) informationAboutPatient.add(help[k]);
                else informationAboutPatient.add("");
            }
			for (int k = 2; k < 16; k++){
                informationAboutPatient.add(i.getText(k));
            }
		});

		add = new Button(shell, SWT.PUSH);
		add.setText("Р”РѕР±Р°РІРёС‚СЊ");
		add.setBounds(1050, 95, 133, 40);

		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new AddWindow(display, controller, RecordsOnPage.this, shell);
			}
		});

		delete = new Button(shell, SWT.PUSH);
		delete.setText("РЈРґР°Р»РёС‚СЊ");
		delete.setBounds(1050, 135, 133, 40);
		delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (keyOfItem != 0) {
					controller.deletePatient(keyOfItem);
					refresh(shell);
					createTablePatients(shell);
					setRecords(controller.getAllPatients());
				}
			}
		});

		change = new Button(shell, SWT.PUSH);
		change.setText("Р�Р·РјРµРЅРёС‚СЊ");
		change.setBounds(1050, 175, 133, 40);
		change.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (keyOfItem != 0) {
				new ChangeWindow(display, controller, RecordsOnPage.this, shell, keyOfItem, informationAboutPatient);
				}
			}
		});

		findRoom = new Button(shell, SWT.PUSH);
		findRoom.setText("РќР°Р№С‚Рё РїР°Р»Р°С‚Сѓ");
		findRoom.setBounds(1050, 215, 133, 40);
		findRoom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new FindRoomAndTelephone(display, controller);
			}
		});

		findDate = new Button(shell, SWT.PUSH);
		findDate.setText("РќР°Р№С‚Рё РїРѕ РґР°С‚Рµ");
		findDate.setBounds(1050, 255, 133, 40);
		findDate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new FindDate(display, controller);
			}
		});

		findFemale = new Button(shell, SWT.PUSH);
        findFemale.setText("РќР°Р№С‚Рё РїРѕ РІРѕР·СЂР°СЃС‚Сѓ");
        findFemale.setBounds(1050, 295, 133, 40);
        findFemale.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new FindWoman(display, controller);
            }
        });

        transfer = new Button(shell, SWT.PUSH);
		transfer.setText("РџРµСЂРµРІРµСЃС‚Рё");
		transfer.setBounds(1050, 335, 133, 40);
		transfer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (keyOfItem != 0) {
					new TransferWindow(display, controller, RecordsOnPage.this, shell, keyOfItem, informationAboutPatient);
				}
			}
		});

		informationAboutTransfer = new Button(shell, SWT.PUSH);
		informationAboutTransfer.setText("Р’СЃРµ РїРµСЂРµРІРѕРґС‹");
		informationAboutTransfer.setBounds(1050, 375, 133, 40);
		informationAboutTransfer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (keyOfItem != 0) {
					new InfAboutTransferWindow(display, controller, keyOfItem);
				}
			}
		});
	}

	public void createTableRoomAndTelephone(Composite shell) {

		createTable(shell);

		TableColumn FIOColumn = new TableColumn(table, SWT.DEFAULT);
		FIOColumn.setText("Р¤Р�Рћ");
		FIOColumn.setWidth(200);

		TableColumn numberOfRoomColumn = new TableColumn(table, SWT.DEFAULT);
		numberOfRoomColumn.setText("РџР°Р»Р°С‚Р°");
		numberOfRoomColumn.setWidth(60);

		TableColumn telephoneNumberColumn = new TableColumn(table, SWT.DEFAULT);
		telephoneNumberColumn.setText("РўРµР»РµС„РѕРЅ");
		telephoneNumberColumn.setWidth(80);

		TableColumn ageColumn = new TableColumn(table, SWT.DEFAULT);
		ageColumn.setText("Р’РѕР·СЂР°СЃС‚");
		ageColumn.setWidth(80);
	}

	public void createTableDate(Composite shell) {

		createTable(shell);

		TableColumn FIOColumn = new TableColumn(table, SWT.DEFAULT);
		FIOColumn.setText("Р¤Р�Рћ");
		FIOColumn.setWidth(200);

		TableColumn numberOfRoomColumn = new TableColumn(table, SWT.DEFAULT);
		numberOfRoomColumn.setText("РџР°Р»Р°С‚Р°");
		numberOfRoomColumn.setWidth(60);

		TableColumn dateOfArrivalColumn = new TableColumn(table, SWT.DEFAULT);
		dateOfArrivalColumn.setText("Р”Р°С‚Р° РїСЂРёР±С‹С‚РёСЏ");
		dateOfArrivalColumn.setWidth(100);

		TableColumn ageColumn = new TableColumn(table, SWT.DEFAULT);
		ageColumn.setText("Р’РѕР·СЂР°СЃС‚");
		ageColumn.setWidth(80);
	}

	public void createTableWoman(Composite shell){
		createTable(shell);

        TableColumn FIOColumn = new TableColumn(table, SWT.DEFAULT);
        FIOColumn.setText("Р¤Р�Рћ");
        FIOColumn.setWidth(200);

        TableColumn ageColumn = new TableColumn(table, SWT.DEFAULT);
        ageColumn.setText("Р’РѕР·СЂР°СЃС‚");
        ageColumn.setWidth(80);
    }

	public void setRecords(List<Patient> patientToSet) {
		for (Patient patient : patientToSet) {
			TableItem tableItem = new TableItem(table, SWT.DEFAULT);
			tableItem.setText(0, Integer.toString(patient.getKey()));
			tableItem.setText(1, patient.getSurname() + " " + patient.getName() + " " + patient.getSecondName());
			tableItem.setText(2, patient.getSex());
			tableItem.setText(3, patient.getAge());
			tableItem.setText(4, patient.getDiagnosis());
			tableItem.setText(5, patient.getHowArrive());
			tableItem.setText(6, patient.getDateOfArrival());
			tableItem.setText(7, patient.getGrowth());
			tableItem.setText(8, patient.getHairColor());
			tableItem.setText(9, patient.getSpecialSigns());
			tableItem.setText(10, patient.getPossibleSigns());
			tableItem.setText(11, patient.getNumberOfRoom());
			tableItem.setText(12, patient.getTelephoneNumber());
			//tableItem.setText(13, patient.getTransfer());
			tableItem.setText(13, patient.getDateOfDischarge());
			tableItem.setText(14, patient.getCauseOfDischarge());
		}
	}

	public void setRooms(Map<Integer, String> allRooms) {
		for (Map.Entry room : allRooms.entrySet()) {
			TableItem tableItem = new TableItem(table, SWT.DEFAULT);
			tableItem.setText(0, Integer.toString((Integer) room.getKey()));
			tableItem.setText(1, (String) room.getValue());
		}
	}

	public void setRecordsRoom(List<Patient> patientToSet, boolean age) {
		for (Patient patient : patientToSet) {
			TableItem tableItem = new TableItem(table, SWT.DEFAULT);
			tableItem.setText(0, patient.getSurname() + " " + patient.getName() + " " + patient.getSecondName());
			tableItem.setText(1, patient.getNumberOfRoom());
			tableItem.setText(2, patient.getTelephoneNumber());
			if (age) tableItem.setText(3, patient.getAge());
		}
	}

	public void setRecordsDate(List<Patient> patientToSet) {
		for (Patient patient : patientToSet) {
			TableItem tableItem = new TableItem(table, SWT.DEFAULT);
			tableItem.setText(0, patient.getSurname() + " " + patient.getName() + " " + patient.getSecondName());
			tableItem.setText(1, patient.getNumberOfRoom());
			tableItem.setText(2, patient.getDateOfArrival());
			tableItem.setText(3, patient.getAge());
		}
	}

    public void setRecordsWoman(List<Patient> patientToSet) {
        for (Patient patient : patientToSet) {
            TableItem tableItem = new TableItem(table, SWT.DEFAULT);
            tableItem.setText(0, patient.getSurname() + " " + patient.getName() + " " + patient.getSecondName());
            tableItem.setText(1, patient.getAge());
        }
    }

	public void refresh(Composite shell){
		table.dispose();
		add.dispose();
		delete.dispose();
		change.dispose();
		findRoom.dispose();
		findDate.dispose();
		findFemale.dispose();
		transfer.dispose();
		informationAboutTransfer.dispose();
        shell.layout(true);
	}

    public void refreshRooms(Composite shell){
        table.dispose();
        addRoom.dispose();
        deleteRoom.dispose();
        changeRoom.dispose();
        shell.layout(true);
    }

	public void refreshTasksTable(Composite shell) {
		table.dispose();
		shell.layout(true);
	}

	private void errorAlarm(Shell shell) {
		MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
		messageError.setText("ERROR!");
		messageError.setMessage("РќРµР»СЊР·СЏ СѓРґР°Р»РёС‚СЊ РїР°Р»Р°С‚Сѓ");
		messageError.open();
	}
}