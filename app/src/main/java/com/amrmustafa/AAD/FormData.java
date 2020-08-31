package com.amrmustafa.AAD;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.amrmustafa.AAD.post.APIService;
import com.amrmustafa.AAD.post.ApiUtils;
import com.amrmustafa.AAD.model.Post;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class FormData extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText edtEmail, edtName,edtLastName,edtLinkToProject;
    Button sendRequest;
    RequestQueue queue;





    //////////
    private APIService mAPIService;







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

        mAPIService = ApiUtils.getAPIService();


        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().trim().length() > 0 && edtLastName.getText().toString().trim().length() > 0
                        && edtEmail.getText().toString().trim().length() > 0 && edtLinkToProject.getText().toString().trim().length() > 0
                ) {
//                    postData(edtEmail.getText().toString().trim(), edtName.getText().toString().trim()
//                    ,edtLastName.getText().toString().trim(),edtLinkToProject.getText().toString().trim());

                    sendPost(edtEmail.getText().toString().trim(), edtName.getText().toString().trim()
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



    public void showErrorMessage() {
        Toast.makeText(this, "Failed To Submit Data...", Toast.LENGTH_SHORT).show();
    }

    public void sendPost(String email, String name,String lName,String url) {


        mAPIService.savePost(email, name, lName,url).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, retrofit2.Response<Post> response) {

                //if(response.isSuccessful())
                {
                    //  showResponse(response.body().toString());
                    Toast.makeText(FormData.this,"Successfully Posted",Toast.LENGTH_LONG).show();

                    Log.i("TAGAAD", "post submitted to API." );
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                showErrorMessage();
                Toast.makeText(FormData.this,"Unable to submit post to API",Toast.LENGTH_LONG).show();

                Log.e("TAGAAD", "Unable to submit post to API.");
            }
        });

    }





    public void showResponse(String response) {

        Toast.makeText(FormData.this, response, Toast.LENGTH_LONG).show();
    }

























    /////////////////////////////////////////////////////////////////
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

                            Toast.makeText(FormData.this,"Successfully Posted",Toast.LENGTH_LONG).show();
                            edtName.setText(null);
                            edtEmail.setText(null);
                            edtLastName.setText(null);
                            edtLinkToProject.setText(null);
                        } else {
                            Toast.makeText(FormData.this,"Try Again",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(FormData.this,"Error while Posting Data",Toast.LENGTH_LONG).show();

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

////////////////////////////////////////////////////////

}





