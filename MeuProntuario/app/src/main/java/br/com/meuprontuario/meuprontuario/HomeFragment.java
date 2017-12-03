package br.com.meuprontuario.meuprontuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by betoj on 13/04/2017.
 */

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInsatanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;


    }
}
