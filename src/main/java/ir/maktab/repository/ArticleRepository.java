package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Article;

public interface ArticleRepository extends BaseRepository<Article,Integer> {

    void displayAUserArticle();

    void displayAll();

}


