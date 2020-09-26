package ir.maktab.repository.Impl;

import ir.maktab.entities.Category;
import ir.maktab.entities.Tag;
import ir.maktab.repository.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public CategoryRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    @Override
    public void insert(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    @Override
    public Category update(Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        return category;
    }

    @Override
    public Category findById(Integer id) {
        return em.find(Category.class,id);
    }

    @Override
    public void deleteById(Integer id) {
        Category c = findById(id);
        delete(c);
    }

    @Override
    public List<Category> findAll() {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery("select entity from Category entity",Category.class);
        List<Category> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
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

    @Override
    public Category findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT u FROM Category u where u.title=:title",
                Category.class);

        query.setParameter("title", title);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        }
        return null;
    }

    @Override
    public void delete(Category category) {
        em.getTransaction().begin();
        em.remove(category);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }
}
