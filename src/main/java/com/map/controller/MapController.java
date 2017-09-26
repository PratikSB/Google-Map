package com.map.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.map.entity.Location;
import com.map.entity.Place;
import com.map.repository.LocationRepository;
import com.map.repository.PlaceRepository;
import com.map.utils.RestClient;

@RestController
@RequestMapping(value="/MapController")
public class MapController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(MapController.class);
	
	@Autowired
	RestClient restClient;
	
	@Autowired
	PlaceRepository placeRepository;
	
	@Resource
	LocationRepository locationRepository;
	
		@RequestMapping(value="/getDetails" , method=RequestMethod.POST)
		public ResponseEntity<List<Place>> getDetails(@RequestBody Location location)
		{
			 System.out.println("#####.....Inside Get Details API1.....#####");
			 logger.debug("##############################################################");
			 logger.debug(" Entry :: getDetails :: " + " :: " + System.currentTimeMillis());
			 logger.debug("###############################################################");
		try {
			
			 List<Place> placeList = new ArrayList<Place>();
			 
			 String latitudeLognitudeURI="https://maps.googleapis.com/maps/api/geocode/json?address="+ location.getPlace() +"&key=AIzaSyC534LvoQ4g-KlIIEpgJjA1LUYe5bNkf2A";
			
			  String latitudelognitude=restClient.get(latitudeLognitudeURI,null);
			
			  JSONObject jsonObject = new JSONObject(latitudelognitude);

			 JSONArray jsonArray = jsonObject.getJSONArray("results");
			 JSONObject latLngJSONObject = jsonArray.getJSONObject(0);
			   
			  Double lat= (Double) latLngJSONObject.getJSONObject("geometry").getJSONObject("location").get("lat");
			  Double lng= (Double) latLngJSONObject.getJSONObject("geometry").getJSONObject("location").get("lng");
			   
			  String placeIdDetailsURI = "https://maps.googleapis.com/maps/api/place/radarsearch/json?location="+lat+","+lng+"&radius=5000&type="+location.getType()+"&keyword="+location.getKeyword()+"&key=AIzaSyA_eo9Ittu-1GL8fRQmO7yPDQw3tFgCaF4";
				
			  String placeIdDetails = restClient.get(placeIdDetailsURI,null);
			  
			  JSONObject placeDetailsJSONObject = new JSONObject(placeIdDetails);
			  JSONArray jsonArray1 = placeDetailsJSONObject.getJSONArray("results");
			  
		        for (int i = 0; i <jsonArray1.length(); i++)
		        {
		        	Place place=new Place();
		            JSONObject placeIdJSONObjecty = jsonArray1.getJSONObject(i);

		            String placeId = (String) placeIdJSONObjecty.get("place_id");

					String placeDetailsURI = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeId+"&key=AIzaSyA_eo9Ittu-1GL8fRQmO7yPDQw3tFgCaF4";
					
					String placeDetails=restClient.get(placeDetailsURI,null);
				
					 JSONObject placeDetailsJson=new JSONObject(placeDetails);
					   place.setPlace_Id(placeId);
					   place.setPlace_Name((String) placeDetailsJson.getJSONObject("result").get("name"));
					   place.setAddress((String) placeDetailsJson.getJSONObject("result").get("formatted_address"));
					   
					   if(placeDetailsJson.getJSONObject("result").has("formatted_phone_number"))
				        {
				         place.setPhone_Number((String)placeDetailsJson.getJSONObject("result").get("formatted_phone_number"));
				        }
				        else
				        {
				          place.setPhone_Number("Not Available");
				        }
				        if(placeDetailsJson.getJSONObject("result").has("rating"))
				        {
				          Number number =  (Number) placeDetailsJson.getJSONObject("result").get("rating");
				          place.setRating(number.toString());
				        }
				        else
				        {
				         place.setRating("Not Available");
				        }
					   placeRepository.save(place);
					   placeList.add(place);
		        }
		        location.setLatitude(lat);
		        location.setLognitude(lng);
		        locationRepository.save(location);
			    return new ResponseEntity<List<Place>>( placeList , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<List<Place>> (HttpStatus.BAD_REQUEST);
		}
		finally
		{
			 logger.debug("##################################################################");
			 logger.debug(" Exit :: getPlaceDetails :: " + " :: " + System.currentTimeMillis());
			 logger.debug("##################################################################");
		}
	}
	
}
