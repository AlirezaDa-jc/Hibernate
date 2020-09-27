package ir.maktab.repository.Impl;


import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Tag;
import ir.maktab.entities.Tag;
import ir.maktab.entities.User;
import ir.maktab.repository.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TagRepositoryImpl extends BaseRepositoryImpl<Tag,Integer> implements TagRepository {
    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }

    @Override
    public void insert(Tag tag) {
        super.insert(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return super.update(tag);
    }

    @Override
    public Tag findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<Tag> findAll() {
        return super.findAll();
    }

    @Override
    public Tag findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Tag> query = em.createQuery(
                "SELECT u FROM Tag u where u.title=:title",
                Tag.class);

        query.setParameter("title", title);
        List<Tag> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Tag> findAllFiltered(Predicate<Tag> predicate) {
        List<Tag> all = findAll();
        return all.stream()
                .filter(predicate).
                collect(Collectors.toList());
    }

    @Override
    public void delete(Tag tag) {
        super.delete(tag);
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
