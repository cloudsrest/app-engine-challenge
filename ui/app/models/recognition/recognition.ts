import {User} from "../user/user";
export class Recognition {
  private fromUserId: number;
  private toUserId: number;
  private type: string;
  private comment: string;
  private toUser: User;
  private fromUser: User;
  private timestamp: string;

  constructor(data: any) {
    this.fromUserId = data.fromUserId;
    this.toUserId = data.toUserId;
    this.type = data.type;
    this.comment = data.comment;
    this.toUser = data.toUser || null;
    this.fromUser = data.fromUser || null;
    this.timestamp = data.timestamp || ((new Date() as any).getTime());
  }

  getComment(): string {
    return this.comment;
  }

  getFromUserId(): number {
    return this.fromUserId;
  }

  getToUserId(): number {
    return this.toUserId;
  }

  getType(): string {
    return this.type;
  }

  toJson(): string {
    return JSON.stringify(this.asJson());
  }

  setToUser(user: User) {
    this.toUser = user;
  }

  getToUser(): User {
    return this.toUser;
  }

  getFromUser(): User {
    return this.fromUser;
  }

  getCreatedAt(): Date {
    return this.timestamp && new Date(this.timestamp) || new Date();
  }

  getIcon(): string {
    return Recognition.getIcon(this.getType());
  }

  setFromUser(user: User) {
    this.fromUser = user;
  }

  asJson(): any {
    return {
      fromUserId: this.getFromUserId(),
      toUserId: this.getToUserId(),
      type: this.getType(),
      comment: this.getComment()
    };
  }

  static asRecognitions(recognitions: any[]): Recognition[] {
    return recognitions.map((recognition: any) => {
      return new Recognition(recognition);
    });
  }

  static getIcon(type: string): string {
    let icon;
    switch (type) {
      case 'TEAMWORK':
        icon = 'img/team.svg';
        break;
      case 'EXPERIMENT':
        icon = 'img/brain.svg';
        break;
      case 'IMPROVEMENT':
        icon = 'img/thumbs-up.svg';
            break;
      case 'DELIVERY':
        icon = 'img/light-bulb.svg';
            break;
      default:
        icon = 'img/thumbs-up.svg';
            break;
    }
    return icon;
  }
}
