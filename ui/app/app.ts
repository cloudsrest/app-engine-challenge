import {Component, ViewChild} from '@angular/core';
import {ionicBootstrap, Platform, Nav} from 'ionic-angular';
import {RecognitionProvider} from "./providers/recognition/recognition-provider";
import {UserProvider} from "./providers/user/user-provider";
import {ActivityPage} from "./pages/activity/activity";
import {LoginPage} from "./pages/login/login";
import {RecognitionCreateModal} from "./pages/recognition-create-modal/recognition-create-modal";

@Component({
  template: `
    <ion-nav [root]="rootPage"></ion-nav>
  `,
  providers: [UserProvider, RecognitionProvider]
})
export class MyApp {
    @ViewChild(Nav) nav: Nav;
    rootPage: any = RecognitionCreateModal;

    constructor(platform: Platform) {
        // platform.ready().then(() => {
        //   // Okay, so the platform is ready and our plugins are available.
        //   // Here you can do any higher level native things you might need.
        //   StatusBar.styleDefault();
        // });
    }
}

ionicBootstrap(MyApp, null, {
    prodMode: true,
    activator: 'highlight',
    mode: 'ios'
});
