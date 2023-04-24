import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { StorageService } from 'src/app/services/storage.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService,
    private actRoute: ActivatedRoute,
    private snackbarService: SnackbarService,
    public util: UtilService
  ) { }
  isAuthenticated = false;
  showPassword = false;
  isLoading = false;
  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/)]],
  });
  formErrors = {
    required: 'This field is required',
    email : {
      email: 'Incorrect email format'
    },
    password: {
      pattern: 'Password must contain: Minimum eight characters, at least one letter and one number.'  
    }
  }

  ngOnInit() {
    this.actRoute.queryParams.subscribe((qparams: any)=> {
        if (qparams?.token === 'expired') {
          this.snackbarService.openSnackbar('Token Expired! Please login again', 'error')
          this.storageService.deleteLocalItem('userdata');
          this.storageService.deleteLocalItem('isLoggedIn');
        }
    });
    if (this.storageService.getLocalItem('isLoggedIn')) {
      this.router.navigate(['/']);
    }
  }

  onFormSubmit() {
    if (this.loginForm.valid) {
      this.isLoading = true;
      //calling the auth service just to validate 
      this.authService.login({
        username_email: this.loginForm.value.email,
        password: this.loginForm.value.password
      }).subscribe(value => {
        this.isLoading = false;
        this.storageService.setLocalItem('userdata', value);
        this.isAuthenticated = true;
        this.router.navigate(['/']);
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
