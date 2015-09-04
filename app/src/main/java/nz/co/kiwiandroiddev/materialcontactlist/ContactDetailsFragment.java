package nz.co.kiwiandroiddev.materialcontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nz.co.kiwiandroiddev.materialcontactlist.domain.Contact;


public class ContactDetailsFragment extends Fragment {

    private static final String ARG_CONTACT = "contact";

    private Contact mContact;

    @Bind(R.id.fragment_contact_details_name)
    TextView mName;

    @Bind(R.id.fragment_contact_details_email)
    TextView mEmail;

    @Bind(R.id.fragment_contact_details_address)
    TextView mAddress;

    @Bind(R.id.fragment_contact_details_company)
    TextView mCompany;

    @Bind(R.id.fragment_contact_details_phone)
    TextView mPhone;

    @Bind(R.id.fragment_contact_details_website)
    TextView mWebsite;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contact Contact whose details will be displayed
     * @return A new instance of fragment ContactDetailsFragment.
     */
    public static ContactDetailsFragment newInstance(Contact contact) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = (Contact) getArguments().getSerializable(ARG_CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mName.setText(mContact.name);
        mEmail.setText(mContact.email);
        mAddress.setText(mContact.address.toString());
        mCompany.setText(mContact.company.toString());
        mPhone.setText(mContact.phone);
        mWebsite.setText(mContact.website);
    }
}
