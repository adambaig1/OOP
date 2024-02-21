import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import java.time.ZonedDateTime;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & YOUR NAME
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  ZonedDateTime time;
  double longitude;
  double latitude;
  double elevation;
  // TODO: Create a stub for the constructor
  Point(ZonedDateTime time_, double longitude_, double latitude_, double elevation_) {
    this.time = time_;
    
    if (MIN_LONGITUDE < longitude_ && MAX_LONGITUDE > longitude_){
      this.longitude = longitude_;
    }
    else{
      throw new GPSException("error: invalid longitude");
    }

    if (MIN_LATITUDE < latitude_ && MAX_LATITUDE > latitude_){
      this.latitude = latitude_;
    }
    else{
      throw new GPSException("error: invalid latitude");
    }

    this.elevation = elevation_;

  }

  // TODO: Create a stub for getTime()
  ZonedDateTime getTime() {
    return this.time;
  }

  // TODO: Create a stub for getLatitude()
  double getLatitude() {
    return this.latitude;
  }

  // TODO: Create a stub for getLongitude()
  double getLongitude() {
    return this.longitude;
  }

  // TODO: Create a stub for getElevation()
  double getElevation() {
    return this.elevation;
  }

  // TODO: Create a stub for toString()
  String tosString() {
    return String.format("(%f, %f), %f m", this.longitude, this.latitude, this.elevation);
  }

  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2) * sin(delta);
    double secondTerm = cos(phi1) * sin(phi2) - sin(phi1) * cos(phi2) * cos(delta);
    double top = sqrt(firstTerm * firstTerm + secondTerm * secondTerm);

    double bottom = sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
