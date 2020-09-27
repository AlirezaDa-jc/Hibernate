package ir.maktab;


import ir.maktab.Menu.FactoryMethod;

public class MainApp {

    private static Scan sc = Scan.getInstance();

    public static Scan getSc() {
        return sc;
    }

    public static void main(String[] args) {
            checkRole();
    }

    private static void checkRole() {
        FactoryMethod.MenuFactory menuFactory =  new FactoryMethod.MenuFactory();
        while (true) {
            FactoryMethod.Menu menu = menuFactory.getMenu();
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
