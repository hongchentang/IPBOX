package com.hcis.ipr.monogo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/10/17
 **/
public interface MongoDao {

    /**
     * 根据id检索文档
     * @param db
     * @param table
     * @param id
     * @return
     */
    Map<String, Object> selectById(MongoDatabase db, String table, Object id);

    /**
     * 根据某个字段查询
     * @param db
     * @param table
     * @param field
     * @param id
     * @return
     */
    Map<String, Object> selectByx(MongoDatabase db, String table, String field, Object id);

    /**
     * 根据doc文档检索文档集合
     * @param db
     * @param table
     * @param doc
     * @return
     */
    List<Map<String, Object>> selectByDoc(MongoDatabase db, String table, BasicDBObject doc);

    /**
     * 查询所有数据
     * @param db
     * @param table
     * @return
     */
    List<Map<String, Object>> selectAll(MongoDatabase db, String table);

    /**
     * 遍历迭代器返回文档集合
     * @param iterable
     * @return
     */
    List<Document> findIterable(FindIterable<Document> iterable);

    /**
     * 插入数据
     * @param db
     * @param table
     * @param doc
     * @return
     */
    boolean insert(MongoDatabase db, String table, Document doc);

    /**
     * 批量插入
     * @param db
     * @param table
     * @param docs
     * @return
     */
    boolean insertBatch(MongoDatabase db, String table, List<Document> docs);

    /**
     * 删除文档
     * @param db
     * @param table
     * @param doc
     * @return
     */
    boolean delete(MongoDatabase db, String table, BasicDBObject doc);

    /**
     * 删除一个文档
     * @param db
     * @param table
     * @param doc
     * @return
     */
    boolean deleteOne(MongoDatabase db, String table, BasicDBObject doc);

    /**
     * 修改文档
     * @param db
     * @param table
     * @param oldDoc
     * @param newDoc
     * @return
     */
    boolean update(MongoDatabase db, String table, BasicDBObject oldDoc, BasicDBObject newDoc);

    /**
     * 修改单个文档
     * @param db
     * @param table
     * @param wehereDoc
     * @param updateDoc
     * @return
     */
    boolean updateOne(MongoDatabase db, String table, BasicDBObject wehereDoc, BasicDBObject updateDoc);

    /**
     * 创建集合
     * @param db
     * @param table
     */
    void createCol(MongoDatabase db, String table);

    /**
     * 删除集合
     * @param db
     * @param table
     */
    void dropCol(MongoDatabase db, String table);
}
