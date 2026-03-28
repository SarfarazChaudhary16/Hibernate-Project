package com.kodnest.employeeManagementSystem.mainpage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import com.kodnest.employeeManagementSystem.Employee;
import com.kodnest.employeeManagementSystem.utility.UtilityEngine;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FetchEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FetchEmp frame = new FetchEmp();
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
	public FetchEmp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("Fetch Employee");
		lblNewLabel.setBackground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(123, 11, 140, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBackground(SystemColor.textHighlight);
		lblNewLabel_1.setBounds(10, 69, 96, 20);
		contentPane.add(lblNewLabel_1);
		
		idText = new JTextField();
		idText.setBounds(123, 70, 96, 20);
		contentPane.add(idText);
		idText.setColumns(10);
		
		JTextArea resultArea = new JTextArea();
		resultArea.setBackground(new Color(135, 206, 235));
		resultArea.setFont(new Font("Monospaced", Font.BOLD, 10));
		resultArea.setBounds(10, 100, 265, 138);
		contentPane.add(resultArea);
		
		JButton fetchBtn = new JButton("Fetch");
		fetchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Session session = null;

			    try {
			    	
			    	 //  Check empty field
			        if (idText.getText().trim().isEmpty()) {
			        	JOptionPane.showMessageDialog(null,
			                    "Please enter ID",
			                    "Error",
			                    JOptionPane.ERROR_MESSAGE);
			            return; 	
			        }
			        
			        
			        session = UtilityEngine.getConnect().openSession();

			        // 1️ ID get karo
			        int id = Integer.parseInt(idText.getText());

			        // 2️ Fetch employee
			        Employee emp = session.get(Employee.class, id);

			        // 3️ TextArea clear karo
			        resultArea.setText("");

			        // 4️ Check employee mila ya nahi
			        
			        
			        if (emp != null) {

			        	resultArea.append("ID: " + emp.getId() + "\n");
			        	resultArea.append("Name: " + emp.getName() + "\n");
			        	resultArea.append("Salary: " + emp.getSalary() + "\n");
			        	resultArea.append("Designation: " + emp.getDesignation() + "\n");
			        	resultArea.append("Department: " + emp.getDepartment() + "\n");

			        } else {
			        	resultArea.setText("Employee Not Found!");
			        }

			    } catch (NumberFormatException ex) {
			    	JOptionPane.showMessageDialog(null,
			                "ID must be numeric!",
			                "Error",
			                JOptionPane.ERROR_MESSAGE);

			    	resultArea.setText("");
			    	
			    	
			    	resultArea.setText("");

			    } catch (Exception ex) {
			        ex.printStackTrace();
			        resultArea.setText("Error occurred!");

			    } finally {
			        if (session != null) {
			            session.close();
			        }
			    }
				
			}
		});
		fetchBtn.setBackground(new Color(30, 144, 255));
		fetchBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		fetchBtn.setBounds(263, 69, 88, 22);
		contentPane.add(fetchBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new WelcomePage();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		backBtn.setBounds(347, 230, 66, 22);
		contentPane.add(backBtn);

	}
}
