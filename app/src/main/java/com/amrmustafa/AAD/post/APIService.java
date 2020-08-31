package com.amrmustafa.AAD.post;


import com.amrmustafa.AAD.Constants;
import com.amrmustafa.AAD.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Chike on 12/3/2016.
 */

public interface APIService {

    @POST("/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Post> savePost(@Field(Constants.Email_Address) String email,

                        @Field(Constants.Name) String name,

                        @Field(Constants.Last_Name) String lName,

                        @Field(Constants.Link_to_project) String linktoproject);



}

