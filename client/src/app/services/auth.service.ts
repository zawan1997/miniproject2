import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { API_URLS } from 'src/api-urls';
import { environment } from 'src/environment';
import { ApiService } from './api.service';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  public isAuthenticated = false;
  constructor(
    private api: ApiService,
    private router: Router,
    private storageService: StorageService
  ) {  }

  ngOnInit() {
    if (this.storageService.getLocalItem('isLoggedIn')) {
      this.isAuthenticated = true;
    }
  }

  login(params: any): Observable<any> {
    const url = API_URLS.LOGIN;
    return this.api.post(url, params).pipe(map((val: any) => {
          //once logged in then is authenticated will pass into true

      this.storageService.setLocalItem('isLoggedIn', 'true');
      this.storageService.setLocalItem('userId', val.userId);
      this.setIsAuthenticated(true);
      return val;
    }));
  }

  logout() {
    this.setIsAuthenticated(false);
    //removing the local stored items when logging out
    this.storageService.deleteLocalItem('isLoggedIn');
    this.storageService.deleteLocalItem('userId');
    this.router.navigate(['/login']);
  }

  setIsAuthenticated(value: boolean) {
    this.isAuthenticated =  value;
  }

  register(params: any): Observable<any> {
    const url = API_URLS.SIGNUP;
    return this.api.post(url, params).pipe(map(val => {
        return val;   
    }));
  }
}

