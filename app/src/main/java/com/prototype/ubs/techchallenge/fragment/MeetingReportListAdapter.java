package com.prototype.ubs.techchallenge.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.MeetingReport;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.List;

/**
 * Created by Michael on 3/7/2015.
 */
public class MeetingReportListAdapter extends BaseAdapter {
    private FragmentManager fm;
    private Context context;
    private List<MeetingReport> reportList;
    private DateTimeFormatter dateFormat;
    private LayoutInflater inflater;

    public MeetingReportListAdapter(Context context, FragmentManager fm) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.fm = fm;
        dateFormat = new DateTimeFormatterBuilder().appendDayOfMonth(2)
                .appendLiteral("/")
                .appendMonthOfYear(2)
                .appendLiteral("/")
                .appendYear(4,4).toFormatter();
    }

    @Override
    public int getCount() {
        return reportList.size();
    }

    @Override
    public Object getItem(int position) {
        return reportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((MeetingReport)getItem(position)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            final MeetingReport report = (MeetingReport) getItem(position);
            Log.d("report", report.getTopic());

            if (report.getStatus() == MeetingReport.Status.VERIFIED) {
                v = inflater.inflate(R.layout.verified_report_item, parent, false);

                TextView txtMeetingDate = (TextView) v.findViewById(R.id.verified_meeting_date);
                TextView txtTopic = (TextView) v.findViewById(R.id.meeting_reports_verified_topic);
                TextView txtTags = (TextView) v.findViewById(R.id.meeting_reports_verified_item_tag);
                TextView txtVerifiedDate = (TextView) v.findViewById(R.id.meeting_reports_date_verification);

                txtMeetingDate.setText(report.getMeetingDate().toString(dateFormat));
                txtTopic.setText(report.getTopic());

                String tagString = "";
                for (int i = 0; i < report.getTags().size(); i++) {
                    if (i < report.getTags().size() - 1) {
                        tagString += report.getTags().get(i) + ", ";
                    } else {
                        tagString += report.getTags().get(i);
                    }
                }
                txtTags.setText(tagString);
                txtVerifiedDate.setText(report.getVerifiedDate().toString(dateFormat));

            } else if (report.getStatus() == MeetingReport.Status.UNVERIFIED){
                v = inflater.inflate(R.layout.unverified_report_item, parent, false);

                TextView txtMeetingDate = (TextView) v.findViewById(R.id.unverified_meeting_date);
                TextView txtTopic = (TextView) v.findViewById(R.id.meeting_reports_unverified_topic);
                TextView txtTags = (TextView) v.findViewById(R.id.meeting_reports_unverified_item_tag);
                final Button btnVerify = (Button) v.findViewById(R.id.verify_meeting_reports_button);

                txtMeetingDate.setText(report.getMeetingDate().toString(dateFormat));
                txtTopic.setText(report.getTopic());

                String tagString = "";
                for (int i = 0; i < report.getTags().size(); i++) {
                    if (i < report.getTags().size() - 1) {
                        tagString += report.getTags().get(i) + ", ";
                    } else {
                        tagString += report.getTags().get(i);
                    }
                }
                txtTags.setText(tagString);
                btnVerify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v == btnVerify) {
                            MeetingReportDetailFragment meetingReportDetailFragment = new MeetingReportDetailFragment();

                            meetingReportDetailFragment.setMeetingReport(report);

                            fm.beginTransaction()
                                    .replace(R.id.content_container, meetingReportDetailFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
            }
        }

        return v;
    }

    public void updateData(List<MeetingReport> reportList) {
        this.reportList = reportList;
    }
}
