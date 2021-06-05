package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents =  session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName='Davidson'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Davidson");
            displayStudents(theStudents);

            theStudents =
                    session.createQuery("from Student s where"
                            + " s.lastName='Davidson' OR s.firstName='Mariam'").getResultList();

            System.out.println("\n\nStudents who have last name of Davidson OR first name Mariam");
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where"
                    + " s.email LIKE '%admin.com'").list();

            System.out.println("\n\nStudents whose email ends with admin.com");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}





