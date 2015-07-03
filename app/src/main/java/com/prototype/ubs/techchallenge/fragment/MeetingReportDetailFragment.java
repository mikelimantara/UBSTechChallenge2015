package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.MainActivity22;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.MeetingReport;
import com.prototype.ubs.techchallenge.utils.Utils;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Created by Michael on 27/6/2015.
 */
public class MeetingReportDetailFragment extends Fragment implements View.OnClickListener {
    private View v;
    private TextView txtMeetingDate;
    private TextView txtAuthor;
    private TextView txtStatus;
    private TextView txtTopic;
    private ListView summaryList;
    private ArrayAdapter<String> summaryAdapter;
    private ListView agreementList;
    private ArrayAdapter<String> agreementAdapter;
    private Button btnVerify;
    private Button btnRequestEdit;

    private MeetingReport report;
    private DateTimeFormatter dateFormat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.meeting_report_unverified_detail, container, false);

        dateFormat = new DateTimeFormatterBuilder().appendDayOfMonth(1)
                .appendLiteral(" ")
                .appendMonthOfYearText()
                .appendLiteral(" ")
                .appendYear(4,4)
                .appendLiteral(" ")
                .appendClockhourOfDay(1)
                .appendLiteral(":")
                .appendMinuteOfHour(2)
                .toFormatter();

        initViews();
        populateData();

        return v;
    }

    public void setMeetingReport(MeetingReport report) {
        this.report = report;
    }

    @Override
    public void onClick(View v) {
        if (v == btnVerify) {

        } else if (v == btnRequestEdit) {

        }
    }

    private void initViews() {
        txtMeetingDate = (TextView) v.findViewById(R.id.meeting_report_date_and_time);
        txtAuthor = (TextView) v.findViewById(R.id.meeting_report_writer);
        txtStatus = (TextView) v.findViewById(R.id.meeting_report_status);
        txtTopic = (TextView) v.findViewById(R.id.meeting_reports_unverified_topic);
        summaryList = (ListView) v.findViewById(R.id.meeting_reports_summary);
        agreementList = (ListView) v.findViewById(R.id.meeting_reports_agreement);
        btnVerify = (Button) v.findViewById(R.id.meeting_reports_verify_button);
        btnRequestEdit = (Button) v.findViewById(R.id.meeting_reports_request_edit_button);
        btnVerify.setOnClickListener(this);
        btnRequestEdit.setOnClickListener(this);
    }

    private void populateData() {
        txtMeetingDate.setText(report.getMeetingDate().toString(dateFormat));
        txtAuthor.setText(report.getAuthor());

        if (report.getStatus() == MeetingReport.Status.VERIFIED) {
            txtStatus.setText("Verified on " + report.getVerifiedDate().toString(dateFormat));
            btnVerify.setVisibility(View.GONE);
            btnRequestEdit.setVisibility(View.GONE);
        } else {
            txtStatus.setText("Unverified");
        }

        txtTopic.setText(report.getTopic());
        summaryAdapter = new ArrayAdapter<>(getActivity(), R.layout.meeting_report_list_item,
                R.id.meeting_reports_bullet_point_content, report.getSummary());
        summaryList.setAdapter(summaryAdapter);
        Utils.setListViewHeightBasedOnChildren(summaryList);

        agreementAdapter = new ArrayAdapter<>(getActivity(), R.layout.meeting_report_list_item,
                R.id.meeting_reports_bullet_point_content, report.getAgreement());
        agreementList.setAdapter(agreementAdapter);
        Utils.setListViewHeightBasedOnChildren(agreementList);
    }
}
