import { Component, EventEmitter, Input, Output } from '@angular/core';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent {
  constructor(
    private api: ApiService,
    private storageService: StorageService,
    private snackbarService: SnackbarService
  ) {}

  @Input() uuid = '';
  //Listening for new comment and image to be addded
  @Output() commentAdded = new EventEmitter();
  @Output() imageCommentAdded = new EventEmitter();
  fileName = '';

  //adding an image
  addAttachment(fileRef: HTMLInputElement) {
    fileRef.click();
  }

  //accepts png and jpg events
  onFileInputChange(event: Event) {
    const file: any = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      this.fileName = file?.name;

      //Reading the image file. takes result as string and put into base64
      const reader = new FileReader();
      if (file.size / 1024 > 50) {
        this.snackbarService.openSnackbar('File too big! only 50kb or less size file allowed', 'error');
        return ;
      }
      reader.onload = (e: any) => {
        //Reads te result and stores into variable base64
        const base64String = reader.result as string;
        const userId: any = +JSON.parse(this.storageService.getLocalItem('userId')!);
        const params = {
          userId,
          locationUuid: this.uuid,
          blobc: base64String
        }
        const url = API_URLS.IMAGE_POST;
        //To complete the URL
        this.api.post(url, params).subscribe((response: any)=> {
          this.imageCommentAdded.emit(response);
          this.snackbarService.openSnackbar('File uploaded successfully', 'info');
        });

      };
      reader.readAsDataURL(file);
    }

  }

  submitTextComment(commentRef: any){
    const userId = +JSON.parse(this.storageService.getLocalItem('userId')!);
    const params = {
      userId,
      locationUuid: this.uuid,
      text: commentRef.value
    }
    const url = API_URLS.COMMENTS_CREATE;
    this.api.post(url, params).subscribe((response: any) => {
      this.commentAdded.emit(response);
      commentRef.value = '';
    })
  }
}

