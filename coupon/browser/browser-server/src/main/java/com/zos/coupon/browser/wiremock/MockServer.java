package com.zos.coupon.browser.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;

/**
 * @author 01Studio
 *
 */
public class MockServer {

	/**
	 * @param args 启动参数
	 * @throws IOException 输入输出异常
	 */
	public static void main(String[] args) throws IOException {
		configureFor("115.28.6.55", 8080);
		removeAllMappings();

		mockGet("/order/1", "01");
		mockGet("/order/2", "02");
	}
	
	private static String getContent(String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/get/" + file + ".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		return content;
	}
	
	private static HttpHeaders getHttpHeaders(String... keys) {
		HttpHeaders httpHeaders = HttpHeaders.noHeaders();
		for (String key : keys) {
			if ("Content-Type".equals(key)) {
				HttpHeader contentType = new HttpHeader("Content-Type", "application/json;charset=UTF-8");
				httpHeaders.plus(contentType);
			} else if ("Authorization".equals(key)) {
				HttpHeader authorization = new HttpHeader("Authorization", "bearer 7c2f93e1-1356-48e8-bd10-5a3dbfc2495f");
				httpHeaders.plus(authorization);
			}
		}
		return httpHeaders;
	}

	private static void mockGet(String url, String file) throws IOException {
		stubFor(get(urlPathEqualTo(url))
				.withHeader("Authorization", new EqualToPattern("bearer 7c2f93e1-1356-48e8-bd10-5a3dbfc2495f"))
				.withHeader("Content-Type", new EqualToPattern("application/json;charset=UTF-8"))
				//.withQueryParam(key, new )
				.willReturn(
						aResponse()
						.withBody(getContent(file))
						.withHeaders(getHttpHeaders("Content-Type"))
						.withStatus(200)));
	}
	
	

}
