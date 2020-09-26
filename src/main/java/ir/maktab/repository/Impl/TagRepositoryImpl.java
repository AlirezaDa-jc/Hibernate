package ir.maktab.repository.Impl;


import ir.maktab.entities.Tag;
import ir.maktab.repository.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TagRepositoryImpl implements TagRepository {
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public TagRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }
    @Override
    public Tag update(Tag tag) {
        em.getTransaction().begin();
        em.merge(tag);
        em.getTransaction().commit();
        return tag;
    }

    @Override
    public Tag findById(Integer id) {
        return em.find(Tag.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Tag tag = findById(id);
        if(tag==null){
            System.out.println("Unknown Tag");
        }
        delete(tag);
    }



    @Override
    public List<Tag> findAll() {
        em.getTransaction().begin();
        TypedQuery<Tag> query = em.createQuery("select entity from Tag entity",Tag.class);
        List<Tag> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public Tag findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Tag> query = em.createQuery(
                "SELECT u FROM Tag u where u.title=:title",
                Tag.class);
        query.setParameter("title", title);
        List<Tag> results = query.getResultList();
        em.getTransaction().commit();
        return results.get(0);
    }

    @Override
    public void delete(Tag tag) {
        em.getTransaction().begin();
        em.remove(tag);
        em.getTransaction().commit();
    }

    @Override
    public void insert(Tag newTag) {
        em.getTransaction().begin();
        em.persist(newTag);
        em.getTransaction().commit();
    }

    @Override
    //default
    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public void displayAll() {
        em.getTransaction().begin();
        TypedQuery<Tag> query = em.createQuery(
                "SELECT c FROM Tag c ",
                Tag.class);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Tag c = query.getResultList().get(i);
            System.out.println("ID: " + c.getId() + "\nTitle: " + c.getTitle());
        }
        em.getTransaction().commit();
    }

}
