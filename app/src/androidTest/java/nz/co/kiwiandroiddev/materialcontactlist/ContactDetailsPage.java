package nz.co.kiwiandroiddev.materialcontactlist;

import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by francish on 1/11/16.
 */

public class ContactDetailsPage {
    public ViewInteraction isNameFieldMatching(String name) {
        ViewInteraction textView = onView(
                allOf(withId(R.id.fragment_contact_details_name), withText(name),
                        CustomMatcher.childAtPosition(
                                CustomMatcher.childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        return textView.check(matches(withText(name)));
    }
}
