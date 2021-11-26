package us.hopecoders.catchy_care_carpentry.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.hopecoders.catchy_care_carpentry.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PendingRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "description";
    private static final String ARG_PARAM3 = "phone";
    private static final String ARG_PARAM4 = "ourLocation";

    // TODO: Rename and change types of parameters
    private String mName;
    private String mDescription;
    private String mPhone;
    private String mOurLocation;

    public PendingRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mName Parameter 1.
     * @param mDescription Parameter 2.
     * @param mPhone Parameter 3.
     * @param mOurLocation Parameter 4.
     * @return A new instance of fragment PendingRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingRequestFragment newInstance(String mName, String mDescription, String mPhone, String mOurLocation) {
        PendingRequestFragment fragment = new PendingRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mName);
        args.putString(ARG_PARAM2, mDescription);
        args.putString(ARG_PARAM3, mPhone);
        args.putString(ARG_PARAM4, mOurLocation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
            mDescription = getArguments().getString(ARG_PARAM2);
            mPhone = getArguments().getString(ARG_PARAM2);
            mOurLocation = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pending_request, container, false);
    }
}