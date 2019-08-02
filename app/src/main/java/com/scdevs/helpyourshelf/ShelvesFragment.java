package com.scdevs.helpyourshelf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShelvesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShelvesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelvesFragment extends Fragment {
    public static ShelvesFragment newInstance(){
        ShelvesFragment fragment = new ShelvesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shelves , container, false);
        return view;
    }
}
