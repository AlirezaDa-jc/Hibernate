package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Admin;
import ir.maktab.entities.User;
import ir.maktab.repository.AdminRepository;

import javax.persistence.TypedQuery;
import java.util.List;


public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin, Integer> implements AdminRepository {

    @Override
    public Admin adminLogin(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<Admin> query = em.createQuery(
                "SELECT a FROM Admin  a where a.name=:name and a.password =:password",
                Admin.class);
        query.setParameter("name", userName);
        query.setParameter("password", password);
        if (query.getResultList().size() > 0) {
            Admin a = query.getSingleResult();
            System.out.println("Welcome BaCk!" + a.getName() + " ^_^ ");
            em.getTransaction().commit();
            return a;
        }
        em.getTransaction().rollback();
        return null;
    }

    @Override
    protected Class<Admin> getEntityClass() {
        return Admin.class;
    }

    @Override
    public void insert(Admin admin) {
        super.insert(admin);
    }

    @Override
    public Admin update(Admin admin) {
        return super.update(admin);
    }

    @Override
    public Admin findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<Admin> findAll() {
        return super.findAll();
    }

    @Override
    public Admin findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Admin> query = em.createQuery(
                "SELECT u FROM Admin u where u.name=:title",
                Admin.class);

        query.setParameter("title", title);
        List<Admin> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void delete(Admin admin) {
        super.delete(admin);
    }

}
