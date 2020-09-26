package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Category;


public interface CategoryRepository extends BaseRepository<Category,Integer> {
    void display();

    boolean checkDuplicateTitle(String title);

}
