package com.trux.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.trux.model.UploadDocumentModel;

public class AWSS3Uploader {
	/*
	 * private static String bucketName = "buzzling"; private static String
	 * keyName = "AKIAJVFWXY5FVY7DUHNA"; private static String secret =
	 * "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"; private static String baseURL
	 * = "https://s3-us-west-2.amazonaws.com/buzzling/profilepictures/";
	 */
	private static String bucketName = "truxs3";
	private static String DRIVER_BUCKET = "truxs3";
	private static String VEHICLE_BUCKET = "truxs3";
	private static String COMPANY_BUCKET = "truxs3";
	private static String keyName = "AKIAIJALK2XEKW3HG4EA";
	private static String secret = "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";
	
	private static String baseURLDriver = "https://s3-ap-southeast-1.amazonaws.com/truxs3/driver/";

	private static String baseURVehicle = "https://s3-ap-southeast-1.amazonaws.com/truxs3/vehicle/";

	private static String baseURLCompany = "https://s3-ap-southeast-1.amazonaws.com/truxs3/client/";

	private static String baseURL = "https://s3-ap-southeast-1.amazonaws.com/truxs3/";

	/*
	 * private static String uploadFileName =
	 * "/Users/abc/Downloads/apk-downlaods/Additional-Changed-Screens/driver/Docuploadscreen.png"
	 * ;
	 * 
	 * public static void main(String[] args) throws IOException {
	 * 
	 * FileInputStream fileInputStream = new FileInputStream(new
	 * File(uploadFileName));
	 * 
	 * // uploadFile("Docuploadscreen.png",fileInputStream);
	 * 
	 * }
	 */

