import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
   providedIn: 'root' 
})
export class LocationService {
    
    constructor(private httpClient:HttpClient) {}

    getLocations(location: string): any {
//        return firstValueFrom(
    return this.httpClient.get<Location[]>(location);
      //  )
    }

    // getLocationsPerUser()
    
}