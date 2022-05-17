package com.example.personalootd.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.personalootd.R;

// PicturFragment에서 전달받은 의류와 어울리는 코디 추천하는 곳
public class PairingFragment extends Fragment implements View.OnClickListener{

    public PairingFragment() {
        // Required empty public constructor
    }

    public static PairingFragment newInstance(String param1, String param2) {
        PairingFragment fragment = new PairingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pairing, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}