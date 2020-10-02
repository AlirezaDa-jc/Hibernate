package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Article.class)
public abstract class Article_ {

	public static volatile SingularAttribute<Article, String> brief;
	public static volatile SingularAttribute<Article, String> modificationDate;
	public static volatile SingularAttribute<Article, Boolean> isPublished;
	public static volatile SingularAttribute<Article, Integer> id;
	public static volatile SingularAttribute<Article, String> publishedDate;
	public static volatile SingularAttribute<Article, String> title;
	public static volatile SingularAttribute<Article, Category> category;
	public static volatile SingularAttribute<Article, User> user;
	public static volatile SingularAttribute<Article, String> content;
	public static volatile SingularAttribute<Article, String> createDate;
	public static volatile SetAttribute<Article, Tag> tags;

}

