package com.sun.learning.main;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class FirstPor extends AbstractVerticle {

	private List<JsonArray> list = new ArrayList<>();
	private List<Stu> stuList = new ArrayList<>();
	
	@Override
	public void start() throws Exception {

		JsonObject mySQLClientConfig = new JsonObject().put("host", "127.0.0.1")
					.put("port", 3306)
					.put("maxPoolSize", 60)
					.put("username", "root")
					.put("password", "123456")
					.put("database", "learning")
					.put("charset", "UTF-8");
		AsyncSQLClient mySQLClient = MySQLClient.createNonShared(vertx, mySQLClientConfig);

		mySQLClient.getConnection(res -> {
			if (res.succeeded()) {
				System.out.println("success");
				SQLConnection connection = res.result();
				connection.query("select * from stu", result -> {
					  if (result.succeeded()) {
					    ResultSet resultSet = result.result();
					    list = resultSet.getResults();
					    for (int i=0;i<list.size();i++) {
					    	JsonArray ja = list.get(i);
					    	System.out.println(ja.getList());
					    	Stu stu = new Stu();
					    	stu.setId(ja.getList().get(0).toString());
					    	stu.setName(ja.getList().get(1).toString());
					    	stu.setGender(ja.getList().get(2).toString());
					    	stuList.add(stu);
					    }
					  } else {
					  }
				});
				
				connection.execute("INSERT INTO stu (name, gender) VALUES ('张三', '1')", result->{
					if (result.succeeded()) {
					  } else {
					  }
				});
			} else {
				System.out.println("failure");
			}
		});
		
		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);

		// 获取列表  get
		router.get("/list").handler(this::getAll);
		
		// 新增 post
		router.route("/stu").handler(BodyHandler.create());
		router.post("/stu").handler(this::addOne);
		router.delete("/stu/:id").handler(this::deleStu);
		
		router.route("/add").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain;charset=utf-8");
			//将list转为json
			response.end("add");
		});

		server.requestHandler(router::accept).listen(8080);
	}
	
	//获取列表数据
	public void getAll(RoutingContext rc) {
		rc.response().putHeader("content-type", "text/plain;charset=utf-8")
			.end(Json.encode(stuList));
	}
	
	//增加
	public void addOne(RoutingContext rc) {
		//获取body里面的值
		final Stu stu = Json.decodeValue(rc.getBodyAsString(),Stu.class);
		//调用mysql保存
		
		rc.response()
	      .setStatusCode(201)
	      .putHeader("content-type", "text/plain;charset=utf-8")
	      .end(Json.encodePrettily(stu));
	}
	
	//修改
	public void updateStu(RoutingContext rc) {
		
	}
	
	//删除
	public void deleStu(RoutingContext rc) {
		//获取要删除的ID
		String id = rc.request().getParam("id");
		if (id == null) {
			//返回400
			rc.response().setStatusCode(400).end();
		}
		//调用mysql删除
		rc.response().setStatusCode(204).end();
	}
	
}
