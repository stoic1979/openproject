
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageUser {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageUser MU = new ManageUser();

      /* Add few User records in database */
      Integer userID1 = MU.addUser("Zara", "Ali", "user1", "password", "email1", true);
      Integer userID2 = MU.addUser("Daisy", "Das", "user2", "password", "email2", false);
      Integer userID3 = MU.addUser("John", "Paul", "user3", "password", "email3", true);

      /* List down all the user */
      MU.listUsers();

      /* Update user's records */
      MU.updateUser(userID1, false );

      /* Delete an user from the database */
      MU.deleteUser(userID2);

      /* List down new list of the user */
      MU.listUsers();
   }

   /* Method to CREATE an User in the database */
   public Integer addUser(String fname, String lname, String username, String password, String email, boolean is_active){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
         tx = session.beginTransaction();
         User user = new User(fname, lname, username, password, email, is_active);
         userID = (Integer) session.save(user); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return userID;
   }

   /* Method to  READ all the Users */
   public void listUsers( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List users = session.createQuery("FROM User").list(); 
         for (Iterator iterator = 
                           users.iterator(); iterator.hasNext();){
            User user = (User) iterator.next(); 
            System.out.print("First Name: " + user.getFirstName()); 
            System.out.print("  Last Name: " + user.getLastName()); 
            System.out.println("  Is Active: " + user.getIsActive()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   /* Method to UPDATE is active for an User */
   public void updateUser(Integer UserID, boolean is_active ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         User user = 
                    (User)session.get(User.class, UserID); 
         user.setIsActive( is_active );
		 session.update(user); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
   /* Method to DELETE an User from the records */
   public void deleteUser(Integer UserID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         User user = 
                   (User)session.get(User.class, UserID); 
         session.delete(user); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
} //ManageUser