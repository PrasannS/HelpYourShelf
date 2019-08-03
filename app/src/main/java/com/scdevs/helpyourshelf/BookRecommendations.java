package com.scdevs.helpyourshelf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookRecommendations.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookRecommendations#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookRecommendations extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String tile1URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";
    private String tile2URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";
    private String tile3URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";
    private String tile4URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";

    private ImageView tile1;
    private ImageView tile2;
    private ImageView tile3;
    private ImageView tile4;

    private OnFragmentInteractionListener mListener;

    public BookRecommendations() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookRecommendations.
     */
    // TODO: Rename and change types and number of parameters
    public static BookRecommendations newInstance(String param1, String param2) {
        BookRecommendations fragment = new BookRecommendations();
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

        tile1.findViewById(R.id.tile1);
        Picasso.get().load(tile1URL).into(tile1);

        tile2.findViewById(R.id.tile2);
        Picasso.get().load(tile2URL).into(tile2);

        tile3.findViewById(R.id.tile3);
        Picasso.get().load(tile3URL).into(tile3);

        tile4.findViewById(R.id.tile4);
        Picasso.get().load(tile4URL).into(tile4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_recommendations, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
