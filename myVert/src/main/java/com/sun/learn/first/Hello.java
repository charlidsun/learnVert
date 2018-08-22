package com.sun.learn.first;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.Router;
import io.vertx.reactivex.ext.sql.SQLConnection;




/**
 * 功能：
 * 说明：
 * @author 孙荆阁:
 * @Date 2018年7月31日 下午1:41:14
 */
public class Hello {
	
	public static void main(String[] args) {
		
		//createServerByVert();
		createServerByRoute();
		
	}
	
	private static void threeRout(){
		
	}
	
	
	private static void createServerByRoute() {
		Vertx vertx  = Vertx.vertx();
		HttpServer httpServer = vertx.createHttpServer();
		JsonObject mySQLClientConfig = new JsonObject()
				.put("host", "127.0.0.1")
				.put("database", "qxgl")
				.put("username", "root")
				.put("password", "123456");
		SQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig,"33");
		mySQLClient.getConnection(res -> {
			  if (res.succeeded()) {
				System.err.println("链接失败");
//			    SQLConnection connection = (SQLConnection) res.result();
//			    connection.query("select * from stu", ar->{
//			    	ResultSet result = ar.result();
//			    	System.err.println(result.toString());
//			    });

			  } else {
				  System.err.println("链接失败");
			  }
			});
		Router route = Router.router(vertx);
		route.route().handler(routingContext->{
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			response.end("Hello World");
		});
		httpServer.requestHandler(route::accept).listen(9999);
		
		
	}

	//直接使用server穿件
	public static void createServerByVert(){
		HttpServer httpServer = Vertx.vertx().createHttpServer();
		httpServer.requestHandler(request->{
			HttpServerResponse response = request.response();
			response.putHeader("content-type", "text/plain");
			response.end("Hello World!");
		});
		
		httpServer.listen(9999);
	}

}
