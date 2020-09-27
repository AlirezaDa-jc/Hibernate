package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Tag;
import ir.maktab.repository.Impl.TagRepositoryImpl;
import ir.maktab.repository.TagRepository;

public class TagService {
    private static Tag tag = new Tag();
    private static TagRepository repository = new TagRepositoryImpl();
    private static Scan sc = MainApp.getSc();

    public static Tag getTag() {
        return tag;
    }

    public static void setTag(Tag tag) {
        TagService.tag = tag;
    }

    public static void add() {
        String title = sc.getString("Title: ");
        Tag newTag = new Tag();
        newTag.setTitle(title);
        if (check(newTag)) {
            System.out.println("Invalid Tag Title");
            return;
        }
        repository.insert(newTag);
    }

    private static boolean check(Tag newTag) {
        Tag tag = repository.findById(newTag.getId());
        return tag != null;
    }

    public static void addArticle() {
        ArticleService.displayAll();
        ArticleService.addTag();

    }

    public static void displayAll() {
        repository.displayAll();
    }

    public static Tag getTag(int id) {
        return repository.findById(id);
    }

}
