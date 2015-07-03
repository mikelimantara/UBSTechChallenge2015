package com.prototype.ubs.techchallenge.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.AssetAllocation;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael on 3/7/2015.
 */
public class AssetAllocationListAdapter extends BaseExpandableListAdapter {
    private HashMap<String, List<AssetAllocation>> assetAllocation;
    private List<String> groupHeader;
    private LayoutInflater inflater;
    private final DecimalFormat percentageFormatter;

    public AssetAllocationListAdapter(Context context) {
        percentageFormatter = new DecimalFormat("###.## '%'");
        percentageFormatter.setMaximumFractionDigits(2);
        this.inflater = LayoutInflater.from(context);
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
