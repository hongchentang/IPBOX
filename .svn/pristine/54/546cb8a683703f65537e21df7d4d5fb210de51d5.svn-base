package com.hcis.ipr.monogo.utils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhw
 * @date 2019/10/17
 **/
public class MongoHelper {

    private static final Logger logger = LoggerFactory.getLogger(MongoHelper.class);

    private final String dbName = "mongodb.dbname";
    private final String hostAndPort = "mongodb.hostport";

    public MongoHelper() {
    }

    public MongoClient getMongoClient(){
        MongoClient client = null;

        try {
            client = new MongoClient(hostAndPort);
            logger.debug("Connection to mongodb Success!");
        }catch (Exception e){
            logger.error(e.getClass().getName() + ":" + e.getMessage());
        }

        return client;
    }

    public MongoDatabase getMongoDatabase(MongoClient client){
        MongoDatabase database = null;
        try {
            if(client != null){
                database = client.getDatabase(dbName);
                logger.debug("Connect to DataBase Success!");
            }else {
                logger.error("请先初始化MongoClient!");
            }
        }catch (Exception e){
            logger.error(e.getClass().getName() + ":" + e.getMessage());
        }

        return database;
    }

    public MongoDatabase getMongoDatabase(){
        MongoDatabase database = null;
        try {
            database = getMongoDatabase(getMongoClient());
        }catch (Exception e){
            logger.error(e.getClass().getName() + ":" + e.getMessage());
        }
        return database;
    }

    public void closeMongoClient(MongoDatabase database, MongoClient client){
        if(database != null){
            database = null;
        }
        if(client != null){
            client.close();
        }

        logger.debug("Close MongoClient Success!");
    }
}
