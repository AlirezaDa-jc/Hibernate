package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Tag;


public interface TagRepository extends BaseRepository<Tag,Integer> {
    void displayAll();
}
