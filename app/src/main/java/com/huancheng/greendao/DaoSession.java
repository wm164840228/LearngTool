package com.huancheng.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.huancheng.learngtool.bean.Classify;

import com.huancheng.greendao.ClassifyDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig classifyDaoConfig;

    private final ClassifyDao classifyDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        classifyDaoConfig = daoConfigMap.get(ClassifyDao.class).clone();
        classifyDaoConfig.initIdentityScope(type);

        classifyDao = new ClassifyDao(classifyDaoConfig, this);

        registerDao(Classify.class, classifyDao);
    }
    
    public void clear() {
        classifyDaoConfig.clearIdentityScope();
    }

    public ClassifyDao getClassifyDao() {
        return classifyDao;
    }

}