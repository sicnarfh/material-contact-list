package nz.co.kiwiandroiddev.materialcontactlist.Page;

import android.support.test.espresso.ViewInteraction;

import nz.co.kiwiandroiddev.materialcontactlist.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rachelg on 9/11/16.
 */

public class ContactDetailsPage {

    public ViewInteraction getContactName() {
        return onView(withId(R.id.fragment_contact_details_name));
    }

    public ViewInteraction getContactAddress() {
        return onView(withId(R.id.fragment_contact_details_address));
    }
}
