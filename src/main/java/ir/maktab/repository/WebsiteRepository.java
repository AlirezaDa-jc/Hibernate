package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Website;

public interface WebsiteRepository extends BaseRepository<Website,Integer> {

    void displayAll();
}
