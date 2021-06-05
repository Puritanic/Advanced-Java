package hibernate.DBUtils;

import com.github.javafaker.Faker;
import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataSeed {
    public static void main(String[] args) {
        Faker faker = new Faker();
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating seed data...");
            // start a transaction
            session.beginTransaction();

            for (int i = 0; i < 100; i++) {
                // create a student object
                Student tempStudent = new Student(faker.name().firstName(), faker.name().lastName(), "test" + i + "@test.com");
                // save the student object
                session.save(tempStudent);
            }
            // commit transaction
            session.getTransaction().commit();

            System.out.println("Seeding done!");
        } finally {
            factory.close();
        }
    }
}
