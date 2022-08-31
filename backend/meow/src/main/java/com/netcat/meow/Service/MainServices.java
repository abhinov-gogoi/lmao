package com.netcat.meow.Service;

import com.netcat.meow.Dao.MainDao;
import com.netcat.meow.Dao.MainMongoDao;


/**
 * @author ABHINOV
 */
public class MainServices {

    private static MainServices instance;
    private final MainMongoDao dao_mongo;
    private final MainDao dao_mysql;

    private MainServices() {
        dao_mongo = MainMongoDao.getInstance();
        dao_mysql = MainDao.getInstance();
    }

    public static MainServices getInstance() {
        if (instance == null) {
            instance = new MainServices();
        }
        return instance;
    }


}
