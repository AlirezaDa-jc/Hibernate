package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Role;


public interface RoleRepository extends BaseRepository<Role,Integer> {

    void display();

}
