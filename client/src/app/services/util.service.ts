import { isPlatformBrowser } from '@angular/common';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root',
})

//check if its a phone or a computer
//check this again
export class UtilService {
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}
  public isBrowser: boolean = isPlatformBrowser(this.platformId);

  isMobile() {
    if (this.isBrowser) {
      if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        return true;
      }
    }
    return false;
  }
}
