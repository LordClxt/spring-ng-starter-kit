import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { RegisterComponent } from './features/auth/register-component/register-component';

export const routes: Routes = [
    {
        path: "",
        component: Home
    },
    {
        path: "auth/register",
        component: RegisterComponent
    }
];
