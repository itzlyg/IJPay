package com.ijpay.core.kit;

import com.ijpay.core.IJPayHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>Http 工具类</p>
 *
 * @author Javen
 */
public class HttpKit {

	/**
	 * get 请求
	 *
	 * @param url      请求url
	 * @param paramMap 请求参数
	 * @return {@link String} 请求返回的结果
	 */
	public static String get(String url, Map<String, Object> paramMap) {
		return null;
	}

	/**
	 * get 请求
	 *
	 * @param url      请求url
	 * @param paramMap 请求参数
	 * @param headers  请求头
	 * @return {@link IJPayHttpResponse} 请求返回的结果
	 */
	public static IJPayHttpResponse get(String url, Map<String, Object> paramMap, Map<String, String> headers) {
		IJPayHttpResponse response = new IJPayHttpResponse();
//		HttpResponse httpResponse = getToResponse(url, paramMap, headers);
//		response.setBody(httpResponse.body());
//		response.setStatus(httpResponse.getStatus());
//		response.setHeaders(httpResponse.headers());
		return response;
	}

	/**
	 * post 请求
	 *
	 * @param url  请求url
	 * @param data 请求参数
	 * @return {@link String} 请求返回的结果
	 */
	public static String post(String url, String data) {
		return null;
	}

	/**
	 * post 请求
	 *
	 * @param url     请求url
	 * @param paramMap    请求参数
	 * @param headers 请求头
	 * @return {@link IJPayHttpResponse}  请求返回的结果
	 */
	public static IJPayHttpResponse post(String url,  Map<String, Object> paramMap, Map<String, String> headers) {
		IJPayHttpResponse response = new IJPayHttpResponse();
//		HttpResponse httpResponse = postToResponse(url, headers, data);
//		response.setBody(httpResponse.body());
//		response.setStatus(httpResponse.getStatus());
//		response.setHeaders(httpResponse.headers());
		return response;
	}

	/**
	 * post 请求
	 *
	 * @param url     请求url
	 * @param data    请求参数
	 * @param headers 请求头
	 * @return {@link IJPayHttpResponse}  请求返回的结果
	 */
	public static IJPayHttpResponse post(String url, String data, Map<String, String> headers) {
		IJPayHttpResponse response = new IJPayHttpResponse();
//		HttpResponse httpResponse = postToResponse(url, headers, data);
//		response.setBody(httpResponse.body());
//		response.setStatus(httpResponse.getStatus());
//		response.setHeaders(httpResponse.headers());
		return response;
	}

	/**
	 * post 请求
	 *
	 * @param url      请求url
	 * @param data     请求参数
	 * @param certPath 证书路径
	 * @param certPass 证书密码
	 * @return {@link String} 请求返回的结果
	 */
	public static String post(String url, String data, String certPath, String certPass) {
		return null;
	}

	/**
	 * post 请求
	 *
	 * @param url      请求url
	 * @param data     请求参数
	 * @param certFile 证书文件输入流
	 * @param certPass 证书密码
	 * @return {@link String} 请求返回的结果
	 */
	public static String post(String url, String data, InputStream certFile, String certPass) {
		return null;
	}
	public static String post(String url, String data, String certPath, String certPass, String protocol) {
		return null;
	}
    public static String readData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder result = new StringBuilder();
            br = request.getReader();
            for (String line; (line = br.readLine()) != null; ) {
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将同步通知的参数转化为Map
     *
     * @param request {@link HttpServletRequest}
     * @return 转化后的 Map
     */
    public static Map<String, String> toMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
