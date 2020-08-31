package com.amrmustafa.AAD;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class FormData extends AppCompatActivity {

    ProgressDialog progressDialog;
    FloatingActionButton fab;
    EditText edtEmail, edtName,edtLastName,edtLinkToProject;
    Button sendRequest;
    RequestQueue queue;
ImageButton toolbar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sending_data_form);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        edtEmail = (EditText) findViewById(R.id.emailaddress);
        edtName = (EditText) findViewById(R.id.Name);
        edtLastName = (EditText) findViewById(R.id.lName);
        edtLinkToProject = (EditText) findViewById(R.id.linkToProject);

        sendRequest=(Button)findViewById(R.id.btn_submit);
        toolbar_back=(ImageButton)findViewById(R.id.toolbar_back);


        queue = Volley.newRequestQueue(getApplicationContext());

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().trim().length() > 0 && edtLastName.getText().toString().trim().length() > 0
                        && edtEmail.getText().toString().trim().length() > 0 && edtLinkToProject.getText().toString().trim().length() > 0
                ) {
                    postData(edtEmail.getText().toString().trim(), edtName.getText().toString().trim()
                    ,edtLastName.getText().toString().trim(),edtLinkToProject.getText().toString().trim());
                } else {
                    Snackbar.make(v, "Required Fields Missing", Snackbar.LENGTH_LONG).show();
                }

            }
        });


        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    public void postData(final String email,final String name,final String lName, final String linkToProject) {

        progressDialog.show();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {
                            Snackbar.make(fab, "Successfully Posted", Snackbar.LENGTH_LONG).show();
                            edtName.setText(null);
                            edtEmail.setText(null);
                            edtLastName.setText(null);
                            edtLinkToProject.setText(null);
                        } else {
                            Snackbar.make(fab, "Try Again", Snackbar.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar.make(fab, "Error while Posting Data", Snackbar.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.Email_Address, email);
                params.put(Constants.Name, name);
                params.put(Constants.Last_Name, lName);
                params.put(Constants.Link_to_project, linkToProject);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }





}

