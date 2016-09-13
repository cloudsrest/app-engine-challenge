import {Component} from '@angular/core';
import {NavController, LoadingController, Storage, LocalStorage} from 'ionic-angular';
import {Http, Response, Headers} from "@angular/http";
import {RegistrationPage} from "../register/register";
import {RecognitionCreateModal} from "../recognition-create-modal/recognition-create-modal";

@Component({
  templateUrl: 'build/pages/login/login.html'
})
export class LoginPage {
  username: string = '';
  password: string = '';
  loginUrl: string = '/api/oauth/token';
  digest: string = 'Zmxhc2hkZXY6c2VjcmV0';
  errorMsg: string;
  pages: Array<{title: string, component: any, data?: any}>;
  private storage: Storage;

  constructor(private navCtrl: NavController, private loadingCtrl: LoadingController, private http: Http) {
    this.storage = new Storage(LocalStorage);
    this.pages = [];
  }

  onPageWillEnter() {
    // clear local storage
    this.storage.clear();
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.navCtrl.setRoot(page.component, page.data);
  }

  goToCreateAccount() {
    this.navCtrl.setRoot(RegistrationPage);
  }

  public login() {
    if (this.loginIsValid()) {
      let url = `${this.loginUrl}?username=${this.username}&password=${this.password}&grant_type=password`;
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
    }
  }

  presentLoading() {
    let loader = this.loadingCtrl.create({
      content: "Please wait...",
      duration: 1000
    });
    loader.present();
  }

  private loginIsValid() {
    return this.username && this.password;
  }
}
