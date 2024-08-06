package com.example.taskstodo;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class open_ai_frag extends Fragment implements View.OnClickListener {

    private Button umair, awais, huzaifa;
    private String umairUrl = "https://www.instagram.com/umair_btw?igsh=ZGxtZmJhYWp0Mm1l ";
    private String awaisUrl = "https://www.instagram.com/awais_awan__?igsh=NG81eWJvaHprMm9x";
    private String huzaifaUrl = "https://www.instagram.com/huziii_here?igsh=aW5vcjY5djVxYWpn";

    public open_ai_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_ai_frag, container, false);

        umair = view.findViewById(R.id.umairInstaBtn);

        umair.setOnClickListener(this);
    ;

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), web_view.class);
        String url = "";

        if (v.getId() == R.id.umairInstaBtn) {
            url = umairUrl;
        }

        intent.putExtra("url", url);
        startActivity(intent);
    }
}
