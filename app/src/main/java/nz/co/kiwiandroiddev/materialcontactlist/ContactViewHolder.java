package nz.co.kiwiandroiddev.materialcontactlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nz.co.kiwiandroiddev.materialcontactlist.domain.Contact;

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context mContext;
    public final TextView name;
    public final TextView email;
    private Contact mContact;

    public ContactViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        name = (TextView) view.findViewById(R.id.list_contact_row_name);
        email = (TextView) view.findViewById(R.id.list_contact_row_email);
        mContext = view.getContext();
    }

    public void bindTo(Contact contact) {
        mContact = contact;
        name.setText(contact.name);
        email.setText(contact.email);
    }

    @Override
    public void onClick(View v) {
        ContactsApplication app = (ContactsApplication) mContext.getApplicationContext();
        app.showContactDetails(mContact);
    }
}
