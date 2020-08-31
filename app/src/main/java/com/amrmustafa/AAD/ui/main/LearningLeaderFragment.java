package com.amrmustafa.AAD.ui.main;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amrmustafa.AAD.ApiClient;
import com.amrmustafa.AAD.ApiInterface;
import com.amrmustafa.AAD.adapter.LearningLeader_Adapter;
import com.amrmustafa.AAD.model.LearningLeader_Model;
import com.amrmustafa.AAD.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeaderFragment extends Fragment {

        private static final String TAG = "LearningLeaderFragment";

    List<LearningLeader_Model> LearnerList;
    RecyclerView recyclerView;
    LearningLeader_Adapter recyclerAdapter;



    private PageViewModel pageViewModel;

        public LearningLeaderFragment() {
            // Required empty public constructor
        }

        /**
         * @return A new instance of fragment RecentsFragment.
         */
        public static LearningLeaderFragment newInstance() {
            return new LearningLeaderFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
            pageViewModel.setIndex(TAG);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_learningleader, container, false);
//            final TextView textView = root.findViewById(R.id.section_label);
//            pageViewModel.getText().observe(this, new Observer<String>() {
//                @Override
//                public void onChanged(@Nullable String s) {
//                    textView.setText(s);
//                }
//            });



            LearnerList = new ArrayList<>();
            recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerAdapter = new LearningLeader_Adapter(getContext(),LearnerList);
            recyclerView.setAdapter(recyclerAdapter);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<List<LearningLeader_Model>> call = apiService.getLearner();

            call.enqueue(new Callback<List<LearningLeader_Model>>() {
                @Override
                public void onResponse(Call<List<LearningLeader_Model>> call, Response<List<LearningLeader_Model>> response) {
                    LearnerList = response.body();
                    Log.d("TAG","Response = "+LearnerList);
                    recyclerAdapter.setLearnerList(LearnerList);
                }

                @Override
                public void onFailure(Call<List<LearningLeader_Model>> call, Throwable t) {
                    Log.d("TAG","Response = "+t.toString());
                }
            });






            return root;
        }
    }
