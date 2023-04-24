import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { SnackbarService } from 'src/app/services/snackbar.service';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit {
  constructor(
    private apiService: ApiService,
    private actRoute: ActivatedRoute,
    private router: Router,
    private snackbarService: SnackbarService
  ) {}

  email: string = '';
  token: string = '';

  ngOnInit() {
    this.getParams();
  }

  getParams() {
    this.actRoute.params.subscribe((val: any) => {
      this.email = val.email;
      this.token = val.token;
      this.verifyToken();
    })
  }

  //Confrima
  verifyToken() {
    const url = API_URLS.EMAIL_VERIFY(this.email, this.token);
    this.apiService.get(url).subscribe((response: any)=> {
      this.snackbarService.openSnackbar('Email Verified Successfully', 'error');
      setTimeout(() => {
        this.router.navigate(['/']);
      }, 3000);
    }, (error: Error) => {
      this.snackbarService.openSnackbar(error?.message, 'error');
      this.router.navigate(['/']);
    })
  }
}
