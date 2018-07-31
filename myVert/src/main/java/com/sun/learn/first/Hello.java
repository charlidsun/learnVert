package com.sun.learn.first;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;




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
		HttpServer httpServer = Vertx.vertx().createHttpServer();
		Router route = Router.router(Vertx.vertx());
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
