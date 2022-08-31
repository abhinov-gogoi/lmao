import { Route } from '@angular/router';
import { AuthEmailConfirmedComponent } from './email-confirmed.component';

export const emailConfirmedRoutes: Route[] = [
    {
        path: '',
        component: AuthEmailConfirmedComponent
    }
];
