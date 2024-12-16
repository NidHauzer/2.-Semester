package ui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ReceiverController;
import controller.ProductController;
import controller.ShipmentController;
import exception.NotEnoughStockException;

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
	private JButton btnExit;
	
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
				startFindProductThread(barcode, quantity);
			}
		});
		btnProduct.setBounds(126, 25, 98, 25);
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
				try {
					int q = Integer.parseInt(textFieldQuantity.getText());
					startAddProductToShipmentThread(
							barcode,
							q,
							Integer.parseInt(shipmentNo)
					);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid quantity.");
				}
				

			}
		});
		btnAddToShipment.setBounds(126, 167, 98, 25);
		contentPane.add(btnAddToShipment);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				AddProductGUI.this.dispose();
			}
		});
		btnExit.setBounds(12, 167, 98, 25);
		contentPane.add(btnExit);
	}
	
	private static void startFindShipmentThread(int shipmentNo) {
		sc = new ShipmentController();
		
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() {
				try {
					sc.findShipment(shipmentNo);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No shipment was found with shipment number " + shipmentNo);
				}
				return true;
			}
			
			protected void done() {
				lblShipment.setText("Shipment: " + sc.getShipment().getShipmentNo() + ", " + sc.getShipment().getReceiver().getName());
			}
		};
		sw.execute();

	}
	
	private static void startAddProductToShipmentThread(String barcode, int quantity, int shipmentNo) {
		sc = new ShipmentController();
		
		SwingWorker sw = new SwingWorker() {
			boolean success = false;
			
			protected Object doInBackground() {
				try {
					sc.addItemLine(barcode, quantity, shipmentNo);
				} 
				
				catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(new JFrame(), "Quantity cannot be less than 1.");
					cancel(true);
				}
				
				catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No product was found with barcode " + barcode);
					cancel(true);
				
				}			
				catch (NotEnoughStockException nese) {
					try {
						JOptionPane.showMessageDialog(new JFrame(), "Not enough stock (" + pc.findProductByBarcode(barcode).getQuantityInStock() + ")");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					cancel(true);
				}
				
				return true;
			}
			
			protected void done() {
				if(!isCancelled()) JOptionPane.showMessageDialog(new JFrame(), "Added " + quantity + " of " + barcode + " to shipment " + shipmentNo);
			}
		};
		sw.execute();
	}
	
	private static Object startFindProductThread(String barcode, String quantity) {
		pc = new ProductController();
		
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() {
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
