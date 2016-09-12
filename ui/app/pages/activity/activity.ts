import {Component} from '@angular/core';
import {NavController, ModalController, NavParams} from 'ionic-angular';
import {RecognitionProvider} from "../../providers/recognition/recognition-provider";
import {Recognition} from "../../models/recognition/recognition";
import {UserProvider} from "../../providers/user/user-provider";
import {User} from "../../models/user/user";
import {LoginPage} from "../login/login";
import {TimeAgoPipe} from "angular2-moment";
import {RecognitionCreateModal} from "../recognition-create-modal/recognition-create-modal";
import {Response} from "@angular/http";
import {OrderByPipe} from "../../pipes/order-by";

@Component({
  templateUrl: 'build/pages/activity/activity.html',
  pipes: [TimeAgoPipe, OrderByPipe]
})
export class ActivityPage {

  static ACTIVITY: any = {
    recent: 'recent',
    notifications: 'notifications'
  };
  recognitions: Recognition[];
  currentView: string;
  loading: boolean;
  pages: Array<{title: string, component: any, data?: any}>;
  reloadOnEnter: boolean;

  constructor(private navCtrl: NavController, params: NavParams, private recData: RecognitionProvider, private userData: UserProvider, private modalCtrl: ModalController) {
    this.recognitions = [];
    // allow context to switch between notifications and recent activity using the same page
    this.currentView = params.data.view || ActivityPage.ACTIVITY.recent;
    this.reloadOnEnter = !!params.data.reload;
    this.pages = [
      {title: 'Home', component: ActivityPage, data: {view: 'recent'}},
      {title: 'Give Kudos', component: RecognitionCreateModal},
      {title: 'Notifications', component: ActivityPage, data: {view: 'notifications'}},
      // {title: 'Reports', component: ReportsPage}, // TODO
      {title: 'Sign Out', component: LoginPage}
    ];
  }

  // load data before page becomes active
  onPageWillEnter() {
    this.loading = true;
    this.userData.currentUser().subscribe(() => {
      // current user is logged in, load recognitions
      if (this.currentView === ActivityPage.ACTIVITY.recent) {
        this.showRecentActivity()
      } else {
        this.showNotifications();
      }
    }, (res) => {
      // revert to login page
      this.errorHandler(res);
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.navCtrl.setRoot(page.component, page.data);
  }

  // clear data before page becomes deactivated
  onPageWillLeave() {
    this.recognitions = [];
  }

  // show the modal where the user can create a new recognition
  openRecognitionCreate() {
    let modal = this.modalCtrl.create(RecognitionCreateModal);
    modal.onDidDismiss((data?:any) => {
      if (data) {
        this.loadAllRecognitions(true);
      }
    });
    modal.present();
  }

  // load "recent activity"
  loadAllRecognitions(reload?: boolean) {
    this.recData.load(reload).subscribe((recognitions: Recognition[]) => {
      this.recognitions = this.mapUsersToRecognitions(recognitions);
      this.loading = false;
    }, (res) => {
      this.errorHandler(res);
    });
  }

  // load "notifications"
  loadRecognitionsForCurrentUser() {
    this.recData.allForCurrentUser().subscribe((recognitions: Recognition[]) => {
      this.recognitions = this.mapUsersToRecognitions(recognitions);
      this.loading = false;
    }, (res) => {
      this.errorHandler(res);
    });
  }

  // set the recognitions context to "recent activity"
  showRecentActivity() {
    this.loadAllRecognitions(this.reloadOnEnter);
  }

  // set the recognitions context to "notifications"
  showNotifications() {
    this.loadRecognitionsForCurrentUser();
  }

  // what type of content is being displayed on the page
  getViewTitle(): string {
    if (this.currentView === ActivityPage.ACTIVITY.notifications) {
      return 'Notifications';
    } else {
      return 'Recent Activity';
    }
  }

  private mapUsersToRecognitions(recognitions) {
    return recognitions.map((recognition: Recognition) => {
      this.userData.get(recognition.getToUserId()).subscribe((user: User) => {
        recognition.setToUser(user);
      });
      this.userData.get(recognition.getFromUserId()).subscribe((user: User) => {
        recognition.setFromUser(user);
      });
      return recognition;
    });
  }

  // generic error handler, send user to login page on unauthorized response
  private errorHandler(res: Response) {
    if (!res || (res && res.status === 401)) {
      this.navCtrl.setRoot(LoginPage);
    }
    console.log(res);
    this.loading = false;
  }

}
