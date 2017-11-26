package com.example.takwa.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.takwa.project.Adapters.Adapter;
import com.example.takwa.project.entities.Comment;
import com.example.takwa.project.entities.Document;
import com.example.takwa.project.entities.User;
import com.example.takwa.project.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;


public class CommentActivity extends AppCompatActivity {
    RecyclerView rc;
    Adapter adapter;
    String json_url = "http://10.0.2.2:18080/pidev-web/api/comments/getall";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        rc = (RecyclerView) findViewById(R.id.rvCommentaire);


        final ArrayList<Comment> list = new ArrayList<>();

        adapter = new Adapter(this, list);
        list.add(new Comment("Ce document est bon .", "19/12/2017"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);
        rc.setAdapter(adapter);

        JsonArrayRequest jar = new JsonArrayRequest(json_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                JSONObject document = jsonObject.getJSONObject("documment");
                                JSONObject u2 = document.getJSONObject("user");
                                Comment c = new Comment();

                                JSONObject user = jsonObject.getJSONObject("utilisateur");
                                String dateString = DateFormat.format("MM/dd/yyyy", new Date(jsonObject.getLong("postdate"))).toString();
                                User u1 = new User();
                                u1.setId(user.getInt("id"));
                                u1.setFirstname(user.getString("lastname"));
                                u1.setLastname(user.getString("firstname"));
                                u1.setCin(user.getInt("cin"));
                                u1.setRole(user.getString("role"));
                                u1.setPassword(user.getString("password"));
                                u1.setSexe(user.getString("sexe"));
                                u1.setAge(user.getInt("age"));
                                u1.setSalaire(user.getInt("salaire"));
                                u1.setBirthday(user.getLong("birthday"));
                                c.setUtilisateur(u1);
                                c.setDate(dateString);
                                c.setId(jsonObject.getInt("id"));
                                c.setDescription(jsonObject.getString("description"));
                                c.setUserName(user.getString("lastname") +"   "+ user.getString("firstname"));

                                Document document1 = new Document();
                                document1.setId(document.getInt("id"));
                                document1.setPath(document.getString("path"));
                                document1.setSize(document.getInt("size"));
                                document1.setName(document.getString("name"));
                                document1.setDate(document.getLong("datesubmit"));
                                document1.setEtadoc(document.getString("etatdoc"));

                                u1.setId(u2.getInt("id"));
                                u1.setFirstname(u2.getString("lastname"));
                                u1.setLastname(u2.getString("firstname"));
                                u1.setCin(u2.getInt("cin"));
                                u1.setRole(u2.getString("role"));
                                u1.setPassword(u2.getString("password"));
                                u1.setSexe(u2.getString("sexe"));
                                u1.setAge(u2.getInt("age"));
                                u1.setSalaire(u2.getInt("salaire"));
                                u1.setBirthday(u2.getLong("birthday"));
                                document1.setUser(u1);
                                c.setDocument(document1);

                                Log.e("CheckPoint" , c.toString()) ;
                                list.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(CommentActivity.class.getSimpleName(), "Error: " + error.getMessage());
                //  hidePDialog();
            }
        });


        MySingleton.getIns(this).addToRequ(jar);

       /* list.add(new Comment("Ce document est bon .", "19/12/2017"));
        list.add(new Comment("Ce document n'est pas valide. Un notaire peut facilement le remarquer .", "19/12/2017"));
        list.add(new Comment("J'étais absent , Alors allons y maintenant.", "19/12/2017"));
        list.add(new Comment("Jétais pas malade .", "19/12/2017"));
        list.add(new Comment("Ce document est bon .", "19/12/2017"));
        list.add(new Comment("Ce document est bon .", "19/12/2017"));
        list.add(new Comment("Ce document est bon .", "19/12/2017"));
        list.add(new Comment("Ce document est bon .", "19/12/2017"));
        */

        //  viewDemande();
    }


}
