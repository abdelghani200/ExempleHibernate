package com.hibernate.exemplehibernate.Dao;

import com.hibernate.exemplehibernate.Dto.Student;
import com.hibernate.exemplehibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class StudentDao {


    public Student seveStudent(Student student) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Object object = session.save(student);
            session.get(Student.class, (Serializable) object);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.getMessage();
        }
        return student;
    }

    public void insertStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String hql = "INSERT INTO Student (firstName, lastName, email) "
                    + "SELECT firstName, lastName, email FROM Student";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Student updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            transaction = session.beginTransaction();

            String sql = "update Student set firstName = :firstName " + "Where id = :StudentId";

            Query query = session.createQuery(sql);

            query.setParameter("firstName", student.getFirstName());
            query.setParameter("StudentId", 1);

            int result = query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.getMessage();
        }
        return student;
    }

    public List<Student> getStudents(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Student", Student.class).list();
        }
    }

}
