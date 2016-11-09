package nz.co.kiwiandroiddev.materialcontactlist.Page;

import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by rachelg on 9/11/16.
 */

public class ContactListPage {

    public void openContactDetails(final String contactName) {
        ViewInteraction contactRow = onView(allOf(withText(contactName), isDisplayed()));
        contactRow.perform(click());
    }
}
