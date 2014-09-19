package com.example.warcreftverone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class NeutralFragment extends Fragment{
	private ExpandableListView elv;
	private int Race=2;
	private String listImageName="imageName";
	private String listName="name";
	private String dirName="Neutral/";
	private String fileNameHero="json/war3neutralhero.json";
//	private String fileNameUnit="json/war3neutralunit.json";
//	private String fileNameArchitect="json/war3neutralarchitect.json";
	private String fileListName="json/war3listname.json";
	private String[] groupArray;
	private String[][] childArray=new String[1][];
	private Bitmap[][] image=new Bitmap[1][];
	private String[][] imageName=new String[1][];
	
	//重写onActivityCreated()
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
    }
	//重写onCreateView(),返回fragment视图
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		View view=inflater.inflate(R.layout.elistview, container,false);
		elv=(ExpandableListView)view.findViewById(R.id.elistview);
		setExpandableListView();
		return elv;
	}
	//set ExpandableListView
	public void setExpandableListView(){
		//取得grouparray、childarray、listImage
		getGroupArrayName();
		getChildArrayName();
		getListImage();
		
		//add Adapter
		elv.setAdapter(new ELVAdapter(getActivity(),groupArray,childArray,image));
		elv.setOnChildClickListener(new OnChildClickListener(){
			@Override
		    public boolean onChildClick(ExpandableListView parent, View v,
		            int groupPosition, int childPosition, long id) {
				//show detail info
				showDetail(groupPosition,childPosition);
				return false;
			}
		});	
	}
	//得到Json数据，放入StringBuilder并返回
	private String getJson(String fileName){
			StringBuilder stringBuilder=new StringBuilder();
			try{
				BufferedReader bf=new BufferedReader(new InputStreamReader(
						getActivity().getAssets().open(fileName)));
				String line;
				while ((line=bf.readLine())!=null){
					stringBuilder.append(line);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return stringBuilder.toString();
		}	
	//得到列表图标的名称
	private String[] getName(String str,String fileName){
		String[] imagename;
		String[] error={"error"};
		try{
			JSONArray array=new JSONArray(getJson(fileName));
			imagename=new String[array.length()];
			for(int k=0;k<array.length();k++){
				JSONObject object=array.getJSONObject(k);
				imagename[k]=object.getString(str);
			}
			return imagename;
		}catch(JSONException e){
			e.printStackTrace();
			return error;
		}
	}
	//根据图片名字，得到图片（列表图标）	
	private Bitmap getImage(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getActivity().getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;
 
    }
	//得到所有列表图标存入image
	public void getListImage(){
			imageName[0]=getName(listImageName,fileNameHero);
			
			for(int i=0;i<imageName.length;i++){
				image[i]=new Bitmap[imageName[i].length];
				for(int j=0;j<imageName[i].length;j++){
					String str=dirName+imageName[i][j];
					image[i][j]=getImage(str);
				}
			}
	}		
	//得到grouparray的名称
	public void getGroupArrayName(){
		try{
			JSONArray array=new JSONArray(getJson(fileListName));
			JSONObject object=array.getJSONObject(Race);
			groupArray=new String[object.length()];
			groupArray[0]=object.getString("hero");
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	//得到childArray的名称
	public void getChildArrayName(){
		childArray[0]=getName(listName,fileNameHero);
	}
	//显示详细数据
	public void showDetail(int group,int child){
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		Fragment fragmentdetail;
		
		Fragment heroFragment=HeroFragment.newInstance(child,fileNameHero,dirName);
		fragmentdetail=heroFragment;	
		ft.replace(R.id.detail_neutral, fragmentdetail);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.addToBackStack(null);
		ft.commit();
	}
}
