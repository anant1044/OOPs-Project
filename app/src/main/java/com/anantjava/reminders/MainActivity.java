package com.anantjava.reminders;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.anantjava.reminders.databinding.ActivityMainBinding;
import com.anantjava.reminders.family.FamilyFragment;
import com.anantjava.reminders.personal.PersonalFragment;
import com.anantjava.reminders.work.WorkFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        binding.fragmentContainer.setAdapter(new ScreenSlidePagerAdapter(this));
        new TabLayoutMediator(binding.tabLayout, binding.fragmentContainer,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0:
                                tab.setText("Personal");
                                tab.setIcon(AppCompatResources.getDrawable(MainActivity.this, R.drawable.icons8_personal));
                                break;
                            case 1:
                                tab.setText("Work");
                                tab.setIcon(AppCompatResources.getDrawable(MainActivity.this, R.drawable.icons8_work));
                                break;
                            default:
                                tab.setText("Family");
                                tab.setIcon(AppCompatResources.getDrawable(MainActivity.this, R.drawable.icons8_family));
                                break;
                        }
                    }
                }
        ).attach();
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new PersonalFragment();
                case 1:
                    return new WorkFragment();
                default:
                    return new FamilyFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
