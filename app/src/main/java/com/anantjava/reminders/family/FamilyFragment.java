package com.anantjava.reminders.family;

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
import com.anantjava.reminders.databinding.FragmentFamilyBinding;

public class FamilyFragment extends Fragment {

    private SharedPreferences prefs;
    private FragmentFamilyBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFamilyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = requireActivity().getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        RecyclerView recyclerview = binding.recyclerviewFamily;

        Reminders[] reminders = new Reminders[]{
                new Reminders(
                        "Door Lock",
                        prefs.getString("Door Lock", ""),
                        R.drawable.door_lockl
                ),
                new Reminders(
                        "Wifi Password",
                        prefs.getString("Wifi Password", ""),
                        R.drawable.wifi_pass
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