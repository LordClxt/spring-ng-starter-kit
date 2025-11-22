import { Component, signal, resource, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

const PAGE_SIZE = 20;

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
  
  onButtonClick() {}
}
