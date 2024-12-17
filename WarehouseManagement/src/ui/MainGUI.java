package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private CreateShipmentGUI createShipmentGUI;
	private AddProductGUI addProductGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateShipment = new JButton("Create Shipment");
		btnCreateShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createShipmentGUI = new CreateShipmentGUI();
				createShipmentGUI.setVisible(true);
			}
		});
		btnCreateShipment.setBounds(117, 100, 205, 37);
		contentPane.add(btnCreateShipment);
		
		JButton btnAddProduct = new JButton("Add Product To Shipment");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductGUI = new AddProductGUI();
				addProductGUI.setVisible(true);
			}
		});
		btnAddProduct.setBounds(117, 149, 205, 37);
		contentPane.add(btnAddProduct);
		
		JButton btnOrderConfirmation = new JButton("Print Order Confirmation");
		btnOrderConfirmation.addActionListener(new ActionListener() {
		//TODO: Create order confirmation for specific order
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Printer is out of ink!");		}
		});
		btnOrderConfirmation.setBounds(117, 196, 205, 37);
		contentPane.add(btnOrderConfirmation);
		
		JLabel lblNewLabel = new JLabel("Warehouse Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(12, 31, 422, 37);
		contentPane.add(lblNewLabel);
	}
}
