package com.storm.def;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StormTopologyConfig {

	private static final Logger log = LoggerFactory.getLogger(StormTopologyConfig.class);
	private static final StormTopologyConfig instance = new StormTopologyConfig();
	
	public static int workernum;

	public static int crawlerbolt_parallelismnum;

	public static String crawlerenginetopology;
 
	public static String redishost;
	
	public static String redisport;
	
	public static String nfs_nginx_server;
	
	public static String nfs_filepath;

	static {
		init();
		log.info("workernum:"+workernum);
		log.info("crawlerbolt_parallelismnum:"+crawlerbolt_parallelismnum);
		log.info("crawlerenginetopology:"+crawlerenginetopology);
		log.info("redishost:"+redishost);
		log.info("redisport:"+redisport);
		log.info("nfs_nginx_server:"+nfs_nginx_server);
		log.info("nfs_filepath:"+nfs_filepath);
	}
	
	private StormTopologyConfig() {
		init();
	}
	public static StormTopologyConfig getInstance() {
		return instance;
	}
	
	public static void init() {
		String propFileName = "topology.properties";
		Properties properties = new Properties();
		try {
			InputStream inputStream = StormTopologyConfig.class.getClassLoader().getResourceAsStream(propFileName);
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		workernum = Integer.parseInt(properties.getProperty("workernum"));
		crawlerbolt_parallelismnum = Integer.parseInt(properties.getProperty("crawlerbolt_parallelismnum"));
		crawlerenginetopology = properties.getProperty("crawlerenginetopology");
		redishost = properties.getProperty("redis.host");
		redisport = properties.getProperty("redis.port");
		nfs_nginx_server = properties.getProperty("nfs.nginx.server");
		nfs_filepath = properties.getProperty("nfs.filepath");
	}



	public static int getWorkernum() {
		return workernum;
	}



	public static void setWorkernum(int workernum) {
		StormTopologyConfig.workernum = workernum;
	}



	public static int getCrawlerbolt_parallelismnum() {
		return crawlerbolt_parallelismnum;
	}



	public static void setCrawlerbolt_parallelismnum(int crawlerbolt_parallelismnum) {
		StormTopologyConfig.crawlerbolt_parallelismnum = crawlerbolt_parallelismnum;
	}



	public static String getCrawlerenginetopology() {
		return crawlerenginetopology;
	}



	public static void setCrawlerenginetopology(String crawlerenginetopology) {
		StormTopologyConfig.crawlerenginetopology = crawlerenginetopology;
	}



	public static String getNfs_nginx_server() {
		return nfs_nginx_server;
	}



	public static void setNfs_nginx_server(String nfs_nginx_server) {
		StormTopologyConfig.nfs_nginx_server = nfs_nginx_server;
	}



	public static String getNfs_filepath() {
		return nfs_filepath;
	}



	public static void setNfs_filepath(String nfs_filepath) {
		StormTopologyConfig.nfs_filepath = nfs_filepath;
	}

}
