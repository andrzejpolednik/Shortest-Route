package inclass;

import java.util.Scanner;

public class RouteFinder {

  private Route[] routes = new Route[20];
  private int routeNumber = 0;
  private Route minumum;


  private void addRoute(String from, String to, int distance) {
    City start = new City(from);
    City end = new City(to);
    Route route = new Route(start, end, distance);
    Route back = new Route(end, start, distance);
    routes[routeNumber++] = route;
    routes[routeNumber++] = back;
  }

  public static void main(String[] args) {
    RouteFinder finder = new RouteFinder();
    Scanner scanner = new Scanner(System.in);

    finder.addRoute("KRK", "GDA", 600);
    finder.addRoute("KRK", "WAW", 250);
    finder.addRoute("WAW", "GDA", 300);
    finder.addRoute("WAW", "POZ", 200);
    finder.addRoute("KRK", "WRO", 300);
    finder.addRoute("WRO", "BER", 400);

    System.out.println("Available cities are: KRK, GDA, WAW, POZ, WRO, BER");
    System.out.println("Enter city from: ");
    String from = scanner.nextLine();
    System.out.println("Enter city to: ");
    String to = scanner.nextLine();

    finder.findMinumumRoute(new City(from), new City(to));
    System.out.println("Minumum route is: " + finder.minumum);
  }

  private void findMinumumRoute(City start, City end) {
    for (int i = 0; i < routeNumber; i++) {
      Route currentRoute = routes[i];
      // IF DIRECT ROUTE IS FOUND
      boolean isDirectRoute = checkDirectRoute(currentRoute, start, end);

      if (!isDirectRoute && currentRoute.isStartFrom(start)) {
        City transfer = currentRoute.getTo();
        // FINDING TRANSFERS TO END
        checkTransfers(start, transfer, end, currentRoute);
      }
    }
  }

  private void checkTransfers(City start, City transfer, City end, Route currentRoute) {
    for (int j = 0; j < routeNumber; j++) {
      Route tempRoute = routes[j];
      boolean isConnection = tempRoute.checkRoute(transfer, end);

      if (isConnection) {
        tryUpdateMinimum(
            new OneTransferRoute(start,
                end,
                currentRoute.getDistance() + tempRoute.getDistance(),
                transfer));
      }
    }
  }

  private boolean checkDirectRoute(Route currentRoute, City start, City end) {
    boolean isDirectRoute = currentRoute.checkRoute(start, end);
    if (isDirectRoute) {
      tryUpdateMinimum(currentRoute);
    }
    return isDirectRoute;
  }


  private void tryUpdateMinimum(Route currentRoute) {
    if (minumum == null || minumum.getDistance() > currentRoute.getDistance()) {
      minumum = currentRoute;
    }
  }

}
