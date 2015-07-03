package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.MeetingReport;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 3/7/2015.
 */
public class MeetingReportVerifiedFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View v;
    private ListView meetingReportsList;
    private MeetingReportListAdapter verifiedMeetingReportListAdapter;
    private List<MeetingReport> verifiedMeetingReports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.meeting_report, container, false);
        initViews();

        return v;
    }

    private void initViews() {
        prepareData();
        meetingReportsList = (ListView) v.findViewById(R.id.meeting_report_listview);
        verifiedMeetingReportListAdapter = new MeetingReportListAdapter(getActivity(),
                getActivity().getSupportFragmentManager());
        verifiedMeetingReportListAdapter.updateData(verifiedMeetingReports);
        meetingReportsList.setAdapter(verifiedMeetingReportListAdapter);
        meetingReportsList.setOnItemClickListener(this);
    }

    private void prepareData() {
        verifiedMeetingReports = new ArrayList<>();

        verifiedMeetingReports = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            MeetingReport meetingReport = new MeetingReport();
            meetingReport.setId(i);
            meetingReport.setStatus(MeetingReport.Status.VERIFIED);
            meetingReport.setTopic("Meeting " + (5 - i));
            meetingReport.setMeetingDate(new DateTime(2014, 12, 10 - i, 12, 30, 0));
            meetingReport.setVerifiedDate(new DateTime(2014, 12, 10 - i, 12, 30, 0));
            meetingReport.setAuthor("Your Wealth Manager");
            meetingReport.addTags("Category " + (i % 2) + 1);
            meetingReport.addTags("Important");

            for (int j = 1; j <= 3; j++) {
                meetingReport.addSummary("Point " + j);
                meetingReport.addAgreement("Agreement " + j);
            }

            verifiedMeetingReports.add(meetingReport);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MeetingReportDetailFragment meetingReportDetailFragment = new MeetingReportDetailFragment();

        MeetingReport meetingReport = (MeetingReport) parent.getAdapter().getItem(position);
        meetingReportDetailFragment.setMeetingReport(meetingReport);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, meetingReportDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
