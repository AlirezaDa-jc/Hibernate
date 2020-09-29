package ir.maktab;


public class MainApp {

    private static Scan sc = Scan.getInstance();

    public static Scan getSc() {
        return sc;
    }

    public static void main(String[] args) {
        check();
    }


    private static void check() {
        FactoryMethod.MenuFactory menuFactory = new FactoryMethod.MenuFactory();
        while (true) {
            try {
                FactoryMethod.Menu menu = menuFactory.getMenu();
                menu.menuHandler();
            }catch (NullPointerException ex){
                System.out.println("Roles Are Admin And User");
                System.out.println("Invalid Role!");
            }
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
