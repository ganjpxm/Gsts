package org.ganjp.gmsw.common.dao.impl;

import java.io.Serializable;

import org.ganjp.gmsw.common.dao.GenericDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDao<T extends Serializable> extends AbstractHibernateDao<T> implements GenericDao<T> {
    //
}