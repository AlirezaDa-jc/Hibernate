package ir.maktab.services;

import ir.maktab.MainApp;

import ir.maktab.entities.Admin;
import ir.maktab.Scan;
//import ir.maktab.entities.Role;
import ir.maktab.entities.Role;
import ir.maktab.entities.User;
import ir.maktab.repository.AdminRepository;
import ir.maktab.repository.Impl.AdminRepositoryImpl;

import java.util.Set;

public class AdminService {

    private static Admin admin;
    private static AdminRepository repository = new AdminRepositoryImpl();
    private static Scan sc = MainApp.getSc();

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        AdminService.admin = admin;
    }

    public static boolean adminLogin() {
        String userName = sc.getString("Username: ");
        String password = sc.getString("Password: ");

        if(repository.adminLogin(userName, password) != null){
            admin = repository.adminLogin(userName, password);
//            admin.setName(userName);
//            admin.setPassword(password);
//            Role role = RoleService.getRole();
//            admin.setRole(role);
//            role.addAdmin(admin);
            return true;
        }
        return false;
    }

    public static void insert() {
        String userName = sc.getString("Username: ");
        String password = sc.getString("Password: ");
        admin.setName(userName);
        admin.setPassword(password);
        Role role = RoleService.getRole();
        admin.setRole(role);
        repository.insert(admin);
        role.addAdmin(admin);
    }

    public static void editRole() {
        Set<User> userSet = RoleService.displayUsers();
        String selectedUserName = sc.getString("Which User You Want To Change Its Role: (Enter Name) : ");
        User selectedUser = null;
        for(User user: userSet){
            if(selectedUserName.equals(user.getName())){
                selectedUser = user;
            }
        }
        RoleService.display();
        assert selectedUser != null;

        String selectedRole = sc.getString("Which Role You Want To Give This User: ");
        switch (selectedRole.toUpperCase()){
            case "ADMIN":
                selectedUser.setRole(RoleService.getRole());
                UserService.update(selectedUser);
                AdminService.adminAdd(selectedUser);
                break;
            case "USER":
                selectedUser.setRole(selectedUser.getRole());
                break;
        }
        System.out.println(selectedUser.getName() +": " + selectedUser.getRole().getRoleTitle());
    }

    private static void adminAdd(User newUser) {

            Admin a = new Admin();
            a.setName(newUser.getName());
            a.setPassword(newUser.getPassword());
            a.setRole(newUser.getRole());
            repository.insert(a);

    }

}
