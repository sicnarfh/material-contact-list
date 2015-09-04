package nz.co.kiwiandroiddev.materialcontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nz.co.kiwiandroiddev.materialcontactlist.ContactListAdapter.SortOrder;
import nz.co.kiwiandroiddev.materialcontactlist.api.ContactsApi;
import nz.co.kiwiandroiddev.materialcontactlist.domain.Contact;
import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static nz.co.kiwiandroiddev.materialcontactlist.ContactListAdapter.SortOrder.A_TO_Z;
import static nz.co.kiwiandroiddev.materialcontactlist.ContactListAdapter.SortOrder.Z_TO_A;

/**
 * Fetches and displays a list of contacts. Contacts can be selected to open a detailed view.
 * Provides list sorting options via the Toolbar's overflow menu.
 */
public class ContactListFragment extends Fragment {

    private static final String KEY_SORT_ORDER = "sortOrder";

    @Bind(android.R.id.list)
    RecyclerView mRecyclerView;

    @Bind(R.id.fragment_contact_list_retry_layout)
    View mRetryLayout;

    @Bind(R.id.fragment_contact_list_error_message)
    TextView mErrorMessage;

    @Bind(R.id.fragment_contact_list_loading_spinner)
    View mLoadingSpinner;

    private ContactListAdapter mContactListAdapter;
    private SortOrder mSortOrder = A_TO_Z;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_SORT_ORDER)) {
            mSortOrder = ((SortOrder) savedInstanceState.getSerializable(KEY_SORT_ORDER));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // make no assumptions about initial view visibility from XML - set to known state
        hideLoadingSpinner();
        hideRetryButtonWithError();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // The adapter field will still be set when this fragment is navigated back to, in
        // which case we don't need to create a new one or make the API call
        if (mContactListAdapter == null) {
            mContactListAdapter = new ContactListAdapter();
            mContactListAdapter.setSortOrder(mSortOrder);
            fetchContacts();
        }

        mRecyclerView.setAdapter(mContactListAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Always save and restore SortOrder on orientation change, even if view is not visible
        // - this is the case when the contact details fragment is visible
        outState.putSerializable(KEY_SORT_ORDER, mSortOrder);
    }

    private void fetchContacts() {
        showLoadingSpinner();

        RestAdapter retrofit = new RestAdapter.Builder()
//                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ContactsApi.URL_CONTACTS)
                .build();

        ContactsApi api = retrofit.create(ContactsApi.class);
        api.contacts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contact>>() {
                    @Override
                    public void onCompleted() {
                        hideLoadingSpinner();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingSpinner();
                        showRetryButtonWithError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Contact> contacts) {
                        mContactListAdapter.setContacts(contacts);
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_contact_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_a_to_z:
                setSortOrder(A_TO_Z);
                return true;
            case R.id.action_sort_z_to_a:
                setSortOrder(Z_TO_A);
                return true;
        }
        return false;
    }

    private void setSortOrder(SortOrder sortOrder) {
        mSortOrder = sortOrder;
        if (mContactListAdapter != null) {
            mContactListAdapter.setSortOrder(sortOrder);
        }
    }

    private void showRetryButtonWithError(String message) {
        mRetryLayout.setVisibility(View.VISIBLE);
        mErrorMessage.setText(message);
    }

    private void hideRetryButtonWithError() {
        mRetryLayout.setVisibility(View.GONE);
    }

    private void showLoadingSpinner() {
        mLoadingSpinner.setVisibility(View.VISIBLE);
    }

    private void hideLoadingSpinner() {
        mLoadingSpinner.setVisibility(View.GONE);
    }

    @OnClick(R.id.fragment_contact_list_retry_button)
    public void onRetryButtonClicked() {
        hideRetryButtonWithError();
        fetchContacts();
    }
}
