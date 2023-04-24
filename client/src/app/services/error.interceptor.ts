import {
    HttpEvent,
    HttpHandler,
    HttpRequest,
    HttpErrorResponse,
    HttpInterceptor
} from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { SnackbarService } from './snackbar.service';

@Injectable({
    providedIn: 'root'
})
export class ErrorIntercept implements HttpInterceptor {
    constructor(
        private snackbarService: SnackbarService
    ) {}
    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        return next.handle(request)
            .pipe(
                retry(1),
                catchError((error: HttpErrorResponse) => {
                    let errorMessage = '';
                    if (error.error instanceof ErrorEvent) {
                        // client-side error
                        errorMessage = `Error: ${error.error.message}`;
                        this.snackbarService.openSnackbar(errorMessage, 'error');
                    } else {
                        // server-side error
                        errorMessage = `Error Status: ${error.status}\nMessage: ${error.message}`;
                        this.snackbarService.openSnackbar(errorMessage, 'error');
                    }
                    return throwError(() => errorMessage);
                })
            )
    }
}