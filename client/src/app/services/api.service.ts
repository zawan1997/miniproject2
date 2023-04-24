import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable, } from '@angular/core';
import { environment } from 'src/environment';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(
    private http: HttpClient,
    private storageService: StorageService,
  ) {}
  apiUrl = environment.APP.API_URLS;
  baseUrl = environment.APP.BASE_URL;

  getheaders() {
    const userdata = JSON.parse(this.storageService.getLocalItem('userdata')!);
    let token = '';
    //there is userdata, we give a token
    if (userdata) {
      token = userdata.token;
    }
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
    return options;
  }

//generic GET POST PUT


  get(url: string, customHeaders = false): any {
    const headers: any = customHeaders ? customHeaders: this.getheaders();
    //To get map the parameters to the base URL
    const apiUrl = this.baseUrl + url;
    return this.http.get(apiUrl, headers);
  }

  post(url: string, data?: any, customHeaders = false): any{
    const headers: any = customHeaders ? customHeaders: this.getheaders();
    const apiUrl = this.baseUrl + url;
    return this.http.post(apiUrl, JSON.stringify(data), headers);
  }

  put(url: string, data?: any, customHeaders?: HttpHeaders): any {
    const headers: any = customHeaders ? customHeaders: this.getheaders();
    const apiUrl = this.baseUrl + url;
    return this.http.put(apiUrl, JSON.stringify(data), headers);
  }

}

