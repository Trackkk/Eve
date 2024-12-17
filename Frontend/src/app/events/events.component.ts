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
    RouterModule
  ],
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  events: Event[] = [];
  displayedColumns: string[] = ['name', 'location', 'date'];
  dataSource = new MatTableDataSource<Event>(this.events);

  newEvent: Event = { id: 0, name: '', location: '', date: new Date() };

  isCreating = false;

  constructor(private eventsService: EventsService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.eventsService.getAllEvents().subscribe(data => {
      this.events = data;
      this.dataSource.data = data;
    });
  }

  addEvent(): void {
    this.eventsService.saveEvent(this.newEvent).subscribe(() => {
      this.loadEvents();
      this.isCreating = false;
      this.resetNewEvent();
    });
  }

  resetNewEvent(): void {
    this.newEvent = { id: 0, name: '', location: '', date: new Date() };
  }
}
