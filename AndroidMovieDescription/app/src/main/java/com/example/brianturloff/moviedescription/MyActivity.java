
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

package com.example.brianturloff.moviedescription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


public class MyActivity extends AppCompatActivity {

    TextView jsonTV;
    public RecyclerView mRecyclerView;
    public static MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<String> movie_titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.util.Log.d("My: ", "........................in onCreate.................................................");
        movie_titles = new LinkedList<String>();
        super.onCreate(savedInstanceState);

        JSONObject jsonObject;
        //MovieLibrary my_movies;


        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        try {
            InputStream is = getAssets().open("movies.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            jsonObject = new JSONObject(new JSONTokener(br.readLine()));
            //my_movies = new MovieLibrary(jsonObject);

            // specify an adapter (see also next example)
            mAdapter = new MyAdapter(movie_titles, getBaseContext());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart(){
        super.onStart();

        try{
            MethodInformation mi = new MethodInformation(this, getString(R.string.defaulturl),"getTitles", new String[]{});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                    ex.getMessage());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent in = new Intent(this, AddActivity.class);
            startActivity(in);

        }

        return super.onOptionsItemSelected(item);
    }



    public void buttonClicked(View v) {

        MovieDescription m = new MovieDescription(getString(R.string.movie_bourne));
        android.util.Log.w(this.getClass().getSimpleName(), getString(R.string.movie_bourne));
        android.util.Log.w(this.getClass().getSimpleName(), m.title);

        //String t = m.toJsonString();
//        TextView n = (TextView) findViewById(R.id.textTitle);
//        n.setText(m.title);
//        ((TextView) findViewById(R.id.textRating)).setText(m.rated);
//        ((TextView) findViewById(R.id.textMeta)).setText(m.metaScore);
//        ((TextView) findViewById(R.id.textYear)).setText(m.year);
//        ((TextView) findViewById(R.id.textImbd)).setText(m.imbdRating);
//        ((TextView) findViewById(R.id.textRuntime)).setText(m.runtime);
//        ((TextView) findViewById(R.id.textReleased)).setText(m.released);
//        ((TextView) findViewById(R.id.textDirector)).setText(m.director);
//        ((TextView) findViewById(R.id.textGenre)).setText(m.genre);
//        ((TextView) findViewById(R.id.textActors)).setText(m.actors);
//        ((TextView) findViewById(R.id.textPlot)).setText(m.plot);
    }
}
