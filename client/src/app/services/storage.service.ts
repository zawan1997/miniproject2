import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class StorageService {
    constructor() {}

    getLocalItem(name: string) {
        return localStorage.getItem(name);
    }

    setLocalItem(name: string, value: string | Object) {
        localStorage.setItem(name, JSON.stringify(value));
    }

    deleteLocalItem(name: string) {
        localStorage.removeItem(name);
    }

    getSessionItem(name: string) {
        sessionStorage.getItem(name);
    }

    setSessionItem(name: string, value: string | Object) {
        sessionStorage.setItem(name, JSON.stringify(value));
    }

    deleteSessionItem(name: string) {
        sessionStorage.removeItem(name);
    }
}
