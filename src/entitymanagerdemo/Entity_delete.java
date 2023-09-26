/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author cld
 */
public class Entity_delete {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Customer> cs = findAllDepartment();
//        System.out.println(cs.toString());
        em.getTransaction().begin();
        try {
            for(Customer i : cs){
                Customer fromDb = em.find(Customer.class, i.getId());
                em.remove(fromDb); 
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        } 
    }
    public static List<Customer> findAllDepartment() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Customer> empList = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        em.close();
        return empList;
    }
    
}
