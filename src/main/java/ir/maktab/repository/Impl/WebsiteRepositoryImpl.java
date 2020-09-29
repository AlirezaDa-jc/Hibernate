package ir.maktab.repository.Impl;

import ir.maktab.entities.Website;
import ir.maktab.repository.WebsiteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WebsiteRepositoryImpl implements WebsiteRepository {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit2");
    EntityManager em = emf.createEntityManager();


    @Override
    public void insert(Website website) {
        em.getTransaction().begin();
        em.persist(website);
        em.getTransaction().commit();
    }



    @Override
    public Website update(Website website) {
        em.getTransaction().begin();
        em.merge(website);
        em.getTransaction().commit();
        return website;
    }

    @Override
    public Website findById(Integer id) {
        return em.find(Website.class,id);
    }

    @Override
    public void deleteById(Integer id) {
        Website website = findById(id);
        em.getTransaction().begin();
        em.remove(website);
        em.getTransaction().commit();
    }

    @Override
    public List<Website> findAll() {
        em.getTransaction().begin();
        TypedQuery<Website> query = em.createQuery(
                "SELECT u FROM Website u",
                Website.class);
        List<Website> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public Website findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Website> query = em.createQuery(
                "SELECT u FROM Website u where u.address=:title",
                Website.class);
        query.setParameter("title", title);
        List<Website> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Website> findAllFiltered(Predicate<Website> predicate) {
        List<Website> all = findAll();
        return all.stream()
                .filter(predicate)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Website website) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(website);
        em.getTransaction().commit();
    }

    @Override
    public void displayAll() {
        em.getTransaction().begin();
        TypedQuery<Website> query = em.createQuery(
                "SELECT c FROM Website c ",
                Website.class);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Website c = query.getResultList().get(i);
            System.out.println("ID: " + c.getId() + "\nAddress: " + c.getAddress() + "\nInfo: " +
                    c.getWebsiteInfo().getInfo());
        }
        em.getTransaction().commit();
    }
}
