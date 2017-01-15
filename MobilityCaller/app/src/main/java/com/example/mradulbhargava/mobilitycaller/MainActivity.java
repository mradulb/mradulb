package com.example.mradulbhargava.mobilitycaller;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Context context;
    ProgressDialog pd;
    String NameOfSearch;

    ArrayList<Contacts> contactAl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.listView);
        pd= new ProgressDialog(this);
        contactAl= new ArrayList<>();

        refreshListView();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
     //   searchView.setSearchableInfo(
        //        searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("TAG", "onQueryTextSubmit " + s);
                NameOfSearch = s;
                new MakeServiceCall().execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                refreshListView();

                return false;
            }
        });
        return true;
    }

public void refreshListView(){
if(contactAl.size()==0){
    Contacts contacts= new Contacts("No Contact Found"," "," ");
    contactAl.add(contacts);
}
    lv.setAdapter(new MyAdapter(this, contactAl));
}

private class MakeServiceCall extends AsyncTask<Void,Void,Void>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setMessage("Getting Details");
        pd.show();

    }

    @Override
    protected Void doInBackground(Void... voids) {
       try{

           URL url = new URL("https://abc.tcsmobilitycloud.com/AbcServicesTCSRR/Abc/SearchContact/"+NameOfSearch);
           URLConnection conn = url.openConnection();
           BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           StringBuilder sb = new StringBuilder();
           String line = null;

           // Read Server Response
           while((line = reader.readLine()) != null)
           {
               // Append server response in string
               sb.append(line + " ");
           }

           // Append Server Response To Content String
           String Content = sb.toString();
            Log.d("Services",Content);
           JSONObject jsonObj = new JSONObject(Content);
           contactAl.clear();
           // Getting JSON Array node
           JSONArray contacts = jsonObj.getJSONArray("arlc");
           for(int i=0;i<contacts.length();i++) {
               JSONObject c = contacts.getJSONObject(i);

               Contacts contact=new Contacts(c.getString("name"),c.getString("contact"),c.getString("empid"));
               contactAl.add(contact);
           }



       }catch (Exception e)
       {
           e.printStackTrace();
       }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        refreshListView();
        pd.dismiss();
    }
}









}
