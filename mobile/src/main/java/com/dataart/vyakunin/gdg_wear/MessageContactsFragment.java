package com.dataart.vyakunin.gdg_wear;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.contact_list_fragment)
public class MessageContactsFragment extends Fragment {
    public static final String TAG = "MessageContactsFragment";

    public static Fragment newInstance() {
        return MessageContactsFragment_.builder().build();
    }

    @ViewById(R.id.phone_number_edit)
    EditText phoneNumberEdit;

    @ViewById(R.id.add_phone)
    Button addPhone;

    @ViewById(R.id.list_view)
    ListView listView;

    @Click(R.id.add_phone)
    void addPhoneClicked() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Store.ShortMessages.VALUE, phoneNumberEdit.getText().toString());
        getActivity().getContentResolver().insert(StoreContentProvider.getContentUri(Store.ContactNumbers.CONTENT_URI), contentValues);
    }

    private SimpleCursorAdapter adapter;

    @AfterViews
    void init() {
        listView.setAdapter(adapter);
        getLoaderManager().restartLoader(1, null, numbersLoader);
    }

    private LoaderManager.LoaderCallbacks<Cursor> numbersLoader = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            return new CursorLoader(getActivity(), StoreContentProvider.getContentUri(Store.ContactNumbers.CONTENT_URI), null, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
            String[] from = new String[]{Store.ContactNumbers.PHONE_NUMBER};
            int[] to = new int[]{android.R.id.text1};
            adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, from, to);
            listView.setAdapter(adapter);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {
            adapter.changeCursor(null);
        }
    };
}
