package com.dataart.vyakunin.gdg_wear;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.message_list_fragment)
public class MessageListFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = "MessageListFragment";

    public static Fragment newInstance() {
        return MessageListFragment_.builder().build();
    }

    @ViewById(R.id.phone_number_edit)
    EditText phoneNumberEdit;

    @ViewById(R.id.add_message)
    Button addMessage;

    @ViewById(R.id.list_view)
    ListView listView;

    private SimpleCursorAdapter adapter;

    @AfterViews
    void init() {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        getLoaderManager().restartLoader(1, null, messageLoader);
    }

    private LoaderManager.LoaderCallbacks<Cursor> messageLoader = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            return new CursorLoader(getActivity(), StoreContentProvider.getContentUri(Store.ShortMessages.CONTENT_URI), null, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
            String[] from = new String[]{Store.ShortMessages.VALUE};
            int[] to = new int[]{android.R.id.text1};
            adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, from, to);
            listView.setAdapter(adapter);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {
            adapter.changeCursor(null);
        }
    };


    @Click(R.id.add_message)
    void addMessageClicked() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Store.ShortMessages.VALUE, phoneNumberEdit.getText().toString());
        getActivity().getContentResolver().insert(StoreContentProvider.getContentUri(Store.ShortMessages.CONTENT_URI), contentValues);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) adapter.getItem(position);
        String messageBody = cursor.getString(cursor.getColumnIndex(Store.ShortMessages.VALUE));
        SharedPreferences prefs = getActivity().getSharedPreferences("com.dataart.vyakunin.app", Context.MODE_PRIVATE);
        prefs.edit().putString(HomeActivity.MESSAGE_TO_SEND, messageBody).apply();
    }
}
