package ser423.student.server;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

//import com.example.brianturloff.moviedescription.MovieDescription;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by brianturloff on 2/8/16.
 */
public class MovieLibrary{

    public LinkedList<MovieDescription> listOfMovies;



    public MovieLibrary(){
    	
    	listOfMovies = new LinkedList<>();
	    try{
	    	String fileName = "movies.json";
	         File f = new File(fileName);
	         FileInputStream is = new FileInputStream(f);
	         JSONObject studentMap = new JSONObject(new JSONTokener(is));
	         Iterator<String> it = studentMap.keys();
	         while (it.hasNext()){
	            String mType = it.next();
	            JSONObject studentJson = studentMap.optJSONObject(mType);
	            MovieDescription stud = new MovieDescription(studentJson.toString());
	            listOfMovies.add(stud);
	            debug("added "+stud.title+" : "+stud.toJsonString()+
	                  "\nstudents.size() is: " + listOfMovies.size());
	         }
	     }
         catch (Exception ex){
         System.out.println("Exception reading json file: "+ex.getMessage());
      }
    	

    }
    
    private void debug(String message) {
     
         System.out.println("debug: "+message);
   }

    public void addMovie(MovieDescription movie){

        listOfMovies.add(movie);
    }
    
    public String[] getTitles(){
    
        String[] titles = new String[listOfMovies.size()];
        int ix = 0;
        for (MovieDescription movieDescription : listOfMovies) {
			 titles[ix] = movieDescription.title;
			 ix++;
		}
        return titles;    
    }
    
     public JSONObject getMovie(int index){
    	
    	 JSONObject movie = listOfMovies.get(index).toJsonObj();
    	 System.out.println("got movie: " + movie);
    	 return movie;    	
    }
     
    public void delete(int index){
    	String title = listOfMovies.get(index).title;
    	listOfMovies.remove(index);
    	System.out.println(title + " DELETED!");
    }
    
    public void add(MovieDescription newMovie){
    	listOfMovies.add(newMovie);
    	System.out.println(newMovie.title + " ADDED!");
    }


}
