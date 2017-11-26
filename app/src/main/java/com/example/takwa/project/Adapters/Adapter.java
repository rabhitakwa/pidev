package com.example.takwa.project.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.takwa.project.CommentActivity;
import com.example.takwa.project.R;
import com.example.takwa.project.entities.Comment;
import com.example.takwa.project.entities.Document;
import com.example.takwa.project.entities.User;
import com.example.takwa.project.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by takwa on 23/11/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<Comment> list;
    String json_url = "http://10.0.2.2:18080/pidev-web/api/comments/";

    public Adapter(Context context, ArrayList<Comment> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rc_row, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //TODO  lehné tekteb asémi el user wel commentaire
        holder.tvDate.setText(list.get(position).getDate());//date
        holder.tvRowUser.setText(list.get(position).getUserName());//Esm el User
        holder.tvCommMessage.setText(list.get(position).getDescription());// el commentaire
        holder.circleImageView.setImageResource(R.drawable.large);//Image user
        holder.ibdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest dr = new StringRequest(Request.Method.DELETE, json_url+list.get(position).getId(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                list.remove(position);
                                Adapter.this.notifyDataSetChanged();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error.

                            }
                        }
                );
                MySingleton.getIns(context).addToRequ(dr);

            }
        });
        holder.ibupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.editText.getVisibility() == View.GONE){
                    holder.editText.setText(holder.tvCommMessage.getText());
                    holder.editText.setVisibility(View.VISIBLE);
                    holder.tvCommMessage.setVisibility(View.GONE);
                }else {
                    holder.tvCommMessage.setText(holder.editText.getText());
                    holder.editText.setVisibility(View.GONE);
                    holder.tvCommMessage.setVisibility(View.VISIBLE);
                    JSONObject commentaire = new JSONObject();
                    Comment comment = list.get(position) ;
                    try {
                        Document d =  list.get(position).getDocument();
                        JSONObject utilisateur = new JSONObject() ;
                        JSONObject user = new JSONObject();
                        User u =  d.getUser();

                        utilisateur.put("id", comment.getUtilisateur().getId());
                        utilisateur.put("lastname", comment.getUtilisateur().getLastname());
                        utilisateur.put("firstname", comment.getUtilisateur().getFirstname());
                        utilisateur.put("cin", comment.getUtilisateur().getCin());
                        utilisateur.put("role", comment.getUtilisateur().getRole());
                        utilisateur.put("sexe", comment.getUtilisateur().getSexe());
                        utilisateur.put("age", comment.getUtilisateur().getAge());
                        utilisateur.put("salaire", comment.getUtilisateur().getSalaire());
                        utilisateur.put("birthday", comment.getUtilisateur().getBirthday());
                        utilisateur.put("valid", true);
                        commentaire.put("id" , comment.getId());
                        commentaire.put("description", holder.tvCommMessage.getText().toString());
                        commentaire.put("postdate",""+ System.currentTimeMillis());
                        commentaire.put("utilisateur",utilisateur);

                        JSONObject documment = new JSONObject () ;
                        documment.put("id", d.getId());
                        documment.put("path", d.getPath());
                        documment.put("size", d.getSize());
                        documment.put("datesubmit", d.getDate());
                        documment.put("etatdoc", d.getEtadoc());
                        user.put("id", comment.getUtilisateur().getId());
                        user.put("lastname", u.getLastname());
                        user.put("firstname", u.getFirstname());
                        user.put("cin", u.getCin());
                        user.put("role", u.getRole());
                        user.put("sexe", u.getSexe());
                        user.put("age", u.getAge());
                        user.put("salaire", u.getSalaire());
                        user.put("birthday", u.getBirthday());
                        user.put("valid", true);

                        documment.put("user" , user);
                        commentaire.put("documment", documment);
                        Log.e("Checkpoint 2" , commentaire.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,"http:/10.0.2.2:18080/pidev-web/api/comments/",commentaire,null,null);
                    MySingleton.getIns(context).addToRequ(request);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView circleImageView;
        TextView tvRowUser;
        TextView tvCommMessage;
        TextView tvDate;
        ImageButton ibupdate;
        ImageButton ibdelete;
        EditText editText ;

        public MyViewHolder(View itemView) {
            super(itemView);
            circleImageView = (ImageView) itemView.findViewById(R.id.circleImageView);
            tvRowUser = (TextView) itemView.findViewById(R.id.tvRowUser);
            tvCommMessage = (TextView) itemView.findViewById(R.id.tvCommMessage);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            ibupdate = (ImageButton) itemView.findViewById(R.id.ibupdate);
            ibdelete = (ImageButton) itemView.findViewById(R.id.ibdelete);
            editText  = (EditText) itemView.findViewById(R.id.editText) ;

        }
    }
}
