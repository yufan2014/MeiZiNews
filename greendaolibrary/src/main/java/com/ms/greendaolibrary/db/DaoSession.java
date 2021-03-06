package com.ms.greendaolibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.greendaolibrary.db.CollectEntity;

import com.ms.greendaolibrary.db.HtmlEntityDao;
import com.ms.greendaolibrary.db.CollectEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig htmlEntityDaoConfig;
    private final DaoConfig collectEntityDaoConfig;

    private final HtmlEntityDao htmlEntityDao;
    private final CollectEntityDao collectEntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        htmlEntityDaoConfig = daoConfigMap.get(HtmlEntityDao.class).clone();
        htmlEntityDaoConfig.initIdentityScope(type);

        collectEntityDaoConfig = daoConfigMap.get(CollectEntityDao.class).clone();
        collectEntityDaoConfig.initIdentityScope(type);

        htmlEntityDao = new HtmlEntityDao(htmlEntityDaoConfig, this);
        collectEntityDao = new CollectEntityDao(collectEntityDaoConfig, this);

        registerDao(HtmlEntity.class, htmlEntityDao);
        registerDao(CollectEntity.class, collectEntityDao);
    }
    
    public void clear() {
        htmlEntityDaoConfig.getIdentityScope().clear();
        collectEntityDaoConfig.getIdentityScope().clear();
    }

    public HtmlEntityDao getHtmlEntityDao() {
        return htmlEntityDao;
    }

    public CollectEntityDao getCollectEntityDao() {
        return collectEntityDao;
    }

}
