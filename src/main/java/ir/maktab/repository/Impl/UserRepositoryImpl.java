package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.repository.UserRepository;
import ir.maktab.services.UserService;
import ir.maktab.entities.User;

import javax.persistence.*;
import java.util.List;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Integer> implements UserRepository {

    @Override
    public boolean userLogin(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u where u.name=:name and u.password =:password",
                User.class);

        query.setParameter("name", userName);
        query.setParameter("password", password);
        if (query.getResultList().size() > 0) {
            User u = query.getSingleResult();
            UserService.setUser(u);
            System.out.println("Welcome BaCk!" + u.getName() + " ^_^ ");
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

    @Override
    public void displayAll() {
        List<User> all = findAll();
        for(User user : all){
            System.out.println("Name: " + user.getName() + " ID: " + user.getId());
        }
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public void insert(User user) {
        super.insert(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public User findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public User findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u where u.name=:title",
                User.class);

        query.setParameter("title", title);
        List<User> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }

    public void close() {
        super.close();
    }
}
