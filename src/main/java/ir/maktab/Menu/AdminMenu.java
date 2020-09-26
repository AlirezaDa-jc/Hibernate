package ir.maktab.Menu;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.base.Menu;
import ir.maktab.services.*;

public class AdminMenu extends Menu {
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
        System.out.println("To add a Tag to an Article 7");

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
                    flag = false;
                    TagService.close();
                    ArticleService.close();
                    AdminService.close();
                    UserService.close();
                    RoleService.close();
                    CategoryService.close();
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
