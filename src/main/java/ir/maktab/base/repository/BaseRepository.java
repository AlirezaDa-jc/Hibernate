package ir.maktab.base.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface BaseRepository<E, PK extends Number> {


    void insert(E e);

    E update(E e);

    E findById(PK id);

    void deleteById(PK id);

    List<E> findAll();

    E findByTitle(String title);

    List<E> findAllFiltered(Predicate<E> predicate);

    void delete(E e);


}
