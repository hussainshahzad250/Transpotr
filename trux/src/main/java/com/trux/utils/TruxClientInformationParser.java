package com.trux.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TruxClientInformationParser {

	private static Document loadTestDocument(String url) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		return factory.newDocumentBuilder().parse(new URL(url).openStream());
	}

	public static void main(String argv[]) {

		Connection conn = null;
		String url = "jdbc:mysql://54.169.177.19:3306/";
		// jdbc:mysql://54.169.176.165:3306/
		String dbName = "truxdev";
		// trux
		String driver = "com.mysql.jdbc.Driver";
		String userName = "devnewuser";
		// prodnewuser
		String password = "trux#dev";
		// Trux9R0d#2016

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			conn = DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("Connected to the database");

			// File fXmlFile = new
			// File("C:\\Users\\shantanu\\Downloads\\NAAPTOL\\naaptol_request.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document doc = null;
			try {
				// doc = dBuilder.parse(loadTestDocument(""));
				doc = loadTestDocument("");
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("HAWB");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element : " + nNode.getNodeName() + " " + (temp + 1));

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					// System.out.println("Staff id : " +
					// eElement.getAttribute("id"));
					System.out.println(
							"HAWBNumber : " + eElement.getElementsByTagName("HAWBNumber").item(0).getTextContent());
					String tHAWBNumber = eElement.getElementsByTagName("HAWBNumber").item(0).getTextContent();
					System.out.println(
							"OrderNumber : " + eElement.getElementsByTagName("OrderNumber").item(0).getTextContent());
					String tOrderNumber = eElement.getElementsByTagName("OrderNumber").item(0).getTextContent();
					System.out.println("ConsigneeName : "
							+ eElement.getElementsByTagName("ConsigneeName").item(0).getTextContent());
					String tConsigneeName = eElement.getElementsByTagName("ConsigneeName").item(0).getTextContent();
					System.out.println("ConsigneeContact : "
							+ eElement.getElementsByTagName("ConsigneeContact").item(0).getTextContent());
					String tConsigneeContact = eElement.getElementsByTagName("ConsigneeContact").item(0)
							.getTextContent();
					System.out.println("Amount : " + eElement.getElementsByTagName("Amount").item(0).getTextContent());
					String tAmount = eElement.getElementsByTagName("Amount").item(0).getTextContent();

					String query = "INSERT INTO naaptol_shipment_data (awb_number, order_no, consignee_name, consignee_contact, awb_date, awb_amount, created_date) "
							+ "VALUES ('" + tHAWBNumber + "', '" + tOrderNumber + "', '" + tConsigneeName + "', '"
							+ tConsigneeContact + "', NOW(), '" + tAmount + "', NOW())";

					Statement st = conn.createStatement();
					st.executeUpdate(query);

					st.close();

				}
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		try {

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Disconnected from database");

	}

}
