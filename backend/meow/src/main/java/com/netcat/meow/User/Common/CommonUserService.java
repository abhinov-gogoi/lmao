package com.netcat.meow.User.Common;

import com.netcat.meow.Service.TokenService;

public class CommonUserService {

    private static CommonUserService instance;
    private CommonUserMongoDao dao_mongo;
    private TokenService tokenservice;

    public CommonUserService() {
        dao_mongo = CommonUserMongoDao.getInstance();
        tokenservice = TokenService.getInstance();
    }

    public static CommonUserService getInstance() {
        if (instance == null) {
            instance = new CommonUserService();
        }
        return instance;
    }
}
