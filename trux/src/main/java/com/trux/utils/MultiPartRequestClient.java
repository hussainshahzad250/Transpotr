package com.trux.utils;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MultiPartRequestClient {
public static void main(String[] args) throws ClientProtocolException, IOException {CloseableHttpClient httpClient = HttpClients.createDefault();
HttpPost uploadFile = new HttpPost("http://truxapp.com/trux/api/vehicleLocation/upload");

MultipartEntityBuilder builder = MultipartEntityBuilder.create();
builder.addTextBody("bookingid", "396");

builder.addBinaryBody("file", new File("/Users/abc/trux/trux-api/trux-api/location.png"), ContentType.APPLICATION_OCTET_STREAM, "file.ext");
HttpEntity multipart = builder.build();

uploadFile.setEntity(multipart);

HttpResponse response = httpClient.execute(uploadFile);
HttpEntity responseEntity = response.getEntity();
String responseStr  = EntityUtils.toString(responseEntity);
System.out.println(responseStr);
}
}
