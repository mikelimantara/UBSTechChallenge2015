package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.MeetingReport;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 27/6/2015.
 */
public class MeetingReportUnverifiedFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View v;
    private ListView meetingReportsList;
    private MeetingReportListAdapter unverifiedMeetingReportListAdapter;
    private List<MeetingReport> unverifiedMeetingReports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.meeting_report, container, false);
        initViews();

        return v;
    }

    private void initViews() {
        prepareData();
        meetingReportsList = (ListView) v.findViewById(R.id.meeting_report_listview);
        unverifiedMeetingReportListAdapter = new MeetingReportListAdapter(getActivity(),
                getActivity().getSupportFragmentManager());
        unverifiedMeetingReportListAdapter.updateData(unverifiedMeetingReports);
//        verifiedMeetingReportListAdapter = new MeetingReportListAdapter(getActivity(),
//                getActivity().getSupportFragmentManager());
//        verifiedMeetingReportListAdapter.updateData(verifiedMeetingReports);
        meetingReportsList.setAdapter(unverifiedMeetingReportListAdapter);
        meetingReportsList.setOnItemClickListener(this);
    }

    private void prepareData() {
        unverifiedMeetingReports = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            MeetingReport meetingReport = new MeetingReport();
            meetingReport.setId(i + 5);
            meetingReport.setStatus(MeetingReport.Status.UNVERIFIED);
            meetingReport.setTopic("Meeting " + (8 - i));
            meetingReport.setMeetingDate(new DateTime(2015, 05, 30 - i, 10, 30, 0));
            meetingReport.setVerifiedDate(new DateTime(2015, 05, 30 - i, 10, 30, 0));
            meetingReport.setAuthor("Your Wealth Manager");
            meetingReport.addTags("Category " + (i % 2) + 1);
            meetingReport.addTags("Important");

            for (int j = 1; j <= 3; j++) {
                meetingReport.addSummary("Point " + j);
                meetingReport.addAgreement("Agreement " + j);
            }

            unverifiedMeetingReports.add(meetingReport);
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
