package vttp.csf.finalproject.server.Services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.finalproject.server.Models.CreateRequest;
import vttp.csf.finalproject.server.Models.Location;
import vttp.csf.finalproject.server.Repostories.MainRepo;

public class LocationService {
    
    public static final String URL = "https://tih-api.stb.gov.sg/content/v1/attractions/search?";

    @Value("${API_KEY}")
    private String key;

    @Autowired
    private MainRepo mainRepo;

    public List<Location> getLocation(String location) {
        

        String payload;

        String url = UriComponentsBuilder.fromUriString(URL)
        .queryParam("keyword", location)
        .queryParam("apikey", key)
        .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req,String.class);

        if(resp.getStatusCodeValue() !=200) {
            System.err.println("Error not 200");
            return Collections.emptyList();
        }

        payload = resp.getBody();

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject results = jsonReader.readObject();
        JsonArray data = results.getJsonArray("data");
        
        List<Location> list = new LinkedList<>();
        for (int i = 0; i < data.size(); i ++) {
            Location loc = new Location();
            loc.setName(getProperText(data.getJsonObject(i).getString("name")));
            loc.setBody(getProperText(data.getJsonObject(i).getString("uuid")));
            loc.setPrimaryContactNo(getProperText(data.getJsonObject(i).getJsonObject("contact").getString("primaryContactNo")));
            if (hasData(data.getJsonObject(i).getJsonArray("businessHour"))) {
                loc.setOpenTime(getProperText(
                        data.getJsonObject(i).getJsonArray("businessHour").getJsonObject(0).getString("openTime")));
                loc.setCloseTime(getProperText(
                        data.getJsonObject(i).getJsonArray("businessHour").getJsonObject(0).getString("closeTime")));
            } else {
                loc.setOpenTime("NA");
                loc.setCloseTime("NA");
            }
            list.add(loc);
        }
        return list;
    }

    
    private String getProperText(String text) {
        String returnValue = "NA";

        if (!(text == null || text.isEmpty() || text.isBlank())) {
            returnValue = text;
        }

        return returnValue;
    }

    // Checking if the array exists
    private boolean hasData(JsonArray arr) {
        return arr.size() > 0;
    }

    // Method to clean up all HTML traces from the result
    private String cleanup(String s) {
        String clean = s.replaceAll("\\<.*?>", "").replace("&nbsp;", "");
        return clean;
    }

    public void saveLocationForUser(CreateRequest create, Location location) {
        mainRepo.saveLocationsPerUser(location, create);
    }

    public List<Location> getLocationPerUser(Integer userId) {
        List<Location> list = new LinkedList<>();
        
        Optional<Location> locationIdList = mainRepo.getLocationByUserid(userId);

    }
}

