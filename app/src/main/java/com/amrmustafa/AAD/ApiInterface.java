package com.amrmustafa.AAD;

import com.amrmustafa.AAD.model.LearningLeader_Model;
import com.amrmustafa.AAD.model.Skill_IQ_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/skilliq")
    Call<List<Skill_IQ_Model>> getSkill();

    @GET("api/hours")
    Call<List<LearningLeader_Model>> getLearner();
}

