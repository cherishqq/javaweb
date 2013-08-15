package com.javaweb.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.javaweb.model.User;
import com.platform.base.dao.SimpleHibernateDao;


@Repository
public class UserDao  extends SimpleHibernateDao<User, Serializable>{

}
