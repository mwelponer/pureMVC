/*
 * Created by JFormDesigner on Thu Jul 20 12:54:15 CEST 2017
 */

package eu.it.welponer.testPuremvc.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import eu.it.welponer.testPuremvc.ApplicationFacade;
import eu.it.welponer.testPuremvc.model.messages.MessageVO;
import eu.it.welponer.testPuremvc.model.client.RequestMethod;
import lombok.Getter;
import lombok.Setter;
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

    synchronized public void writeToOutputConsole(String output){
        System.out.println("  MainWindow: writeToOutputConsole()");
        response_textArea.append(output);
        response_textArea.append("\r\n");
    }

    synchronized public void clearOutputConsole(){
        System.out.println("  MainWindow: clearOutputConsole()");
        response_textArea.setText("");
    }

    private void send_ButtonActionPerformed(ActionEvent e) {
        System.out.println("  MainWindow: send_ButtonActionPerformed()");

        MessageVO message = new MessageVO();
        message.setMethod(RequestMethod.parse(method_comboBox.getSelectedIndex()));
        message.setTimestamp(System.currentTimeMillis());
        message.setTargetURL(url_textField.getText());
        message.setJsonObject(new JSONObject(input_textArea.getText()));

        mediator.sendNotification(ApplicationFacade.SEND_MESSAGE, message);
	}

	public void changeUrl(String url){
        url_textField.setText(url);
    }

    public void changeStatusbar(String status){statusBar_label.setText(status);}

    public void changeMethod(int methodIndex){
	    method_comboBox.setSelectedIndex(methodIndex);
    }

    private void clear_ButtonActionPerformed(ActionEvent e) {
        System.out.println("  MainWindow: clear_ButtonActionPerformed()");

        mediator.sendNotification(ApplicationFacade.CLEAR_MESSAGES);
    }

    private void send_menuItemActionPerformed(ActionEvent e) {
        send_ButtonActionPerformed(e);
    }

    private void clear_menuItemActionPerformed(ActionEvent e) {
        clearOutputConsole();
    }

    private void exit_menuItemActionPerformed(ActionEvent e) {
        mediator.sendNotification(ApplicationFacade.SHUTDOWN);
    }

    private void portChange_menuItemActionPerformed(ActionEvent e) {
	    mediator.changeServerPort();
    }

	private void initComponents() {
		System.out.println("  MainWindow: initComponents()");
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar = new JMenuBar();
        file_menu = new JMenu();
        send_menuItem = new JMenuItem();
        clear_menuItem = new JMenuItem();
        exit_menuItem = new JMenuItem();
        edit_menu = new JMenu();
        portChange_menuItem = new JMenuItem();
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
        clear_Button = new JButton();
        send_Button = new JButton();
        Status_panel = new JPanel();
        statusBar_label = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar ========
        {
            menuBar.setBorder(null);

            //======== file_menu ========
            {
                file_menu.setText("File");

                //---- send_menuItem ----
                send_menuItem.setText("Send Message");
                send_menuItem.addActionListener(e -> send_menuItemActionPerformed(e));
                file_menu.add(send_menuItem);

                //---- clear_menuItem ----
                clear_menuItem.setText("Clear Console");
                clear_menuItem.addActionListener(e -> clear_menuItemActionPerformed(e));
                file_menu.add(clear_menuItem);

                //---- exit_menuItem ----
                exit_menuItem.setText("Exit");
                exit_menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK));
                exit_menuItem.addActionListener(e -> exit_menuItemActionPerformed(e));
                file_menu.add(exit_menuItem);
            }
            menuBar.add(file_menu);

            //======== edit_menu ========
            {
                edit_menu.setText("Edit");

                //---- portChange_menuItem ----
                portChange_menuItem.setText("Change Server port");
                portChange_menuItem.addActionListener(e -> portChange_menuItemActionPerformed(e));
                edit_menu.add(portChange_menuItem);
            }
            menuBar.add(edit_menu);
        }
        setJMenuBar(menuBar);

        //======== dialogPane ========
        {
            dialogPane.setMinimumSize(new Dimension(265, 124));
            dialogPane.setBorder(null);
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setBorder(BorderFactory.createEmptyBorder());
                contentPanel.setLayout(new BorderLayout());

                //======== splitPane1 ========
                {
                    splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
                    splitPane1.setDividerLocation(90);
                    splitPane1.setBorder(null);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportBorder(null);

                        //---- input_textArea ----
                        input_textArea.setText("{\"coordX\": 0.111,\"coordY\": 6.666}");
                        input_textArea.setMargin(new Insets(2, 3, 0, 0));
                        scrollPane2.setViewportView(input_textArea);
                    }
                    splitPane1.setTopComponent(scrollPane2);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportBorder(null);

                        //---- response_textArea ----
                        response_textArea.setMargin(new Insets(2, 3, 0, 0));
                        scrollPane1.setViewportView(response_textArea);
                    }
                    splitPane1.setBottomComponent(scrollPane1);
                }
                contentPanel.add(splitPane1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(2, 0, 2, 0));
                buttonBar.setLayout(new GridLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

                    //---- method_comboBox ----
                    method_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                        "GET",
                        "HEAD",
                        "POST"
                    }));
                    method_comboBox.setPreferredSize(new Dimension(93, 29));
                    method_comboBox.setMinimumSize(new Dimension(99, 29));
                    method_comboBox.setMaximumSize(new Dimension(32767, 29));
                    method_comboBox.setBorder(UIManager.getBorder("List.border"));
                    method_comboBox.setAlignmentY(0.51F);
                    panel1.add(method_comboBox);

                    //---- url_textField ----
                    url_textField.setPreferredSize(new Dimension(30, 29));
                    url_textField.setMargin(new Insets(0, 20, 0, 0));
                    url_textField.setText(" http://localhost:9000");
                    url_textField.setMaximumSize(new Dimension(2147483647, 29));
                    url_textField.setFocusCycleRoot(true);
                    url_textField.setScrollOffset(1);
                    url_textField.setBorder(new LineBorder(Color.lightGray));
                    url_textField.setMinimumSize(new Dimension(64, 29));
                    panel1.add(url_textField);

                    //---- clear_Button ----
                    clear_Button.setText("Clear");
                    clear_Button.setMinimumSize(new Dimension(50, 25));
                    clear_Button.setMaximumSize(new Dimension(78, 50));
                    clear_Button.addActionListener(e -> clear_ButtonActionPerformed(e));
                    panel1.add(clear_Button);

                    //---- send_Button ----
                    send_Button.setText("Send");
                    send_Button.setMaximumSize(new Dimension(78, 50));
                    send_Button.setMinimumSize(new Dimension(78, 25));
                    send_Button.addActionListener(e -> send_ButtonActionPerformed(e));
                    panel1.add(send_Button);
                }
                buttonBar.add(panel1);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);

        //======== Status_panel ========
        {
            Status_panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            Status_panel.setLayout(new BoxLayout(Status_panel, BoxLayout.X_AXIS));

            //---- statusBar_label ----
            statusBar_label.setText("abcdefghilmnopqrstuvwz");
            statusBar_label.setBorder(new EmptyBorder(2, 5, 3, 0));
            Status_panel.add(statusBar_label);
        }
        contentPane.add(Status_panel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JMenuBar menuBar;
    private JMenu file_menu;
    private JMenuItem send_menuItem;
    private JMenuItem clear_menuItem;
    private JMenuItem exit_menuItem;
    private JMenu edit_menu;
    private JMenuItem portChange_menuItem;
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
    private JButton clear_Button;
    private JButton send_Button;
    private JPanel Status_panel;
    private JLabel statusBar_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
