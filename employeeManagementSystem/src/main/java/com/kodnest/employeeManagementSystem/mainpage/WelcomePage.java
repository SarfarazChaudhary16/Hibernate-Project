package com.kodnest.employeeManagementSystem.mainpage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
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
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 192, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		JButton AddEmp = new JButton("Add New Emp");
		AddEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AddNewEmp();
			}
		});
		AddEmp.setFont(new Font("Tahoma", Font.BOLD, 14));
		AddEmp.setBackground(new Color(173, 216, 230));
		AddEmp.setBounds(10, 45, 135, 35);
		contentPane.add(AddEmp);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setBackground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(158, 11, 97, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnFetchEmp = new JButton("Fetch Emp");
		btnFetchEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new FetchEmp();
			}
		});
		btnFetchEmp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFetchEmp.setBackground(new Color(216, 191, 216));
		btnFetchEmp.setBounds(245, 45, 135, 35);
		contentPane.add(btnFetchEmp);
		
		JButton btnUpdateEmp = new JButton("Update Emp");
		btnUpdateEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new UpdateEmp();
			}
		});
		btnUpdateEmp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdateEmp.setBackground(new Color(216, 191, 216));
		btnUpdateEmp.setBounds(10, 118, 135, 35);
		contentPane.add(btnUpdateEmp);
		
		JButton btnDeleteRecord = new JButton("Delete Record");
		btnDeleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Deleteemp();
			}
		});
		btnDeleteRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeleteRecord.setBackground(new Color(135, 206, 250));
		btnDeleteRecord.setBounds(245, 118, 135, 35);
		contentPane.add(btnDeleteRecord);
		
		JButton FetchAllEmp = new JButton("Fetch All Emp");
		FetchAllEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FetchAllEmp();
			}
		});
		FetchAllEmp.setFont(new Font("Tahoma", Font.BOLD, 14));
		FetchAllEmp.setBackground(new Color(224, 255, 255));
		FetchAllEmp.setBounds(132, 186, 135, 35);
		contentPane.add(FetchAllEmp);
	}
}
