import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  constructor(
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService,
    public util: UtilService
  ) {}

  formErrors = {
    emailId: {
      email: 'Email is invalid'
    },
    password: {
      pattern: 'Password must consist of combination of letters and atleast a number and no special characters'
    },
    required: 'This field is required'
  }

  showPassword = false;

  signupForm = new FormGroup({
    name: new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    emailId: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/)]),
    agree: new FormControl(false, Validators.required)
  });

  onFormSubmit() {
    if (this.signupForm.valid) {
      const params = {
        ...this.signupForm.value
      }
      delete params.agree;
      this.authService.register(params).subscribe((response: any) => {
        const user = {
          username: this.signupForm.value.username,
          name: this.signupForm.value.name,
          email: this.signupForm.value.emailId,
        }
        //At sign up stores user data as constant user 
        this.storageService.setLocalItem('userdata', user);
        this.router.navigate(['/login']);
      });
    } else {
      this.signupForm.markAllAsTouched();
    }
  }

}
