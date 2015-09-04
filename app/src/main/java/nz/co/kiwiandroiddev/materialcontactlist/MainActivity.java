package nz.co.kiwiandroiddev.materialcontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Single activity for the app. Acts as a host for content fragments and provides a Toolbar.
 */
public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        // Listen for changes in the fragment back stack
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        // Handle when activity is recreated like on orientation change
        shouldDisplayHomeUp();

        ContactsApplication app = (ContactsApplication) getApplication();
        app.registerMainActivity(this);

        if (savedInstanceState == null) {
            String tag = "contactList";
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_content, new ContactListFragment(), tag)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ContactsApplication app = (ContactsApplication) getApplication();
        app.unregisterMainActivity();
    }

    public void pushFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slight_zoom_fade_out,
                        R.anim.slight_zoom_fade_in, R.anim.slide_out_right)
                .replace(R.id.activity_main_content, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    /**
     * Enables Up ("back arrow") in the toolbar only if there are fragments in the back stack
     *
     * Ref: https://stackoverflow.com/questions/13086840/actionbar-up-navigation-with-fragments
     */
    private void shouldDisplayHomeUp() {
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
