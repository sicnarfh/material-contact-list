package nz.co.kiwiandroiddev.materialcontactlist;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import nz.co.kiwiandroiddev.materialcontactlist.Page.ContactDetailsPage;
import nz.co.kiwiandroiddev.materialcontactlist.Page.ContactListPage;

import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewContactDetailsTest {

    private final String contactName = "Clementine Bauch";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void viewContactDetailsTest() {
        ContactListPage contactListPage = new ContactListPage();
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage();

        contactListPage.openContactDetails(contactName);
        contactDetailsPage.getContactName().check(matches(isDisplayed())).check(matches(withText(contactName)));
        contactDetailsPage.getContactAddress().check(matches(isDisplayed())).check(matches(withText("Suite 847\nDouglas Extension\nMcKenziehaven\n59590-4157")));
    }
}
