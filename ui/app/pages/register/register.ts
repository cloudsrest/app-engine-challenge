import {Component} from '@angular/core';
import {NavController, LoadingController, Storage, LocalStorage} from 'ionic-angular';
import {ActivityPage} from "../activity/activity";
import {Http, Response, Headers} from "@angular/http";
import {LoginPage} from "../login/login";
import {RecognitionCreateModal} from "../recognition-create-modal/recognition-create-modal";

@Component({
    templateUrl: 'build/pages/register/register.html'
})
export class RegistrationPage {
    registrationUser: any;
    errorMsg: string;
    private digest: string = 'Zmxhc2hkZXY6c2VjcmV0';
    private endpoint: string = '/api/public/register';
    private loginUrl: string = '/api/oauth/token';
    private storage: Storage;

    constructor(private navCtrl: NavController, private loadingCtrl: LoadingController, private http: Http) {
        this.storage = new Storage(LocalStorage);
        this.registrationUser = {admin: false};
    }

    createAccount() {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this.http.post(this.endpoint, JSON.stringify(this.registrationUser), {headers: headers}).subscribe(() => {
            // make a call to authenticate the user and fetch an access token
            let url = `${this.loginUrl}?username=${this.registrationUser.username}&password=${this.registrationUser.password}&grant_type=password`;
            let headers = new Headers();
            headers.append('Authorization', `Basic ${this.digest}`);
            this.presentLoading();
            this.http.post(url, '', {headers: headers}).subscribe((res: Response) => {
                let accessToken = res.json().access_token;
                let refreshToken = res.json().refresh_token;
                this.storage.set('access_token', accessToken);
                this.storage.set('refresh_token', refreshToken);
                this.navCtrl.setRoot(RecognitionCreateModal);
            }, () => {
                this.errorMsg = 'Unable to login';
            });
        }, (err: Response) => {
            this.errorMsg = err.statusText;
        });
        this.presentLoading();
    }

    goToSignIn() {
        this.navCtrl.setRoot(LoginPage);
    }

    presentLoading() {
        let loader = this.loadingCtrl.create({
            content: "Please wait...",
            duration: 1000
        });
        loader.present();
    }
}
