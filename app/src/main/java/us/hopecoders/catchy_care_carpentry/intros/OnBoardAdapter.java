package us.hopecoders.catchy_care_carpentry.intros;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.SignUp;

public class OnBoardAdapter extends FragmentPagerAdapter {
    public OnBoardAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:return new FragmentA();
            case 1:return new FragmentB();
            case 2:return new FragmentC();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
