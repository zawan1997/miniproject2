import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { StorageService } from 'src/app/services/storage.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent {

  showPassword = false;
  profilePicValue = '';
  
  formErrors = {
    required: 'This field is required',
    pattern: 'Must contain letters and numbers and no special characters'
  }

  userForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    agreeUser: new FormControl('', [Validators.required])
  })

  changePasswordForm = new FormGroup({
    password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/)]),
    agreePass: new FormControl('', [Validators.required])
  });

  profilePicForm = new FormGroup({
    profilePic: new FormControl('', [Validators.required]),
    agreePic: new FormControl('', [Validators.required])
  });

  constructor(
    private api: ApiService,
    private storageService: StorageService,
    private snackbarService: SnackbarService,
    public util: UtilService
  ) {}
  

  ngOnInit() {
    // this.getProfilePic();
  }
  
  getProfilePic(){
    const userId = +JSON.parse(this.storageService.getLocalItem('userId')!);
    const url = API_URLS.GET_USER_IMAGE(userId);
    this.api.get(url).subscribe((response:any) => {
      console.log(response);
    });
  }

  onUserFormSubmit() {
    if (this.userForm.valid) {
      const userId = +JSON.parse(this.storageService.getLocalItem('userId')!)
      const params = {
         id: userId,
          ...this.userForm.value,
      }
      delete params.agreeUser;
      const url = API_URLS.UPDATE_PROFILE;
      this.api.put(url, params).subscribe((response: any) => {
        const userdata: any =  JSON.parse(this.storageService.getLocalItem('userdata')!);
        userdata.name = this.userForm.value.name;
        userdata.username = this.userForm.value.username;
        this.storageService.setLocalItem('userdata', userdata);
        this.snackbarService.openSnackbar('Updated successfully', 'info');
      });
    } else {
      this.userForm.markAllAsTouched();
    }
  }

  onChangePasswordSubmit() {
    if (this.changePasswordForm.valid) {
      const url = API_URLS.UPDATE_PASSWORD;
      const userId = +JSON.parse(this.storageService.getLocalItem('userId')!);
      const params = {
        id: userId,
        ...this.changePasswordForm.value
      }
      delete params.agreePass;
      this.api.put(url, params).subscribe((response: any) => {
        this.snackbarService.openSnackbar('Updated successfully', 'info');
      })
    } else {
      this.changePasswordForm.markAllAsTouched();
    }

  }

  onProfilePicUpdated() {
    if (this.profilePicForm.valid) {
      const userId = +JSON.parse(this.storageService.getLocalItem('userId')!)
      const file: any = this.profilePicForm.value.profilePic;
      const fd = new FormData();
      fd.append('file', file);
      const url = API_URLS.FILE_UPLOAD(userId + "");
      this.api.post(url, fd).subscribe((response: any) => {
        console.log(response);
        this.snackbarService.openSnackbar('Updated successfully', 'info');
      })
    } else {
      this.profilePicForm.markAllAsTouched();
    }
  }

  onFileChange(event: any) {
    this.profilePicForm.patchValue({
      profilePic: event.target.files[0]
    })
  }


}
