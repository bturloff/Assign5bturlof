package com.example.brianturloff.moviedescription;

/*
* Copyright 2016 Brian Turloff,
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.

* @author Brian Turloff mailto:bturlof@asu.edu
*         Computer Science, CIDSE, ASU - Tempe
* @version February 2016
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

// hello
public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    MovieDescription new_movie;
    private TextView view_title;
    private TextView view_year;
    private TextView view_rated;
    private TextView view_released;
    private TextView view_runtime;
    private TextView view_genre;
    private TextView view_actors;
    private TextView view_plot;


    // This is a method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new_movie = new MovieDescription();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        view_title = (EditText) findViewById(R.id.editText);
        view_year = (EditText) findViewById(R.id.editText2);
        //view_rated = (EditText) findViewById(R.id.editText3);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ratings_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        view_released = (EditText) findViewById(R.id.editText4);
        view_runtime = (EditText) findViewById(R.id.editText5);
        view_genre = (EditText) findViewById(R.id.editText6);
        view_actors = (EditText) findViewById(R.id.editText7);
        view_plot = (EditText) findViewById(R.id.editText8);

    }


    public void update_click(View view){

        new_movie.title = view_title.getText().toString();
        new_movie.year = (String) view_year.getText().toString();
        new_movie.runtime = (String) view_runtime.getText().toString();
        new_movie.released = (String) view_released.getText().toString();
        new_movie.genre = (String) view_genre.getText().toString();
        new_movie.actors = (String) view_actors.getText().toString();
        new_movie.plot = (String) view_plot.getText().toString();

        try{
            MethodInformation mi = new MethodInformation(this, getString(R.string.defaulturl),"add", new String[]{new_movie.toJsonString()});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                    ex.getMessage());
        }

        //MyActivity.mAdapter.add(new_movie.title);

        for(String m : MyActivity.mAdapter.mDataset){

            android.util.Log.d("Title: ", m);
        }

        finish();

        return;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         String v  = (String) parent.getItemAtPosition(position);
        android.util.Log.d("spinner: ", v);
        new_movie.rated = v;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
