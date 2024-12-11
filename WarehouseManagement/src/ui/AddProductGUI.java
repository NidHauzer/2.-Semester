package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PartyController;
import controller.ProductController;
import controller.ShipmentController;

import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;

public class AddProductGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProduct;
	private JTextField textFieldQuantity;
	
	private static JLabel lblProduct;
	private static JLabel lblShipment;
	
	private static ProductController pc;
	private static ShipmentController sc;

	private String barcode;
	private String quantity;
	private String shipmentNo;
	
	private JTextField txtShipment;
	private JButton btnAddToShipment;
	private JButton btnCancel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProductGUI frame = new AddProductGUI();
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
	public AddProductGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblProduct = new JLabel("Product:");
		lblProduct.setBounds(12, 12, 422, 15);
		contentPane.add(lblProduct);
		
		textFieldProduct = new JTextField();
		textFieldProduct.setText("Barcode");
		textFieldProduct.setBounds(12, 28, 104, 19);
		contentPane.add(textFieldProduct);
		textFieldProduct.setColumns(10);
		
		JButton btnProduct = new JButton("Find");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				barcode = textFieldProduct.getText();
				quantity = textFieldQuantity.getText();
				startFindProductThread(barcode, quantity);
			}
		});
		btnProduct.setBounds(126, 59, 98, 25);
		contentPane.add(btnProduct);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setText("Quantity");
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(12, 62, 104, 19);
		contentPane.add(textFieldQuantity);
		
		lblShipment = new JLabel("Shipment:");
		lblShipment.setBounds(12, 96, 212, 15);
		contentPane.add(lblShipment);
		
		txtShipment = new JTextField();
		txtShipment.setText("Shipment number");
		txtShipment.setColumns(10);
		txtShipment.setBounds(12, 114, 104, 19);
		contentPane.add(txtShipment);
		
		JButton btnShipment = new JButton("Find");
		btnShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startFindShipmentThread(Integer.parseInt(txtShipment.getText()));
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid shipment number " + txtShipment.getText());
				}
				shipmentNo = txtShipment.getText();
			}
		});
		btnShipment.setBounds(126, 111, 98, 25);
		contentPane.add(btnShipment);
		
		btnAddToShipment = new JButton("Add");
		btnAddToShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAddProductToShipmentThread(
						barcode,
						Integer.parseInt(quantity),
						Integer.parseInt(shipmentNo)
				);
			}
		});
		btnAddToShipment.setBounds(126, 167, 98, 25);
		contentPane.add(btnAddToShipment);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 167, 98, 25);
		contentPane.add(btnCancel);
	}
	
	private static ShipmentController startFindShipmentThread(int shipmentNo) {
		sc = new ShipmentController();
		
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() {
				try {
					sc.findShipment(shipmentNo);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No shipment was found with shipment number " + shipmentNo);
				}
				return sc;
			}
			
			protected void done() {
				lblShipment.setText("Shipment: " + sc.getShipment().getShipmentNo() + ", " + sc.getShipment().getParty().getName());
			}
		};
		sw.execute();
		return sc;
	}
	
	private static Object startAddProductToShipmentThread(String barcode, int quantity, int shipmentNo) {
		sc = new ShipmentController();
		
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() {
				if(pc.getProduct().getQuantityInStock() < quantity) {
					JOptionPane.showMessageDialog(new JFrame(), "Not enough stock (" + pc.getProduct().getQuantityInStock() + ")");
					cancel(true);
				}
				
				if(!isCancelled()) {
					try {
						sc.addItemLine(barcode, quantity, shipmentNo);
					}
					catch (SQLException e) {
						JOptionPane.showMessageDialog(new JFrame(), "No product was found with barcode " + barcode);
						cancel(true);
					}
				}
				
				return pc;
			}
			
			protected void done() {
				if(!isCancelled()) JOptionPane.showMessageDialog(new JFrame(), "Added " + quantity + " of " + barcode + " to shipment " + shipmentNo);
			}
		};
		sw.execute();
		return pc;
	}
	
	private static Object startFindProductThread(String barcode, String quantity) {
		pc = new ProductController();
		
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() {
				try {
					Integer.parseInt(quantity);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid quantity '" + quantity + "'");
					cancel(true);
				}
				
				if(Integer.parseInt(quantity) <= 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Quantity cannot be less than or equal to 0.");
				}
				
				try {
					pc.findProductByBarcode(barcode);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No product was found with barcode " + barcode);
					cancel(true);
				}
				return pc;
			}
			
			protected void done() {
				lblProduct.setText("Product: " + pc.getProduct().getType() + " " + pc.getProduct().getColour() + " " + pc.getProduct().getLength() + "cm " + pc.getProduct().getAmount() + "g");
			}
		};
		sw.execute();
		return pc;
	}
}
