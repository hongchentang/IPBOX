package com.hcis.ipr.warehouse.patentHouse.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.warehouse.patentHouse.entity.PatentMongo;
import com.hcis.ipr.warehouse.patentHouse.utils.MongoConnectionJDBC;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("patentMongoDao")
public class PatentMongoDao {

	/**
	 * 创建集合
	 * 
	 * @author 宗高金 20190529
	 */
	public void createCollection() {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		System.out.println("Connect to database successfully");
		mongoDatabase.createCollection("test");
	}

	public void insertDocument() {
		MongoCollection<Document> collection = MongoConnectionJDBC.connectMongoDB().getCollection("patent");
		try {
			System.out.println("集合 test 选择成功");
			// 插入文档
			/**
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document>
			 * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>)
			 * 插入单个文档可以用 mongoCollection.insertOne(Document)
			 */
			Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100)
					.append("by", "Fly");
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 * 文档查询
	 * 
	 * @author 宗高金
	 */
	public void searchDocument() {
		MongoCollection<Document> collection = MongoConnectionJDBC.connectMongoDB().getCollection("test");
		try {
			// 检索所有文档
			/**
			 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
			 * 通过游标遍历检索出的文档集合
			 */
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 * 更新文档内容
	 * 
	 * @author Administrator
	 */
	public void updateDocument() {
		MongoCollection<Document> collection = MongoConnectionJDBC.connectMongoDB().getCollection("test");
		try {
			collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
			// 检索查看结果
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 * 删除文档内容
	 * 
	 * @author Administrator
	 */
	public void deleteDocument() {
		MongoCollection<Document> collection = MongoConnectionJDBC.connectMongoDB().getCollection("test");
		try {
			collection.deleteOne(Filters.eq("likes", 200));
			// 删除所有符合条件的文档
			collection.deleteMany(Filters.eq("likes", 200));
			// 检索查看结果
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public List<PatentMongo> selectBySearchParam(SearchParam searchParam) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		MongoCollection<Document> collection = mongoDatabase.getCollection("patent");
		List<PatentMongo> result = new ArrayList<PatentMongo>();
		try {
			// 检索所有文档
			/**
			 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
			 * 通过游标遍历检索出的文档集合
			 */
			FindIterable<Document> findIterable = collection.find().limit(10);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());

				final String json = mongoCursor.next().toJson();
				ObjectMapper mapper = new ObjectMapper();
				PatentMongo token = new PatentMongo();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				token = mapper.readValue(json, PatentMongo.class);
				result.add(token);
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return result;
	}

	public Map<String, Object> selectJsonSearchParam(int pageNumber, int pageSize, SearchParam searchParam) {

		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		MongoCollection<Document> collection = mongoDatabase.getCollection("patent");
		JSONArray JsonArray = new JSONArray();
		Map<String, Object> map = new HashMap<>();
		int total = 0;
		/**
		 * 计算该集合的总记录数据 使用
		 */
		total = (int) collection.count();
		/**
		 * 检索所有文档 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
		 * 通过游标遍历检索出的文档集合
		 */
		Bson filters = null;
		MongoCursor<Document> mongoCursor = null;
		Document document = new Document();
		if (searchParam.getParamMap().get("title").toString() != null
				&& !"".equals(searchParam.getParamMap().get("title").toString())) {
			filters = Filters.eq("title.original", searchParam.getParamMap().get("title").toString());
		}
		if (searchParam.getParamMap().get("application_number").toString() != null
				&& !"".equals(searchParam.getParamMap().get("application_number").toString())) {
			Pattern pattern = Pattern.compile("^" + searchParam.getParamMap().get("application_number").toString() + ".*$", Pattern.CASE_INSENSITIVE);
			filters = Filters.regex("application_number", pattern);
		}
		if (filters != null) {
			FindIterable<Document> findIterable = collection.find(filters).skip((pageNumber - 1) * pageSize)
					.limit(pageSize);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					// System.out.println(mongoCursor.next());
					final String json = mongoCursor.next().toJson();
					// JSONObject Json = new JSONObject();
					// Json.put("Patent", json);//JSONObject对象中添加键值对
					JsonArray.add(json);// 将JSONObject对象添加到Json数组中
				}
			} finally {
				mongoCursor.close();
			}
		} else {
			FindIterable<Document> findIterable = collection.find().skip(pageNumber * pageSize - 1).limit(pageSize);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					JsonArray.add(json);// 将JSONObject对象添加到Json数组中
				}
			} finally {
				mongoCursor.close();
			}
		}
		map.put("total", total);
		map.put("rows", JsonArray);
		return map;
	}

	public JSONObject selectByPatentId(SearchParam searchParam) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		/**
		 * 检索所有文档 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
		 * 通过游标遍历检索出的文档集合 4. searchParam
		 * 设置type参数,代表不同的集合，分别为1.patent;2.claim;3.decrible;4.history;5.photo;6.legal
		 */
		System.out.println(searchParam.getParamMap().get("type").toString());
		MongoCursor<Document> mongoCursor = null;
		JSONObject obj = new JSONObject();
		if (searchParam.getParamMap().get("type").toString() == "1") {
			MongoCollection<Document> collection = mongoDatabase.getCollection("patent");
			Bson filters = Filters.eq("_id", searchParam.getParamMap().get("id").toString());
			FindIterable<Document> findIterable = collection.find(filters);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					// JsonArray.add(json);//将JSONObject对象添加到Json数组中
					obj = new JSONObject().fromObject(json);
				}
			} finally {
				mongoCursor.close();
			}
		} else if (searchParam.getParamMap().get("type").toString() == "2") {
			MongoCollection<Document> collection = mongoDatabase.getCollection("claim");
			Bson filters = Filters.eq("_id", searchParam.getParamMap().get("id").toString());
			FindIterable<Document> findIterable = collection.find(filters);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					// JsonArray.add(json);//将JSONObject对象添加到Json数组中
					obj = new JSONObject().fromObject(json);
				}
			} finally {
				mongoCursor.close();
			}
		} else if (searchParam.getParamMap().get("type").toString() == "3") {
			MongoCollection<Document> collection = mongoDatabase.getCollection("description");
			Bson filters = Filters.eq("_id", searchParam.getParamMap().get("id").toString());
			FindIterable<Document> findIterable = collection.find(filters);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					// JsonArray.add(json);//将JSONObject对象添加到Json数组中
					obj = new JSONObject().fromObject(json);
				}
			} finally {
				mongoCursor.close();
			}
		} else if (searchParam.getParamMap().get("type").toString() == "4") {
			MongoCollection<Document> collection = mongoDatabase.getCollection("history");
			Bson filters = Filters.eq("_id", searchParam.getParamMap().get("id").toString());
			FindIterable<Document> findIterable = collection.find(filters);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					// JsonArray.add(json);//将JSONObject对象添加到Json数组中
					obj = new JSONObject().fromObject(json);
				}
			} finally {
				mongoCursor.close();
			}
		} else if (searchParam.getParamMap().get("type").toString() == "5") {
			MongoCollection<Document> collection = mongoDatabase.getCollection("photo");
			Bson filters = Filters.eq("publication_id", searchParam.getParamMap().get("id").toString());
			FindIterable<Document> findIterable = collection.find(filters);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					// JsonArray.add(json);//将JSONObject对象添加到Json数组中
					obj = new JSONObject().fromObject(json);
				}
			} finally {
				mongoCursor.close();
			}
		} else {
			MongoCollection<Document> collection = mongoDatabase.getCollection("legal");
			Bson filters = Filters.eq("_id", searchParam.getParamMap().get("id").toString());
			FindIterable<Document> findIterable = collection.find(filters);
			try {
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					final String json = mongoCursor.next().toJson();
					// JsonArray.add(json);//将JSONObject对象添加到Json数组中
					obj = new JSONObject().fromObject(json);
				}
			} finally {
				mongoCursor.close();
			}
		}
		return obj;
	}

	public void insertMany(String type, List<Document> documents) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		if (type.equals("1")) {
			MongoCollection<Document> collection = mongoDatabase.getCollection("patent");
			collection.insertMany(documents);
		} else if (type.equals("2")) {
			MongoCollection<Document> collection = mongoDatabase.getCollection("claim");
			collection.insertMany(documents);
		} else if (type.equals("3")) {
			MongoCollection<Document> collection = mongoDatabase.getCollection("history");
			collection.insertMany(documents);
		} else if (type.equals("4")) {
			MongoCollection<Document> collection = mongoDatabase.getCollection("photo");
			collection.insertMany(documents);
		} else if (type.equals("5")) {
			MongoCollection<Document> collection = mongoDatabase.getCollection("legal");
			collection.insertMany(documents);
		} else if (type.equals("6")) {
			MongoCollection<Document> collection = mongoDatabase.getCollection("description");
			collection.insertMany(documents);
		}

	}

	public JSONObject selectByPatentByAppNumber(String appNumber) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		MongoCursor<Document> mongoCursor = null;
		JSONObject obj = new JSONObject();
		MongoCollection<Document> collection = mongoDatabase.getCollection("patent");
		Bson filters = Filters.eq("application_number", appNumber);
		FindIterable<Document> findIterable = collection.find(filters);
		// 基础信息
		try {
			mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				final String json = mongoCursor.next().toJson();
				obj = new JSONObject().fromObject(json);
			}
		} finally {
			mongoCursor.close();
		}
		return obj;
	}

	public List<Document> selectByPatentIds(List<String> mongoPatentIdList) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		MongoCursor<Document> mongoCursor = null;
		MongoCollection<Document> collection = mongoDatabase.getCollection("legal");
		Bson filters = Filters.in("_id", mongoPatentIdList);
		FindIterable<Document> findIterable = collection.find(filters);
		// 基础信息
		List<Document> list = new ArrayList<>();
		try {
			mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				list.add(mongoCursor.next());
			}
		} finally {
			mongoCursor.close();
		}

		return list;
	}

	public Document getLegalById(String id) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		MongoCursor<org.bson.Document> mongoCursor = null;
		MongoCollection<org.bson.Document> collection = mongoDatabase.getCollection("legal");
		Bson filters = Filters.eq("_id", id);
		FindIterable<org.bson.Document> findIterable = collection.find(filters);
		// 基础信息
		Document document = null;
		try {
			mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				document = mongoCursor.next();
			}
		} finally {
			mongoCursor.close();
		}

		return document;
	}

	public Document getPatentByAppNumber(String appNumber) {
		MongoDatabase mongoDatabase = MongoConnectionJDBC.connectMongoDB();
		MongoCursor<org.bson.Document> mongoCursor = null;
		MongoCollection<org.bson.Document> collection = mongoDatabase.getCollection("patent");
		Bson filters = Filters.eq("application_number", appNumber);
		FindIterable<org.bson.Document> findIterable = collection.find(filters);
		// 基础信息
		Document document = null;
		try {
			mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				document = mongoCursor.next();
			}
		} finally {
			mongoCursor.close();
		}

		return document;
	}
}