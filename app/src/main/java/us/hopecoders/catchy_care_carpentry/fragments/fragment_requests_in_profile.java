package us.hopecoders.catchy_care_carpentry.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.hopecoders.catchy_care_carpentry.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_requests_in_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_requests_in_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "description";

    // TODO: Rename and change types of parameters
    private String mName;
    private String mDescription;

    public fragment_requests_in_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mName Parameter 1.
     * @param mDescription Parameter 2.
     * @return A new instance of fragment fragment_requests_in_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_requests_in_profile newInstance(String mName, String mDescription) {
        fragment_requests_in_profile fragment = new fragment_requests_in_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mName);
        args.putString(ARG_PARAM2, mDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
            mDescription = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests_in_profile, container, false);
    }
}