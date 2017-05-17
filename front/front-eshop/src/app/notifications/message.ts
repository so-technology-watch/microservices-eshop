export class Message {

  public constructor(private message: string, private success: boolean, private visible: boolean) {

  }

  public getMessage(): string {
    return this.message;
  }

  public isSuccess(): boolean {
    return this.success;
  }

  public setMessage(message: string): void {
    this.message = message;
  }

  public setSucces(succes: boolean): void {
    this.success = succes;
  }

  public setVisible(visible: boolean): void {
    this.visible = visible;
  }

  public isVisible(): boolean {
    return this.visible;
  }
}
