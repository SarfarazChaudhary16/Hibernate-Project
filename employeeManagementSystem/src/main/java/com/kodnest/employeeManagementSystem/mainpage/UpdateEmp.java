package com.kodnest.employeeManagementSystem.mainpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.hibernate.Session;

import com.kodnest.employeeManagementSystem.Employee;
import com.kodnest.employeeManagementSystem.utility.UtilityEngine;

public class UpdateEmp extends JFrame {

    private static final long serialVersionUID = 1L;

    // 🔹 Declare variables (ONLY HERE)
    JTextField txtSearchId, txtId, txtName, txtSalary, txtDesignation, txtDepartment;
    JButton btnSearch, btnUpdate, btnClear,btnBack;;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateEmp frame = new UpdateEmp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Constructor
    public UpdateEmp() {

        setTitle("Update Employee");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        setVisible(true);
        // 🔎 Search Section
        panel.add(new JLabel("Enter Employee ID:"));
        txtSearchId = new JTextField();
        panel.add(txtSearchId);

        btnSearch = new JButton("Search");
        panel.add(btnSearch);
        panel.add(new JLabel(""));

        // 🧾 Employee Details
        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false); // ID cannot change
        panel.add(txtId);

        panel.add(new JLabel("Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Salary:"));
        txtSalary = new JTextField();
        panel.add(txtSalary);

        panel.add(new JLabel("Designation:"));
        txtDesignation = new JTextField();
        panel.add(txtDesignation);

        panel.add(new JLabel("Department:"));
        txtDepartment = new JTextField();
        panel.add(txtDepartment);

        // Buttons
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 Session session = null;
        	        org.hibernate.Transaction tx = null;

        	        try {

        	            // 1️⃣ Check if employee loaded
        	            if (txtId.getText().trim().isEmpty()) {
        	                JOptionPane.showMessageDialog(null,
        	                        "Please search employee first!",
        	                        "Error",
        	                        JOptionPane.ERROR_MESSAGE);
        	                return;
        	            }

        	            // 2️⃣ Get updated values
        	            int id = Integer.parseInt(txtId.getText().trim());
        	            String name = txtName.getText().trim();
        	            String salaryText = txtSalary.getText().trim();
        	            String designation = txtDesignation.getText().trim();
        	            String department = txtDepartment.getText().trim();

        	            // 3️⃣ Validate empty fields
        	            if (name.isEmpty() || salaryText.isEmpty()
        	                    || designation.isEmpty() || department.isEmpty()) {

        	                JOptionPane.showMessageDialog(null,
        	                        "All fields must be filled!",
        	                        "Error",
        	                        JOptionPane.ERROR_MESSAGE);
        	                return;
        	            }

        	            double salary = Double.parseDouble(salaryText);

        	            // 4️⃣ Open session & begin transaction
        	            session = UtilityEngine.getConnect().openSession();
        	            tx = session.beginTransaction();

        	            // 5️⃣ Fetch existing employee
        	            Employee emp = session.get(Employee.class, id);

        	            if (emp == null) {
        	                JOptionPane.showMessageDialog(null,
        	                        "Employee not found!",
        	                        "Error",
        	                        JOptionPane.ERROR_MESSAGE);
        	                return;
        	            }

        	            // 6️⃣ Update values
        	            emp.setName(name);
        	            emp.setSalary(salary);
        	            emp.setDesignation(designation);
        	            emp.setDepartment(department);

        	            // 7️⃣ Commit changes
        	            tx.commit();

        	            JOptionPane.showMessageDialog(null,
        	                    "Employee Updated Successfully!");

        	        }
        	        catch (NumberFormatException ex) {

        	            JOptionPane.showMessageDialog(null,
        	                    "Salary must be numeric!",
        	                    "Error",
        	                    JOptionPane.ERROR_MESSAGE);
        	        }
        	        catch (Exception ex) {

        	            if (tx != null) {
        	                tx.rollback();  // 🔥 important
        	            }

        	            JOptionPane.showMessageDialog(null,
        	                    "Update failed!",
        	                    "Error",
        	                    JOptionPane.ERROR_MESSAGE);

        	            ex.printStackTrace();
        	        }
        	        finally {

        	            if (session != null) {
        	                session.close();
        	            }
        	        }
        	    }
        	
        });
        panel.add(btnUpdate);

        btnClear = new JButton("Clear");
        panel.add(btnClear);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		new WelcomePage();
        	}
        });
        panel.add(btnBack);
        panel.add(new JLabel(""));  // layout balance ke liye

        getContentPane().add(panel);

        // SEARCH BUTTON LOGIC
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Session session = null;

                try {

                    String idText = txtSearchId.getText().trim();

                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter Employee ID!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int id = Integer.parseInt(idText);

                    session = UtilityEngine.getConnect().openSession();

                    Employee emp = session.get(Employee.class, id);

                    if (emp == null) {

                        JOptionPane.showMessageDialog(null,
                                "Employee not found!",
                                "Invalid ID",
                                JOptionPane.ERROR_MESSAGE);

                        clearFields();
                        return;
                    }

                    // Fill fields
                    txtId.setText(String.valueOf(emp.getId()));
                    txtName.setText(emp.getName());
                    txtSalary.setText(String.valueOf(emp.getSalary()));
                    txtDesignation.setText(emp.getDesignation());
                    txtDepartment.setText(emp.getDepartment());

                }
                catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(null,
                            "ID must be numeric!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                    clearFields();
                }
                catch (Exception ex) {

                    JOptionPane.showMessageDialog(null,
                            "Something went wrong!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                    ex.printStackTrace();
                }
                finally {

                    if (session != null) {
                        session.close();
                    }
                }
            }
        });
        // CLEAR BUTTON
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 txtSearchId.setText("");
                 txtId.setText("");
                 txtName.setText("");
                 txtSalary.setText("");
                 txtDesignation.setText("");
                 txtDepartment.setText("");

                 btnUpdate.setEnabled(false);   // optional but recommended
                 txtSearchId.requestFocus();    // cursor wapas ID field pe  
            }
        });
    }
    // Helper Method
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtSalary.setText("");
        txtDesignation.setText("");
        txtDepartment.setText("");
    }
}