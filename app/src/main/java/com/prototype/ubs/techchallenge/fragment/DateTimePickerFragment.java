package com.prototype.ubs.techchallenge.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Michael on 28/6/2015.
 */
public class DateTimePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    private EditText txtDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, monthOfYear, dayOfMonth);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = "";
        if (dayOfMonth < 10) {
            date += 0;
        }
        date += dayOfMonth + "/";

        if (monthOfYear < 10) {
            date += 0;
        }
        date += (monthOfYear +  1) + "/";

        date += year;

        txtDate.setText(date);
    }

    public void setTxtDate(EditText txtDate) {
        this.txtDate = txtDate;
    }
}
