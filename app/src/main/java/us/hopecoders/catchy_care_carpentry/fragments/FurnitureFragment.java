package us.hopecoders.catchy_care_carpentry.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.hopecoders.catchy_care_carpentry.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FurnitureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FurnitureFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "type";
    private static final String ARG_PARAM2 = "model";

    // TODO: Rename and change types of parameters
    private String mType;
    private String mModel;

    public FurnitureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mType Parameter 1.
     * @param mModel Parameter 2.
     * @return A new instance of fragment CarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FurnitureFragment newInstance(String mType, String mModel) {
        FurnitureFragment fragment = new FurnitureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mType);
        args.putString(ARG_PARAM2, mModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_PARAM1);
            mModel = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_furniture, container, false);
    }}