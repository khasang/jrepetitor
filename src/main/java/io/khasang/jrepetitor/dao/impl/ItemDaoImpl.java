package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.ItemDao;
import io.khasang.jrepetitor.entity.Item;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends BasicDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl(Class<Item> entityClass) {
        super(entityClass);
    }

    @Override
    public Item getById(long id) {
        Item item = getSessionFactory().get(Item.class, id);
        return initializeAndUnproxy(item);
    }

    @Override
    public Item delete(Item entity) {
        return super.delete(entity);
    }

    @Override
    public Item create(Item entity) {
        return super.create(entity);
    }

    @Override
    public List<Item> getList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);

        List<Item> list = getSessionFactory().createQuery(criteriaQuery).list();

        List<Item> items = new ArrayList<>();
        for (Item item:list) {
            items.add(initializeAndUnproxy(item));
        }

        return items;
    }

}