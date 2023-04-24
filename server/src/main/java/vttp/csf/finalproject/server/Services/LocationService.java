package vttp.csf.finalproject.server.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import vttp.csf.finalproject.server.Models.Location;
import vttp.csf.finalproject.server.Repositories.LocationRepo;

@Service
public class LocationService implements MainServiceInt<Location> {
    @Autowired
	LocationRepo locationRepo;
	
	@Override
	public Location create(Location locationReq) {		
		boolean res = locationRepo.createLocation(locationReq);
		System.out.println("res : "+res);
		
		return locationReq;
	}
	
	@Override
	public Location update(Location locationReq) {		
		boolean res = locationRepo.updateLocation(locationReq);
		System.out.println("res : "+res);

		return locationReq;
	}
	
	@Override
	public List<Location> get() {		
		List<Location> res = locationRepo.getAllLocations();
		System.out.println("res : "+res);
				
		return res;
	}

	@Override
	public Location get(int id) {		
		Location res = locationRepo.getLocationById(id);
		System.out.println("res : "+res);
		
		return res;
	}

	@Override
	public String delete(int id) {		
		boolean res = locationRepo.deleteLocation(id);
		System.out.println("res : "+res);
		
		return "Location Delete Response is "+res;
	}
}
