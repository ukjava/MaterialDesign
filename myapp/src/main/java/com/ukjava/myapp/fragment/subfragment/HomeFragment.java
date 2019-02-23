package com.ukjava.myapp.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ukjava.myapp.Constants;
import com.ukjava.myapp.R;

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        homeFragment.setArguments(bundle);

        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_content, container, false);
        TextView textView = view.findViewById(R.id.fragment_text_view);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        textView.setText(s);
        return view;
    }

}
