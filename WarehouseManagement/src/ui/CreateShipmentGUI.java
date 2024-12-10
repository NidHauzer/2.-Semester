package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.EmployeeController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CreateShipmentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private static JLabel lblReceiver;
	private static JLabel lblEmployee;
	
	private static EmployeeController ec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateShipmentGUI frame = new CreateShipmentGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public CreateShipmentGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(12, 12, 137, 15);
		contentPane.add(lblEmployee);
		
		textField = new JTextField();
		textField.setToolTipText("Employee number");
		textField.setBounds(12, 31, 151, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnFindEmployee = new JButton("Find");
		btnFindEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFindEmployeeThread(Integer.parseInt(textField.getText()));
			}
		});
		btnFindEmployee.setBounds(175, 28, 74, 25);
		contentPane.add(btnFindEmployee);
		
		lblReceiver = new JLabel("Receiver:");
		lblReceiver.setBounds(12, 62, 55, 15);
		contentPane.add(lblReceiver);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 80, 151, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnFindEmployee_1 = new JButton("Find");
		btnFindEmployee_1.setBounds(175, 77, 74, 25);
		contentPane.add(btnFindEmployee_1);
		
		JButton btnCreateShipment = new JButton("Create Shipment");
		btnCreateShipment.setBounds(119, 144, 130, 25);
		contentPane.add(btnCreateShipment);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 144, 74, 25);
		contentPane.add(btnCancel);
	}
	

	private static void startFindEmployeeThread(int employeeNo) {
		ec = new EmployeeController();
		
		SwingWorker sw1 = new SwingWorker() {
			protected String doInBackground() {
				String name = null;
				try {
					name = ec.findEmployeeByEmployeeNo(employeeNo).getName();
				} catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No employee found.");
				};
				return name;
			}
			
			protected void done() {
				lblEmployee.setText("Employee: " + doInBackground());
			}
		};
		
		sw1.execute();
	}
}
