import { Component, signal, resource, inject } from '@angular/core';

const PAGE_SIZE = 20;

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
  
  onButtonClick() {}
}
