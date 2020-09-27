package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Role;
import ir.maktab.entities.User;
import ir.maktab.repository.RoleRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class RoleRepositoryImpl extends BaseRepositoryImpl<Role,Integer> implements RoleRepository {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public void insert(Role role) {
        super.insert(role);
    }

    @Override
    public Role update(Role role) {
        return super.update(role);
    }

    @Override
    public Role findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return super.findAll();
    }

    @Override
    public Role findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Role> query = em.createQuery(
                "SELECT u FROM Role u where u.roleTitle=:title",
                Role.class);

        query.setParameter("title", title);
        List<Role> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void delete(Role role) {
        super.delete(role);
    }

    public void display() {
        List<Role> all = findAll();
        all.forEach((c) -> System.out.println("Title: " + c.getRoleTitle()));
    }

}
