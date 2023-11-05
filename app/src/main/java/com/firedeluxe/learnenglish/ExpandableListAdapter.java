package com.firedeluxe.learnenglish;


import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, String> itemCollections;
    private List<String> childs;

    public ExpandableListAdapter(Activity context, List<String> childs,
                                 Map<String, String> itemCollections) {
        this.context = context;
        this.itemCollections = itemCollections;
        this.childs = childs;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return itemCollections.get(childs.get(childPosition));
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String laptop = (String) getChild(groupPosition, groupPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item_list_layout, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.translate);

        Button delete = (Button) convertView.findViewById(R.id.button_green);
        item.setText(laptop);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
//        return itemCollections.get(childs.get(groupPosition)).size();
        return 1;
    }

    public Object getGroup(int groupPosition) {
        return childs.get(groupPosition);
    }

    public int getGroupCount() {
        return childs.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_list_layout,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.word);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}