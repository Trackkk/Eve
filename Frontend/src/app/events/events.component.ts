import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EventsService, Event } from '../events.service';
import { RouterModule } from '@angular/router';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {AuthService} from '../auth.service';


@Component({
  selector: 'app-events',
  standalone: true,
  imports: [
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    CommonModule,
    FormsModule,
    RouterModule,
    MatDatepickerToggle,
    MatDatepicker,
    MatDatepickerInput
  ],
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  events: Event[] = [];
  filteredEvents: Event[] = [];
  searchTerm: string = '';
  startDate: string = '';
  endDate: string = '';

  showModal: boolean = false;
  isEditMode: boolean = false;

  currentEvent: Event = { id: 0, name: '', location: '', date: new Date(), creatorEmail: '' };

  currentUserEmail: string = '';

  constructor(private eventsService: EventsService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadEvents();
    this.getCurrentUserEmail();
  }

  loadEvents(): void {
    this.eventsService.getAllEvents().subscribe(data => {
      this.events = data.map(event => ({
        ...event,
        date: new Date(event.date)
      }));
      this.filterEvents();
    });
  }


  filterEvents(): void {
    this.filteredEvents = this.events.filter(event => {
      const matchesSearch =
        event.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        event.location.toLowerCase().includes(this.searchTerm.toLowerCase());

      const matchesStartDate = !this.startDate || new Date(event.date) >= new Date(this.startDate);
      const matchesEndDate = !this.endDate || new Date(event.date) <= new Date(this.endDate);

      return matchesSearch && matchesStartDate && matchesEndDate;
    });
  }

  openCreateForm(): void {
    this.isEditMode = false;
    this.currentEvent = { id: 0, name: '', location: '', date: new Date(), creatorEmail: '' };
    this.showModal = true;
  }

  openEditForm(event: Event): void {
    if (event.creatorEmail === this.currentUserEmail) {
      this.isEditMode = true;
      this.currentEvent = { ...event };
      this.showModal = true;
    }
  }

  saveEvent(): void {
    this.currentEvent.date = new Date(this.currentEvent.date);

    if (this.isEditMode) {
      this.eventsService.saveEvent(this.currentEvent).subscribe(() => this.loadEvents());
    } else {
      this.eventsService.saveEvent(this.currentEvent).subscribe(() => this.loadEvents());
    }
    this.closeModal();
  }

  deleteEvent(eventId: number): void {
    this.eventsService.deleteEvent(eventId).subscribe(() => this.loadEvents());
  }

  closeModal(): void {
    this.showModal = false;
  }

  buyTicket(eventId: number): void {
    console.log('Ticket purchased for event ID:', eventId);
  }

  getCurrentUserEmail(): void {
    this.authService.getUserEmail().subscribe({
      next: (email) => {
        this.currentUserEmail = email;
      },
      error: (err) => {
        console.error('Failed to fetch user email:', err);
        alert('Unable to fetch user email. Please log in again.');
      }
    });
  }

}
