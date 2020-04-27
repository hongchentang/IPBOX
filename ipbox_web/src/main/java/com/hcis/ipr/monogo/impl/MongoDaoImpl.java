package com.hcis.ipr.monogo.impl;

import com.hcis.ipr.monogo.dao.MongoDao;
import com.hcis.ipr.monogo.utils.JsonStrToMap;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/10/17
 **/
public class MongoDaoImpl implements MongoDao {

    private static final Logger logger = LoggerFactory.getLogger(MongoDaoImpl.class);

    @Override
    public Map<String, Object> selectById(MongoDatabase db, String table, Object id) {
        MongoCollection<Document> collection = db.getCollection(table);
        BasicDBObject query = new BasicDBObject("_id", id);
        FindIterable<Document> iterable = collection.find(query);

        Map<String, Object> map = null;
        for (Document document : iterable) {
            map = JsonStrToMap.jsonStrToMap(document.toJson());
        }

        logger.debug("检索ID完毕，db：" + db.getName() + "，table：" + table + "，id：" + id);
        return map;
    }

    @Override
    public Map<String, Object> selectByx(MongoDatabase db, String table, String field, Object val) {
        MongoCollection<Document> collection = db.getCollection(table);
        BasicDBObject query = new BasicDBObject(field, val);
        FindIterable<Document> iterable = collection.find(query);
        Map<String, Object> map = null;
        for (Document document : iterable) {
            map = JsonStrToMap.jsonStrToMap(document.toJson());
        }

        logger.debug("检索ID完毕，db：" + db.getName() + "，table：" + table + "，"+ field + "：" + val);
        return map;
    }

    @Override
    public List<Map<String, Object>> selectByDoc(MongoDatabase db, String table, BasicDBObject doc) {
        MongoCollection<Document> collection = db.getCollection(table);
        FindIterable<Document> iterable = collection.find(doc);

        List<Map<String, Object>> list = new ArrayList<>();
        for(Document document : iterable){
            list.add(JsonStrToMap.jsonStrToMap(document.toJson()));
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> selectAll(MongoDatabase db, String table) {
        MongoCollection<Document> collection = db.getCollection(table);
        FindIterable<Document> iterable = collection.find();

        List<Map<String, Object>> list = new ArrayList<>();
        for (Document doc : iterable){
            list.add(JsonStrToMap.jsonStrToMap(doc.toJson()));
        }

        return list;
    }

    @Override
    public List<Document> findIterable(FindIterable<Document> iterable) {
        List<Document> list = new ArrayList<>();

        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            list.add(cursor.next());
        }
        cursor.close();

        return list;
    }

    @Override
    public boolean insert(MongoDatabase db, String table, Document doc) {
        MongoCollection<Document> collection = db.getCollection(table);
        collection.insertOne(doc);
        long count = collection.count();
        if(count > 0){
            logger.debug("插入数据成功");
            return true;
        }else {
            logger.error("插入数据失败");
            return false;
        }
    }

    @Override
    public boolean insertBatch(MongoDatabase db, String table, List<Document> docs) {
        MongoCollection<Document> collection = db.getCollection(table);
        collection.insertMany(docs);
        long count = collection.count();
        if(count > 0){
            logger.debug("插入数据成功");
            return true;
        }else {
            logger.error("插入数据失败");
            return false;
        }
    }

    @Override
    public boolean delete(MongoDatabase db, String table, BasicDBObject doc) {
        MongoCollection<Document> collection = db.getCollection(table);
        collection.deleteMany(doc);
        long count = collection.count();
        if(count > 0){
            logger.debug("删除数据成功");
            return true;
        }else {
            logger.error("删除数据失败");
            return false;
        }
    }

    @Override
    public boolean deleteOne(MongoDatabase db, String table, BasicDBObject doc) {
        MongoCollection<Document> collection = db.getCollection(table);
        collection.deleteOne(doc);
        long count = collection.count();
        if(count > 0){
            logger.debug("删除数据成功");
            return true;
        }else {
            logger.error("删除数据失败");
            return false;
        }
    }

    @Override
    public boolean update(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject newDoc) {
        MongoCollection collection = db.getCollection(table);
        UpdateResult result = collection.updateMany(whereDoc, new Document("$set", newDoc));
        long modifiedCount = result.getModifiedCount();

        if(modifiedCount > 0){
            logger.debug("修改数据成功");
            return true;
        }else {
            logger.error("修改数据失败");
            return false;
        }
    }

    @Override
    public boolean updateOne(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc) {
        MongoCollection collection = db.getCollection(table);
        UpdateResult result = collection.updateMany(whereDoc, new Document("$set", updateDoc));
        long modifiedCount = result.getModifiedCount();

        if(modifiedCount > 0){
            logger.debug("修改数据成功");
            return true;
        }else {
            logger.error("修改数据失败");
            return false;
        }
    }

    @Override
    public void createCol(MongoDatabase db, String table) {
        db.createCollection(table);
        logger.debug("集合创建成功");
    }

    @Override
    public void dropCol(MongoDatabase db, String table) {
        db.getCollection(table).drop();
        logger.debug("集合删除失败");
    }
}
