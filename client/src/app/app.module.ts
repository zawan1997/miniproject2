import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './common/footer/footer.component';
import { HeaderComponent } from './common/header/header.component';
import { LayoutsComponent } from './common/layouts/layouts.component';
import { HomeComponent } from './pages/home/home.component';
import { LocationDetailsComponent } from './pages/location-details/location-details.component';
import { LocationListComponent } from './pages/location-list/location-list.component';
import { LoginComponent } from './pages/login/login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { SignupComponent } from './pages/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VerifyEmailComponent } from './pages/verify-email/verify-email.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LocationCardComponent } from './common/location-card/location-card.component';
import { CommentListComponent } from './common/comment-list/comment-list.component';
import { AddCommentComponent } from './common/add-comment/add-comment.component';
import { EditProfileComponent } from './pages/edit-profile/edit-profile.component';
import { ErrorIntercept } from './services/error.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LocationListComponent,
    LocationDetailsComponent,
    LayoutsComponent,
    NotFoundComponent,
    SignupComponent,
    VerifyEmailComponent,
    LocationCardComponent,
    CommentListComponent,
    AddCommentComponent,
    EditProfileComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorIntercept,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }