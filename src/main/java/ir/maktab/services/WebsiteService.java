package ir.maktab.services;

import ir.maktab.Scan;
import ir.maktab.entities.User;
import ir.maktab.entities.Website;
import ir.maktab.entities.WebsiteInfo;
import ir.maktab.repository.Impl.WebsiteInfoRepositoryImpl;
import ir.maktab.repository.Impl.WebsiteRepositoryImpl;
import ir.maktab.repository.WebsiteInfoRepository;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class WebsiteService {
    private static Scan sc = Scan.getInstance();
    private static WebsiteRepositoryImpl repository = new WebsiteRepositoryImpl();
    private static WebsiteInfoRepository infoRepository = new WebsiteInfoRepositoryImpl();
    private static Website website = new Website();


    public static void insert() {
        String address = sc.getString("Address: ");
        website.setAddress(address);
        website.setAllow(false);
        WebsiteInfo websiteInfo = new WebsiteInfo();
        websiteInfo.setInfo(sc.getString("Website Info: "));
        website.setWebsiteInfo(websiteInfo);
        repository.insert(website);
        User user = UserService.getUser();
        writeData(website, user);
    }

    private static void writeData(Website website, User user) {
        File f1 = new File("D:\\Programs\\Java\\Hibernate1\\web.txt");
        try {
            FileWriter fileWriter = new FileWriter(f1.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("\n");
            bw.write(website.getAddress());
            bw.write("  ");
            bw.write(user.getName());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    public static void delete() {
        displayAll();
        int id = Integer.parseInt(sc.getString("Enter id You Want to Remove"));
        repository.deleteById(id);
    }

    private static void displayAll() {
        repository.displayAll();
    }

    public static void display() {
        Predicate<Website> predicate = Website::isAllow;
        repository.findAllFiltered(predicate).forEach((c) -> System.out.println("ID: " + c.getId() + "\nWebsite:" +
                " " + c.getAddress() + "\nInfo: " + c.getWebsiteInfo().getInfo()));

    }

    public static void update() {
        Predicate<Website> predicate = (c) -> !c.isAllow();
        repository.findAllFiltered(predicate).forEach((c) -> System.out.println("ID: " + c.getId() + "\nWebsite:" +
                " " + c.getAddress() + "\nInfo: " + c.getWebsiteInfo().getInfo()));
        int id = Integer.parseInt(sc.getString("Which One Do you Want to Allow : (ID) : "));
        Website website = repository.findById(id);
        website.setAllow(true);
        repository.update(website);
    }

    public static void dataSourcePermission() {
        Predicate<Website> predicate = Website::isAllow;
        List<Website> allFiltered = repository.findAllFiltered(predicate);
        readData(allFiltered);
    }

    private static void readData(List<Website> allFiltered) {
        User user = UserService.getUser();
        try(FileInputStream fileInputStream = new FileInputStream("D:\\Programs\\Java\\Hibernate1\\web.txt")) {
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String website = scanner.next();
                for (Website web : allFiltered) {
                    if (website.equals(web.getAddress())) {
                        String userName = scanner.next();
                        if (userName.equals(user.getName())) {
                            System.out.println("You Have Permission!");
                            System.out.println("Source Code Is: ");
                            System.out.println("https://github.com/AlirezaDa-jc/Hibernate");
                            return;
                        }
                        System.out.println("Error In Username Or Website Address");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Invalid Address");
    }
}
