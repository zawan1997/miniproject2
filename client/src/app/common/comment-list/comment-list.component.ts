import { Component, Input } from '@angular/core';
import { API_URLS } from 'src/api-urls';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent {
  constructor(
    private api: ApiService,
    private storageService: StorageService
  ) {}

  @Input() uuid = '';
  commentList: any = [];
  userId = 0;
  imageCommentList: any = [];

  //On initialising calls function and initialises user id
  //stores your userId and userData back into 
  ngOnInit() {
    this.getCommentList();
    this.getImageComments();
    //to store the current user id. to manipulate like if this is not a current user then cannot do this 
    this.userId = +JSON.parse(this.storageService.getLocalItem('userId')!);
  }

  //If user id in storage = current user id then it will give user data
  getCommentList() {
    const url = API_URLS.COMMENTS_LIST(this.uuid);
    this.api.get(url).subscribe((response: any) => {
      response.forEach((item: any) => {
        // if (item.userId === this.userId) {
        //   const name = JSON.parse(this.storageService.getLocalItem('userdata')!).name;
        //   item.name = name;
        // }
      })
      this.commentList = response;
    });
  }

  //When comment addded by anyone just push in
  onCommentAdded(event: any) {
    this.commentList.push(event);
  }

  getImageComments() {
    const url = API_URLS.IMAGE_COMMENTS(this.uuid);
    this.api.get(url).subscribe((response: any)=> {
      this.imageCommentList = response;
    })
  }

  onImageCommentAdded(event: any) {
    this.imageCommentList.push(event);
  }
}