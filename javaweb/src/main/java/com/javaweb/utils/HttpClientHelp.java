package com.javaweb.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @author Derek.pan
 * 
 */
public class HttpClientHelp {

	private static Logger logger = LoggerFactory
			.getLogger(HttpClientHelp.class);

	private static final String KEEP_ALIVE = "Keep-Alive";
	private static final String CONNECTION = "Connection";
	private static final String SET_COOKIE = "set-cookie";
	private static final String TEXT_XML_CHARSET_UTF_8 = "text/xml; charset=utf-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String COOKIE = "Cookie";

	public static HttpResponse makeRequest(String url, String body) {

		if (url == null || body == null || url.length() <= 0) {
			logger.debug(" url is null or body is null");
			return null;
		}

		return executeHttpRequest(url, body);

		// return null;

	}

	/**
	 * 
	 * 
	 * @param url
	 * @param body
	 *            body 的格式
	 * @return
	 */

	private static HttpResponse executeHttpRequest(String url, String body) {
		logger.debug("request body" + body);
		HttpClient client = HttpClientFactory.getHttpClient();
		HttpResponse response = null;
		try {
			HttpEntity entity = new StringEntity(body);
			final HttpPost post = new HttpPost(url);
			post.addHeader(CONTENT_TYPE, TEXT_XML_CHARSET_UTF_8);
			post.addHeader(CONNECTION, KEEP_ALIVE);
			// todo 缺少了一个判断网络状态的
			post.setEntity(entity);

			response = client.execute(post);

			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

			} else {
				if (logger.isDebugEnabled()) {
					if (response == null) {
						logger.debug("response is null");
					} else {
						logger.debug("getStatusCode"
								+ String.valueOf(response.getStatusLine()
										.getStatusCode()));
					}
				}
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

}
