package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.EmployeeController;
import controller.PartyController;
import controller.ShipmentController;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;

public class CreateShipmentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmployee;
	private JTextField textFieldReceiver;
	
	private static JLabel lblReceiver;
	private static JLabel lblEmployee;
	
	private static EmployeeController ec;
	private static PartyController pc;
	private static ShipmentController sc;
	
	private static int employeeNo;
	private static String phoneNo;
	

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
		setBounds(100, 100, 274, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(12, 12, 237, 15);
		contentPane.add(lblEmployee);
		
		textFieldEmployee = new JTextField();
		textFieldEmployee.setToolTipText("Employee number");
		textFieldEmployee.setBounds(12, 31, 151, 19);
		contentPane.add(textFieldEmployee);
		textFieldEmployee.setColumns(10);
		
		JButton btnFindEmployee = new JButton("Find");
		btnFindEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeNo = Integer.parseInt(textFieldEmployee.getText());
				startFindEmployeeThread(employeeNo);
			}
		});
		btnFindEmployee.setBounds(175, 28, 74, 25);
		contentPane.add(btnFindEmployee);
		
		lblReceiver = new JLabel("Receiver:");
		lblReceiver.setBounds(12, 62, 237, 15);
		contentPane.add(lblReceiver);
		
		textFieldReceiver = new JTextField();
		textFieldReceiver.setBounds(12, 80, 151, 19);
		contentPane.add(textFieldReceiver);
		textFieldReceiver.setColumns(10);
		
		JButton btnFindReceiver = new JButton("Find");
		btnFindReceiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phoneNo = textFieldReceiver.getText();
				startFindReceiverThread(phoneNo);
			}
		});
		btnFindReceiver.setBounds(175, 77, 74, 25);
		contentPane.add(btnFindReceiver);
		
		JButton btnCreateShipment = new JButton("Create Shipment");
		btnCreateShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startCreateShipmentThread(employeeNo, phoneNo);
			}
		});
		btnCreateShipment.setBounds(119, 144, 130, 25);
		contentPane.add(btnCreateShipment);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				CreateShipmentGUI.this.dispose();
			}
		});
		btnCancel.setBounds(12, 144, 74, 25);
		contentPane.add(btnCancel);
	}
	
	
	private static void startCreateShipmentThread(int employeeNo, String phoneNo) {
		sc = new ShipmentController();
		
		SwingWorker sw = new SwingWorker() {
			int shipmentNo = 0;
			
			protected Integer doInBackground() {
				try {
					shipmentNo = sc.createShipment(employeeNo, phoneNo, LocalDate.now()).getShipmentNo();
					JOptionPane.showMessageDialog(new JFrame(), "Shipment created with shipment number " + shipmentNo);
				} catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "Error! No shipment was created.");
					cancel(true);
				};
				return shipmentNo;
			}
		};
		sw.execute();
	}
	
	private static void startFindReceiverThread(String phoneNo) {
		pc = new PartyController();
		
		SwingWorker sw = new SwingWorker() {
			String name = "";
			
			protected String doInBackground() {
				try {
					name = pc.findPartyByPhoneNo(phoneNo).getName();
				} catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No receiver found.");
					cancel(true);
				};
				return name;
			}
			
			protected void done() {
				if(!isCancelled()) lblReceiver.setText("Receiver: " + name);
			}
		};
		
		sw.execute();
	}

	private static void startFindEmployeeThread(int employeeNo) {
		ec = new EmployeeController();
		
		SwingWorker sw = new SwingWorker() {
			String name = "";
			
			protected String doInBackground() {

				try {
					name = ec.findEmployeeByEmployeeNo(employeeNo).getName();
				} catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No employee found.");
					cancel(true);
				};
				return name;
			}
			
			protected void done() {
				if(!isCancelled()) lblEmployee.setText("Employee: " + name);
			}
		};
		
		sw.execute();
	}
}
