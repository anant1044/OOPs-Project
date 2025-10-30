package com.anantjava.reminders.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.anantjava.reminders.ItemAdapter;
import com.anantjava.reminders.R;
import com.anantjava.reminders.Reminders;
import com.anantjava.reminders.databinding.FragmentPersonalBinding;

public class PersonalFragment extends Fragment {

    private FragmentPersonalBinding binding;
    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = requireActivity().getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        RecyclerView recyclerview = binding.recyclerviewPersonal;

        Reminders[] reminders = new Reminders[]{
                new Reminders(
                        "Phone Password",
                        prefs.getString("Phone Password", ""),
                        R.drawable.phone_icon
                ),
                new Reminders(
                        "UPI Pin",
                        prefs.getString("UPI Pin", ""),
                        R.drawable.upi_icon
                ),
                new Reminders(
                        "Bike Lock",
                        prefs.getString("Bike Lock", ""),
                        R.drawable.bike_icon
                ),
                new Reminders(
                        "Metro Card",
                        prefs.getString("Metro Card", ""),
                        R.drawable.metro_card
                )
        };

        ItemAdapter adapter = new ItemAdapter(reminders);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}