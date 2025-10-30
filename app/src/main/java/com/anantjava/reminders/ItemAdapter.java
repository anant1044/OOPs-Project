package com.anantjava.reminders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Reminders[] reminders;

    public ItemAdapter(Reminders[] reminders) {
        this.reminders = reminders;
    }

    @Override
    public int getItemCount() {
        return reminders.length;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reminders, parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        final SharedPreferences prefs = holder.itemView.getContext()
                .getSharedPreferences("Reminders", Context.MODE_PRIVATE);


        holder.bind(reminders[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentPosition = holder.getAdapterPosition();
                if (currentPosition == RecyclerView.NO_POSITION) {
                    return;
                }

                Reminders currentReminder = reminders[currentPosition];

                View dialogView = LayoutInflater.from(holder.itemView.getContext())
                        .inflate(R.layout.dialog_layout, null);

                EditText editText = dialogView.findViewById(R.id.edit_text_input);

                editText.setText(prefs.getString(currentReminder.title(), ""));

                new MaterialAlertDialogBuilder(holder.itemView.getContext())
                        .setView(dialogView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newInfo = editText.getText().toString();

                                SharedPreferences.Editor editor = prefs.edit();


                                editor.putString(currentReminder.title(), newInfo);
                                editor.apply();

                                holder.info.setText(newInfo);


                                reminders[currentPosition] = new Reminders(
                                        currentReminder.title(),
                                        newInfo,
                                        currentReminder.image()
                                );
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButton("Clear", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = prefs.edit();

                                editor.remove(currentReminder.title());
                                editor.apply();

                                holder.info.setText("");

                                reminders[currentPosition] = new Reminders(
                                        currentReminder.title(),
                                        "",
                                        currentReminder.image()
                                );
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView heading;
        TextView info;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.item_image_cardview);
            heading = itemView.findViewById(R.id.item_heading_cardview);
            info = itemView.findViewById(R.id.item_info_cardview);
        }

        public void bind(Reminders reminder) {

            imageview.setImageResource(reminder.image());
            heading.setText(reminder.title());
            info.setText(reminder.info());
        }
    }
}