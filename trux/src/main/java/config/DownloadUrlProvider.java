package config;

import java.io.InputStream;
import java.util.Properties;

public class DownloadUrlProvider {
	private DownloadUrlProvider() {
	}

	public static String downloadAppFile;
	public static String redirectUrl;

	static {
		try {
			InputStream in = DownloadUrlProvider.class.getResourceAsStream("/config/DownloadConfig.properties");
			Properties prop = new Properties();
			prop.load(in);
			downloadAppFile = prop.getProperty("downloadAppFile");
			redirectUrl = prop.getProperty("redirectUrl");
			if (redirectUrl == null) {
				redirectUrl = "http://truxapp.com";
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized String getDownloadAppFile() {
		return downloadAppFile;
	}

	public static void main(String[] args) {
		System.out.println(getDownloadAppFile());
	}

	public static synchronized String getRedirectUrls() {
		return redirectUrl;
	}

}
