package ir.maktab;

import ir.maktab.FactoryMethod.Menu;
import ir.maktab.FactoryMethod.MenuFactory;
import ir.maktab.Menu.AdminMenu;
import ir.maktab.Menu.UserMenu;
import ir.maktab.entities.Role;
import ir.maktab.services.AdminService;
import ir.maktab.services.RoleService;
import ir.maktab.services.UserService;

public class MainApp {

    private static Scan sc = Scan.getInstance();

    public static Scan getSc() {
        return sc;
    }

    public static void main(String[] args) {
            checkRole();
    }

    private static void checkRole() {
        MenuFactory menuFactory =  new MenuFactory();
        while (true) {
            Menu menu = menuFactory.getMenu();
            menu.menuHandler();
        }
    }

//    private static void checkRole() {
//        if(RoleService.checkRole()) {
//            Role role1 = RoleService.getRole();
//            switch (role1.getRoleTitle()) {
//                case "USER":
//                    UserService.userLogin();
//                    new UserMenu().menuHandler();
//                    break;
//                case "ADMIN":
//                    if(AdminService.adminLogin()) {
//                        new AdminMenu().menuHandler();
//                    }
//                    break;
//            }
//        }
//
//    }


}
