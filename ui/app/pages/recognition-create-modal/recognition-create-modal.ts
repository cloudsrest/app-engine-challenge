import {Component} from "@angular/core";
import {RecognitionProvider} from "../../providers/recognition/recognition-provider";
import {ViewController, NavController} from "ionic-angular";
import {UserProvider} from "../../providers/user/user-provider";
import {User} from "../../models/user/user";
import {Recognition} from "../../models/recognition/recognition";
import {ActivityPage} from "../activity/activity";
import {LoginPage} from "../login/login";
import {Response, Http, Headers} from "@angular/http";
import {CapitalizePipe} from "../../pipes/capitalize";

@Component({
  templateUrl: './build/pages/recognition-create-modal/recognition-create-modal.html',
  providers: [RecognitionProvider, UserProvider],
  pipes: [CapitalizePipe]
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
  successAlertShown: boolean;
  showAddUserForm: boolean;
  userToAdd: any;

  constructor(private recognitionProvider: RecognitionProvider, private viewCtrl: ViewController, private userProvider: UserProvider, private navCtrl: NavController, private http: Http) {
    this.userProvider.currentUser().subscribe((currentUser: User) => {
      this.currentUser = currentUser;
      this.loadUsers();
      this.loadRecognitionTypes();
    }, (err: Response) => {
      this.errorHandler(err);
    });
    this.successAlertShown = false;
    this.showAddUserForm = false;
    this.userToAdd = {};
    this.pages = [
      {title: 'Home', component: ActivityPage, data: {view: 'recent'}},
      {title: 'Give Kudos', component: RecognitionCreateModal},
      {title: 'Notifications', component: ActivityPage, data: {view: 'notifications'}},
      // {title: 'Reports', component: ReportsPage}, // TODO
      {title: 'Sign Out', component: LoginPage}
    ];
  }

  loadRecognitionTypes() {
    this.recognitionProvider.recognitionTypes().subscribe((types: string[]) => {
      this.recognitionTypes = types;
    });
  }

  loadUsers() {
    this.userProvider.all().subscribe((users: User[]) => {
      this.users = users;
    });
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
    if (this.validUserForAdd()) {
      this.addUser().subscribe((res: Response) => {
        this.userToAdd = null;
        this.recognitionUser = res.json().id;
        this.createRecognition();
      });
    } else {
      this.createRecognition();
    }
  }

  createRecognition() {
    let recognition = new Recognition({
      toUserId: this.recognitionUser,
      fromUserId: this.currentUser.getId(),
      type: this.recognitionType,
      comment: this.recognitionComment
    });

    this.recognitionProvider.create(recognition).subscribe(() => {
      this.showSuccessAlert();
      this.loadUsers();
      this.reset();
    }, () => {
      this.errorMsg = 'Unable to give recognition';
    });
  }

  addUser() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post('/api/public/register', JSON.stringify(this.userToAdd), {headers: headers});
  }

  validUserForAdd() {
    return this.userToAdd.firstName && this.userToAdd.lastName && this.userToAdd.email;
  }

  getIcon(type: string) {
    return Recognition.getIcon(type);
  }

  showSuccessAlert() {
    this.successAlertShown = true;
    setTimeout(() => {
      this.successAlertShown = false;
    }, 5000);
  }

  updateUserCanGiveToFlag(userId) {
    this.userProvider.get(userId).subscribe((user: User) => {
      if (user) {
        user.setCanGiveTo(false);
      }
    });
  }

  // generic error handler, send user to login page on unauthorized response
  private errorHandler(res: Response) {
    if (!res || (res && res.status === 401)) {
      this.navCtrl.setRoot(LoginPage);
    }
    console.log(res);
  }

  private reset() {
    this.recognitionComment = null;
    this.recognitionType = null;
    this.recognitionUser = null;
  }

}
