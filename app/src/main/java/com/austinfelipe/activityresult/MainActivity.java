package com.austinfelipe.activityresult;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int STATE_REQUEST = 1;
    private static final String STATE = "state";

    private Button btnState;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnState = (Button)findViewById(R.id.btnState);
        btnState.setOnClickListener(this);

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString(STATE);
            btnState.setText(state);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectionActivity.class);
        intent.putExtra(SelectionActivity.EXTRA_STATE, state);

        startActivityForResult(intent, STATE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || requestCode != STATE_REQUEST)
            return;

        state = data.getStringExtra(SelectionActivity.EXTRA_RESULT);

        if (state != null)
            btnState.setText(state);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE, state);
    }
}
