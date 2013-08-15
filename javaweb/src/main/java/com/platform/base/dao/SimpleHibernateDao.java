
package com.platform.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


@SuppressWarnings("unchecked")
public class SimpleHibernateDao<T, PK extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;


	public SimpleHibernateDao() {
		
		
		Class clazz = getClass();
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			this.entityClass  = (Class<T>) Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (0 >= params.length || 0 < 0) {
			logger.warn("Index: " + 0 + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			this.entityClass =  (Class<T>) Object.class;
		}
		if (!(params[0] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			this.entityClass = (Class<T>) Object.class;
		}

		this.entityClass =  (Class) params[0];
		
		System.out.println(entityClass);
		
	}


	public SimpleHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}


	public void save(final T entity) {
		Assert.notNull(entity, "entity娑撳秷鍏樻稉铏光敄");
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}


	public void delete(final T entity) {
		Assert.notNull(entity, "entity娑撳秷鍏樻稉铏光敄");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}


	public void delete(final PK id) {
		Assert.notNull(id, "id娑撳秷鍏樻稉铏光敄");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}


	public T get(final PK id) {
		Assert.notNull(id, "id娑撳秷鍏樻稉铏光敄");
		return (T) getSession().load(entityClass, id);
	}


	public List<T> get(final Collection<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}


	public List<T> getAll() {
		return find();
	}


	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}


	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName娑撳秷鍏樻稉铏光敄");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}


	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName娑撳秷鍏樻稉铏光敄");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}


	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}


	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}


	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}


	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}


	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}


	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}


	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString娑撳秷鍏樻稉铏光敄");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}


	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString娑撳秷鍏樻稉铏光敄");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}


	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}


	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}


	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}


	public void initProxyObject(Object proxy) {
		Hibernate.initialize(proxy);
	}


	public void flush() {
		getSession().flush();
	}


	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}


	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}


	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}


	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}
}