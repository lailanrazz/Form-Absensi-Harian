package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.spinner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private String[] presensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the string array from resources
        presensi = getResources().getStringArray(R.array.presensi);

        ArrayAdapter<String> adapterPresensi =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presensi);
        adapterPresensi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPresensi.setAdapter(adapterPresensi);

        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);

        binding.datePicker.init(
                binding.datePicker.getYear(),
                binding.datePicker.getMonth(),
                binding.datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = day + "/" + (month + 1) + "/" + year;
                        Toast.makeText(MainActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        binding.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                Toast.makeText(MainActivity.this, selectedTime, Toast.LENGTH_SHORT).show();
            }
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPresensiToast();
            }

        private void showPresensiToast() {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();
            int hourOfDay = timePicker.getHour();
            int minute = timePicker.getMinute();

            String selectedSpinnerItem = binding.spinnerPresensi.getSelectedItem().toString();

            String presensiMessage = String.format("Presensi berhasil dilakukan pada jam %02d:%02d, %02d/%02d/%d, dengan keterangan %s", hourOfDay, minute, day, month, year, selectedSpinnerItem);
            Toast.makeText(MainActivity.this, presensiMessage, Toast.LENGTH_SHORT).show();

        }
        });}}
