package com.netcat.meow.Dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.MongoDbConnection;
import com.netcat.meow.Utility.Utility;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author ABHINOV
 */
public class MainMongoDao {

    private static MainMongoDao instance;
    private final MongoTemplate mongoTemplate;

    public MainMongoDao() {
        mongoTemplate = MongoDbConnection.getInstance().mongoTemplate();
    }

    public static MainMongoDao getInstance() {
        if (instance == null) {
            instance = new MainMongoDao();
        }
        return instance;
    }


    public Document getLoginDataById(String ref_id) {
        try {
            return mongoTemplate.findOne(new BasicQuery(new Document(Literal._id, ref_id)), Document.class, Utility.col_master);
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return new Document();
        }
    }

    public boolean updtLoginUsr(String login_id, Update updt_login) {
        try {
            UpdateResult upd = mongoTemplate.updateFirst(new Query(Criteria.where(Literal._id).is(login_id)), updt_login, Utility.col_master);
            return upd.wasAcknowledged();
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }


    public long count_qry(String db_name, String coll_name, String srch_qry) {
        try {
            MongoDatabase db = MongoDbConnection.MONGOCLIENT.getDatabase(db_name);
            return db.getCollection(coll_name).count(BasicDBObject.parse(srch_qry));
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return -1L;
        }
    }

    public List<String> distinct_qry(String db_name, String coll_name, String srch_qry) {
        try {
            MongoDatabase db = MongoDbConnection.MONGOCLIENT.getDatabase(db_name);
            List<String> str_list = new ArrayList<String>();
            for (String doc : db.getCollection(coll_name).distinct(srch_qry.substring(1, srch_qry.length() - 1), String.class)) {
                str_list.add(doc);
            }
            return str_list;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return null;
        }
    }

    public List<String> distinct_qryWidFltr(String db_name, String coll_name, String srch_qry, String filter_qry) {
        try {
            MongoDatabase db = MongoDbConnection.MONGOCLIENT.getDatabase(db_name);
            List<String> str_list = new ArrayList<String>();
            for (String doc : db.getCollection(coll_name).distinct(srch_qry.substring(1, srch_qry.length() - 1), BasicDBObject.parse(filter_qry), String.class)) {
                str_list.add(doc);
            }
            return str_list;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return null;
        }
    }

    public BasicDBList find_qry(String db_name, String coll_name, String srch_qry, String target_qry, int count, int r_count) {
        try {
            MongoDatabase db = MongoDbConnection.MONGOCLIENT.getDatabase(db_name);
            BasicDBList list = new BasicDBList();
            for (Document doc : db.getCollection(coll_name).find(BasicDBObject.parse(srch_qry)).projection(BasicDBObject.parse(target_qry)).skip(count).limit(r_count)) {
                list.add(doc);
            }
            return list;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
        }
        return null;
    }

    public Set<String> getAllCollections(String db_name) {
        try {
            MongoTemplate mongoTemplate = MongoDbConnection.getInstance().mongoTemplate(db_name);
            return mongoTemplate.getCollectionNames();
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return null;
        }
    }

    public Document findOne(Document filter_doc, Document search_doc, String collection) {
        try {
            Query query;
            if(search_doc == null || search_doc.isEmpty()) {
                query = new BasicQuery(filter_doc);
            } else {
                query = new BasicQuery(filter_doc, search_doc);
            }
            Document user_doc =  mongoTemplate.findOne(query, Document.class, collection);
            return user_doc;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return new Document();
        }
    }
}
