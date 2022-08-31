import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { FuseAlertType } from '@fuse/components/alert';
import { AuthService } from 'app/core/auth/auth.service';

@Component({
    selector: 'auth-sign-up',
    templateUrl: './sign-up.component.html',
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})
export class AuthSignUpComponent implements OnInit {
    @ViewChild('signUpNgForm') signUpNgForm: NgForm;

    alert: { type: FuseAlertType; message: string } = {
        type: 'success',
        message: ''
    };
    signUpForm: FormGroup;
    showAlert: boolean = false;

    /**
     * Constructor
     */
    constructor(
        private _authService: AuthService,
        private _formBuilder: FormBuilder,
        private _router: Router
    ) {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        // Create the form
        this.signUpForm = this._formBuilder.group({
            name: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required],
            agreements: ['', Validators.requiredTrue]
        }
        );
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Sign up
     */
    signUp(): void {
        // Do nothing if the form is invalid
        if (this.signUpForm.invalid) {
            return;
        }

        //Make the request body
        var payload = {
            "name": "" + this.signUpForm.value.name.trim() + "",
            "login_id": "" + this.signUpForm.value.email.trim() + "",
            "password": "" + this.signUpForm.value.password.trim() + "",
            "device_type": "W",
            "device_os": "angular webapp",
            "device_token": "YxueNFEN1lqhXNO6JelqZ"
        }

        // create the request body
        var body = JSON.stringify(payload);
        // create request header
        const headers = { "Content-Type": "application/json" };

        // Disable the form
        this.signUpForm.disable();

        // Hide the alert
        this.showAlert = false;

        // Sign up
        this._authService.signUp(payload, headers)
            .subscribe(
                (response) => {
                    console.log(response)
                    if (response.STATUS === 'Success') {
                        this._router.navigateByUrl('/confirmation-required');
                    } else {
                        // Re-enable the form
                        this.signUpForm.enable();

                        // Set the alert
                        this.alert = {
                            type: 'error',
                            message: response.MESSAGE
                        };
                        // Show the alert
                        this.showAlert = true;
                    }

                },
                (error) => {
                    console.log(error)
                    // Re-enable the form
                    this.signUpForm.enable();

                    // Reset the form
                    this.signUpNgForm.resetForm();

                    // Set the alert
                    this.alert = {
                        type: 'error',
                        message: 'Something went wrong, please try again.'
                    };

                    // Show the alert
                    this.showAlert = true;
                }
            );
    }
}
