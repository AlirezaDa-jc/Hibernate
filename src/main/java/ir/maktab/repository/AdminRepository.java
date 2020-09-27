package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Admin;
import ir.maktab.entities.User;
import ir.maktab.entities.UserInfo;

import java.util.List;
import java.util.function.Function;


public interface AdminRepository extends BaseRepository<Admin,Integer> {
    Admin adminLogin(String userName, String password);

}
