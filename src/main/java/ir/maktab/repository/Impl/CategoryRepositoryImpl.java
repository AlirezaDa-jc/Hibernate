package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Category;
import ir.maktab.entities.User;
import ir.maktab.repository.CategoryRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category,Integer> implements CategoryRepository {

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    public void insert(Category article) {
        super.insert(article);
    }

    @Override
    public Category update(Category article) {
        return super.update(article);
    }

    @Override
    public Category findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return super.findAll();
    }

    @Override
    public Category findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT u FROM Category u where u.title=:title",
                Category.class);

        query.setParameter("title", title);
        List<Category> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void delete(Category article) {
        super.delete(article);
    }

    @Override
    public void display() {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c ",
                Category.class);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Category c = query.getResultList().get(i);
            System.out.println("ID: " + c.getId() + "\nTitle: " + c.getTitle() + "\nDescription: " + c.getDescription());
        }
        em.getTransaction().commit();
    }

    @Override
    public boolean checkDuplicateTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT u FROM Category u where u.title=:title",
                Category.class);

        query.setParameter("title", title);
        if (query.getResultList().size() > 0) {
            em.getTransaction().rollback();
            return false;
        }
        em.getTransaction().commit();
        return true;
    }


}
