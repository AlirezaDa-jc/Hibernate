package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Admin.class)
public abstract class Admin_ {

	public static volatile SingularAttribute<Admin, String> password;
	public static volatile SingularAttribute<Admin, Role> role;
	public static volatile SingularAttribute<Admin, String> name;
	public static volatile SingularAttribute<Admin, Integer> id;

}

