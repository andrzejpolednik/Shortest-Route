package inclass;

public class OneTransferRoute extends Route {

  private City transfer;

  public OneTransferRoute(City from, City to, int distance, City transfer) {
    super(from, to, distance);
    this.transfer = transfer;
  }

  public City getTransfer() {
    return transfer;
  }

  @Override
  public String toString() {
    return from + "->" + transfer + "->" + to + " :: " + distance;
  }
}
