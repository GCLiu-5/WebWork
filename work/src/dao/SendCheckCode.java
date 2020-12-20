package dao;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendCheckCode {
	private static final String SERVER_URL="https://api.netease.im/sms/sendcode.action";
	private static final String APP_KEY="67adb1fd66bb9e485ae2be20f19ae5eb";
	private static final String APP_SECRET="d1d8df7b380c";
	private static final String NONCE="123456";
	private static final String CODELEN="6";

	public static String sendCode(String mobile,String templateId) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(SERVER_URL);
		String curTime = String.valueOf((new Date()).getTime() / 1000L);

		String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
		


		httpPost.addHeader("AppKey", APP_KEY);
		httpPost.addHeader("Nonce", NONCE);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("templateid", templateId));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("codeLen", CODELEN));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));


		HttpResponse response = httpClient.execute(httpPost);

		return EntityUtils.toString(response.getEntity(), "utf-8");
	}
}

