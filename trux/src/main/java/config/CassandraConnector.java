package config;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.trux.utils.DateFormaterUtils;
 
 
public class CassandraConnector
{
	private static Cluster.Builder cassandraCluster =null;
	private static String cassandraDb="";
	private static Double radiusLocations;
	private CassandraConnector(){}
 	static {
		try {
			InputStream in = CassandraConnector.class.getResourceAsStream("/config/jdbc.properties");
			Properties prop = new Properties();
			prop.load(in); 
			String radiusLocation=prop.getProperty("radiusLocation");
		 
			if(radiusLocation!=null && !radiusLocation.equals("")){
				radiusLocations=Double.parseDouble(radiusLocation) ;
			}
			String port=prop.getProperty("jdbcProperties.port");
			cassandraDb=prop.getProperty("jdbcProperties.db");
			int portS=0;
			if(port!=null && !port.equals("")){
			  portS=Integer.parseInt(port) ;}
			  cassandraCluster = Cluster.builder().addContactPoints(prop.getProperty("jdbcProperties.ip")).withPort(portS).withCredentials(prop.getProperty("jdbcProperties.username"), prop.getProperty("jdbcProperties.password"));
		   in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
	public  static synchronized Double getRadiusLocations() {
		return radiusLocations;
	}
	public static synchronized Session getCassandraSession(){
		cassandraCluster=getCassandraConnection();
	   return cassandraCluster.build().connect(cassandraDb);
	}
	public static synchronized Cluster.Builder  getCassandraConnection() {

		return cassandraCluster;
	}
	
	 
  
   public static void main(String[] args) {
	   try
	   {
		   long epochTMS = System.currentTimeMillis()/1000;
			String ls_vh_loct_id =""+epochTMS;
		 	 
			String query1 = "INSERT INTO leased_vehicle_location (ls_vh_loct_id, booking_id, driver_id, driver_mobile, company_sub_id, vehicle_lat, vehicle_long, loct_time) VALUES("+ls_vh_loct_id+", "+23+","+32+",8882397104, "+2+", "+ 28.9899+", "+27.622222+", '"+ DateFormaterUtils.convertGMTToISTWithDates(new Date().toString()).replace(".0", "").trim()+"');" ;
			System.out.println(query1);
		Session	cassandraSession= getCassandraSession();
		    cassandraSession.execute(query1);
		    cassandraSession.close();
	   }catch(Exception er){er.printStackTrace();}
}
}