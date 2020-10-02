package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Website.class)
public abstract class Website_ {

	public static volatile SingularAttribute<Website, Boolean> allow;
	public static volatile SingularAttribute<Website, String> address;
	public static volatile SingularAttribute<Website, Integer> id;
	public static volatile SingularAttribute<Website, WebsiteInfo> websiteInfo;

}

