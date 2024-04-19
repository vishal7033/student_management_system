package student_management_system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import student_management_system.dto.Student;

public class Studentdao {

    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nanditha");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public Student saveStudent(Student student) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(student);
        entityTransaction.commit();
        return student;
    }

    public Student loginStudent(String email) {
        EntityManager entityManager = getEntityManager();
        try {
            Query query = entityManager.createQuery("select s from Student s where s.email=?1");
            query.setParameter(1, email);
            Student student = (Student) query.getSingleResult();
            return student;
        } catch (NoResultException e) {
            // No result found for the given email
            return null;
        } finally {
            entityManager.close();
        }
    }

    public Student updateStudent(Student student) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(student);
        entityTransaction.commit();
        return student;
    }

    public boolean deleteStudent(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityTransaction.begin();
            entityManager.remove(student);
            entityTransaction.commit();
            return true;
        } else {
            return false;
        }
    }

    public Student getStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    public List<Student> getAllStudents() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select a from Student a");
        return query.getResultList();
    }
}
