import { Component, OnInit } from '@angular/core';
import { TicketService, Ticket, Event } from '../ticket.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  standalone: true,
  imports: [CommonModule],
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {
  myTickets: Ticket[] = [];
  errorMessage: string | null = null;

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getMyTickets().subscribe(
      (tickets: Ticket[]) => {
        console.log('Tickets:', tickets);
        this.myTickets = tickets;
        this.errorMessage = null;

        this.myTickets.forEach(ticket => {
          this.ticketService.getEventById(ticket.eventId).subscribe(
            (event: Event) => {
              ticket.eventName = event.name;
              ticket.eventLocation = event.location;
            }
          );
        });
      }
    );
  }

  deleteTicket(ticketId: number | undefined): void {
    this.ticketService.deleteTicket(ticketId).subscribe(
      () => {
        this.loadTickets();
      }
    );
  }
}
