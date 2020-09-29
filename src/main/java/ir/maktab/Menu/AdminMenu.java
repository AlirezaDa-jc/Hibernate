package ir.maktab.Menu;


import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.repository.FactoryMethod.Menu;
import ir.maktab.repository.FactoryMethod.MenuImpl;
import ir.maktab.services.*;

public class AdminMenu extends MenuImpl implements Menu {
    private Scan sc = null;

    public AdminMenu() {
        sc = MainApp.getSc();
    }
    @Override
    public void display() {
        System.out.println("To Edit An Article Published Press 1");
        System.out.println("To Add Category Press 2");
        System.out.println("To Delete an Article Press 3");
        System.out.println("To Add a Tag Press 4");
        System.out.println("To Edit a User's Role Press 5");
        System.out.println("To add a Role Press 6");
        System.out.println("To add a Tag to an Article Press 7");
        System.out.println("To Delete a User Press 8");
        System.out.println("To See Users Press 9");
        System.out.println("To Go To Website DataBase Service Press 10");
        System.out.println("To Log out 11");

    }

    @Override
    public void menuHandler() {
        boolean flag = true;
        while (flag) {
            display();
            setOption(sc);
            int option = getOption();
            switch (option) {
                case 1:
                    ArticleService.displayAll();
                    int id = ArticleService.displayAnArticle();
                    ArticleService.updateAdmin(id);
                    break;
                case 2:
                    CategoryService.add();
                    break;
                case 3:
                    ArticleService.deleteArticleAdmin();
                    break;
                case 4:
                    TagService.add();
                    break;
                case 5:
                    AdminService.editRole();
                    break;
                case 6:
                    RoleService.add();
                    break;
                case 7:
                    TagService.addArticle();
                    break;
                case 8:
                    UserService.delete();
                    break;
                case 9:
                    UserService.display();
                    break;
                case 10:
                    WebsiteMenu websiteMenu = new WebsiteMenu();
                    websiteMenu.setFlagRole(0);
                    websiteMenu.menuHandler();
                    break;
                case 11:
                    flag = false;
            }
        }
    }

    @Override
    public boolean checkChoice() {
        return false;
    }

    public void setOption(Scan sc){
        while(true) {
            try {
                option = Integer.parseInt(sc.getString("Enter a Number: "));
                super.setOption(option);
                break;
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
    }

    public int getOption(){
        return super.getOption();
    }


}
