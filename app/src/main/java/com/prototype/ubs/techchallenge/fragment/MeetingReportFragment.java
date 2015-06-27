package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.MeetingReport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 27/6/2015.
 */
public class MeetingReportFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View v;
    private ActionBar actionBar;
    private ListView meetingReportsList;
    private MeetingReportListAdapter unverifiedMeetingReportListAdapter;
    private MeetingReportListAdapter verifiedMeetingReportListAdapter;
    private List<MeetingReport> verifiedMeetingReports;
    private List<MeetingReport> unverifiedMeetingReports;
    private int tabPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.meeting_report, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        showTabsOnActionBar();
        ((MainActivity) getActivity()).setMenuBarState(MainActivity.MenuBarState.DEFAULT);
        getActivity().invalidateOptionsMenu();

        return v;
    }

    private void prepareData() {
        verifiedMeetingReports = new ArrayList<>();
        unverifiedMeetingReports = new ArrayList<>();

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

    private void showTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                if (tab.getPosition() == 0) {
                    tabPosition = tab.getPosition();
                    meetingReportsList.setAdapter(unverifiedMeetingReportListAdapter);
                } else if (tab.getPosition() == 1) {
                    tabPosition = tab.getPosition();
                    meetingReportsList.setAdapter(verifiedMeetingReportListAdapter);
                }
            }

            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        actionBar.addTab(actionBar.newTab().setText("Unverified Reports").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Verified Reports").setTabListener(tabListener));
    }

    private void initViews() {
        prepareData();
        meetingReportsList = (ListView) v.findViewById(R.id.meeting_report_listview);
        unverifiedMeetingReportListAdapter = new MeetingReportListAdapter();
        unverifiedMeetingReportListAdapter.updateData(unverifiedMeetingReports);
        verifiedMeetingReportListAdapter = new MeetingReportListAdapter();
        verifiedMeetingReportListAdapter.updateData(verifiedMeetingReports);
        meetingReportsList.setAdapter(unverifiedMeetingReportListAdapter);
        meetingReportsList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("meeting", "clicked");

        MeetingReportDetailFragment meetingReportDetailFragment = new MeetingReportDetailFragment();

        MeetingReport meetingReport = (MeetingReport) parent.getAdapter().getItem(position);
        meetingReportDetailFragment.setMeetingReport(meetingReport);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, meetingReportDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    private class MeetingReportListAdapter extends BaseAdapter {
        private List<MeetingReport> reportList;
        private DateTimeFormatter dateFormat;
        private LayoutInflater inflater;

        public MeetingReportListAdapter() {
            inflater = LayoutInflater.from(getActivity());
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
                MeetingReport report = (MeetingReport) getItem(position);
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
                }
            }

            return v;
        }

        private void updateData(List<MeetingReport> reportList) {
            this.reportList = reportList;
        }
    }
}
