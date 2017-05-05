import { FrontEshopPage } from './app.po';

describe('front-eshop App', () => {
  let page: FrontEshopPage;

  beforeEach(() => {
    page = new FrontEshopPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
