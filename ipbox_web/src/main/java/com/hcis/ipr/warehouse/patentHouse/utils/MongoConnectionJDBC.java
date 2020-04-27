package com.hcis.ipr.warehouse.patentHouse.utils;

import java.util.ArrayList;  
import java.util.List;

import com.hcis.ipanther.core.utils.AppConfig;
import com.mongodb.MongoClient;  
import com.mongodb.MongoCredential;  
import com.mongodb.ServerAddress;  
import com.mongodb.client.MongoDatabase;  
  
public class MongoConnectionJDBC {
	
	
	public static String getMongoPort() {
		return AppConfig.getProperty("mongodb","mongodb.port");
	}
	
	public static String getMongoHost() {
		return AppConfig.getProperty("mongodb","mongodb.host");
	}
	
	public static String getMongoHostPort() {
		return AppConfig.getProperty("mongodb","mongodb.hostport");
	}

	public static String getMongodbUser() {
		return AppConfig.getProperty("mongodb","mongodb.dbUser");
	}
	
	public static String getMongodbName() {
		return AppConfig.getProperty("mongodb","mongodb.dbname");
	}
	
	public static String getMongoPassword() {
		return AppConfig.getProperty("mongodb","mongodb.password");
	}
	/**
	 * 授权访问 
	 * @author 宗高金 20190915
	 * @return
	 */
	public static MongoDatabase connectMongoDBAuth(){ 
	    //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
	    //ServerAddress()两个参数分别为 服务器地址 和 端口
		String host = getMongoHost();
		int port = Integer.parseInt(getMongoPort());
		String password = getMongoPassword();
		String dbName = getMongodbName();
		String dbUser = getMongodbUser();
		
	    ServerAddress serverAddress = new ServerAddress(host,port);  
	    List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	    addrs.add(serverAddress); 
	      
	    //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
	    MongoCredential credential = MongoCredential.createScramSha1Credential(dbUser,dbName, password.toCharArray());  
	    List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	    credentials.add(credential);  
	      
	    //通过连接认证获取MongoDB连接  
	    MongoClient mongoClient = new MongoClient(addrs,credentials);  
	      
	    //连接到数据库  
	    MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);  
	    System.out.println("Connect to database successfully");  
        return mongoDatabase;
    }
	/**
	 * 非授权登入
	 * @author 宗高金 20190915
	 * @return
	 */
	public static MongoDatabase connectMongoDB(){ 
	    //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
	    //ServerAddress()两个参数分别为 服务器地址 和 端口
		String host = getMongoHost();
		int port = Integer.parseInt(getMongoPort());
		String dbName = getMongodbName();
		MongoClient mongoClient = new MongoClient( host , port );
	    MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);  
        return mongoDatabase;
    }

	//获取服务连接
	public static MongoClient getMongoClient(){  
        ServerAddress serverAddress = new ServerAddress("localhost",27017);  
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
        addrs.add(serverAddress);  
        MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());  
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
        credentials.add(credential);  
          
        //通过连接认证获取MongoDB连接  
        MongoClient mongoClient = new MongoClient(addrs,credentials);   
		return mongoClient;
	}   	
	
	//连接数据库
	public static MongoDatabase getMongoDatabase(MongoClient mongoClient){
		MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");  
		System.out.println("Connect to database successfully");  
		return mongoDatabase;
	}   	
    	
	//关闭数据库
	public static void closeMongoDatabase(MongoClient mongoClient){
		mongoClient.close();
	}
	
} 