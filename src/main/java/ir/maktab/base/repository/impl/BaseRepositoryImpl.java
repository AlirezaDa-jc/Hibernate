package ir.maktab.base.repository.impl;

import ir.maktab.base.repository.BaseRepository;

import java.util.Collection;
import java.util.List;

public class BaseRepositoryImpl<E, PK extends Number>  implements BaseRepository<E,PK> {

    @Override
    public void insert(E e) {
        System.out.println("Insert Entity");
    }

    @Override
    public E update(E e) {
        System.out.println("update entity");
        return null;
    }

    @Override
    public E findById(PK id) {
        System.out.println("find by id :" + id);
        return null;
    }

    @Override
    public void deleteById(PK id) {
        System.out.println("delete by id :" + id);
    }


    @Override
    public List<E> findAll() {
        return null;
    }


    @Override
    public E findByTitle(String title) {
        return null;
    }

    @Override
    public void delete(E e) {

    }

    @Override
    public void close() {

    }
}
