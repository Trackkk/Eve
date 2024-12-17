import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Event {
  id: number;
  name: string;
  location: string;
  date: Date;
}

@Injectable({
  providedIn: 'root'
})
export class EventsService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Access-Control-Allow-Origin': '*'
    });
  }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/events`, { headers: this.getHeaders() });
  }

  saveEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(`${this.baseUrl}/events`, event, { headers: this.getHeaders() });
  }
}
