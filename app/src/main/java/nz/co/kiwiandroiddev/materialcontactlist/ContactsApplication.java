package nz.co.kiwiandroiddev.materialcontactlist;

import android.app.Application;

import nz.co.kiwiandroiddev.materialcontactlist.domain.Contact;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class ContactsApplication extends Application {

    private MainActivity mMainActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }
    }

    // TODO use an ApplicationController instead
    // See <a href="http://martinfowler.com/eaaCatalog/applicationController.html">http://martinfowler.com/eaaCatalog/applicationController.html</a>
    public void showContactDetails(Contact contact) {
        if (mMainActivity != null) {
            ContactDetailsFragment fragment = ContactDetailsFragment.newInstance(contact);
            mMainActivity.pushFragment(fragment, "contactDetails");
        }
    }

    public void registerMainActivity(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void unregisterMainActivity() {
        mMainActivity = null;
    }
}
