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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private String title;
    private MovieDescription movie;
    private int position;

    TextView titleTV;
    TextView yearTV;
    TextView ratedTV;
    TextView releasedTV;
    TextView runtimeTV;
    TextView genreTV;
    TextView actorsTV;
    TextView plotTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        MyActivity parent = (MyActivity) getParent();
        Intent in =  getIntent();

        title = in.getStringExtra("movie");
        position =  (int) in.getSerializableExtra("position");
        android.util.Log.d("Detail posit: ", String.valueOf(position));
        String pos = Integer.toString(position);

        try{
            MethodInformation mi = new MethodInformation(this, getString(R.string.defaulturl),"getMovie", new String[]{pos});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                    ex.getMessage());
        }

        titleTV = (TextView) findViewById(R.id.textView);
        yearTV = (TextView) findViewById(R.id.textView2);
        ratedTV = (TextView) findViewById(R.id.textView3);
        releasedTV = (TextView) findViewById(R.id.textView4);
        runtimeTV = (TextView) findViewById(R.id.textView5);
        genreTV = (TextView) findViewById(R.id.textView6);
        actorsTV = (TextView) findViewById(R.id.textView7);
        plotTV = (TextView) findViewById(R.id.textView8);


       // populateMovie(movie);



    }

    private void populateMovie(MovieDescription movie){

        ((TextView) findViewById(R.id.textView)).setText(movie.title);
        ((TextView) findViewById(R.id.textView2)).setText(movie.year);
        ((TextView) findViewById(R.id.textView3)).setText(movie.rated);
        ((TextView) findViewById(R.id.textView4)).setText(movie.released);
        ((TextView) findViewById(R.id.textView5)).setText(movie.runtime);
        ((TextView) findViewById(R.id.textView6)).setText(movie.genre);
        ((TextView) findViewById(R.id.textView7)).setText(movie.actors);
        ((TextView) findViewById(R.id.textView8)).setText(movie.plot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {

            try{
                MethodInformation mi = new MethodInformation(this, getString(R.string.defaulturl),"delete", new String[]{Integer.toString(position)});
                AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
            } catch (Exception ex){
                android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                        ex.getMessage());
            }
            MyActivity.mAdapter.delete(position);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
