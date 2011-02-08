package com.thoughtworks.videorental.acceptance.context;

import com.thoughtworks.jetty.JettyServer;

import net.sf.sahi.client.Browser;

public class ServerIsRunning {
	private static final int CURRENT_ITERATION = 1;

	private static final String WEBAPP_PATH = System.getProperty("webapp.path", "../src/main/webapp");

	private JettyServer jettyServer;

	public ServerIsRunning(Browser browser) {
	}

	public void setUp() throws Exception {
		System.setProperty("current.iteration", String.valueOf(CURRENT_ITERATION));
		jettyServer = new JettyServer(WEBAPP_PATH, 8081, "/");
		jettyServer.start();
	}

	public void tearDown() throws Exception {
		jettyServer.stop();
	}

}
