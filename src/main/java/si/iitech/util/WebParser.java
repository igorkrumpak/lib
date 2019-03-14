package si.iitech.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import si.iitech.exception.impl.WebParserException;

public class WebParser {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2";

	public static WebParser createInstance() {
		return new WebParser();
	}
	
	private WebParser() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			throw new RuntimeException(e);
		}

		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}
	
	public Document readWebSite(String url) throws WebParserException {
		return readWebSite(url, false);
	}

	public Document readWebSite(String url, boolean useUserAgent) throws WebParserException {
		Document doc;
		Connection conn;
		try {
			if (useUserAgent) {
				conn = Jsoup.connect(url).userAgent(USER_AGENT);
				doc = Jsoup.parse(new String(conn.execute().bodyAsBytes(), StandardCharsets.UTF_8));
			} else {
				conn = Jsoup.connect(url);
				doc = Jsoup.parse(new String(conn.execute().bodyAsBytes(), StandardCharsets.UTF_8));
			}
			return doc;
		} catch (IllegalArgumentException e) {
			throw new WebParserException(e);
		} catch (MalformedURLException e) {
			throw new WebParserException(e);
		} catch (UnsupportedEncodingException e) {
			throw new WebParserException(e);
		} catch (IOException e) {
			throw new WebParserException(e);
		}
	}
}
