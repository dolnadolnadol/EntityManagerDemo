/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //init data
        Customer customer = new Customer(1L, "John", "Henry", "jh@mail.com"); 
        Address address = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH"); 
        address.setCustomerFk(customer);
        customer.setAddressId(address);
        createData(address, customer);
        
        customer = new Customer(2L, "Mary", "Jane", "mj@mail.com"); 
        address = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH"); 
        address.setCustomerFk(customer);
        customer.setAddressId(address);
        createData(address, customer);
        
        customer = new Customer(3L, "Peter", "Parker", "pp@mail.com"); 
        address = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH"); 
        address.setCustomerFk(customer);
        customer.setAddressId(address);
        createData(address, customer);
        
        customer = new Customer(4L, "Bruce", "Wayne", "bw@mail.com"); 
        address = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH"); 
        address.setCustomerFk(customer);
        customer.setAddressId(address);
        createData(address, customer);
        
        // SHOW
        printAllCustomer();
        
        //Show By City
        printCustomerByCity("Bangkok");
    }
    public static void createData(Address ad, Customer cs){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(ad);
            em.persist(cs);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void printAllCustomer(){
        List<Customer> cs = findAllCustomer();
        System.out.println("PrintAllCustomer ");
        System.out.println("-------------------------------");
        for(Customer emp : cs) {
           System.out.println("First Name : " + emp.getFirstname() + " ");
           System.out.println("Last Name : " + emp.getLastname() + " ");   
           System.out.println("Email : " + emp.getEmail() + " ");
           System.out.println("Street : " + emp.getAddressId().getStreet() + " ");
           System.out.println("City : " + emp.getAddressId().getCity() + " ");
           System.out.println("Country : " + emp.getAddressId().getCountry() + " ");
           System.out.println("Zip Code : " + emp.getAddressId().getZipcode() + " ");
           System.out.println("-------------------------------");
        }
    }
    public static void printCustomerByCity(String city){
        
        List<Customer> cs = findAllCustomer();
        System.out.println("PrintByCity : " + city);
        System.out.println("-------------------------------");
        for(Customer emp : cs) {
           if (emp.getAddressId().getCity().equals(city)){
                System.out.println("First Name : " + emp.getFirstname() + " ");
                System.out.println("Last Name : " + emp.getLastname() + " ");   
                System.out.println("Email : " + emp.getEmail() + " ");
                System.out.println("Street : " + emp.getAddressId().getStreet() + " ");
                System.out.println("City : " + emp.getAddressId().getCity() + " ");
                System.out.println("Country : " + emp.getAddressId().getCountry() + " ");
                System.out.println("Zip Code : " + emp.getAddressId().getZipcode() + " ");
                System.out.println("-------------------------------");
           }
        }
    }
    //Add-on
    public static List<Customer> findAllCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Customer> empList = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        em.close();
        return empList;
    }
}
