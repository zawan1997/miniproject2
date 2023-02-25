import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
   providedIn: 'root' 
})
export class LocationService {
    
    constructor(private httpClient:HttpClient) {}

    getLocation(location: string, apiKey:string): Promise<any>{
        const params = new HttpParams()
        .set("keyword",location)
        .set("apikey", apiKey)

        return lastValueFrom(this.httpClient
        .get(environment.tih_api_url, {params: params}))

    }
}