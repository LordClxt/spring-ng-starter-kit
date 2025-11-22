import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputText } from 'primeng/inputtext';
import { Message } from 'primeng/message';
import { ButtonModule } from 'primeng/button';
import { AuthControllerService, LoginDto } from '../../../api';
import { first } from 'rxjs';

@Component({
  selector: 'app-register-component',
  imports: [ReactiveFormsModule, InputText, Message, ButtonModule],
  templateUrl: './register-component.html',
  styleUrl: './register-component.css',
})
export class RegisterComponent {
  fb = inject(FormBuilder);
  authService = inject(AuthControllerService);

  form = this.fb.group({
    username: ['', [Validators.required]],
    firstname: ['', [Validators.required]],
    lastname: ['', [Validators.required]],
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  onSubmit() {
    const loginDto: LoginDto = {
      email: this.form.value.email!,
      firstName: this.form.value.firstname!,
      lastName: this.form.value.lastname!,
      username: this.form.value.username!,
      credentials: {
        type: "password",
        value: this.form.value.password!
      },
      enabled: true,
      emailVerified: false,
      attributes: {
        
      }
    }
    this.authService.register(loginDto).pipe(first()).subscribe({
      next() {
          
      },
    })
  }
}
