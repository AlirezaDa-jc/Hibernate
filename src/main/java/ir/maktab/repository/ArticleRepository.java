package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Article;

import java.util.function.Predicate;

public interface ArticleRepository extends BaseRepository<Article,Integer> {

    void displayAUserArticle();

    void displayAll();

    void displayAllFiltered(Predicate<Article> predicate);
}


