import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from '../auth.service';
import { ParticipantService, Participant } from '../participant.service';
import {FormsModule} from '@angular/forms';
import {CommonModule, NgClass} from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-participant',
  imports: [
    FormsModule,
    NgClass,
    CommonModule
  ],
  templateUrl: './participant.component.html',
  styleUrl: './participant.component.css'
})
export class ParticipantComponent implements OnInit {
  participant: { password: string; phoneNumber: string; name: string; id: number; email: string } = {
    id: 0,
    name: '',
    email: '',
    password: '',
    phoneNumber: ''
  };

  passwordVisible: boolean = false;

  constructor(
    private participantService: ParticipantService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadParticipant();
  }

  loadParticipant(): void {
    this.authService.getUserEmail().subscribe(email => {
      this.authService.getParticipantIdByEmail(email).subscribe(participantId => {
        this.participantService.getParticipant(participantId).subscribe(data => {
          this.participant.id = data.id;
          this.participant.name = data.name;
          this.participant.email = data.email;
          this.participant.phoneNumber = data.phoneNumber;
        });
      });
    });
  }

  updateParticipant(): void {
    this.authService.getUserEmail().subscribe(email => {
      this.authService.getParticipantIdByEmail(email).subscribe(participantId => {
        this.participantService.updateParticipant(this.participant).subscribe({
          next: () => {
            console.log(`Participant Updated`);
            alert('Participant updated successfully!');
          }
        });
      });
    });
  }

  deleteParticipant(): void {
    if (confirm('Are you sure you want to delete your account?')) {
      this.participantService.deleteParticipant(this.participant.id).subscribe(() => {
        alert('Account deleted successfully!');
        localStorage.clear();
        this.router.navigate(['/events']);
      });
    }
  }

  goToEvents(): void {
    this.router.navigate(['/events']);
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
  }
}
