package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.AssetAllocation;
import com.prototype.ubs.techchallenge.model.Portfolio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael on 26/6/2015.
 */
public class AssetAllocationFragment extends Fragment {
    private View v;
    private ActionBar actionBar;
    private ExpandableListView assetAllocationList;
    private AssetAllocationListAdapter assetAllocationAdapter;
    private ImageView assetAllocationPieChart;
    private ScrollView scrollView;

    private List<HashMap<String, List<AssetAllocation>>> assetAllocationCategories;
    private List<List<String>> groupHeaderCategories;
    private Portfolio portfolio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.asset_allocation_product_type, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        showTabsOnActionBar();

        return v;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    private void showTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                assetAllocationAdapter.updateData(getAssetAllocation(tab.getPosition()),
                        getGroupHeaders(tab.getPosition()));
                assetAllocationAdapter.notifyDataSetChanged();
            }

            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        actionBar.addTab(actionBar.newTab().setText(Html.fromHtml("Product <br/> Types")).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Sectors").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Regional").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Countries").setTabListener(tabListener));
    }

    private void initViews() {
        prepareData();
        assetAllocationList = (ExpandableListView) v.findViewById(R.id.asset_allocation_list);
        scrollView = (ScrollView) v.findViewById(R.id.asset_allocation_scroll_view);
        assetAllocationPieChart = (ImageView) v.findViewById(R.id.asset_allocation_piechart);
        assetAllocationAdapter = new AssetAllocationListAdapter();
        assetAllocationAdapter.updateData(getAssetAllocation(0), getGroupHeaders(0));
        assetAllocationList.setAdapter(assetAllocationAdapter);
    }

    private HashMap<String, List<AssetAllocation>> getAssetAllocation(int tabPosition) {
        return assetAllocationCategories.get(tabPosition);
    }

    private List<String> getGroupHeaders(int tabPosition) {
        return groupHeaderCategories.get(tabPosition);
    }

    private void prepareData() {
        assetAllocationCategories = new ArrayList<>();
        prepareHeadersData();
        prepareAssetAllocationProductTypeData();
        prepareAssetAllocationSectorData();
        prepareAssetAllocationRegionalData();
        prepareAssetAllocationCountryData();
    }

    private void prepareAssetAllocationProductTypeData() {
        HashMap<String, List<AssetAllocation>> assetAllocation = new HashMap<>();
        // 33.28%
        List<AssetAllocation> equityList = new ArrayList<>();
        equityList.add(new AssetAllocation("Equity 1", 8.32));
        equityList.add(new AssetAllocation("Equity 2", 8.32));
        equityList.add(new AssetAllocation("Equity 3", 8.32));
        equityList.add(new AssetAllocation("Equity 4", 8.32));

        // 33.28%
        List<AssetAllocation> fundsList = new ArrayList<>();
        fundsList.add(new AssetAllocation("Funds 1", 8.32));
        fundsList.add(new AssetAllocation("Funds 2", 8.32));
        fundsList.add(new AssetAllocation("Funds 3", 8.32));
        fundsList.add(new AssetAllocation("Funds 4", 8.32));

        // 33.28%
        List<AssetAllocation> bondsList = new ArrayList<>();
        bondsList.add(new AssetAllocation("Bonds 1", 8.32));
        bondsList.add(new AssetAllocation("Bonds 2", 8.32));
        bondsList.add(new AssetAllocation("Bonds 3", 8.32));
        bondsList.add(new AssetAllocation("Bonds 4", 8.32));

        //  0.11%
        List<AssetAllocation> cashDepositsList = new ArrayList<>();
        cashDepositsList.add(new AssetAllocation("Local Currency", 0.08));
        cashDepositsList.add(new AssetAllocation("Foreign Currency", 0.03));

        //  0.05%
        List<AssetAllocation> otherAssetsList = new ArrayList<>();
        otherAssetsList.add(new AssetAllocation("Other Assets 1", 0.05));

        assetAllocation.put("Equities", equityList);
        assetAllocation.put("Funds", fundsList);
        assetAllocation.put("Bonds", bondsList);
        assetAllocation.put("Cash/Deposits", cashDepositsList);
        assetAllocation.put("Other Assets", otherAssetsList);

        assetAllocationCategories.add(assetAllocation);
    }

    private void prepareAssetAllocationSectorData() {
        HashMap<String, List<AssetAllocation>> assetAllocation = new HashMap<>();
        // 45.60%
        List<AssetAllocation> financialList = new ArrayList<>();
        financialList.add(new AssetAllocation("Funds 2", 30.40));
        financialList.add(new AssetAllocation("Equity 1", 15.20));

        // 23.50%
        List<AssetAllocation> technologyList = new ArrayList<>();
        technologyList.add(new AssetAllocation("Funds 1", 22.30));
        technologyList.add(new AssetAllocation("Funds 3", 1.20));

        // 22.30%
        List<AssetAllocation> utilityList = new ArrayList<>();
        utilityList.add(new AssetAllocation("Bonds 1", 11.17));
        utilityList.add(new AssetAllocation("Bonds 2", 11.13));

        //  8.60%
        List<AssetAllocation> industrialList = new ArrayList<>();
        industrialList.add(new AssetAllocation("Bonds 3", 2.20));
        industrialList.add(new AssetAllocation("Equity 4", 1.50));
        industrialList.add(new AssetAllocation("Funds 3", 1.30));
        industrialList.add(new AssetAllocation("Bonds 4", 1.30));
        industrialList.add(new AssetAllocation("Funds 4", 1.10));
        industrialList.add(new AssetAllocation("Bonds 2", 0.60));
        industrialList.add(new AssetAllocation("Funds 3", 0.60));

        assetAllocation.put("Financials", financialList);
        assetAllocation.put("Technologies", technologyList);
        assetAllocation.put("Utilities", utilityList);
        assetAllocation.put("Industrials", industrialList);

        assetAllocationCategories.add(assetAllocation);
    }

    private void prepareAssetAllocationRegionalData() {
        HashMap<String, List<AssetAllocation>> assetAllocation = new HashMap<>();

        // 55.80%
        List<AssetAllocation> americaList = new ArrayList<>();
        americaList.add(new AssetAllocation("Funds 1", 30.40));
        americaList.add(new AssetAllocation("Funds 2", 15.20));
        americaList.add(new AssetAllocation("Equity 1", 10.20));

        // 32.90%
        List<AssetAllocation> europeList = new ArrayList<>();
        europeList.add(new AssetAllocation("Equity 2", 22.30));
        europeList.add(new AssetAllocation("Bonds 1", 5.20));
        europeList.add(new AssetAllocation("Bonds 2", 3.20));
        europeList.add(new AssetAllocation("Bonds 3", 2.20));

        // 5.10%
        List<AssetAllocation> australiaList = new ArrayList<>();
        australiaList.add(new AssetAllocation("Funds 3", 3.95));
        australiaList.add(new AssetAllocation("Funds 3", 1.15));

        //  4.40%
        List<AssetAllocation> asiaList = new ArrayList<>();
        asiaList.add(new AssetAllocation("Equity 3", 2.20));
        asiaList.add(new AssetAllocation("Equity 4", 2.20));

        //  1.80%
        List<AssetAllocation> africaList = new ArrayList<>();
        africaList.add(new AssetAllocation("Bonds 4", 1.80));

        assetAllocation.put("America", americaList);
        assetAllocation.put("Europe", europeList);
        assetAllocation.put("Australia", australiaList);
        assetAllocation.put("Asia", asiaList);
        assetAllocation.put("Africa", africaList);

        assetAllocationCategories.add(assetAllocation);
    }

    private void prepareAssetAllocationCountryData() {
        HashMap<String, List<AssetAllocation>> assetAllocation = new HashMap<>();

        // 55.80%
        List<AssetAllocation> usaList = new ArrayList<>();
        usaList.add(new AssetAllocation("Funds 1", 30.40));
        usaList.add(new AssetAllocation("Funds 2", 15.20));
        usaList.add(new AssetAllocation("Equity 1", 10.20));

        // 32.90%
        List<AssetAllocation> ukList = new ArrayList<>();
        ukList.add(new AssetAllocation("Equity 2", 22.30));
        ukList.add(new AssetAllocation("Bonds 1", 5.20));
        ukList.add(new AssetAllocation("Bonds 2", 3.20));
        ukList.add(new AssetAllocation("Bonds 3", 2.20));

        // 5.10%
        List<AssetAllocation> indonesiaList = new ArrayList<>();
        indonesiaList.add(new AssetAllocation("Funds 3", 3.95));
        indonesiaList.add(new AssetAllocation("Funds 3", 1.15));

        //  4.40%
        List<AssetAllocation> australiaList = new ArrayList<>();
        australiaList.add(new AssetAllocation("Equity 3", 2.20));
        australiaList.add(new AssetAllocation("Equity 4", 2.20));

        //  1.80%
        List<AssetAllocation> southAfricaList = new ArrayList<>();
        southAfricaList.add(new AssetAllocation("Bonds 4", 1.80));

        assetAllocation.put("USA", usaList);
        assetAllocation.put("United Kingdom", ukList);
        assetAllocation.put("Indonesia", indonesiaList);
        assetAllocation.put("Australia", australiaList);
        assetAllocation.put("South Africa", southAfricaList);

        assetAllocationCategories.add(assetAllocation);
    }

    private void prepareHeadersData() {
        groupHeaderCategories = new ArrayList<>();

        List<String> groupHeaderProductType = new ArrayList<>();
        groupHeaderProductType.add("Equities");
        groupHeaderProductType.add("Funds");
        groupHeaderProductType.add("Bonds");
        groupHeaderProductType.add("Cash/Deposits");
        groupHeaderProductType.add("Other Assets");

        List<String> groupHeaderSector = new ArrayList<>();
        groupHeaderSector.add("Financials");
        groupHeaderSector.add("Technologies");
        groupHeaderSector.add("Utilities");
        groupHeaderSector.add("Industrials");

        List<String> groupHeaderRegional = new ArrayList<>();
        groupHeaderRegional.add("America");
        groupHeaderRegional.add("Europe");
        groupHeaderRegional.add("Australia");
        groupHeaderRegional.add("Asia");
        groupHeaderRegional.add("Africa");

        List<String> groupHeaderCountry = new ArrayList<>();
        groupHeaderCountry.add("USA");
        groupHeaderCountry.add("United Kingdom");
        groupHeaderCountry.add("Indonesia");
        groupHeaderCountry.add("Australia");
        groupHeaderCountry.add("South Africa");

        groupHeaderCategories.add(groupHeaderProductType);
        groupHeaderCategories.add(groupHeaderSector);
        groupHeaderCategories.add(groupHeaderRegional);
        groupHeaderCategories.add(groupHeaderCountry);
    }

    private class AssetAllocationListAdapter extends BaseExpandableListAdapter {
        private HashMap<String, List<AssetAllocation>> assetAllocation;
        private List<String> groupHeader;
        private LayoutInflater inflater;
        private final DecimalFormat percentageFormatter;

        public AssetAllocationListAdapter() {
            percentageFormatter = new DecimalFormat("###.## '%'");
            percentageFormatter.setMaximumFractionDigits(2);
            this.inflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groupHeader.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return assetAllocation.get(groupHeader.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupHeader.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return assetAllocation.get(groupHeader.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.asset_allocation_list_header, parent, false);
            }

            TextView txtName = (TextView) v.findViewById(R.id.asset_allocation_expandable_list_header);
            TextView txtPercentage = (TextView) v.findViewById(R.id.asset_allocation_expandable_list_header_percentage);

            String header = groupHeader.get(groupPosition);
            txtName.setText(header);

            List<AssetAllocation> groupChild = assetAllocation.get(header);
            Double totalPercentage = 0.00;
            for (AssetAllocation asset: groupChild) {
                totalPercentage += asset.getPercentage();
            }

            txtPercentage.setText(percentageFormatter.format(totalPercentage));

            return v;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.asset_allocation_list_content, parent, false);
            }

            TextView txtName = (TextView) v.findViewById(R.id.asset_allocation_expandable_list_content);
            TextView txtPercentage = (TextView) v.findViewById(R.id.asset_allocation_expandable_list_content_percentage);

            AssetAllocation child = (AssetAllocation) getChild(groupPosition, childPosition);

            txtName.setText(child.getProductName());
            txtPercentage.setText(percentageFormatter.format(child.getPercentage()));

            return v;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public void updateData(HashMap<String, List<AssetAllocation>> assetAllocation,
                               List<String> groupHeaders){
            this.assetAllocation = assetAllocation;
            this.groupHeader = groupHeaders;
        }
    }
}
