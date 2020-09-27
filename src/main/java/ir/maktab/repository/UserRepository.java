package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.User;


public interface UserRepository  extends BaseRepository<User,Integer> {

    boolean userLogin(String userName, String password);

    void displayAll();
}
