package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.AssetAllocation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael on 26/6/2015.
 */
public class AssetAllocationProductTypeFragment extends Fragment {
    private View v;
    private ExpandableListView assetAllocationList;
    private AssetAllocationListAdapter assetAllocationAdapter;
    private ImageView assetAllocationPieChart;
    private ScrollView scrollView;
    private List<String> headers;
    private HashMap<String, List<AssetAllocation>> assetAllocationData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.asset_allocation_product_type, container, false);
        initViews();

        return v;
    }

    private void initViews() {
        assetAllocationList = (ExpandableListView) v.findViewById(R.id.asset_allocation_list);
        scrollView = (ScrollView) v.findViewById(R.id.asset_allocation_scroll_view);
        assetAllocationPieChart = (ImageView) v.findViewById(R.id.asset_allocation_piechart);
        assetAllocationAdapter = new AssetAllocationListAdapter(getActivity());
        assetAllocationAdapter.updateData(assetAllocationData, headers);
        assetAllocationList.setAdapter(assetAllocationAdapter);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("drawable://" + R.drawable.asset_allocation_pie_chart,
                assetAllocationPieChart);
    }

    public void setAssetAllocationData(HashMap<String, List<AssetAllocation>> assetAllocationData,
                                       List<String> groupHeaders) {
        this.assetAllocationData = assetAllocationData;
        this.headers = groupHeaders;
    }
}
