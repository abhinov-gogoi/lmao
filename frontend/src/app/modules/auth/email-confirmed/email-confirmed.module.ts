import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { FuseCardModule } from '@fuse/components/card';
import { SharedModule } from 'app/shared/shared.module';
import { emailConfirmedRoutes } from 'app/modules/auth/email-confirmed/email-confirmed.routing';
import { AuthEmailConfirmedComponent } from './email-confirmed.component';

@NgModule({
    declarations: [
        AuthEmailConfirmedComponent
    ],
    imports: [
        RouterModule.forChild(emailConfirmedRoutes),
        MatButtonModule,
        FuseCardModule,
        SharedModule
    ]
})
export class AuthEmailConfirmedModule {
}