import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Ticket {
  id?: number;
  price: number;
  category: string;
  eventId: number;
  participantId: number;
  eventName?: string;
  eventLocation?: string;
}

export interface Event {
  id: number;
  name: string;
  location: string;
  date: string;
  creatorEmail: string;
}

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private baseUrl = 'http://localhost:8080/api/tickets';
  private eventsUrl = 'http://localhost:8080/api/events';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Content-Type': 'application/json',
    });
  }

  createTicket(ticket: Partial<Ticket>): Observable<Ticket> {
    return this.http.post<Ticket>(this.baseUrl, ticket, { headers: this.getHeaders() });
  }

  getMyTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.baseUrl}/myTickets`, { headers: this.getHeaders() });
  }

  getEventById(eventId: number): Observable<Event> {
    return this.http.get<Event>(`${this.eventsUrl}/${eventId}`, { headers: this.getHeaders() });
  }

  deleteTicket(ticketId: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${ticketId}`, { headers: this.getHeaders() });
  }

}
