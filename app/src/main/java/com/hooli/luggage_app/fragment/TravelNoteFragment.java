package com.hooli.luggage_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hooli.luggage_app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TravelNoteFragment extends Fragment {
    TextView tv_title;
    ImageView iv_add_icon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_travel_notes,container,false);
        init(v);
        return v;
    }

    private void init(View v){
        tv_title = v.findViewById(R.id.tv_title);
        iv_add_icon = v.findViewById(R.id.iv_add_icon);

        tv_title.setText("游记");
        iv_add_icon.setVisibility(View.GONE);
    }
}
