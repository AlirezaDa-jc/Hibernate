package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Category;
import ir.maktab.repository.CategoryRepository;
import ir.maktab.repository.Impl.CategoryRepositoryImpl;

public class CategoryService {
    private static Category category = new Category();
    private static CategoryRepository repository = new CategoryRepositoryImpl();
    private static Scan sc = MainApp.getSc();


    public static Category getCategory() {
        return category;
    }

    public static void setCategory(Category category) {
        CategoryService.category = category;
    }

    public static CategoryRepository getRepository() {
        return repository;
    }

    public static void setRepository(CategoryRepositoryImpl repository) {
        CategoryService.repository = repository;
    }

    public static void display() {
    repository.display();
    }


    public static Category use() {
        String title = sc.getString("Enter Title: ");
        return repository.findByTitle(title);
    }

    public static void add() {
        String title = sc.getString("Enter Title: ");
//        if (!CategoryRepositoryImpl.checkDuplicateTitle(title)) return;
        if (!repository.checkDuplicateTitle(title)) return;
        String description = sc.getString("Enter Description: ");
        category.setTitle(title);
        category.setDescription(description);
//        CategoryRepositoryImpl.add(category);
        repository.insert(category);
    }

    public static void close() {
        repository.close();
    }
}
