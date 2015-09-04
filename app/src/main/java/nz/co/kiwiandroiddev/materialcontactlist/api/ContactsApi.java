package nz.co.kiwiandroiddev.materialcontactlist.api;


import java.util.List;

import nz.co.kiwiandroiddev.materialcontactlist.domain.Contact;
import retrofit.http.GET;
import rx.Observable;

public interface ContactsApi {
    String URL_CONTACTS = "http://jsonplaceholder.typicode.com/";

    @GET("/users")
    Observable<List<Contact>> contacts();
}
