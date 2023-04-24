import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-location-details',
  templateUrl: './location-details.component.html',
  styleUrls: ['./location-details.component.css']
})
export class LocationDetailsComponent {
  constructor(
    private api: ApiService,
    private actRoute: ActivatedRoute,
    public util: UtilService
  ) {}
  uuid = '';

  ngOnInit() {
    this.getParams();
  }

  details: any = {};

  getParams() {
    this.actRoute.params.subscribe((params: any) => {
      //to throw into the second api search via uuid
      this.uuid = params.uuid;
      this.getLocationDetails()
    });
  }

  getLocationDetails() {
    const url = API_URLS.LOCATION_DETAIL(this.uuid);
    this.api.get(url).subscribe((response: any)=> {
      console.log(response, 'this is response');
      this.details = response.data[0];
    })
  }
}
