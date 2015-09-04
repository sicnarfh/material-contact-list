package nz.co.kiwiandroiddev.materialcontactlist.domain;

import java.io.Serializable;

public class Address implements Serializable {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public GeoCoordinates geo;

    public static class GeoCoordinates implements Serializable {
        public String lat;
        public String lng;
    }

    @Override
    public String toString() {
        return suite + "\n" + street + "\n" +
                city + "\n" + zipcode;
    }
}
