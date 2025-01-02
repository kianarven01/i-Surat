package com.example.gca2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MaterialFragment extends Fragment {

    LinearLayout baybay;
    LinearLayout salita;
    LinearLayout bantas;
    LinearLayout baybay_ppt;
    LinearLayout salita_ppt;
    LinearLayout bantas_ppt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_material, container, false);
        baybay = rootView.findViewById(R.id.baybayVid);
        salita = rootView.findViewById(R.id.salitaVid);
        bantas = rootView.findViewById(R.id.bantasVid);
        baybay_ppt = rootView.findViewById(R.id.baybayPpt);
        salita_ppt = rootView.findViewById(R.id.salitaPpt);
        bantas_ppt = rootView.findViewById(R.id.bantasPpt);

        baybay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "Wastong Pagbabaybay");
                bundle.putString("link", "https://drive.google.com/uc?export=download&id=1--e57DvZ8xYUO6ino8vFl2oQb3iINm8Q");
                VideoViewFragment fragment = new VideoViewFragment();
                fragment.setArguments(bundle);
                navigateToFragment(fragment);
            }
        });

        salita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "Wastong gamit ng mga salita");
                bundle.putString("link", "https://drive.google.com/uc?export=download&id=1pbyNZ-puAht4fa3i8fhZK2B9oFwWkwBW");
                VideoViewFragment fragment = new VideoViewFragment();
                fragment.setArguments(bundle);
                navigateToFragment(fragment);
            }
        });

        bantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "Wastong Pagbabantas");
                bundle.putString("link", "https://drive.google.com/uc?export=download&id=1DtZiKMCW11rH4hLlquHfrb_5CD66Jp4P");
                VideoViewFragment fragment = new VideoViewFragment();
                fragment.setArguments(bundle);
                navigateToFragment(fragment);
            }
        });

        baybay_ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "Wastong Pagbabaybay PPT");
                bundle.putString("link", "https://www.slideshare.net/slideshow/pagbabaybay-nang-tama-sa-mga-salita/238635476");
                PptViewFragment fragment = new PptViewFragment();
                fragment.setArguments(bundle);
                navigateToFragment(fragment);
            }
        });

        salita_ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "Wastong gamit ng mga salita PPT");
                bundle.putString("link", "https://www.slideshare.net/slideshow/wastong-gamit-ng-salita-38295197/38295197");
                PptViewFragment fragment = new PptViewFragment();
                fragment.setArguments(bundle);
                navigateToFragment(fragment);
            }
        });

        bantas_ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "Wastong Pagbabantas PPT");
                bundle.putString("link", "https://www.slideshare.net/slideshow/wastong-pagbabantas/10163504");
                PptViewFragment fragment = new PptViewFragment();
                fragment.setArguments(bundle);
                navigateToFragment(fragment);
            }
        });

        return rootView;
    }

    private void navigateToFragment(Fragment fragment) {
        if (getActivity() != null) {
            // Replace the current fragment and add the transaction to the back stack
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}