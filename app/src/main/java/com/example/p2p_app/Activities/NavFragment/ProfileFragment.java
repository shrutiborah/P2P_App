package com.example.p2p_app.Activities.NavFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p2p_app.Activities.EditprofileActivity;
import com.example.p2p_app.Activities.MyMaterialsActivity;
import com.example.p2p_app.Activities.MyProfileActivity;
import com.example.p2p_app.Activities.RegisterActivity;
import com.example.p2p_app.Activities.UploadActivity;
import com.example.p2p_app.Activities.UserProfilesActivity;
import com.example.p2p_app.R;
import com.example.p2p_app.chat.ChatActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Activity activity;
    TextView mymaterial;
    TextView myprofile;
    TextView userprofiles;

    Button editprofile;
    Button chat;
    TextView upload;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mymaterial = (TextView) view.findViewById(R.id.mymaterial);
        myprofile = (TextView) view.findViewById(R.id.Myprofilr);
        userprofiles = (TextView) view.findViewById(R.id.userprofiles);
        editprofile = (Button) view.findViewById(R.id.btnEditProfile);
        chat = (Button) view.findViewById(R.id.btnChat);
        upload = (TextView) view.findViewById(R.id.upload);

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        activity = getActivity();
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ProfileFragment.this.getActivity(), EditprofileActivity.class);
                startActivity(myIntent);
            }
        });

        mymaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ProfileFragment.this.getActivity(), MyMaterialsActivity.class);
                startActivity(myIntent);
            }
        });
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ProfileFragment.this.getActivity(), MyProfileActivity.class);
                startActivity(myIntent);
            }
        });

        userprofiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ProfileFragment.this.getActivity(), UserProfilesActivity.class);
                startActivity(myIntent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ProfileFragment.this.getActivity(), UploadActivity.class);
                startActivity(myIntent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ProfileFragment.this.getActivity(), ChatActivity.class);
                startActivity(myIntent);
            }
        });
    }
}