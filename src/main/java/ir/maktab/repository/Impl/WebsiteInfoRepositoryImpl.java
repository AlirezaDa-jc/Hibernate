package ir.maktab.repository.Impl;

import ir.maktab.entities.WebsiteInfo;
import ir.maktab.repository.WebsiteInfoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WebsiteInfoRepositoryImpl implements WebsiteInfoRepository {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit2");
    private static         EntityManager em = emf.createEntityManager();


    @Override
    public void insert(WebsiteInfo websiteInfo) {
        em.getTransaction().begin();
        em.persist(websiteInfo);
        em.getTransaction().commit();
    }



    @Override
    public WebsiteInfo update(WebsiteInfo websiteInfo) {
        em.getTransaction().begin();
        em.merge(websiteInfo);
        em.getTransaction().commit();
        em.close();
        return websiteInfo;
    }

    @Override
    public WebsiteInfo findById(Integer id) {
        return em.find(WebsiteInfo.class,id);
    }

    @Override
    public void deleteById(Integer id) {
        WebsiteInfo websiteInfo = findById(id);
        em.getTransaction().begin();
        em.remove(websiteInfo);
        em.getTransaction().commit();
    }

    @Override
    public List<WebsiteInfo> findAll() {
        em.getTransaction().begin();
        TypedQuery<WebsiteInfo> query = em.createQuery(
                "SELECT u FROM WebsiteInfo u",
                WebsiteInfo.class);
        List<WebsiteInfo> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public WebsiteInfo findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<WebsiteInfo> query = em.createQuery(
                "SELECT u FROM WebsiteInfo u where u.address=:title",
                WebsiteInfo.class);
        query.setParameter("title", title);
        List<WebsiteInfo> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<WebsiteInfo> findAllFiltered(Predicate<WebsiteInfo> predicate) {
        List<WebsiteInfo> all = findAll();
        return all.stream()
                .filter(predicate)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(WebsiteInfo websiteInfo) {
        em.getTransaction().begin();
        em.remove(websiteInfo);
        em.getTransaction().commit();
    }
}
