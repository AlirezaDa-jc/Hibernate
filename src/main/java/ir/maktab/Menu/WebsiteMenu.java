package ir.maktab.Menu;

import ir.maktab.Scan;
import ir.maktab.repository.FactoryMethod.Menu;
import ir.maktab.repository.FactoryMethod.MenuImpl;
import ir.maktab.services.WebsiteService;

public class WebsiteMenu extends MenuImpl implements Menu {
    private Scan sc;
    private int flagRole = 0;
    public WebsiteMenu() {
        sc = Scan.getInstance();
    }

    @Override
    public void display() {
        System.out.println("To Use This Database and Codes for Your Website Press 1");
        System.out.println("To See Websites Using This Source Press 2");
        System.out.println("To Get Data Source Press 3");
        if(flagRole == 0) {
            System.out.println("To Remove This Database And Codes From Your Website Press 4(You Should Be Admin!)");
            System.out.println("To Give Permission To use database Press 5(You Should Be Admin!)");
        }
        System.out.println("To Log Out Press 6");
        System.out.println("Via Alireza.D.a");
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
                    WebsiteService.insert();
                    break;
                case 2:
                    WebsiteService.display();
                    break;
                case 3:
                    WebsiteService.dataSourcePermission();
                    break;
                case 4:
                    if(flagRole == 0) {
                        WebsiteService.delete();
                    }else{
                        System.out.println("You Are Not An Admin!");
                    }
                    break;
                case 5:
                    if(flagRole == 0) {
                    WebsiteService.update();
                    }else{
                        System.out.println("You Are Not An Admin!");
                    }
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid Number");
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

    public void setFlagRole(int i) {
        flagRole = i;
    }
}
