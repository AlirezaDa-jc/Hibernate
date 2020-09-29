package ir.maktab.repository.FactoryMethod;

import ir.maktab.Menu.AdminMenu;
import ir.maktab.Menu.UserMenu;
import ir.maktab.Scan;
import ir.maktab.services.AdminService;
import ir.maktab.services.RoleService;
import ir.maktab.services.UserService;

public class MenuFactory {
    Scan sc = Scan.getInstance();

    public Menu getMenu() {

        String type = sc.getString("Enter Role: ");
        type = type.toUpperCase();
        switch (type) {
            case "USER":
                RoleService.checkRole(type);
                if (UserService.userLogin()) {
                    return new UserMenu();
                }
                break;
            case "ADMIN":
                RoleService.checkRole(type);
                if (AdminService.adminLogin()) {
                    return new AdminMenu();
                }
                break;
            default:
                System.out.println("Invalid Role");
                return null;
        }
        return null;
    }
}
