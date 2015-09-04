package nz.co.kiwiandroiddev.materialcontactlist.domain;

import java.io.Serializable;

public class Contact implements Serializable {
    public int id;
    public String name;
    public String email;
    public Address address;
    public String phone;
    public String website;
    public Company company;

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}
