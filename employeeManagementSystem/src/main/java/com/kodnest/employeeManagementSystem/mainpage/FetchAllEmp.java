package com.kodnest.employeeManagementSystem.mainpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import com.kodnest.employeeManagementSystem.Employee;
import com.kodnest.employeeManagementSystem.utility.UtilityEngine;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FetchAllEmp extends JFrame {

	JTable table;
	DefaultTableModel model;
	JButton btnFetch, btnBack;
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FetchAllEmp frame = new FetchAllEmp();
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
	public FetchAllEmp() {
		
		setTitle("View All Employees");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		setVisible(true);
		//Title
		JLabel title = new JLabel("ALL EMPLOYEES", JLabel.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		getContentPane().add(title, BorderLayout.NORTH);

		//Table Setup
		String[] columns = {"ID", "Name", "Salary", "Designation", "Department"};
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		//Bottom Panel
		JPanel bottomPanel = new JPanel();

		btnFetch = new JButton("Fetch All");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 model.setRowCount(0);   //clear old data

			        Session session = null;

			        try {

			            session = UtilityEngine.getConnect().openSession();

			            List<Employee> list = session
			                    .createQuery("from Employee", Employee.class)
			                    .list();

			            for (Employee emp : list) {

			                Object[] row = {
			                        emp.getId(),
			                        emp.getName(),
			                        emp.getSalary(),
			                        emp.getDesignation(),
			                        emp.getDepartment()
			                };

			                model.addRow(row);
			            }

			            JOptionPane.showMessageDialog(FetchAllEmp.this,
			                    "Employees Loaded Successfully!");

			        }
			        catch (Exception ex) {

			            JOptionPane.showMessageDialog(FetchAllEmp.this,
			                    "Error fetching data!",
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
		btnFetch.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnFetch.setBackground(new Color(70,130,180));
		btnFetch.setForeground(Color.WHITE);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new WelcomePage();
			}
		});

		bottomPanel.add(btnFetch);
		bottomPanel.add(btnBack);

		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}

}
