import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { EventsComponent } from './events/events.component';
import { TicketComponent } from './ticket/ticket.component';
import { ParticipantComponent } from './participant/participant.component';

export const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'events', component: EventsComponent },
  { path: 'tickets', component: TicketComponent },
  { path: 'participant', component: ParticipantComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
