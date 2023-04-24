import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { LayoutsComponent } from './common/layouts/layouts.component';
import { EditProfileComponent } from './pages/edit-profile/edit-profile.component';
import { HomeComponent } from './pages/home/home.component';
import { LocationDetailsComponent } from './pages/location-details/location-details.component';
import { LocationListComponent } from './pages/location-list/location-list.component';
import { LoginComponent } from './pages/login/login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { SignupComponent } from './pages/signup/signup.component';
import { VerifyEmailComponent } from './pages/verify-email/verify-email.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutsComponent,
    children: [
      {path: 'home', component: HomeComponent,canActivate: [AuthGuard]},
      {path: 'login',component: LoginComponent},
      {path: 'signup',component: SignupComponent},
      {path: 'locations/:search', component: LocationListComponent,canActivate: [AuthGuard]},
      {path: 'locations/details/:uuid',component: LocationDetailsComponent,canActivate: [AuthGuard]},
      {path: 'users/verifyEmail/:email/:token',component: VerifyEmailComponent},
      {path: 'edit-profile',component: EditProfileComponent,canActivate: [AuthGuard]},
      {path: '',redirectTo: '/home',pathMatch: 'full'},
      {path: '**',component: NotFoundComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


 }
