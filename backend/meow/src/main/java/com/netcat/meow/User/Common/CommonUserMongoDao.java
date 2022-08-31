package com.netcat.meow.User.Common;

import com.netcat.meow.Utility.MongoDbConnection;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CommonUserMongoDao {

    private static CommonUserMongoDao instance = new CommonUserMongoDao();
    private MongoTemplate mongoTemplate;


    public CommonUserMongoDao() {
        mongoTemplate = MongoDbConnection.getInstance().mongoTemplate();
    }

    public static CommonUserMongoDao getInstance() {
        if (instance == null) {
            instance = new CommonUserMongoDao();
        }
        return instance;
    }
}


