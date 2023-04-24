import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { SnackbarService } from 'src/app/services/snackbar.service';

@Component({
  selector: 'app-location-list',
  templateUrl: './location-list.component.html',
  styleUrls: ['./location-list.component.css']
})
export class LocationListComponent {
  constructor(
    private actRoute: ActivatedRoute,
    private api: ApiService,
    private snackbarService: SnackbarService
  ){}
  locationList = [];

  //Trying load function if slow
  isLoading = false;
  search = '';
  ngOnInit(){
    this.getParams();
  }
  getParams() {
    this.actRoute.params.subscribe((params: any) => {
      this.search = params.search;
      this.searchLocation()
    });
  }
  searchLocation() {
    const url = API_URLS.SEARCH_LOCATION(this.search);
    this.isLoading = true;
    this.api.get(url).subscribe((response: any) => {
      if (response?.data && response.data.length > 0) {
        this.locationList = response.data;
        this.isLoading = false;
      } else {
        this.isLoading = false;
        this.snackbarService.openSnackbar('No Data Found', 'info');
      }
    }, (error: Error) => {
      this.isLoading = false;
    })
  }
}
