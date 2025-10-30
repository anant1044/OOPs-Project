package com.anantjava.reminders.work;

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
import com.anantjava.reminders.databinding.FragmentWorkBinding;

public class WorkFragment extends Fragment {

    private FragmentWorkBinding binding;
    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWorkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = requireActivity().getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        RecyclerView recyclerview = binding.recyclerviewWork;

        Reminders[] reminders = new Reminders[]{
                new Reminders(
                        "Roll NO.",
                        prefs.getString("Roll NO.", ""),
                        R.drawable.roll_student
                ),
                new Reminders(
                        "Student ID",
                        prefs.getString("Student ID", ""),
                        R.drawable.student_ic
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