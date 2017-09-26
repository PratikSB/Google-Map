package com.map.utils;

import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RestClient {

	private static final Logger logger = Logger.getLogger(RestClient.class);
	  
	public final String get(String uri, Map<String, String> map) throws Exception {
		logger.debug("RestClient.get.uri:" + uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(uri);
            if (null != map) {
	            for (Map.Entry<String, String> entry: map.entrySet()) {
	            	httpget.setHeader(entry.getKey(), entry.getValue());
	            	logger.debug("RestClient.get.header:" + entry.getKey() + ":" + entry.getValue());
	            }
            }
            return httpclient.execute(httpget, new MapResponseHandler());
        } finally {
            httpclient.close();
        }
    }
}
