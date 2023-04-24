import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(
    private storageService: StorageService,
    private router: Router
  ) {}

  name = '';

  ngOnInit() {
    const userdata = this.storageService.getLocalItem('userdata') as string;
    this.name = JSON.parse(userdata).name;
  }

  onSearchClick(value: string) {
    this.router.navigate([`/locations/${value}`]);
  }
}
