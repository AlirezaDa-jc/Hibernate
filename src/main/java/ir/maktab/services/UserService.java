package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Address;
import ir.maktab.entities.Role;
import ir.maktab.entities.User;
import ir.maktab.repository.Impl.UserRepositoryImpl;
import ir.maktab.repository.UserRepository;


public class UserService {
    private static User user = new User();
    private static UserRepository repository = new UserRepositoryImpl();
    private static Scan sc = MainApp.getSc();


    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserService.user = user;
    }

    public static void userSignIn() {
        String userName = sc.getString("Username: ");
        user.setName(userName);
        String nationalCode = sc.getString("National Code: ");
        user.setNationalCode(nationalCode);
        String birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        while (!birthday.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        }
        user.setBirthday(birthday);
        user.setPassword(nationalCode);
        Role role = RoleService.getRole();
        user.setRole(role);
        role.addUser(user);
        Address address = new Address();
        address.setAddress(sc.getString("Address: "));
        user.setAddress(address);
        address.setUser(user);
        System.out.println("Before");
        AddressService.insert(address);
        System.out.println("After");
        repository.insert(user);
    }

    public static boolean signInOrLogin() {
        String choice = sc.getString("Sign In Or Login Or Exit: ");
        switch (choice.toUpperCase()) {
            case "SIGN IN":
                userSignIn();
                return true;
            case "LOGIN":
                if (login()) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean login() {
        String userName = sc.getString("Username: ");
        String password = sc.getString("Password: ");
        return repository.userLogin(userName, password);
    }

    public static void changePassword() {
        String oldPassword = sc.getString("Enter Your Old Password: ");
        if (!oldPassword.equals(user.getPassword())) {
            System.out.println("Wrong Password!");
            return;
        }
        String newPassword = sc.getString("Enter Your New Password: ");
        String confirmedPassword = sc.getString("Enter Your New Password Again: ");
        if (newPassword.equals(confirmedPassword)) {
            user.setPassword(newPassword);
            repository.update(user);
        }
        System.out.println("Unmatched Passwords!");

    }


    public static boolean userLogin() {
        return signInOrLogin();
    }

    public static User findUser(String name) {
        return repository.findByTitle(name);
    }

    public static void update(User newUser) {
        repository.delete(newUser);
    }

    public static void delete() {
        repository.displayAll();
        int id = Integer.parseInt(sc.getString("ID Of User"));
        repository.deleteById(id);
    }
}
