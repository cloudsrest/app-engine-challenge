import {Component} from "@angular/core";
import {RecognitionProvider} from "../../providers/recognition/recognition-provider";
import {ViewController, NavController} from "ionic-angular";
import {UserProvider} from "../../providers/user/user-provider";
import {User} from "../../models/user/user";
import {Recognition} from "../../models/recognition/recognition";
import {ActivityPage} from "../activity/activity";
import {LoginPage} from "../login/login";

@Component({
  templateUrl: './build/pages/recognition-create-modal/recognition-create-modal.html',
  providers: [RecognitionProvider, UserProvider]
})

export class RecognitionCreateModal {

  users: User[];
  recognitionTypes: string[];
  currentUser: User;
  recognitionUser: number;
  recognitionType: string;
  recognitionComment: string;
  pages: Array<{title: string, component: any, data?: any}>;
  errorMsg: string;

  constructor(private recognitionProvider: RecognitionProvider, private viewCtrl: ViewController, private userProvider: UserProvider, private navCtrl: NavController) {
    this.userProvider.load().subscribe((users: User[]) => {
      this.users = users;
    });
    this.userProvider.currentUser().subscribe((currentUser: User) => {
      this.currentUser = currentUser;
    });
    this.recognitionProvider.recognitionTypes().subscribe((types: string[]) => {
      this.recognitionTypes = types;
    });
    this.pages = [
      {title: 'Home', component: ActivityPage, data: {view: 'recent'}},
      {title: 'Give Kudos', component: RecognitionCreateModal},
      {title: 'Notifications', component: ActivityPage, data: {view: 'notifications'}},
      // {title: 'Reports', component: ReportsPage}, // TODO
      {title: 'Sign Out', component: LoginPage}
    ];
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.navCtrl.setRoot(page.component, page.data);
  }

  dismiss(data?: any) {
    this.viewCtrl.dismiss(data);
  }

  save() {
    let recognition = new Recognition({
      toUserId: this.recognitionUser,
      fromUserId: this.currentUser.getId(),
      type: this.recognitionType,
      comment: this.recognitionComment
    });

    this.recognitionProvider.create(recognition).subscribe((res: Recognition) => {
      this.navCtrl.setRoot(ActivityPage, {reload: true});
    }, () => {
      this.errorMsg = 'Unable to give recognition';
    });
  }

  getIcon(type: string) {
    return Recognition.getIcon(type);
  }

}
