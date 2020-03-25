/*
 * Created by JFormDesigner on Thu Jul 20 12:54:15 CEST 2017
 */

package application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import application.ApplicationFacade;
import application.model.messages.MessageVO;
import application.model.server.RequestMethod;
import org.json.JSONObject;

/**
 * @author Michele Welponer
 */
public class MainWindow extends JFrame {
	
	private MainWindowMediator mediator;
	
	public MainWindow(MainWindowMediator m) {
		System.out.println("MainWindow()");
		initComponents();
		url_textField.requestFocus();
		
		this.mediator = m;
	}

//	private void addButtonActionPerformed(ActionEvent e) {
//		System.out.println("  MainWindow: addButtonActionPerformed()");
//		String s = textField1.getText();
//		if (!s.isEmpty()) {
//			ItemVO item = new ItemVO(textField1.getText());
//			mediator.sendNotification(ApplicationFacade.ADD_ITEM, item);
//		}
//
//		textField1.requestFocus();
//	}

    public void writeToOutputConsole(String output){
        System.out.println("  MainWindow: writeToOutputConsole()");
	    response_textArea.append(output);
    }

    public void clearOutputConsole(){
        System.out.println("  MainWindow: clearOutputConsole()");
        response_textArea.setText("");
    }
	
	public void clearTextField() {
		System.out.println("  MainWindow: clearTextField()");
        url_textField.setText("");
	}

	private void menuItem3ActionPerformed(ActionEvent e) {
		mediator.sendNotification(ApplicationFacade.SHUTDOWN);
		//System.exit(0);
	}

    private void send_ButtonActionPerformed(ActionEvent e) {
        System.out.println("  MainWindow: sendButtonActionPerformed()");

        MessageVO message = new MessageVO();
        message.setMethod(RequestMethod.parse(method_comboBox.getSelectedIndex()));
        message.setTimestamp(System.currentTimeMillis());
        message.setTargetURL(url_textField.getText());
        message.setJsonObject(new JSONObject(input_textArea.getText()));

        mediator.sendNotification(ApplicationFacade.SEND_MESSAGE, message);
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
        splitPane1 = new JSplitPane();
        scrollPane2 = new JScrollPane();
        input_textArea = new JTextArea();
        scrollPane1 = new JScrollPane();
        response_textArea = new JTextArea();
        buttonBar = new JPanel();
        panel1 = new JPanel();
        method_comboBox = new JComboBox<>();
        url_textField = new JTextField();
        send_Button = new JButton();

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
            dialogPane.setMinimumSize(new Dimension(265, 124));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== splitPane1 ========
                {
                    splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
                    splitPane1.setDividerLocation(90);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(input_textArea);
                    }
                    splitPane1.setTopComponent(scrollPane2);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(response_textArea);
                    }
                    splitPane1.setBottomComponent(scrollPane1);
                }
                contentPanel.add(splitPane1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

                    //---- method_comboBox ----
                    method_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                        "GET",
                        "POST"
                    }));
                    panel1.add(method_comboBox);

                    //---- url_textField ----
                    url_textField.setPreferredSize(new Dimension(200, 30));
                    url_textField.setMargin(new Insets(2, 60, 2, 6));
                    url_textField.setText("http://");
                    panel1.add(url_textField);

                    //---- send_Button ----
                    send_Button.setText("Send");
                    send_Button.addActionListener(e -> {
			send_ButtonActionPerformed(e);
		});
                    panel1.add(send_Button);
                }
                buttonBar.add(panel1);
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
    private JSplitPane splitPane1;
    private JScrollPane scrollPane2;
    private JTextArea input_textArea;
    private JScrollPane scrollPane1;
    private JTextArea response_textArea;
    private JPanel buttonBar;
    private JPanel panel1;
    private JComboBox<String> method_comboBox;
    private JTextField url_textField;
    private JButton send_Button;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
