import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LocationService } from '../services/location.service';

@Component({
  selector: 'app-location-details',
  templateUrl: './location-details.component.html',
  styleUrls: ['./location-details.component.css']
})
export class LocationDetailsComponent implements OnInit {
  TIH_API_KEY = environment.tih_api_key;
  params$! : Subscription
  locationModel: Location = new Location;

  constructor(private locationService: LocationService, private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      (params)=>{

      }
    )
  }

  getLocationFromAPI(location: string) {
//    this.locationService.getLocations(location);
  }
  
}
