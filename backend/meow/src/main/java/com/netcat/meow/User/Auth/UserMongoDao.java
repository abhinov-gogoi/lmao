package com.netcat.meow.User.Auth;

import com.mongodb.client.result.UpdateResult;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.MongoDbConnection;
import com.netcat.meow.Utility.Utility;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.swing.*;

public class UserMongoDao {
    /**
     * Hold the UserMongoDao
     */
    private static UserMongoDao instance = new UserMongoDao();
    /**
     * MONGOTEMPLE for MONGO connection
     */
    private MongoTemplate mongoTemplate;

    /**
     * @return
     */
    public static UserMongoDao getInstance() {
        /**
         * Check for the Null
         */
        if (instance == null) {
            instance = new UserMongoDao();
        }
        return instance;
    }

    /**
     * Call constructor
     */
    public UserMongoDao() {
        /**
         * Get mongotemplate connection
         */
        mongoTemplate = MongoDbConnection.getInstance().mongoTemplate();
    }

    /**
     * check if a user id is available for signup
     * @param login_id
     * @return
     */
    public boolean chkIdAvailable(String login_id) {
        try {
            /**
             * Filter document
             */
            Document filter_doc = new Document().append(Literal.login_id, login_id);
            /**
             * Search/Projection document
             */
            Document search_doc = new Document(Literal._id, Literal.ONE);
            /**
             * Frame the query
             */
            Query qry = new BasicQuery(filter_doc, search_doc);
            /**
             * Fire the query
             */
            Document result = mongoTemplate.findOne(qry, Document.class, Utility.col_login);
            /**
             * check the result
             */
            if (result != null && !result.isEmpty()) {
                /**
                 * means a user is found with this login id, Its already taken
                 */
                return Literal.FALSE;
            } else {
                /**
                 * return true, this id is available
                 */
                return Literal.TRUE;
            }
        } catch (Exception e) {
            return Literal.FALSE;
        }
    }

    /**
     * get user login doc by login_id
     * @param login_id
     * @return
     */
    public Document getUserLoginDetails(String login_id) {
        return getUserLoginDetails(login_id, null);
    }

    /**
     * get user login doc by login_id and password
     * @param login_id
     * @param password
     * @return
     */
    public Document getUserLoginDetails(String login_id, String password) {
        try {
            /**
             * Filter doc
             */
            Document filter_doc = new Document();
            /**
             * check if password is not provided
             */
            if(password == null) {
                filter_doc.append(Literal.login_id, login_id);
            } else {
                filter_doc.append(Literal.login_id, login_id).append(Literal.password, password);
            }
            Query qry = new BasicQuery(filter_doc);
            return mongoTemplate.findOne(qry, Document.class, Utility.col_login);
        } catch (Exception e) {
            return Literal.EMPTY_DOCUMENT;
        }
    }

    /**
     * @param updt_login
     * @return
     */
    public boolean updtLoginUsr(String login_id, Update updt_login) {
        try {
            Document filter_doc = new Document().append(Literal.login_id, login_id);
            Query qry = new BasicQuery(filter_doc);
            /**
             * Update the login data
             */
            UpdateResult upd = mongoTemplate.updateFirst(qry, updt_login, Utility.col_login);
            return upd.wasAcknowledged();
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    /**
     * @return
     */
    public boolean insertLoginData(Document login_doc) {
        try {
            /**
             * Update the login data
             */
            mongoTemplate.insert(login_doc, Utility.col_login);
            return Literal.TRUE;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    public boolean updateLoginData(Update update, Document filter_doc) {
        try {
            /**
             * Update the login data
             */
            mongoTemplate.updateFirst(new BasicQuery(filter_doc), update, Utility.col_login);
            return Literal.TRUE;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    public boolean uploadProfilePic(String image, String login_id) {
        Document filter_doc = new Document().append(Literal.login_id, login_id);
        Update update = new Update().set(Literal.avatar, image);
        UpdateResult updateResult = mongoTemplate.updateFirst(new BasicQuery(filter_doc), update, Utility.col_login);
        return updateResult.wasAcknowledged();
    }
}
