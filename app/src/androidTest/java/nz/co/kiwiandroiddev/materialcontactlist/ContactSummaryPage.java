package nz.co.kiwiandroiddev.materialcontactlist;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by francish on 1/11/16.
 */

public class ContactSummaryPage {
    public void tapListPosition(int position) {
        ViewInteraction recyclerView = onView(
                allOf(withId(android.R.id.list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(position, click()));
    }
}
