
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageProject {
	private static SessionFactory factory; 
	public static void main(String[] args) {
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		ManageProject MP = new ManageProject();

		/* Add few Project records in database */
		Integer projectID1 = MP.addProject("Website", "website project", "2016-10-03", "2016-10-23", "WB");
		Integer projectID2 = MP.addProject("Website2", "website project", "2016-10-03", "2016-10-23", "WB1");
		Integer projectID3 = MP.addProject("Website3", "website project", "2016-10-03", "2016-10-23", "WB");

		/* List down all the project */
		MP.listProjects();

		/* Update project's records */
		MP.updateProject(projectID1, "2016-10-05" );

		/* Delete an project from the database */
		MP.deleteProject(projectID2);

		/* List down new list of the project */
		MP.listProjects();
	}

	/* Method to CREATE an Project in the database */
	public Integer addProject(String title, String description, String startDate, String endDate, String owner){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer projectID = null;
		try{
			tx = session.beginTransaction();
			Project project = new Project(title, description, startDate, endDate, owner );
			projectID = (Integer) session.save(project); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return projectID;
	}

	/* Method to  READ all the Projects */
	public void listProjects( ){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List projects = session.createQuery("FROM Project").list(); 
			for (Iterator iterator = 
					projects.iterator(); iterator.hasNext(); ){
				Project project = (Project) iterator.next(); 
				System.out.print("Title: " + project.getTitle() ); 
				System.out.print("  Description: " + project.getDescription() ); 
				System.out.println("  Owner: " + project.getOwner() );
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
	/* Method to UPDATE salary for an Project */
	public void updateProject(Integer ProjectID, String startDate ){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Project project = 
					(Project)session.get(Project.class, ProjectID); 
			project.setStartDate(startDate);
			session.update(project); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}

	/* Method to DELETE an Project from the records */
	public void deleteProject(Integer ProjectID){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Project project = 
					(Project)session.get(Project.class, ProjectID); 
			session.delete(project); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
} //ManageProject