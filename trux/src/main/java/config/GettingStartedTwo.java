/*package config;
public class GettingStartedTwo {

 

public static void main(String[] args) {

 
 

Session session;

ResultSet results;

Row rows;
Cluster cluster = Cluster.builder()

.addContactPoint("localhost")

.build();

Session session = cluster.connect("demo");

cluster = Cluster

.builder()

.addContactPoint("192.168.0.30")

.withRetryPolicy(DefaultRetryPolicy.INSTANCE)

.build();

session = cluster.connect("demo");


cluster = Cluster

.builder()

.addContactPoint("192.168.0.30")

.withRetryPolicy(DefaultRetryPolicy.INSTANCE)

.withLoadBalancingPolicy(

 new TokenAwarePolicy(new DCAwareRoundRobinPolicy()))

.build();

session = cluster.connect("demo");*/