package com.amrmustafa.AAD;

import com.amrmustafa.AAD.model.LearningLeader_Model;
import com.amrmustafa.AAD.model.Post;
import com.amrmustafa.AAD.model.Skill_IQ_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("api/skilliq")
    Call<List<Skill_IQ_Model>> getSkill();

    @GET("api/hours")
    Call<List<LearningLeader_Model>> getLearner();
    @POST("/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Post> savePost(@Field(Constants.Email_Address) String email,

                        @Field(Constants.Name) String name,

                        @Field(Constants.Last_Name) String lName,

                        @Field(Constants.Link_to_project) String linktoproject);

}

