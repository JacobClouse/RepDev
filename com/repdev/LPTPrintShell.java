/**
 *  RepDev - RepGen IDE for Symitar
 *  Copyright (C) 2007  Jake Poznanski, Ryan Schultz, Sean Delaney
 *  http://repdev.org/ <support@repdev.org>
 *
 *  This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.repdev;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class LPTPrintShell {
	private Shell shell;
	private boolean result = false;

	private void create(Shell parent, final SymitarFile file) {
		FormLayout layout = new FormLayout();
		layout.marginTop = 5;
		layout.marginBottom = 5;
		layout.marginLeft = 5;
		layout.marginRight = 5;
		layout.spacing = 5;

		shell = new Shell(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		shell.setText("Line Printer Options");
		shell.setLayout(layout);
		
		Label queueLabel = new Label(shell,SWT.NONE);
		queueLabel.setText("LPT Queue");

		Label overrideLabel = new Label(shell,SWT.NONE);
		overrideLabel.setText("Forms Override");
		
		Label lengthLabel = new Label(shell,SWT.NONE);
		lengthLabel.setText("Form Length");
		
		Label startLabel = new Label(shell,SWT.NONE);
		startLabel.setText("Start Page");
		
		Label endLabel = new Label(shell,SWT.NONE);
		endLabel.setText("End Page");
		
		Label copiesLabel = new Label(shell,SWT.NONE);
		copiesLabel.setText("Copies");
		
		Label landscapeLabel = new Label(shell,SWT.NONE);
		landscapeLabel.setText("Landscape");
		
		Label duplexLabel = new Label(shell,SWT.NONE);
		duplexLabel.setText("Duplex");
		
		Label priorityLabel = new Label(shell,SWT.NONE);
		priorityLabel.setText("Queue Priority");
		
		//Select All when tabbing through
		FocusListener selectAllFocuser = new FocusListener(){

			public void focusGained(FocusEvent e) {
				if( e.widget instanceof Text)
					((Text)e.widget).selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
			
		};
		
		MouseListener mouseFocuser = new MouseAdapter(){

			public void mouseDown(MouseEvent e) {
				if( e.widget instanceof Text)
					((Text)e.widget).selectAll();
			}
		
		};
		
		final Text queueText = new Text(shell,SWT.BORDER);
		queueText.setText("0");
		queueText.selectAll();
		queueText.addFocusListener(selectAllFocuser);
		queueText.addMouseListener(mouseFocuser);
		
		final Combo overrideCombo = new Combo(shell,SWT.READ_ONLY);
		overrideCombo.add("No");
		overrideCombo.add("Yes");
		overrideCombo.select(0);
		
		final Text lengthText = new Text(shell,SWT.BORDER);
		lengthText.setText("0");
		lengthText.addFocusListener(selectAllFocuser);
		lengthText.addMouseListener(mouseFocuser);
		
		final Text startText = new Text(shell,SWT.BORDER);
		startText.setText("0");
		startText.addFocusListener(selectAllFocuser);
		startText.addMouseListener(mouseFocuser);
		
		final Text endText = new Text(shell,SWT.BORDER);
		endText.setText("0");
		endText.addFocusListener(selectAllFocuser);
		endText.addMouseListener(mouseFocuser);
		
		final Text copiesText = new Text(shell,SWT.BORDER);
		copiesText.setText("1");
		copiesText.addFocusListener(selectAllFocuser);
		copiesText.addMouseListener(mouseFocuser);
		
		final Combo landscapeCombo = new Combo(shell,SWT.READ_ONLY);
		landscapeCombo.add("No");
		landscapeCombo.add("Yes");
		landscapeCombo.select(0);
		
		final Combo duplexCombo = new Combo(shell,SWT.READ_ONLY);
		duplexCombo.add("No");
		duplexCombo.add("Yes");
		duplexCombo.select(0);
		
		
		final Text priorityText = new Text(shell,SWT.BORDER);
		priorityText.setText("4");
		priorityText.addFocusListener(selectAllFocuser);
		priorityText.addMouseListener(mouseFocuser);
		
		Button okButton = new Button(shell,SWT.PUSH);
		okButton.setText("Print");
		
		Button cancelButton = new Button(shell,SWT.PUSH);
		cancelButton.setText("Cancel");
		
		shell.setDefaultButton(okButton);
		queueText.setFocus();
		
		FormData data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(0);
		data.width=120;
		queueLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(overrideLabel);
		data.top = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.width=80;
		queueText.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(queueText);
		data.width=120;
		overrideLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(overrideLabel);
		data.top = new FormAttachment(queueText);
		data.right = new FormAttachment(100);
		data.width=80;
		overrideCombo.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(overrideCombo);
		data.width=120;
		lengthLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(lengthLabel);
		data.top = new FormAttachment(overrideCombo);
		data.right = new FormAttachment(100);
		data.width=80;
		lengthText.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(lengthText);
		data.width=120;
		startLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(startLabel);
		data.top = new FormAttachment(lengthText);
		data.right = new FormAttachment(100);
		data.width=80;
		startText.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(startText);
		data.width=120;
		endLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(endLabel);
		data.top = new FormAttachment(startText);
		data.right = new FormAttachment(100);
		data.width=80;
		endText.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(endText);
		data.width=120;
		copiesLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(copiesLabel);
		data.top = new FormAttachment(endText);
		data.right = new FormAttachment(100);
		data.width=80;
		copiesText.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(copiesText);
		data.width=120;
		landscapeLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(landscapeLabel);
		data.top = new FormAttachment(copiesText);
		data.right = new FormAttachment(100);
		data.width=80;
		landscapeCombo.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(landscapeCombo);
		data.width=120;
		duplexLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(duplexLabel);
		data.top = new FormAttachment(landscapeCombo);
		data.right = new FormAttachment(100);
		data.width=80;
		duplexCombo.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(duplexCombo);
		data.width=120;
		priorityLabel.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(priorityLabel);
		data.top = new FormAttachment(duplexCombo);
		data.right = new FormAttachment(100);
		data.width=80;
		priorityText.setLayoutData(data);
		
		data = new FormData();
		data.top = new FormAttachment(priorityText);
		data.right = new FormAttachment(100);
		cancelButton.setLayoutData(data);
		cancelButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = false;
				shell.dispose();
			}			
		});
		
		data = new FormData();
		data.right = new FormAttachment(cancelButton);
		data.top= new FormAttachment(priorityText);
		okButton.setLayoutData(data);
		okButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = true;
				
				SessionError result = SessionError.INPUT_ERROR;
				
				try {
					result = RepDevMain.SYMITAR_SESSIONS.get(file.getSym()).printFileLPT(file, Integer.parseInt(queueText.getText()), false, 0, Integer.parseInt(startText.getText()), Integer.parseInt(endText.getText()), Integer.parseInt(copiesText.getText()), landscapeCombo.getSelectionIndex()==1, duplexCombo.getSelectionIndex() == 1, Integer.parseInt(priorityText.getText()));
				} catch (NumberFormatException e1) {
					
				}
				
				if( result != SessionError.NONE){
					MessageBox dialog = new MessageBox(shell,SWT.OK | SWT.ICON_ERROR);
					dialog.setText("Print Error");
					dialog.setMessage(result.toString());
					dialog.open();
				}
				
				shell.dispose();
			}			
		});
		
		shell.pack();
		shell.open();
	}

	/**
	 * Convencience method to just print a symitar file and ask for all needed infoz
	 * @param display
	 * @param parent
	 * @param file
	 * @return
	 */
	public static boolean print(Display display, Shell parent, SymitarFile file) {
		LPTPrintShell dialog = new LPTPrintShell(); 
		dialog.create(parent, file);

		while (!dialog.shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		return dialog.result;
	}
}
