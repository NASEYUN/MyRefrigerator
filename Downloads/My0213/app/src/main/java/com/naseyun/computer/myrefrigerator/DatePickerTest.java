package com.naseyun.computer.myrefrigerator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerTest extends Activity {

    DatePicker mDate;
    TextView mTxtDate;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(DatePickerTest.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                try {
                    Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();
//        findViewById(R.id.btnnow).setOnClickListener(new View.OnClickListener() {
//
//            //버튼 클릭시 DatePicker로부터 값을 읽어와서 Toast메시지로 보여준다.
//
//            @Override
//
//            public void onClick(View v) {
//
//                // TODO Auto-generated method stub
//
//                String result = String.format("%d년 %d월 %d일", mDate.getYear(),
//
//                        mDate.getMonth() + 1, mDate.getDayOfMonth());
//
//                Toast.makeText(DatePickerTest.this, result, Toast.LENGTH_SHORT).show();
//            }
//
//        });
    }

}
