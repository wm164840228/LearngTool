package com.huancheng.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.huancheng.learngtool.bean.Classify;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLASSIFY".
*/
public class ClassifyDao extends AbstractDao<Classify, Long> {

    public static final String TABLENAME = "CLASSIFY";

    /**
     * Properties of entity Classify.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Uri = new Property(1, String.class, "uri", false, "URI");
        public final static Property Kemu = new Property(2, String.class, "kemu", false, "KEMU");
        public final static Property Laiyuan = new Property(3, String.class, "laiyuan", false, "LAIYUAN");
        public final static Property Chengdu = new Property(4, String.class, "chengdu", false, "CHENGDU");
        public final static Property Tixing = new Property(5, String.class, "tixing", false, "TIXING");
        public final static Property Yuanyin = new Property(6, String.class, "yuanyin", false, "YUANYIN");
        public final static Property Beizhu = new Property(7, String.class, "beizhu", false, "BEIZHU");
        public final static Property Daan = new Property(8, String.class, "daan", false, "DAAN");
        public final static Property Nianji = new Property(9, String.class, "nianji", false, "NIANJI");
        public final static Property Time = new Property(10, long.class, "time", false, "TIME");
    }


    public ClassifyDao(DaoConfig config) {
        super(config);
    }
    
    public ClassifyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLASSIFY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"URI\" TEXT," + // 1: uri
                "\"KEMU\" TEXT," + // 2: kemu
                "\"LAIYUAN\" TEXT," + // 3: laiyuan
                "\"CHENGDU\" TEXT," + // 4: chengdu
                "\"TIXING\" TEXT," + // 5: tixing
                "\"YUANYIN\" TEXT," + // 6: yuanyin
                "\"BEIZHU\" TEXT," + // 7: beizhu
                "\"DAAN\" TEXT," + // 8: daan
                "\"NIANJI\" TEXT," + // 9: nianji
                "\"TIME\" INTEGER NOT NULL );"); // 10: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLASSIFY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Classify entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String uri = entity.getUri();
        if (uri != null) {
            stmt.bindString(2, uri);
        }
 
        String kemu = entity.getKemu();
        if (kemu != null) {
            stmt.bindString(3, kemu);
        }
 
        String laiyuan = entity.getLaiyuan();
        if (laiyuan != null) {
            stmt.bindString(4, laiyuan);
        }
 
        String chengdu = entity.getChengdu();
        if (chengdu != null) {
            stmt.bindString(5, chengdu);
        }
 
        String tixing = entity.getTixing();
        if (tixing != null) {
            stmt.bindString(6, tixing);
        }
 
        String yuanyin = entity.getYuanyin();
        if (yuanyin != null) {
            stmt.bindString(7, yuanyin);
        }
 
        String beizhu = entity.getBeizhu();
        if (beizhu != null) {
            stmt.bindString(8, beizhu);
        }
 
        String daan = entity.getDaan();
        if (daan != null) {
            stmt.bindString(9, daan);
        }
 
        String nianji = entity.getNianji();
        if (nianji != null) {
            stmt.bindString(10, nianji);
        }
        stmt.bindLong(11, entity.getTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Classify entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String uri = entity.getUri();
        if (uri != null) {
            stmt.bindString(2, uri);
        }
 
        String kemu = entity.getKemu();
        if (kemu != null) {
            stmt.bindString(3, kemu);
        }
 
        String laiyuan = entity.getLaiyuan();
        if (laiyuan != null) {
            stmt.bindString(4, laiyuan);
        }
 
        String chengdu = entity.getChengdu();
        if (chengdu != null) {
            stmt.bindString(5, chengdu);
        }
 
        String tixing = entity.getTixing();
        if (tixing != null) {
            stmt.bindString(6, tixing);
        }
 
        String yuanyin = entity.getYuanyin();
        if (yuanyin != null) {
            stmt.bindString(7, yuanyin);
        }
 
        String beizhu = entity.getBeizhu();
        if (beizhu != null) {
            stmt.bindString(8, beizhu);
        }
 
        String daan = entity.getDaan();
        if (daan != null) {
            stmt.bindString(9, daan);
        }
 
        String nianji = entity.getNianji();
        if (nianji != null) {
            stmt.bindString(10, nianji);
        }
        stmt.bindLong(11, entity.getTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Classify readEntity(Cursor cursor, int offset) {
        Classify entity = new Classify( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // uri
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // kemu
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // laiyuan
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // chengdu
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // tixing
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // yuanyin
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // beizhu
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // daan
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // nianji
            cursor.getLong(offset + 10) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Classify entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUri(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKemu(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLaiyuan(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setChengdu(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTixing(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setYuanyin(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBeizhu(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDaan(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setNianji(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTime(cursor.getLong(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Classify entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Classify entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Classify entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
