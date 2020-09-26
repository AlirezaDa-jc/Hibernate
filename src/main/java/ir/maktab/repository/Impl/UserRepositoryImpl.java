package ir.maktab.repository.Impl;

import ir.maktab.repository.UserRepository;
import ir.maktab.services.UserService;
import ir.maktab.entities.User;

import javax.persistence.*;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public UserRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    @Override
    public void insert(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public boolean userLogin(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u where u.name=:name and u.password =:password",
                User.class);

        query.setParameter("name",userName);
        query.setParameter("password",password);
        if(query.getResultList().size() > 0) {
            User u= query.getSingleResult();
            UserService.setUser(u);
            System.out.println("Welcome BaCk!"+u.getName() +" ^_^ ");
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

    @Override
    public User update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return user;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        User entity = findById(id);
        if(entity==null){
            System.out.println("Invalid ID");
            return;
        }
        delete(entity);
    }

    @Override
    public List<User> findAll() {
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery("select entity from User entity",User.class);
        List<User> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public User findByTitle(String name) {
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery(
                "SELECT c FROM  User c where c.name=:name",
                User.class);
        query.setParameter("name",name);
        List<User> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList.get(0);
    }

    @Override
    public void delete(User newUser) {
        em.getTransaction().begin();
        em.remove(newUser);
        em.getTransaction().commit();
    }
}
