import { Component, Input } from '@angular/core';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-location-card',
  templateUrl: './location-card.component.html',
  styleUrls: ['./location-card.component.css']
})
export class LocationCardComponent {
  @Input() locationData: any;

  constructor(
    private api: ApiService,
    private storageService: StorageService
  ) {}

  //To save the location to a users dash
  //Come back to this!
  saveUserLocation() {
    const userId = +JSON.parse(this.storageService.getLocalItem('userId')!);
    const params= {
      userId,
      locationUuid: this.locationData.uuid,
      name: this.locationData.name,
      body: this.locationData.body,
      primaryContactNo: this.locationData?.contact?.primaryContactNo ?? "",
      openTime:  this.getTime('openTime'),
      closeTime: this.getTime('closeTime')
    }
    const url = API_URLS.USER_LOCATION_CREATE;
    this.api.post(url, params).subscribe((response: any) => {
      console.log(response);
    })
  }

  getTime(openClose: string) {
    if (this.locationData.businessHour.length > 0) {
      return this.locationData.businessHour.find((item: any) => item[openClose])[openClose];
    }
    return '';
  }
  
}

