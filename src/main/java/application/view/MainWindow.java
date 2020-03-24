/*
 * Created by JFormDesigner on Thu Jul 20 12:54:15 CEST 2017
 */

package application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import application.ApplicationFacade;
import application.model.items.ItemVO;

/**
 * @author Barbara Mezzabotta
 */
public class MainWindow extends JFrame {
	
	private MainWindowMediator mediator;
	
	public MainWindow(MainWindowMediator m) {
		System.out.println("MainWindow()");
		initComponents();
		textField1.requestFocus();
		
		this.mediator = m;
	}

	private void addButtonActionPerformed(ActionEvent e) {
		System.out.println("  MainWindow: addButtonActionPerformed()");
		String s = textField1.getText();
		if (!s.isEmpty()) {
			ItemVO item = new ItemVO(textField1.getText());
			mediator.sendNotification(ApplicationFacade.ADD_ITEM, item);
		}
		
		textField1.requestFocus();
	}
	
	public void insertText(ItemVO item) {
		System.out.println("  MainWindow: insertText()");
		textArea1.setText(textArea1.getText() + item.getText() + "\n");
	}
	
	public void clearTextField() {
		System.out.println("  MainWindow: clearTextField()");
		textField1.setText("");
	}
	
	public void clearTextArea() {
		System.out.println("  MainWindow: clearTextArea()");
		textArea1.setText("");
	}

	private void menuItem1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void menuItem3ActionPerformed(ActionEvent e) {
		mediator.sendNotification(ApplicationFacade.SHUTDOWN);
		//System.exit(0);
	}

	private void initComponents() {
		System.out.println("  MainWindow: initComponents()");
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();
		buttonBar = new JPanel();
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		addButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("File");

				//---- menuItem1 ----
				menuItem1.setText("Add");
				menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
				menu1.add(menuItem1);

				//---- menuItem2 ----
				menuItem2.setText("Cancel");
				menu1.add(menuItem2);

				//---- menuItem3 ----
				menuItem3.setText("Exit");
				menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK));
				menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
				menu1.add(menuItem3);
			}
			menuBar1.add(menu1);
		}
		setJMenuBar(menuBar1);

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new BorderLayout());

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(textArea1);
				}
				contentPanel.add(scrollPane1, BorderLayout.CENTER);
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

				//======== panel1 ========
				{
					panel1.setLayout(new BorderLayout(5, 5));

					//---- label1 ----
					label1.setText("Item:");
					panel1.add(label1, BorderLayout.WEST);
					panel1.add(textField1, BorderLayout.CENTER);
				}
				buttonBar.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- addButton ----
				addButton.setText("Add");
				addButton.addActionListener(e -> addButtonActionPerformed(e));
				buttonBar.add(addButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	private JPanel buttonBar;
	private JPanel panel1;
	private JLabel label1;
	private JTextField textField1;
	private JButton addButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
