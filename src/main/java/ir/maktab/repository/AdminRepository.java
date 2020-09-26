package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Admin;


public interface AdminRepository extends BaseRepository<Admin,Integer> {
    Admin adminLogin(String userName, String password);

}
