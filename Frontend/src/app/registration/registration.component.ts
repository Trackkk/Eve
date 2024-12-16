import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  name = '';
  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    const registrationData = {
      name: this.name,
      email: this.email,
      password: this.password
    };

    this.authService.register(registrationData).subscribe(
      (response: string) => {
        const jsonResponse = { token: response };
        console.log('Sikeres regisztráció, token:', jsonResponse);
        this.router.navigate(['/login']);
      },
      error => {
        console.error('Hiba történt a regisztráció során', error);
      }
    );
  }
}
