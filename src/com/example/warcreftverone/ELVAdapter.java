package com.example.warcreftverone;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ELVAdapter extends BaseExpandableListAdapter{
	Activity activity;
	String[] groupArray;
	String[][] childArray;
	Bitmap[][] image;
	public ELVAdapter(Activity activity,String[] groupArray,String[][] childArray,Bitmap[][] image){
		this.activity=activity;
		this.groupArray=groupArray;
		this.childArray=childArray;
		this.image=image;

	}
	//override child method
	public Object getChild(int groupPosition,int childPosition){
		return childArray[groupPosition][childPosition];
	}
	@Override
	public long getChildId(int groupPosition,int childPosition){
		return childPosition;
	}
	public int getChildrenCount(int groupPosition){
		return childArray[groupPosition].length;
	}
	public View getChildView(int groupPosition,int childPosition,
			boolean isLastChild,View convertView,ViewGroup parent){
		LinearLayout ll=new LinearLayout(activity);
		AbsListView.LayoutParams layoutParams=new AbsListView.LayoutParams(60,60);
		ll.setOrientation(0);
		ll.setPadding(40, 0, 0, 0);
		ImageView childImage=new ImageView(activity);
		childImage.setImageBitmap(image[groupPosition][childPosition]);
		childImage.setLayoutParams(layoutParams);
		ll.addView(childImage);
		String string=childArray[groupPosition][childPosition];
		TextView textview=getGenericView(string);
		textview.setPadding(5,0,0,0);
		ll.addView(textview);
		return ll;
	}
	//override group method
	public Object getGroup(int groupPosition){
		return groupArray[groupPosition];
	}
	@Override
	public long getGroupId(int groupPosition){
		return groupPosition;
	}
	public int getGroupCount(){
		return groupArray.length;
	}
	public View getGroupView(int groupPosition,boolean isExpand,
			View convertView,ViewGroup parent){
		String string=groupArray[groupPosition];
		TextView textview=getGenericView(string);
		textview.setPadding(40,0,0,0);
		return textview;
	}
	//View stub to create Group/Child View
	public TextView getGenericView(String string){
		AbsListView.LayoutParams layoutParams=new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,60);
		TextView text=new TextView(activity);
		text.setLayoutParams(layoutParams);
		text.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
		text.setTextSize(20);
		text.setText(string);
		return text;
	}
	public boolean hasStableIds(){
		return false;
	}
	public boolean isChildSelectable(int groupPosition,int childPosition){
		return true;
	}		
}