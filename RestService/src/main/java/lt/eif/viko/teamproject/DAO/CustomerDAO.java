/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lt.eif.viko.teamproject.Entities.Customer;

/**
 * This class is to manage Customer's data in Database
 * 
 * @author Tomas
 */
public class CustomerDAO implements DAO<Customer> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    List<Customer> customers = new ArrayList<>();

    /**
     * DAO customer object constructor
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public CustomerDAO() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
    }

    @Override
    public List<Customer> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Customer");
            Boolean next = resultSet.first();
            while (next == true) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("ID"));
                customer.setName(resultSet.getString("Name"));
                customer.setSurname(resultSet.getString("Surname"));
                customers.add(customer);
                next = resultSet.next();
            }
            return customers;
        } catch (SQLException ex) {

        }
        return null;
    }

    
    /**
     * 
     * @param id customer id
     * @return customer
     */
    
    @Override
    public Customer get(Object id) {
        int idInt = (int) id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Event WHERE ID = " + idInt);
            resultSet.first();
            Customer customer = new Customer();
            customer.setCustomerID(resultSet.getInt("ID"));
            customer.setName(resultSet.getString("Name"));
            customer.setSurname(resultSet.getString("Surname"));
            return customer;

        } catch (SQLException ex) {
       //    Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
     /**
     * insert customer object into DB
     *
     * @param object
     */
    @Override
    public void insert(Customer object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Customer VALUES (" + object.getCustomerID()+ ", '" + object.getName() + "'), '" + object.getSurname()+ "')");
        } catch (SQLException ex) {
       
        }
    }
    
     /**
     * update customer object into DB
     *
     * @param object
     */
    @Override
    public void update(Customer object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Customer SET Surname =" + object.getSurname() + ", Name=" + object.getName() + "' WHERE ID =" + object.getCustomerID());
        } catch (SQLException ex) {
      
        }
    }
    
  /**
     * delete customer object from DB
     *
     * @param object
     */
    @Override
    public void delete(Customer object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Customer WHERE ID = " + object.getCustomerID());
        } catch (SQLException ex) {
           // Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    

}
