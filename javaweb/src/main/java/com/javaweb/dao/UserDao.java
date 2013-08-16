package com.javaweb.dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.model.User;
import com.platform.base.dao.SimpleHibernateDao;


@Component
public class UserDao  extends SimpleHibernateDao<User, Serializable>{

}
