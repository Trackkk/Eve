import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { EventsComponent } from './events/events.component';

export const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'events', component: EventsComponent },
  { path: '', redirectTo: '/registration', pathMatch: 'full' },
];
