package us.hopecoders.catchy_care_carpentry.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.hopecoders.catchy_care_carpentry.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMAGE = "param1";

    // TODO: Rename and change types of parameters
    private String mImage;

    public GalaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mImage Parameter 1.
     * @return A new instance of fragment GalaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalaryFragment newInstance(String mImage) {
        GalaryFragment fragment = new GalaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE, mImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getString(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galary, container, false);
    }
}