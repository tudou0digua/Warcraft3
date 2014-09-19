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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ArchitectFragment extends Fragment{
	private View view;
	private String dirName;
	private static int listNum;
	private static String fileName="war3humanarchitect.json";
	private String[] architect;
	private ImageView architectImage;
	
	public ArchitectFragment(){}
	public static ArchitectFragment newInstance(int listN,String fileNameArchit,String dirName){
		ArchitectFragment af=new ArchitectFragment();
		Bundle bundle=new Bundle();
		bundle.putInt("listnum", listN);
		bundle.putString("fileNameArchit",fileNameArchit);
		bundle.putString("dirName", dirName);
		af.setArguments(bundle);
		return af;
	}
	public int getListNum(){
		return listNum;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	    Bundle args=getArguments();
	    if(args!=null){
			listNum=args.getInt("listnum");
			fileName=args.getString("fileNameArchit");
			dirName=args.getString("dirName");
		}
	    parseJson(getJson(fileName));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		if(container==null){
			return null;
		}
		view=inflater.inflate(R.layout.architect, container,false);		
		setArchitectView(listNum);
		return view;
	}
	
	//得到Json数据，放入StringBuilder
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
	//解析Json数据，放入heroInfo数组
	private void parseJson(String str){
		try{
			JSONArray array=new JSONArray(str);
			int len=array.length();
			architect=new String[len];
			for(int i=0;i<len;i++){
				JSONObject object=array.getJSONObject(i);
				architect[i]=object.getString("imageName3");
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	//set architect View
	private void setArchitectView(int listNum){
		architectImage=(ImageView)view.findViewById(R.id.architect);
		architectImage.setImageBitmap(getImage(dirName+architect[listNum]));
	}
	//get Image
	private Bitmap getImage(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
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
}
