<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <style>
      #map-canvas {
        width: 1000px;
        height: 600px;
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js"></script>
	<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
    
    <script type="text/javascript">
	
		var windowWidth = window.innerWidth;
		var windowHieght = window.innerHeight;
		 
			var latitude;
			var longitude;
			var pickupAddress;
			
			var vehicleCurrentLat;
			var vehicleCurrentLong;
			var vehicleCurrentAddr;
			
			function setLatLong(bookingid){
				
				 $.ajax({
				      type: "GET",
				      url: "/trux/vehicleLocation/getVehicleLocationByBookingId",
				      data:{
				    	  bookingId:bookingid
						  } ,
				      success: function(data) {
						
						  
						  longitude = data.booking.pickup_lng;
						  latitude = data.booking.pickup_lat;
		            	  pickupAddress = data.booking.alloted_trux_info.pickup_address;
						  
						  vehicleCurrentLat = data.booking.alloted_trux_info.lat;
						  vehicleCurrentLong = data.booking.alloted_trux_info.lng;
						
						  initialize();
				      }
				    });
				
				
				
			}
		      function initialize() {
			    
		    	var mapCanvas = document.getElementById('map-canvas');
				var myLatlng = new google.maps.LatLng(latitude, longitude);
				
				var bounds = new google.maps.LatLngBounds();
				
				 var markers = [];
				if(latitude != null && longitude != null){
					var pickArr = ['Pick up point: '+pickupAddress, latitude,longitude];
					markers.push(pickArr);
				}
				if(vehicleCurrentLat != null && vehicleCurrentLong != null){
					var vehicleLocArr = ['Current Vehicle location', vehicleCurrentLat,vehicleCurrentLong];
					markers.push(vehicleLocArr);
				}
				
							
					
				
		        var mapOptions = {
		          center: myLatlng,
		          zoom: 8,
		          mapTypeId: google.maps.MapTypeId.ROADMAP
		        }
		        var map = new google.maps.Map(mapCanvas, mapOptions)
		        
		        var infoWindow = new google.maps.InfoWindow(), marker, i;
		        
		        for( i = 0; i < markers.length; i++ ) {
		            var position = new google.maps.LatLng(markers[i][1], markers[i][2]);
		            bounds.extend(position);
					var iconImg = null;
					if((markers[i][0]).includes('Current Vehicle location')){
						iconImg = '/trux/resources/images/truxMarker.png';
					}
		            marker = new google.maps.Marker({
		                position: position,
		                map: map,
						icon: iconImg,
		                title: markers[i][0]
		         });
		            
		         // Allow each marker to have an info window    
		            google.maps.event.addListener(marker, 'click', (function(marker, i) {
		                return function() {
		                    infoWindow.setContent(infoWindowContent[i][0]);
		                    infoWindow.open(map, marker);
		                }
		            })(marker, i));

		            // Automatically center the map fitting all markers on the screen
		            map.fitBounds(bounds);
		        }
		      }
		
      //google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <%String bookingId = (String)request.getParameter("bookingId"); %>
  <body onload="setLatLong(<%=bookingId %>);">
    <div id="map-canvas"></div>
  </body>
</html>