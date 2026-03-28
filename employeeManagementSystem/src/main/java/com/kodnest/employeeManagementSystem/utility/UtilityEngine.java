package com.kodnest.employeeManagementSystem.utility;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilityEngine {
	
	 private static SessionFactory factory;
	    
	    public static SessionFactory getConnect() {
	    	try {
	            factory = new Configuration()
	                    .configure("hibernate.cfg.xml")
	                    .buildSessionFactory();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            System.out.println("Error in Utitlity class.");
	           //why we take try catch
	           //Hibernate config file missing ho sakti hai
               //DB unreachable ho sakta hai
               //Mapping error ho sakta hai    
	        }
	        return factory;
	    }
	    public static void closeFactory() {
	        if (factory != null) {
	            factory.close();
	        }
	    }
	   //static block?
	   //Runs only once
	   //SessionFactory ek hi baar banega
	   //getConnect()
	   //Sirf already bana hua factory return karega
	   //Performance safe
}
