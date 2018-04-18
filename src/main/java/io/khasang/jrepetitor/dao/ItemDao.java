package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Item;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;

public interface ItemDao extends BasicDao<Item>  {
}
