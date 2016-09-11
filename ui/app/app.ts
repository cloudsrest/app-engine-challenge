import {Component, ViewChild} from '@angular/core';
import {ionicBootstrap, Platform, Nav} from 'ionic-angular';
import {RecognitionProvider} from "./providers/recognition/recognition-provider";
import {UserProvider} from "./providers/user/user-provider";
import {ActivityPage} from "./pages/activity/activity";
import {LoginPage} from "./pages/login/login";

@Component({
  template: `
    <ion-menu [content]="content">
      <ion-toolbar>
        <ion-title>Menu</ion-title>
      </ion-toolbar>
      <ion-content>
        <ion-list>
          <button menuClose ion-item *ngFor="let p of pages" (click)="openPage(p)">
            {{p.title}}
          </button>
        </ion-list>
      </ion-content>
    </ion-menu>
    <ion-nav [root]="rootPage" #content swipeBackEnabled="false"></ion-nav>`,
  providers: [UserProvider, RecognitionProvider]
})
export class MyApp {
    @ViewChild(Nav) nav: Nav;
    rootPage: any = ActivityPage;
    pages: Array<{title: string, component: any, data?: any}>;

    constructor(platform: Platform) {
        // platform.ready().then(() => {
        //   // Okay, so the platform is ready and our plugins are available.
        //   // Here you can do any higher level native things you might need.
        //   StatusBar.styleDefault();
        // });
        this.pages = [
            {title: 'Activity', component: ActivityPage, data: {view: 'recent'}},
            {title: 'Notifications', component: ActivityPage, data: {view: 'notifications'}},
            {title: 'Sign Out', component: LoginPage}
        ];
    }

    openPage(page) {
        // Reset the content nav to have just this page
        // we wouldn't want the back button to show in this scenario
        this.nav.setRoot(page.component, page.data);
    }
}

ionicBootstrap(MyApp, null, {
    prodMode: true,
    activator: 'highlight',
    mode: 'ios'
});
