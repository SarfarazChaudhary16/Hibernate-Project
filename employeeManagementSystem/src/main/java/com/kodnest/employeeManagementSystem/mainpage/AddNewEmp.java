package com.kodnest.employeeManagementSystem.mainpage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kodnest.employeeManagementSystem.Employee;
import com.kodnest.employeeManagementSystem.utility.UtilityEngine;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField salary;
	private JTextField desig;
	private JTextField depart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewEmp frame = new AddNewEmp();
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
	public AddNewEmp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("Add New Employee");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(145, 11, 138, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(23, 67, 91, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Salary");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(23, 110, 91, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter Designation");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(23, 154, 108, 21);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Enter Department");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(23, 197, 117, 21);
		contentPane.add(lblNewLabel_1_2_1);
		
		name = new JTextField();
		name.setBounds(151, 68, 96, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(151, 111, 96, 20);
		contentPane.add(salary);
		
		desig = new JTextField();
		desig.setColumns(10);
		desig.setBounds(151, 155, 96, 20);
		contentPane.add(desig);
		
		depart = new JTextField();
		depart.setColumns(10);
		depart.setBounds(150, 198, 96, 20);
		contentPane.add(depart);
		
		JButton Addbtn = new JButton("Add");
		Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Session session = null;
				Transaction tx = null;
				try {
				session = UtilityEngine.getConnect().openSession();
				tx = session.beginTransaction();
				
				// Get data from text fields
				String name2 = name.getText();
				Double salary2 = Double.parseDouble(salary.getText());
				String designation = desig.getText();
				String department = depart.getText();
				
				if (name.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Name cannot be empty");
				    return;
				}
				
				Employee emp = new Employee(name2,salary2,designation,department);
				session.persist(emp);
				
				tx.commit();
				 JOptionPane.showMessageDialog(null, "Employee Added Successfully!");
				 
				} catch (Exception e2) {
					
					e2.printStackTrace();
					
					if(tx != null) {
						tx.rollback(); // undo changes if transaction failed.
						JOptionPane.showMessageDialog(null, "transaction commit failed");
					}
				} finally {
					
					if(session != null) {
				     session.close();
					}
				}
			}
		});
		Addbtn.setBackground(new Color(175, 238, 238));
		Addbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		Addbtn.setBounds(308, 137, 67, 39);
		contentPane.add(Addbtn);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WelcomePage();
			}
		});
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(359, 230, 67, 22);
		contentPane.add(btnBack);
		
	}
}
