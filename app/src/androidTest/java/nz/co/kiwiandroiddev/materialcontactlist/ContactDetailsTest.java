package nz.co.kiwiandroiddev.materialcontactlist;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ContactDetailsTest {

    ContactSummaryPage contactSummaryPage = new ContactSummaryPage();
    ContactDetailsPage contactDetailsPage = new ContactDetailsPage();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testContactListDetailReturnCorrectName() {
        contactSummaryPage.tapListPosition(0);
        contactDetailsPage.isNameFieldMatching("Chelsey Dietrich");
    }
}
