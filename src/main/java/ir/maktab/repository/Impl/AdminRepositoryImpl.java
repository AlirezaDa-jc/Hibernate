package ir.maktab.repository.Impl;

import ir.maktab.entities.Admin;
import ir.maktab.entities.Category;
import ir.maktab.repository.AdminRepository;

import javax.persistence.*;
import java.util.List;


public class AdminRepositoryImpl implements AdminRepository {
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public AdminRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    @Override
    public Admin adminLogin(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<Admin> query = em.createQuery(
                "SELECT a FROM Admin  a where a.name=:name and a.password =:password",
                Admin.class);
        query.setParameter("name",userName);
        query.setParameter("password",password);
        if(query.getResultList().size() > 0) {
            Admin a= query.getSingleResult();
            System.out.println("Welcome BaCk!"+a.getName() +" ^_^ ");
            em.getTransaction().commit();
            return a;
        }
        em.getTransaction().rollback();
        return null;
    }

    @Override
    public void insert(Admin admin) {
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
    }

    @Override
    public Admin update(Admin admin) {
        em.getTransaction().begin();
        em.merge(admin);
        em.getTransaction().commit();
        return admin;
    }

    @Override
    public Admin findById(Integer id) {
        return em.find(Admin.class,id);
    }

    @Override
    public void deleteById(Integer id) {
        Admin admin = findById(id);
        delete(admin);
    }

    @Override
    public List<Admin> findAll() {
        em.getTransaction().begin();
        TypedQuery<Admin> query = em.createQuery("select entity from Admin entity",Admin.class);
        List<Admin> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public Admin findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Admin> query = em.createQuery(
                "SELECT u FROM Admin u where u.name=:title",
                Admin.class);

        query.setParameter("title", title);
        List<Admin> resultList = query.getResultList();
        if (resultList.size()>0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void delete(Admin admin) {
        em.getTransaction().begin();
        em.remove(admin);
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }

}
