package com.example.sahithi.realtimedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

      final EditText edit = (EditText) findViewById (R.id.editText);
       final TextView text = (TextView) findViewById (R.id.textView);
        Button button = (Button) findViewById (R.id.button2);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance ();
        final DatabaseReference myRef = database.getReference ("message");


        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String s1 = edit.getText ().toString ();

                myRef.setValue (s1);
            }
        });

// Read from the database
        myRef.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue (String.class);
                Log.d ("log", "Value is: " + value);
                text.setText (value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w ("log", "Failed to read value.", error.toException ());
            }
        });
    }
}
