package ir.maktab.repository.Impl;

import ir.maktab.entities.Tag;
import ir.maktab.repository.ArticleRepository;
import ir.maktab.services.UserService;
import ir.maktab.entities.Article;
import ir.maktab.entities.User;

import javax.persistence.*;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public ArticleRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }
@Override
    public void insert(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
    }
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
        for(Tag tag : a.getTags()){
            System.out.println("\nTags: " + tag.getTitle());
        }
    }

    @Override
    public Article update(Article article) {
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        return article;
    }

    @Override
    public Article findById(Integer id) {
        return em.find(Article.class,id);
    }

    @Override
    public void deleteById(Integer id) {
        Article a = findById(id);
        delete(a);
    }

    @Override
    public List<Article> findAll() {
        em.getTransaction().begin();
        TypedQuery<Article> query = em.createQuery("select entity from Article entity",Article.class);
        List<Article> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public Article findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Article> query = em.createQuery(
                "SELECT c FROM  Article c where c.title=:name",
                Article.class);
        query.setParameter("name",title);
        List<Article> resultList = query.getResultList();
        em.getTransaction().commit();
        return resultList.get(0);
    }

    @Override
    public void delete(Article article) {
        em.getTransaction().begin();
        em.remove(article);
        em.getTransaction().commit();
    }

    @Override
    public void displayAll() {
        List<Article> all = findAll();
        all.forEach((a) -> System.out.println("ID: " + a.getId() + "\nTitle: " + a.getTitle() +
                "\nBrief: " + a.getBrief()));

    }
@Override
    public void close() {
        em.close();
        emf.close();
    }
}
