package com.trux.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.sql.*;

//Main class
public class SchedulerMain {
	public static void main(String args[]) throws InterruptedException {

		Connection conn = null;
		String url = "jdbc:mysql://54.169.176.165:3306/";
		// jdbc:mysql://54.169.177.19:3306/
		String dbName = "trux";
		// truxdev
		String driver = "com.mysql.jdbc.Driver";
		String userName = "prodnewuser";
		// devnewuser
		String password = "Trux9R0d#2016";
		// trux#dev

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
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			System.out.println("Connected to the database");

			String query = "SELECT bkLsStpId, bookingLeaseId, GROUP_CONCAT(stopLat,' ',stopLong) lat_long, distance_between_stops FROM booking_lease_stop WHERE bookingLeaseId=2895;";// 2689

			// SELECT bkLsStpId, bookingLeaseId, GROUP_CONCAT(stopLat,' ', stopLong) lat_long, distance_between_stops FROM booking_lease_stop WHERE bkLsStpId > 4979 GROUP BY bookingLeaseId ORDER BY bkLsStpId ASC;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					int bkLsStpId = rs.getInt("bkLsStpId");
					int bookingLeaseId = rs.getInt("bookingLeaseId");
					String lat_long = rs.getString("lat_long");
					String array_lat_long[] = null;
					if (lat_long != null) {
						array_lat_long = lat_long.split(",");
					}
					String distance_between_stops = rs
							.getString("distance_between_stops");
					if (bookingLeaseId != 0) {
						if (distance_between_stops == null
								|| !distance_between_stops.equals("")) {

							String insert_query1 = "UPDATE booking_lease_stop SET distance_between_stops = '0.0 km' WHERE bkLsStpId = "
									+ bkLsStpId + ";";
							Statement st2 = conn.createStatement();
							st2.executeUpdate(insert_query1);

							System.out.println(bkLsStpId);
								for (int i = 0; i < array_lat_long.length - 1; i++) {

									String curr_lat_long1[] = array_lat_long[i]
											.split(" ");
									String lat1 = null;
									String long1 = null;
									if (curr_lat_long1 != null) {
										lat1 = curr_lat_long1[0];
										long1 = curr_lat_long1[1];
									}

									String curr_lat_long2[] = array_lat_long[i + 1]
											.split(" ");
									String lat2 = null;
									String long2 = null;
									if (curr_lat_long2 != null) {
										lat2 = curr_lat_long2[0];
										long2 = curr_lat_long2[1];
									}

									String dist = Utils.getDistance(
											Double.parseDouble(lat1),
											Double.parseDouble(long1),
											Double.parseDouble(lat2),
											Double.parseDouble(long2));

									System.out.println((bkLsStpId + i + 1)
											+ "\t\t\t" + dist);

									// double distance = 0.0;
									// if (dist.contains(" km")) {
									// dist = dist.replace(" km", "");
									// distance = Double.parseDouble(dist);
									// }

									String insert_query2 = "UPDATE booking_lease_stop SET distance_between_stops = '"
											+ dist
											+ "' WHERE bkLsStpId = "
											+ (bkLsStpId + i + 1) + ";";
									Statement st3 = conn.createStatement();
									st3.executeUpdate(insert_query2);
								}

						}
					}
					// System.out.format("%s, %s, %s\n", bookingLeaseId,
					// lat_long,
					// distance_between_stops);
				}
			}

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Disconnected from database");

		// Timer time = new Timer(); // Instantiate Timer Object
		// ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask
		// // class
		// time.schedule(st, 0, 1000); // Create Repetitively task for every 1
		// secs
		//
		// // for demo only.
		// for (int i = 0; i <= 5; i++) {
		// System.out.println("Execution in Main Thread...." + i);
		// Thread.sleep(2000);
		// if (i == 5) {
		// System.out.println("Application Terminates");
		// System.exit(0);
		// }
		// }
	}
}