package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.User;
import ir.maktab.entities.UserInfo;

import java.util.List;
import java.util.function.Function;


public interface UserRepository  extends BaseRepository<User,Integer> {

    boolean userLogin(String userName, String password);
    List<UserInfo> findUsersInfo(Function<User,UserInfo> function);
    void displayAll();
}
