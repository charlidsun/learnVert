package com.sun.learning.main;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Learning extends AbstractVerticle {	

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(FirstPor.class.getName());
	}
}
