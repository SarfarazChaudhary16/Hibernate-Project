package com.kodnest.employeeManagementSystem.mainpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kodnest.employeeManagementSystem.Employee;
import com.kodnest.employeeManagementSystem.utility.UtilityEngine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Deleteemp extends JFrame {

	
	JTextField txtSearchId, txtId, txtName, txtSalary, txtDesignation, txtDepartment;
	JButton btnSearch, btnDelete, btnBack;
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deleteemp frame = new Deleteemp();
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
	public Deleteemp() {
		setTitle("Delete Employee");
		setSize(550, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		// 🔥 Title
		JLabel title = new JLabel("DELETE EMPLOYEE", JLabel.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		getContentPane().add(title, BorderLayout.NORTH);

		// 🔹 Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		/* =====================
		   🔎 SEARCH ROW
		===================== */
		JPanel searchPanel = new JPanel();

		JLabel lblSearch = new JLabel("Enter Employee ID:");
		lblSearch.setPreferredSize(new Dimension(140,25));

		txtSearchId = new JTextField(15);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Session session = null;

		        try {
		            String idText = txtSearchId.getText().trim();

		            // 1️ Empty check
		            if (idText.isEmpty()) {
		                JOptionPane.showMessageDialog(Deleteemp.this,
		                        "Please enter Employee ID!",
		                        "Error",
		                        JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // 2️ Numeric check
		            int id = Integer.parseInt(idText);

		            session = UtilityEngine.getConnect().openSession();

		            Employee emp = session.get(Employee.class, id);

		            // 3️ Not found
		            if (emp == null) {

		                JOptionPane.showMessageDialog(Deleteemp.this,
		                        "Employee not found!",
		                        "Error",
		                        JOptionPane.ERROR_MESSAGE);

		                clearFields();
		                btnDelete.setEnabled(false);
		                return;
		            }

		            // 4️ Fill fields
		            txtId.setText(String.valueOf(emp.getId()));
		            txtName.setText(emp.getName());
		            txtSalary.setText(String.valueOf(emp.getSalary()));
		            txtDesignation.setText(emp.getDesignation());
		            txtDepartment.setText(emp.getDepartment());

		            btnDelete.setEnabled(true);

		        }
		        catch (NumberFormatException ex) {

		            JOptionPane.showMessageDialog(Deleteemp.this,
		                    "ID must be numeric!",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);

		            clearFields();
		            btnDelete.setEnabled(false);
		        }
		        catch (Exception ex) {
		            ex.printStackTrace();
		        }
		        finally {
		            if (session != null)
		                session.close();
		        }
		    }
		});
		btnSearch.setBackground(new Color(70,130,180));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFocusPainted(false);

		searchPanel.add(lblSearch);
		searchPanel.add(txtSearchId);
		searchPanel.add(btnSearch);

		mainPanel.add(searchPanel);

		/* =====================
		   🧾 DETAILS PANEL
		===================== */
		JPanel formPanel = new JPanel(new GridLayout(5,2,10,10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

		formPanel.add(new JLabel("ID:"));
		txtId = new JTextField();
		txtId.setEditable(false);
		formPanel.add(txtId);

		formPanel.add(new JLabel("Name:"));
		txtName = new JTextField();
		txtName.setEditable(false);
		formPanel.add(txtName);

		formPanel.add(new JLabel("Salary:"));
		txtSalary = new JTextField();
		txtSalary.setEditable(false);
		formPanel.add(txtSalary);

		formPanel.add(new JLabel("Designation:"));
		txtDesignation = new JTextField();
		txtDesignation.setEditable(false);
		formPanel.add(txtDesignation);

		formPanel.add(new JLabel("Department:"));
		txtDepartment = new JTextField();
		txtDepartment.setEditable(false);
		formPanel.add(txtDepartment);

		mainPanel.add(formPanel);

		/* =====================
		   🔘 BUTTON PANEL
		===================== */
		JPanel btnPanel = new JPanel();

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Session session = null;
			     Transaction tx = null;
			        try {

			            // 1️Get ID from hidden txtId (after search)
			            String idText = txtId.getText().trim();
			            if (idText.isEmpty()) {
			                JOptionPane.showMessageDialog(Deleteemp.this,
			                        "Please search employee first!",
			                        "Error",
			                        JOptionPane.ERROR_MESSAGE);
			                return;
			            }

			            int id = Integer.parseInt(idText);
			            // 2️Confirmation popup
			            int confirm = JOptionPane.showConfirmDialog(Deleteemp.this,
			                    "Are you sure you want to delete this employee?",
			                    "Confirm Delete",
			                    JOptionPane.YES_NO_OPTION);

			            if (confirm != JOptionPane.YES_OPTION) {
			                return;
			            }

			            // 3️ Open session
			            session = UtilityEngine.getConnect().openSession();
			            tx = session.beginTransaction();

			            Employee emp = session.get(Employee.class, id);

			            if (emp == null) {
			                JOptionPane.showMessageDialog(Deleteemp.this,
			                        "Employee not found!",
			                        "Error",
			                        JOptionPane.ERROR_MESSAGE);
			                return;
			            }

			            // 4️ Delete
			            session.remove(emp);
			            tx.commit();

			            JOptionPane.showMessageDialog(Deleteemp.this,
			                    "Employee Deleted Successfully!");

			            // 5️ Clear fields
			            clearFields();
			            txtSearchId.setText("");
			            btnDelete.setEnabled(false);

			        }
			        catch (Exception ex) {

			            if (tx != null)
			                tx.rollback();

			            JOptionPane.showMessageDialog(Deleteemp.this,
			                    "Delete failed!",
			                    "Error",
			                    JOptionPane.ERROR_MESSAGE);

			            ex.printStackTrace();
			        }
			        finally {
			            if (session != null)
			                session.close();
			        }
			    }
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDelete.setBackground(new Color(220,20,60));  // cool red
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFocusPainted(false);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WelcomePage();
			}
		});

		btnPanel.add(btnDelete);
		btnPanel.add(btnBack);

		getContentPane().add(btnPanel, BorderLayout.SOUTH);
	}
	
	private void clearFields() {
	    txtId.setText("");
	    txtName.setText("");
	    txtSalary.setText("");
	    txtDesignation.setText("");
	    txtDepartment.setText("");
	}
}
