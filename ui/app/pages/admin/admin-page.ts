import {Component} from '@angular/core';
import {NavController, LoadingController, Storage, LocalStorage} from 'ionic-angular';
import {Http, Response, Headers} from "@angular/http";
import {UserProvider} from "../../providers/user/user-provider";
import {User} from "../../models/user/user";
import {LoginPage} from "../login/login";
import {ActivityPage} from "../activity/activity";
import {RecognitionCreateModal} from "../recognition-create-modal/recognition-create-modal";

@Component({
    templateUrl: 'build/pages/admin/admin.html'
})
export class AdminPage {

    users: User[];
    pages: Array<{title: string, component: any, data?: any}>;
    private digest: string = 'Zmxhc2hkZXY6c2VjcmV0';

    constructor(private navCtrl: NavController, private loadingCtrl: LoadingController, private http: Http, private userProvider: UserProvider) {
        this.userProvider.all().subscribe((users: User[]) => {
            this.users = users;
        });
        this.pages = [
            {title: 'Home', component: ActivityPage, data: {view: 'recent'}},
            {title: 'Give Kudos', component: RecognitionCreateModal},
            {title: 'Notifications', component: ActivityPage, data: {view: 'notifications'}},
            // {title: 'Reports', component: ReportsPage}, // TODO
            {title: 'Sign Out', component: LoginPage}
        ];
    }

    enableUsers() {
        // TODO
    }

    openPage(page) {
        // Reset the content nav to have just this page
        // we wouldn't want the back button to show in this scenario
        this.navCtrl.setRoot(page.component, page.data);
    }
}
