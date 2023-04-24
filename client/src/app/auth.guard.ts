import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { StorageService } from "./services/storage.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard {
    constructor(private router: Router, private storageService: StorageService) {}

    canActivate(): boolean {
      const isLoggedIn = this.storageService.getLocalItem('isLoggedIn');
      if (isLoggedIn) {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    }
}