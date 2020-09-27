package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Article;
import ir.maktab.entities.Tag;
import ir.maktab.entities.User;
import ir.maktab.repository.ArticleRepository;
import ir.maktab.services.UserService;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArticleRepositoryImpl extends BaseRepositoryImpl<Article, Integer> implements ArticleRepository {

    @Override
    public void displayAUserArticle() {
        em.getTransaction().begin();
        User user = UserService.getUser();
        TypedQuery<Article> query = em.createQuery(
                "SELECT a FROM Article a where a.user=:user",
                Article.class);
        query.setParameter("user", user);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Article a = query.getResultList().get(i);
            printArticleDetails(a);
        }
        em.getTransaction().commit();
    }

    public static void printArticleDetails(Article a) {
        System.out.println("ID: " + a.getId() + "\nTitle: " + a.getTitle() + "\nBrief: " + a.getBrief()
                + "\nContent: " + a.getContent() + "\nCreate Date: " + a.getCreateDate() + "\nAuthor: " +
                a.getUser().getName() + "\nIs Published: " + a.isPublished() + "\nCategory: "
                + a.getCategory().getTitle());
        a.getTags().forEach((c) -> System.out.println("\nTags: " + c.getTitle()));
    }


    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }

    @Override
    public void insert(Article article) {
        super.insert(article);
    }

    @Override
    public Article update(Article article) {
        return super.update(article);
    }

    @Override
    public Article findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public List<Article> findAll() {
        return super.findAll();
    }

    @Override
    public Article findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Article> query = em.createQuery(
                "SELECT u FROM Article u where u.title=:title",
                Article.class);

        query.setParameter("title", title);
        List<Article> resultList = query.getResultList();
        em.getTransaction().commit();

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Article> findAllFiltered(Predicate<Article> predicate) {
        List<Article> all = findAll();
        return all.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Article article) {
        super.delete(article);
    }

    @Override
    public void displayAll() {
        List<Article> all = findAll();
        all.forEach((a) -> System.out.println("ID: " + a.getId() + "\nTitle: " + a.getTitle() +
                "\nBrief: " + a.getBrief()));

    }

    @Override
    public void displayAllFiltered(Predicate<Article> predicate) {
        List<Article> allFiltered = findAllFiltered(predicate);
        allFiltered.forEach((a) -> System.out.println("ID: " + a.getId() + "\nTitle: " + a.getTitle() +
                "\nBrief: " + a.getBrief()));
    }
}
