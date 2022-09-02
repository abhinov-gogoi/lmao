package com.netcat.meow.Service;

import com.netcat.meow.Dao.MainMongoDao;
import com.netcat.meow.Utility.Conceal;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import java.sql.Timestamp;
import java.util.Base64;

public class TokenService {

    private static TokenService instance;
    private Conceal encdnc;
    private MainMongoDao dao_mongo;

    private TokenService() {
        encdnc = Conceal.getInstance();
        dao_mongo = MainMongoDao.getInstance();
    }

    public static TokenService getInstance() {
        if (instance == null) {
            instance = new TokenService();
        }
        return instance;
    }

    public boolean validateToken(String token, String login_id) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(login_id)) {
            return false;
        }
        Document filter_doc = new Document();
        filter_doc
                .append(Literal.session_id, token)
                .append(Literal.login_id, login_id);
        Document search_doc = new Document().append(Literal.password, Literal.ZERO_VALUE);
        return !dao_mongo.findOne(filter_doc, search_doc, Utility.col_login).isEmpty();
    }

    public String generateTokenLogin(final Document req_map, final String user_agent, final String login_uuid) {
        return this.generateToken(
                Utility.getDocStr(req_map, Literal.login_id),
                user_agent,
                new Timestamp(System.currentTimeMillis()),
                login_uuid);

    }

    private String generateToken(final String login_id,
                                 final String user_agent,
                                 final Timestamp session_time,
                                 final String login_uuid) {

        String token = login_id + Literal.TOKEN_SPLITER + session_time + Literal.TOKEN_SPLITER + user_agent + Literal.TOKEN_SPLITER + login_uuid + Literal.TOKEN_SPLITER;
        return encdnc.TokenEncrypt(token);
    }

}
