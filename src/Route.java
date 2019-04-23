package inclass;

public class Route {

  City from;
  City to;
  int distance;

  public Route(City from, City to, int distance) {
    this.from = from;
    this.to = to;
    this.distance = distance;
  }

  public City getFrom() {
    return from;
  }

  public City getTo() {
    return to;
  }

  public int getDistance() {
    return distance;
  }

  public boolean checkRoute(City source, City destination) {
    return from.equals(source) && to.equals(destination);
  }


  @Override
  public String toString() {
    return from + "->" + to + " :: " + distance;
  }

  public boolean isStartFrom(City start) {
    return from.equals(start);
  }
}
