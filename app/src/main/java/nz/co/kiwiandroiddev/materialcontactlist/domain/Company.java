package nz.co.kiwiandroiddev.materialcontactlist.domain;

import java.io.Serializable;

public class Company implements Serializable {
    public String name;
    public String catchPhrase;
    public String bs;

    @Override
    public String toString() {
        return name + "\n" +
                catchPhrase + "\n" +
                bs;
    }
}
