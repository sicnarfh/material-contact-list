package nz.co.kiwiandroiddev.materialcontactlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nz.co.kiwiandroiddev.materialcontactlist.domain.Contact;

public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    public enum SortOrder {
        A_TO_Z, Z_TO_A
    }

    private static final Comparator<Contact> mAToZComparator =
            new Comparator<Contact>() {
                @Override
                public int compare(Contact lhs, Contact rhs) {
                    return lhs.name.compareTo(rhs.name);
                }
            };

    private List<Contact> mContacts;

    @NonNull
    private SortOrder mSortOrder = SortOrder.A_TO_Z;

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact_row, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int position) {
        Contact contact = mContacts.get(position);
        contactViewHolder.bindTo(contact);
    }

    @Override
    public int getItemCount() {
        return mContacts == null ? 0 : mContacts.size();
    }

    public void setSortOrder(@NonNull SortOrder sortOrder) {
        if (sortOrder != mSortOrder) {
            mSortOrder = sortOrder;
            sortContactsAndNotifyObservers();
        }
    }

    public void setContacts(List<Contact> contacts) {
        mContacts = contacts;
        sortContactsAndNotifyObservers();
    }

    private Comparator<Contact> getComparatorForSortOrder() {
        switch (mSortOrder) {
            case Z_TO_A:
                return Collections.reverseOrder(mAToZComparator);
            case A_TO_Z:
            default:
                return mAToZComparator;
        }
    }

    private void sortContactsAndNotifyObservers() {
        if (mContacts != null && mContacts.size() > 0) {
            Collections.sort(mContacts, getComparatorForSortOrder());
            notifyDataSetChanged();
        }
    }
}
