package com.example.myapplication.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button saveInfoBtn;
    Spinner userGender;
    Button userDOB;
    EditText userName, userAddress;
    int year_x, month_x, day_x;
    String gender = "";
    static final int DIALOG_ID = 0;

//    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        userName = view.findViewById(R.id.userName);
        userAddress = view.findViewById(R.id.userAddress);
        userDOB = view.findViewById(R.id.userDOB);
        saveInfoBtn = view.findViewById(R.id.saveProfileBtn);
        userGender = view.findViewById(R.id.userGender);
        displayData();
        saveInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(userName.getText()) || TextUtils.isEmpty(userAddress.getText()) || TextUtils.isEmpty(gender)){
                    Toast.makeText(getContext(),"Please fill all fields!!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    saveUserInfo(userName.getText()+"",userAddress.getText()+"",gender);
                }
            }
        });
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        List<String> list = new ArrayList<>();
        list.add("Gender");
        list.add("Male");
        list.add("Female");
        list.add("Other");
        list.add("Prefer Not to say");
        userDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


//        showDialogOnButtonClick();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userGender.setAdapter(adapter);
        userGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    view.setEnabled(false);
                    return;
                } else {
                    String itemvalue = adapterView.getItemAtPosition(position).toString();
                    gender = itemvalue;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return view;
    }

    private void displayData() {
        SharedPreferences sharedPreferences =  getContext().getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("userName","");
        String address = sharedPreferences.getString("userAddress","");
        gender = sharedPreferences.getString("gender","");
        userName.setText(name);
        userAddress.setText(address);
    }

    private void saveUserInfo(String userName, String userAddress, String gender) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName",userName);
        editor.putString("userAddress",userAddress);
        editor.putString("gender",gender);
        editor.apply();
        Toast.makeText(getContext(),"Information Saved!!!",Toast.LENGTH_LONG).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
