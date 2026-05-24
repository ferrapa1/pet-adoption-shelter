import { Component, signal, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface Greeting {
  message: string;
}

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  protected readonly title = signal('pet-adoption-ui');
  protected readonly greetingMessage = signal<string | null>(null);

  private http = inject(HttpClient);

  ngOnInit() {
    this.fetchGreeting();
  }

  fetchGreeting(name?: string) {
    const url = name ? `/api/greeting?name=${name}` : '/api/greeting';

    this.http.get<Greeting>(url).subscribe({
      next: (response) => {
        this.greetingMessage.set(response.message);
      },
      error: (error) => {
        console.error('Error fetching greeting', error);
        this.greetingMessage.set('Failed to load greeting.');
      }
    });
  }
}
