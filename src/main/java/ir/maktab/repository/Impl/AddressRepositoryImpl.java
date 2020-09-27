package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Address;
import ir.maktab.entities.Admin;
import ir.maktab.entities.User;
import ir.maktab.repository.AddressRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class AddressRepositoryImpl extends BaseRepositoryImpl<Address, Integer> implements AddressRepository {

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    public void insert(Address address) {
        super.insert(address);
    }

    @Override
    public Address update(Address address) {
        return super.update(address);
    }

    @Override
    public Address findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<Address> findAll() {
        return super.findAll();
    }

    @Override
    public Address findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Address> query = em.createQuery(
                "SELECT u FROM Address u where u.name=:title",
               Address.class);

        query.setParameter("title", title);
        List<Address> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void delete(Address address) {
        super.delete(address);
    }

}
