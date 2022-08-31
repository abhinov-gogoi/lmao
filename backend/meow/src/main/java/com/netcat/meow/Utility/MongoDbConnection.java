package com.netcat.meow.Utility;

import com.mongodb.*;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDbConnection {
	/**
	 * Hold the class instance
	 */
	private static MongoDbConnection instance;
	/**
	 * Dynamic DB configuration
	 */
	public static MongoClient MONGOCLIENT=mongoClient();
	/**
	 * Hold the mongo_template instance
	 */
	public static MongoTemplate mongo_template;
	/**
	 * 
	 * @return
	 */
	public static MongoDbConnection getInstance() {
		/**
		 * Check for the Null
		 */
		if(instance==null) {
			instance=new MongoDbConnection();
		}
		return instance;
	}
	/**
	 * Get mongo client
	 * @return
	 */
	public static MongoClient mongoClient() {
    	try {
			if(Utility.isLocalMango) {
//				return new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
				MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
				MongoClientOptions options = builder.connectionsPerHost(Utility.mongoDb_pool_count).build();
				ServerAddress serverAddress=new ServerAddress(Utility.local_mongoDb_ip, Utility.local_mongoDb_port);
				MongoCredential credential = MongoCredential.createScramSha1Credential(Utility.local_mongoDb_usr_name, "admin",Utility.local_mongoDb_pwd.toCharArray());
				return new MongoClient(serverAddress, options);
			} else {
				return new MongoClient(new MongoClientURI("mongodb+srv://groot:root@meow.nqrji.mongodb.net/meow"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Mongotemplate for frtu
	 * @return
	 */
	public MongoTemplate mongoTemplate() {
    	try {
    		if(mongo_template==null) {
    			mongo_template=new MongoTemplate(mongoClient(), Utility.mongoDb_Databse);
    		}
    		return mongo_template;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Mongotemplate for any db name
	 * @return
	 */
	public MongoTemplate mongoTemplate(String db_name) {
		try {
			return new MongoTemplate( mongoClient(), db_name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
