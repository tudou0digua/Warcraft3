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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UnitFragment extends Fragment{
	private View view;
	private String dirName;
	private static int listNum;
	private static String fileName="war3humanunit.json";
	private String[][] UnitInfo;
	private String[][][] UnitSkillInfo;
	private TextView[] unitText=new TextView[22];
	private TextView[][] skillText=new TextView[7][3];
	private ImageView unitImage;
	private ImageView[] skillImage=new ImageView[7];
	private RelativeLayout[] Relayout=new RelativeLayout[7];
	
	public UnitFragment(){}
	public static UnitFragment newInstance(int listN,String fileNameUnit,String dirName){
		UnitFragment uf=new UnitFragment();
		Bundle bundle=new Bundle();
		bundle.putInt("listnum", listN);
		bundle.putString("fileNameUnit",fileNameUnit);
		bundle.putString("dirName", dirName);
		uf.setArguments(bundle);
		return uf;
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
			fileName=args.getString("fileNameUnit");
			dirName=args.getString("dirName");
		}
	    //得到unit json数据
	    parseJson(getJson(fileName));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		if(container==null){
			return null;
		}
		view=inflater.inflate(R.layout.unit, container,false);		
		getConpoment();
		setUnitView(listNum);
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
			UnitInfo=new String[23][len];
			UnitSkillInfo=new String[len][][];
			//get heroName
			for(int i=0;i<len;i++){
				JSONObject object=array.getJSONObject(i);
				JSONArray skill=object.getJSONArray("skill");
				UnitSkillInfo[i]=new String[skill.length()][];
				//get unitSkillDetaill
				for(int j=0;j<skill.length();j++){
					JSONObject skillObject=skill.getJSONObject(j);
					UnitSkillInfo[i][j]=new String[4];
					UnitSkillInfo[i][j][0]=skillObject.getString("skill");
					UnitSkillInfo[i][j][1]=skillObject.getString("skilldescription");
					UnitSkillInfo[i][j][2]=skillObject.getString("skilldetail");
					UnitSkillInfo[i][j][3]=skillObject.getString("skillimage");
					
				}
				//get UnitDetail
				UnitInfo[0][i]=object.getString("level");
				UnitInfo[1][i]=object.getString("cost");
				UnitInfo[2][i]=object.getString("unitType");
				UnitInfo[3][i]=object.getString("attackType");
				UnitInfo[4][i]=object.getString("weaponType");
				UnitInfo[5][i]=object.getString("armonType");
				UnitInfo[6][i]=object.getString("armon");
				UnitInfo[7][i]=object.getString("groundAttack");
				UnitInfo[8][i]=object.getString("airAttack");
				UnitInfo[9][i]=object.getString("life");
				UnitInfo[10][i]=object.getString("lifeRecovery");
				UnitInfo[11][i]=object.getString("mana");
				UnitInfo[12][i]=object.getString("manaRecovery");
				UnitInfo[13][i]=object.getString("attackRange");
				UnitInfo[14][i]=object.getString("dayView");
				UnitInfo[15][i]=object.getString("nightView");
				UnitInfo[16][i]=object.getString("speed");
				UnitInfo[17][i]=object.getString("trainingTime");
				UnitInfo[18][i]=object.getString("trainingPlace");
				UnitInfo[19][i]=object.getString("requirement");
				UnitInfo[20][i]=object.getString("hotKey");
				UnitInfo[21][i]=object.getString("description");
				UnitInfo[22][i]=object.getString("imageName3");
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}	
	
	private void getConpoment(){
		//get unitText
		unitText[0]=(TextView)view.findViewById(R.id.textView2);
		unitText[1]=(TextView)view.findViewById(R.id.textView4);
		unitText[2]=(TextView)view.findViewById(R.id.textView6);
		unitText[3]=(TextView)view.findViewById(R.id.textView8);
		unitText[4]=(TextView)view.findViewById(R.id.textView10);
		unitText[5]=(TextView)view.findViewById(R.id.textView12);
		unitText[6]=(TextView)view.findViewById(R.id.textView14);
		unitText[7]=(TextView)view.findViewById(R.id.textView16);
		unitText[8]=(TextView)view.findViewById(R.id.textView18);
		unitText[9]=(TextView)view.findViewById(R.id.textView20);
		unitText[10]=(TextView)view.findViewById(R.id.textView22);
		unitText[11]=(TextView)view.findViewById(R.id.textView24);
		unitText[12]=(TextView)view.findViewById(R.id.textView26);
		unitText[13]=(TextView)view.findViewById(R.id.textView28);
		unitText[14]=(TextView)view.findViewById(R.id.textView30);
		unitText[15]=(TextView)view.findViewById(R.id.textView32);
		unitText[16]=(TextView)view.findViewById(R.id.textView34);
		unitText[17]=(TextView)view.findViewById(R.id.textView36);
		unitText[18]=(TextView)view.findViewById(R.id.textView38);
		unitText[19]=(TextView)view.findViewById(R.id.textView40);
		unitText[20]=(TextView)view.findViewById(R.id.textView42);
		unitText[21]=(TextView)view.findViewById(R.id.textView45);
		//get skillText
		skillText[0][0]=(TextView)view.findViewById(R.id.skill1name);
		skillText[0][1]=(TextView)view.findViewById(R.id.skill1desc);
		skillText[0][2]=(TextView)view.findViewById(R.id.skill1detaill);
		skillText[1][0]=(TextView)view.findViewById(R.id.skill2name);
		skillText[1][1]=(TextView)view.findViewById(R.id.skill2desc);
		skillText[1][2]=(TextView)view.findViewById(R.id.skill2detaill);
		skillText[2][0]=(TextView)view.findViewById(R.id.skill3name);
		skillText[2][1]=(TextView)view.findViewById(R.id.skill3desc);
		skillText[2][2]=(TextView)view.findViewById(R.id.skill3detaill);
		skillText[3][0]=(TextView)view.findViewById(R.id.skill4name);
		skillText[3][1]=(TextView)view.findViewById(R.id.skill4desc);
		skillText[3][2]=(TextView)view.findViewById(R.id.skill4detaill);
		skillText[4][0]=(TextView)view.findViewById(R.id.skill5name);
		skillText[4][1]=(TextView)view.findViewById(R.id.skill5desc);
		skillText[4][2]=(TextView)view.findViewById(R.id.skill5detaill);
		skillText[5][0]=(TextView)view.findViewById(R.id.skill6name);
		skillText[5][1]=(TextView)view.findViewById(R.id.skill6desc);
		skillText[5][2]=(TextView)view.findViewById(R.id.skill6detaill);
		skillText[6][0]=(TextView)view.findViewById(R.id.skill7name);
		skillText[6][1]=(TextView)view.findViewById(R.id.skill7desc);
		skillText[6][2]=(TextView)view.findViewById(R.id.skill7detaill);
		//get unitImage
		unitImage=(ImageView)view.findViewById(R.id.imageView1);
		//get skillImage
		skillImage[0]=(ImageView)view.findViewById(R.id.imageView2);
		skillImage[1]=(ImageView)view.findViewById(R.id.imageView3);
		skillImage[2]=(ImageView)view.findViewById(R.id.imageView4);
		skillImage[3]=(ImageView)view.findViewById(R.id.imageView5);
		skillImage[4]=(ImageView)view.findViewById(R.id.imageView6);
		skillImage[5]=(ImageView)view.findViewById(R.id.imageView7);
		skillImage[6]=(ImageView)view.findViewById(R.id.imageView8);
		//get relativeLayout
		Relayout[0]=(RelativeLayout)view.findViewById(R.id.relativelayout2);
		Relayout[1]=(RelativeLayout)view.findViewById(R.id.relativelayout3);
		Relayout[2]=(RelativeLayout)view.findViewById(R.id.relativelayout4);
		Relayout[3]=(RelativeLayout)view.findViewById(R.id.relativelayout5);
		Relayout[4]=(RelativeLayout)view.findViewById(R.id.relativelayout6);
		Relayout[5]=(RelativeLayout)view.findViewById(R.id.relativelayout7);
		Relayout[6]=(RelativeLayout)view.findViewById(R.id.relativelayout8);
	}
	
	private void setUnitView(int listNum){
		int skillNumber=UnitSkillInfo[listNum].length;
		for(int i=0;i<22;i++){
			unitText[i].setText(UnitInfo[i][listNum]);
		}
		unitImage.setImageBitmap(getImage(dirName+UnitInfo[22][listNum]));
		//set skillView
		for(int i=0;i<7;i++){
			Relayout[i].setVisibility(View.GONE);
		}
		for(int i=0;i<skillNumber;i++){
			Relayout[i].setVisibility(View.VISIBLE);
			skillImage[i].setImageBitmap(getImage(dirName+UnitSkillInfo[listNum][i][3]));
			for(int j=0;j<3;j++){
				skillText[i][j].setText(UnitSkillInfo[listNum][i][j]);
				if(j==1){
					setTextSize(skillText[i][j],UnitSkillInfo[listNum][i][j].length());
				}
				if(j==2){
					if(UnitSkillInfo[listNum][i][j].length()<3){
						skillText[i][j].setVisibility(View.GONE);
					}
				}
			}
		}
	}
	
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
	
	private void setTextSize(TextView textview,int len){
		if(len<50) textview.setTextSize(13);
		else if(len<60) textview.setTextSize(12);
		else if(len<65) textview.setTextSize(11);
		else if(len<92) textview.setTextSize(10);
		else textview.setTextSize(9);
	}	
}
