import { Injectable } from "@angular/core";

interface iType {
    info: string; 
    error: string; 
    warn: string;
}

@Injectable({
    providedIn: 'root'
})
export class SnackbarService {
    constructor() {}

    types: iType = {
        info: 'text-bg-primary',
        error: 'text-bg-danger',
        warn: 'text-bg-warn'
    }

    openSnackbar(message: string, type: string): void {
        const snackbar = document.createElement('div');
        const typeClass = this.types[type as keyof iType];
        snackbar.innerHTML = `<div class="toast show position-absolute top-50 start-50 translate-middle ${typeClass}" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header float-end ${typeClass}">
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                ${message}
            </div>
        </div>`
    
        document.body.appendChild(snackbar);
    
        setTimeout(() => {
          snackbar.remove();
        }, 5000);
      }
    
    
}