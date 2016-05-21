package com.austinfelipe.activityresult;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class SelectionActivity extends ListActivity {

    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] states = getResources().getStringArray(R.array.brazilian_states);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, states));

        String state = getIntent().getStringExtra(EXTRA_STATE);

        if (state != null) {
            int position = Arrays.asList(states).indexOf(state);

            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(position, true);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String selectedState = l.getItemAtPosition(position).toString();
        Intent intent = new Intent();

        intent.putExtra(EXTRA_RESULT, selectedState);
        setResult(RESULT_OK, intent);
        finish();
    }
}
