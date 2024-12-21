import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Event} from './events.service';

export interface Participant {
  id: number,
  name: string,
  email: string,
  password: string,
  phoneNumber: string
}

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Access-Control-Allow-Origin': '*'
    });
  }

  getParticipant(participantId: number): Observable<Participant> {
    return this.http.get<Participant>(`${this.baseUrl}/participants/${participantId}`, { headers: this.getHeaders() });
  }

  updateParticipant(participant: Participant): Observable<any> {
    return this.http.put(`${this.baseUrl}/participants/${participant.id}`, participant, { headers: this.getHeaders() });
  }

  deleteParticipant(participantId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/participants/${participantId}`, { headers: this.getHeaders() });
  }

}
