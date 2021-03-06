package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Article;
import ir.maktab.entities.Category;
import ir.maktab.entities.Tag;
import ir.maktab.entities.User;
import ir.maktab.repository.ArticleRepository;
import ir.maktab.repository.Impl.ArticleRepositoryImpl;

import java.util.Set;
import java.util.function.Predicate;

public class ArticleService {

    private static Article article = new Article();
    private static ArticleRepository repository = new ArticleRepositoryImpl();
    private static Scan sc = MainApp.getSc();

    public static void add() {
        String title = sc.getString("Title: ");
        article.setTitle(title);
        String brief = getText("Brief (Enter :ESC: To Finish) : ");
        article.setBrief(brief);
        String content = getText("Content: (Enter :ESC: To Finish) : ");
        article.setContent(content);
        String createDate = sc.getString("Create Date: (Format : YYYY-MM-DD): ");
        createDate = checkDate(createDate);
        article.setCreateDate(createDate);
        article.setPublished(false);
        CategoryService.display();
        Category category = CategoryService.use();
        article.setCategory(category);
        User user = UserService.getUser();
        article.setUser(user);
        if (category == null || user == null) {
            System.out.println("Invalid Category Or User Usage");
            return;
        }
        article.setModificationDate(createDate);
        repository.insert(article);
    }

    private static String checkDate(String createDate) {
        while (!createDate.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            createDate = sc.getString("Create Date: (Format : YYYY-MM-DD): ");
        }
        return createDate;
    }

    public static void displayAUserArticle() {
        repository.displayAUserArticle();
    }

    private static String getText(String s) {
        System.out.print(s);
        StringBuilder text = new StringBuilder();
        while (true) {
            String temp = sc.getString();
            if (temp.equals("ESC")) {
                break;
            }
            text.append(temp).append("\n");
        }
        return text.toString();
    }

    public static void updateUser() {
        displayAUserArticle();
        int id = Integer.parseInt(sc.getString("ID of Article You Want to Edit: "));
        String update = sc.getString("What You Want To Edit: (Title/Brief/Content/CreateDate/" +
                "Category): ");
        Article article = repository.findById(id);
        switch (update.toLowerCase()) {
            case "title":
                updateTitle(article);
                break;
            case "brief":
                updateBrief(article);
                break;
            case "content":
                updateContent(article);
                break;
            case "createdate":
                updateCreateDate(article);
                break;
            case "category":
                if (updateCategory(article)) {
                    break;
                }
            default:
                System.out.println("Invalid Input");
                return;
        }
        String date = sc.getString("Today's Date: ");
        date = checkDate(date);
        article.setModificationDate(date);
    }

    public static void deleteArticleUser() {
        displayAUserArticle();
        int id = Integer.parseInt(sc.getString("ID of Article You Want to Delete: "));
        User user = UserService.getUser();
        Set<Article> articleSet = user.getArticles();
        for (Article article : articleSet) {
            if (article.getId() == id) {
                repository.deleteById(id);
                Set<Tag> tags = article.getTags();
                System.out.println("Deleted Successfully");
                tags.forEach((t) -> t.removeArticle(article));
            } else {
                System.out.println("Invalid ID");
            }
            return;
        }
    }


    /*
    In Persistence we used Update then It's Not Necessary to Use ExecuteUpdate Here !
    */

    private static boolean updateCategory(Article article) {
        Category category = CategoryService.use();
        if (category == null) {
            System.out.println("Invalid Usage Of Category");
            return false;
        }
        article.setCategory(category);
        repository.update(article);
        return true;
    }


    private static void updateCreateDate(Article article) {
        String createDate = sc.getString("New Create Date: (Format : YYYY-MM-DD): ");
        article.setCreateDate(createDate);
        repository.update(article);

    }

    private static void updateContent(Article article) {
        String content = getText("New Content: (Enter :ESC: To Finish): ");
        article.setContent(content);
        repository.update(article);

    }

    private static void updateBrief(Article article) {
        String brief = getText("New Brief: (Enter :ESC: To Finish): ");
        article.setBrief(brief);
        repository.update(article);
    }

    private static void updateTitle(Article article) {
        String title = sc.getString("New Title: ");
        article.setTitle(title);
        repository.update(article);
    }

    public static void displayAll() {
        repository.displayAll();
    }

    public static int displayAnArticle() {
        int id = Integer.parseInt(sc.getString("Enter ID of Article: "));
        Article article = repository.findById(id);
        ArticleRepositoryImpl.printArticleDetails(article);
        return id;
    }


    public static void updateAdmin(int id) {
        String update = sc.getString("What You Want To Edit: (Title/Brief/Content/CreateDate/" +
                "Category/isPublished): ");
        Article article = repository.findById(id);
        switch (update.toLowerCase()) {
            case "title":
                updateTitle(article);
                break;
            case "brief":
                updateBrief(article);
                break;
            case "content":
                updateContent(article);
                break;
            case "createdate":
                updateCreateDate(article);
                break;
            case "category":
                if (updateCategory(article)) {
                    break;
                }
            case "ispublished":
                updatePublished(article);
                break;
            default:
                System.out.println("Invalid Input");
                return;
        }
        String date = sc.getString("Today's Date: ");
        date = checkDate(date);
        article.setPublishedDate(date);
        article.setModificationDate(date);

    }

    private static void updatePublished(Article article) {
        char published = sc.getString("Is Published(Y/N): ").charAt(0);
        if (published == 'Y' || published == 'N') {
            article.setPublished(published == 'Y');
            repository.update(article);
        }
    }

    public static void deleteArticleAdmin() {
        int id = Integer.parseInt(sc.getString("ID of Article You Want to Delete: "));
        Article a = repository.findById(id);
        Set<Tag> tags = a.getTags();
        repository.deleteById(id);
            System.out.println("Deleted Successfully");
            tags.forEach((t) -> t.removeArticle(a));
    }

    public static void addTag() {
        int id = Integer.parseInt(sc.getString("Article ID: "));
        Article a = repository.findById(id);
        TagService.displayAll();
        id = Integer.parseInt(sc.getString("Tag ID: "));
        Tag tag = TagService.getTag(id);
        a.addTag(tag);
        tag.addArticle(a);
    }

    public static void displayAllFiltered() {
        Predicate<Article> predicate = Article::isPublished;
        repository.displayAllFiltered(predicate);
    }

    public static void displayYearsFiltered() {
        int firstYear = Integer.parseInt(sc.getString("Year: "));
        int secondYear = Integer.parseInt(sc.getString("Year: "));
        Predicate<Article> predicate = (c) -> {
            int createData = Integer.parseInt(c.getCreateDate().substring(0,4));
            if(createData >= firstYear){
                return createData <= secondYear;
            }
            return false;
        };
        repository.displayAllFiltered(predicate);

    }
}
