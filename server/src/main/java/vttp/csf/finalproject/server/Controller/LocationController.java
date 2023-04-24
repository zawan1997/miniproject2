package vttp.csf.finalproject.server.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.csf.finalproject.server.Models.Location;
import vttp.csf.finalproject.server.Services.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {
	@Autowired
	LocationService locationService;
	
	@PostMapping("/create")
	public ResponseEntity<Location> createLocation(@RequestBody Location locationReq) {		
		Location res = locationService.create(locationReq);
		System.out.println("res : "+res);		
		ResponseEntity<Location> responseEntity = new ResponseEntity<>(res, 
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@PutMapping("/update")
	public ResponseEntity<Location> updateLocation(@RequestBody Location locationReq) {
		Location res = locationService.update(locationReq);
		System.out.println("res : "+res);		
		ResponseEntity<Location> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Location>> getAllLocations() {		
		List<Location> res = locationService.get();
		System.out.println("res : "+res);		
		ResponseEntity<List<Location>> responseEntity = new ResponseEntity<List<Location>>(res, HttpStatus.ACCEPTED);
		
		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Location> getLocation(@PathVariable int id) {		
		Location res = locationService.get(id);
		System.out.println("res : "+res);
		
		ResponseEntity<Location> responseEntity = new ResponseEntity<>(res, HttpStatus.ACCEPTED);
		
		return responseEntity;
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLocation(@PathVariable int id) {		
		String res = locationService.delete(id);
		System.out.println("res : "+res);		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
		
		return responseEntity;
	}
    
}