	public static UploadDocumentModel uploadCompanyFile(String fileName,
			String fileContnetType, long size, InputStream fileStream,
			UploadDocumentModel uploadDocumentModel) {

		try {

			String fileExt = getExtentionName(fileName);
			String uuid = UUID.randomUUID().toString();
			String randomFileName = uuid + fileExt;

			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
					keyName, secret));
			try {
				System.out
						.println("Uploading a new object to S3 from a file\n");
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(size);
				metadata.setContentType(fileContnetType);

				/*
				 * MessageDigest messageDigest =
				 * MessageDigest.getInstance("MD5"); messageDigest.reset();
				 * messageDigest.update(IOUtils.toByteArray(fileStream)); byte[]
				 * resultByte = messageDigest.digest(); String hashtext = new
				 * String(Hex.encodeHex(resultByte));
				 * 
				 * ObjectMetadata meta = new ObjectMetadata();
				 * meta.setContentLength
				 * (IOUtils.toByteArray(fileStream).length);
				 * meta.setContentMD5(hashtext);
				 */
				s3client.putObject(new PutObjectRequest(COMPANY_BUCKET,
						"client/" + randomFileName, fileStream, metadata));

			} catch (AmazonServiceException ase) {
				System.out
						.println("Caught an AmazonServiceException, which "
								+ "means your request made it "
								+ "to Amazon S3, but was rejected with an error response"
								+ " for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				ace.printStackTrace();
				System.out.println("Caught an AmazonClientException, which "
						+ "means the client encountered "
						+ "an internal error while trying to "
						+ "communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}

			uploadDocumentModel.setFileName(randomFileName);
			uploadDocumentModel.setUploadURL(baseURLCompany + randomFileName);

			System.out.println(baseURLCompany + randomFileName);

			return uploadDocumentModel;
		} catch (Exception er) {
			er.printStackTrace();
			return new UploadDocumentModel();
		}
	}

	public static UploadDocumentModel uploadVehicleFile(String fileName,
			String fileContnetType, long size, InputStream fileStream,
			UploadDocumentModel uploadDocumentModel) {

		try {

			String fileExt = getExtentionName(fileName);
			String uuid = UUID.randomUUID().toString();
			String randomFileName = uuid + fileExt;

			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
					keyName, secret));
			try {
				System.out
						.println("Uploading a new object to S3 from a file\n");
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(size);
				metadata.setContentType(fileContnetType);

				/*
				 * MessageDigest messageDigest =
				 * MessageDigest.getInstance("MD5"); messageDigest.reset();
				 * messageDigest.update(IOUtils.toByteArray(fileStream)); byte[]
				 * resultByte = messageDigest.digest(); String hashtext = new
				 * String(Hex.encodeHex(resultByte));
				 * 
				 * ObjectMetadata meta = new ObjectMetadata();
				 * meta.setContentLength
				 * (IOUtils.toByteArray(fileStream).length);
				 * meta.setContentMD5(hashtext);
				 */
				s3client.putObject(new PutObjectRequest(VEHICLE_BUCKET,
						"vehicle/" + randomFileName, fileStream, metadata));

			} catch (AmazonServiceException ase) {
				System.out
						.println("Caught an AmazonServiceException, which "
								+ "means your request made it "
								+ "to Amazon S3, but was rejected with an error response"
								+ " for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				ace.printStackTrace();
				System.out.println("Caught an AmazonClientException, which "
						+ "means the client encountered "
						+ "an internal error while trying to "
						+ "communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}

			uploadDocumentModel.setFileName(randomFileName);
			uploadDocumentModel.setUploadURL(baseURVehicle + randomFileName);

			System.out.println(baseURVehicle + randomFileName);

			return uploadDocumentModel;
		} catch (Exception er) {
			er.printStackTrace();
			return new UploadDocumentModel();
		}
	}

	public static UploadDocumentModel uploadDriverFile(String fileName,
			String fileContentType, long size, InputStream fileStream,
			UploadDocumentModel uploadDocumentModel) {

		try {

			String fileExt = getExtentionName(fileName);
			String uuid = UUID.randomUUID().toString();
			String randomFileName = uuid + fileExt;

			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
					keyName, secret));
			try {
				System.out
						.println("Uploading a new object to S3 from a file\n");

				//
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(size);
				metadata.setContentType(fileContentType);

				s3client.putObject(new PutObjectRequest(DRIVER_BUCKET,
						"driver/" + randomFileName, fileStream, metadata));

			} catch (AmazonServiceException ase) {
				System.out
						.println("Caught an AmazonServiceException, which "
								+ "means your request made it "
								+ "to Amazon S3, but was rejected with an error response"
								+ " for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				ace.printStackTrace();
				System.out.println("Caught an AmazonClientException, which "
						+ "means the client encountered "
						+ "an internal error while trying to "
						+ "communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}

			uploadDocumentModel.setFileName(randomFileName);
			uploadDocumentModel.setUploadURL(baseURLDriver + randomFileName);

			System.out.println(baseURLDriver + randomFileName);

			return uploadDocumentModel;
		} catch (Exception er) {
			er.printStackTrace();
			return new UploadDocumentModel();
		}
	}

	public static UploadDocumentModel uploadFiles(String fileName,
			String fileContnetType, int size, InputStream fileStream,
			UploadDocumentModel uploadDocumentModel) {

		try {

			String fileExt = getExtentionName(fileName);
			String uuid = UUID.randomUUID().toString();
			String randomFileName = uuid + fileExt;

			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
					keyName, secret));
			try {
				System.out
						.println("Uploading a new object to S3 from a file\n");
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(size);
				metadata.setContentType(fileContnetType);

				s3client.putObject(new PutObjectRequest(bucketName, "driver/"
						+ randomFileName, fileStream, metadata));

			} catch (AmazonServiceException ase) {
				System.out
						.println("Caught an AmazonServiceException, which "
								+ "means your request made it "
								+ "to Amazon S3, but was rejected with an error response"
								+ " for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				ace.printStackTrace();
				System.out.println("Caught an AmazonClientException, which "
						+ "means the client encountered "
						+ "an internal error while trying to "
						+ "communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}

			uploadDocumentModel.setFileName(randomFileName);
			uploadDocumentModel.setUploadURL(baseURLCompany + randomFileName);

			System.out.println(baseURLCompany + randomFileName);

			return uploadDocumentModel;
		} catch (Exception er) {
			er.printStackTrace();
			return new UploadDocumentModel();
		}
	}

	public static String getExtentionName(String fileName) {

		int indexOfDot = fileName.indexOf(".");

		if (indexOfDot <= 0)
			return "";

		return fileName.substring(indexOfDot, fileName.length());
	}

	public static UploadDocumentModel uploadFile(String fileName,
			InputStream fileStream) {
		UploadDocumentModel uploadDocumentModel = new UploadDocumentModel();
		String fileExt = getExtentionName(fileName);
		String uuid = UUID.randomUUID().toString();
		String randomFileName = uuid + fileExt;

		AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(keyName,
				secret));
		try {
			System.out.println("Uploading a new object to S3 from a file\n");
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType("");

			s3client.putObject(new PutObjectRequest(bucketName, "driver/"
					+ randomFileName, fileStream, metadata));

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which "
					+ "means your request made it "
					+ "to Amazon S3, but was rejected with an error response"
					+ " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			System.out.println("Caught an AmazonClientException, which "
					+ "means the client encountered "
					+ "an internal error while trying to "
					+ "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

		uploadDocumentModel.setFileName(randomFileName);
		uploadDocumentModel.setUploadURL(baseURL + randomFileName);

		System.out.println(baseURL + randomFileName);

		return uploadDocumentModel;

	}

	public static UploadDocumentModel uploadDriverFile(String fileName,
			String fileContentType, long size, InputStream fileStream) {

		try {

			String fileExt = getExtentionName(fileName);
			String uuid = UUID.randomUUID().toString();
			String randomFileName = uuid + fileExt;

			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
					keyName, secret));
			try {
				System.out
						.println("Uploading a new object to S3 from a file\n");

				//
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(size);
				metadata.setContentType(fileContentType);

				s3client.putObject(new PutObjectRequest(DRIVER_BUCKET,
						"driver/" + randomFileName, fileStream, metadata));

			} catch (AmazonServiceException ase) {
				System.out
						.println("Caught an AmazonServiceException, which "
								+ "means your request made it "
								+ "to Amazon S3, but was rejected with an error response"
								+ " for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				ace.printStackTrace();
				System.out.println("Caught an AmazonClientException, which "
						+ "means the client encountered "
						+ "an internal error while trying to "
						+ "communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}
			UploadDocumentModel uploadDocumentModel = new UploadDocumentModel();
			uploadDocumentModel.setFileName(randomFileName);
			uploadDocumentModel.setUploadURL(baseURLDriver + randomFileName);

			System.out.println(baseURLDriver + randomFileName);

			return uploadDocumentModel;
		} catch (Exception er) {
			er.printStackTrace();
			return new UploadDocumentModel();
		}
	}

	public static URL getSignedURL(String path) {

		/*
		 * URL signedURL ; AWSCredentials credentials = new
		 * BasicAWSCredentials("AKIAIJALK2XEKW3HG4EA",
		 * "EEPU2WCXhD8NWI4lBSHpZ81mQVx9RxWPiHcAWA6p"); ClientConfiguration
		 * clientConfig = new ClientConfiguration();
		 * 
		 * clientConfig.setProtocol(Protocol.HTTP);
		 * 
		 * AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
		 * conn.setEndpoint("s3-ap-southeast-1.amazonaws.com");
		 * 
		 * GeneratePresignedUrlRequest request = new
		 * GeneratePresignedUrlRequest(bucketName, path);
		 * //GeneratePresignedUrlRequest request = new
		 * GeneratePresignedUrlRequest("truxs3",
		 * "client/ff04274d-64c4-40ff-a37f-ddc803a7db34.png"); signedURL =
		 * conn.generatePresignedUrl(request);
		 */
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAIJALK2XEKW3HG4EA",
				"EEPU2WCXhD8NWI4lBSHpZ81mQVx9RxWPiHcAWA6p");
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3-ap-southeast-1.amazonaws.com");
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
				"truxs3", path);
		System.out.println(conn.generatePresignedUrl(request));
		System.out.println("Done");
		return conn.generatePresignedUrl(request);
	}
	
	public static URL s3UrGenerator(String url) throws AmazonClientException {

		String temp;
		temp = url.substring(url.lastIndexOf(".com/") + 5);
		System.out.println(temp);
		String bucket = temp.substring(0, temp.indexOf("/"));
		System.out.println(bucket);
		temp = temp.substring(temp.indexOf("/") + 1);
		System.out.println(temp);

		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAIJALK2XEKW3HG4EA",
				"EEPU2WCXhD8NWI4lBSHpZ81mQVx9RxWPiHcAWA6p");
		ClientConfiguration clientConfig = new ClientConfiguration();

		clientConfig.setProtocol(Protocol.HTTP);

		AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3-ap-southeast-1.amazonaws.com");

		// GeneratePresignedUrlRequest request = new
		// GeneratePresignedUrlRequest("truxs3",
		// "client/ff04274d-64c4-40ff-a37f-ddc803a7db34.png");
		GeneratePresignedUrlRequest request1 = new GeneratePresignedUrlRequest(
				bucket, temp);
		// https://s3-ap-southeast-1.amazonaws.com/truxs3/driver/ac0b9de4-63df-4917-a3f8-11c25c547a23.jpg
		// https://s3-us-west-2.amazonaws.com/buzzling/profilepictures/3f9c92b3-cc12-4dca-a470-7383372e5b56.png
		// String ss = conn.generatePresignedUrl(request).toString();
		// String ss1 = conn.generatePresignedUrl(request1).toString();
		return conn.generatePresignedUrl(request1);
	}
	
	
}