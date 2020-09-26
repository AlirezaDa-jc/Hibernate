package ir.maktab.repository.Impl;

import ir.maktab.entities.Role;
import ir.maktab.repository.RoleRepository;
import ir.maktab.services.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public RoleRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public Role findById(Integer id) {
        return em.find(Role.class ,id);
    }

    @Override
    public void deleteById(Integer id) {
        Role r = findById(id);
        delete(r);
    }

    @Override
    public Role update(Role role) {
        em.getTransaction().begin();
        em.merge(role);
        RoleService.setRole(role);
        em.getTransaction().commit();
        return role;
    }

    @Override
    public List<Role> findAll(){
        em.getTransaction().begin();
        TypedQuery<Role> query = em.createQuery("SELECT u FROM Role u", Role.class);
        List <Role> roles = query.getResultList();
        em.getTransaction().commit();
        return roles;
    }

    @Override
    public Role findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Role> query = em.createQuery(
                "SELECT u FROM Role u where u.title=:title",
                Role.class);
        query.setParameter("title", title);
        List<Role> results = query.getResultList();
        em.getTransaction().commit();
        return results.get(0);
    }

    @Override
    public void delete(Role role) {
        em.getTransaction().begin();
        em.remove(role);
        em.getTransaction().commit();
    }

    public void display() {
        List<Role> all = findAll();
        all.forEach((c) -> System.out.println("Title: " + c.getRoleTitle()));
    }

    @Override
    public void insert(Role newRole) {
        em.getTransaction().begin();
        em.persist(newRole);
        em.getTransaction().commit();
    }
}
