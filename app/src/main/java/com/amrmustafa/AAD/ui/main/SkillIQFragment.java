package com.amrmustafa.AAD.ui.main;
import com.amrmustafa.AAD.ApiClient;
import com.amrmustafa.AAD.ApiInterface;
import com.amrmustafa.AAD.adapter.IQ_Adapter;
import com.amrmustafa.AAD.R;
import com.amrmustafa.AAD.model.Skill_IQ_Model;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIQFragment extends Fragment {

    private static final String TAG = "SkillIQFragment";


    List<Skill_IQ_Model> SkillList;
    RecyclerView recyclerView;
    IQ_Adapter recyclerAdapter;


    private PageViewModel pageViewModel;

    public SkillIQFragment() {
      // Required empty public constructor
    }

    /**
     * @return A new instance of fragment RecentsFragment.
     */
    public static SkillIQFragment newInstance() {
      return new SkillIQFragment();
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
      View root = inflater.inflate(R.layout.fragment_iq, container, false);
//      final TextView textView = root.findViewById(R.id.section_label);
//      pageViewModel.getText().observe(this, new Observer<String>() {
//        @Override
//        public void onChanged(@Nullable String s) {
//          textView.setText(s);
//        }
//      });






      SkillList = new ArrayList<>();
      recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview);
      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
      recyclerView.setLayoutManager(layoutManager);
      recyclerAdapter = new IQ_Adapter(getContext(),SkillList);
      recyclerView.setAdapter(recyclerAdapter);

      ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
      Call<List<Skill_IQ_Model>> call = apiService.getSkill();

      call.enqueue(new Callback<List<Skill_IQ_Model>>() {
        @Override
        public void onResponse(Call<List<Skill_IQ_Model>> call, Response<List<Skill_IQ_Model>> response) {
          SkillList = response.body();
          Log.d("TAG","Response = "+SkillList);
          recyclerAdapter.setIQList(SkillList);
        }

        @Override
        public void onFailure(Call<List<Skill_IQ_Model>> call, Throwable t) {
          Log.d("TAG","Response = "+t.toString());
        }
      });

      return root;
    }
  }
