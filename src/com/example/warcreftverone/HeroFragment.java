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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class HeroFragment extends Fragment{
	private View view;
	private String dirName;
	private static int listNum;
	private static String fileNameHero="War3humanhero.json";
	public String[][] heroInfo;
	public String[][] humanListImageName=new String[3][];
	private ImageView heroimage,skill1image,skill2image,skill3image,skill4image;
	private TextView name,property,speed,hotkey,attackInterval,str,dex,intelli,level,attack,armon,life,mana;
	private TextView description,skillname1,skillname2,skillname3,skillname4,skill1desc,skill2desc,skill3desc,skill4desc;
	private TextView skill1detail,skill2detail,skill3detail,skill4detail;
	private SeekBar seekBar1;
		
	public HeroFragment(){}
	public static HeroFragment newInstance(int listN,String fileNameHero,String dirName){
		HeroFragment hf=new HeroFragment();
		Bundle bundle=new Bundle();
		bundle.putInt("listnum", listN);
		bundle.putString("fileNameHero",fileNameHero);
		bundle.putString("dirName", dirName);
		hf.setArguments(bundle);
		return hf;
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
			fileNameHero=args.getString("fileNameHero");
			dirName=args.getString("dirName");
		}
	    parseJson(getJson(fileNameHero));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		if(container==null){
			return null;
		}
		view=inflater.inflate(R.layout.hero, container,false);		
		getconpoment();
		setSeekBar();
		setHeroView(listNum);
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
			heroInfo=new String[76][len];
			//get heroName
			for(int i=0;i<len;i++){
				JSONObject object=array.getJSONObject(i);
				heroInfo[0][i]=object.getString("name");
				heroInfo[1][i]=object.getString("description");
				heroInfo[2][i]=object.getString("cost");
				heroInfo[3][i]=object.getString("attackType");
				heroInfo[4][i]=object.getString("weaponType");
				heroInfo[5][i]=object.getString("ArmonType");
				heroInfo[6][i]=object.getString("attackInterval");
				heroInfo[7][i]=object.getString("attackRange");
				heroInfo[8][i]=object.getString("property");
				heroInfo[9][i]=object.getString("str");
				heroInfo[10][i]=object.getString("dex");
				heroInfo[11][i]=object.getString("int");
				heroInfo[12][i]=object.getString("lifeRecovery");
				heroInfo[13][i]=object.getString("manaRecovery");
				heroInfo[14][i]=object.getString("dayView");
				heroInfo[15][i]=object.getString("nightView");
				heroInfo[16][i]=object.getString("speed");
				heroInfo[17][i]=object.getString("traningTime");
				heroInfo[18][i]=object.getString("hotKey");
				heroInfo[19][i]=object.getString("attack1");
				heroInfo[20][i]=object.getString("attack2");
				heroInfo[21][i]=object.getString("attack3");
				heroInfo[22][i]=object.getString("attack4");
				heroInfo[23][i]=object.getString("attack5");
				heroInfo[24][i]=object.getString("attack6");
				heroInfo[25][i]=object.getString("attack7");
				heroInfo[26][i]=object.getString("attack8");
				heroInfo[27][i]=object.getString("attack9");
				heroInfo[28][i]=object.getString("attack10");
				heroInfo[29][i]=object.getString("armon1");
				heroInfo[30][i]=object.getString("armon2");
				heroInfo[31][i]=object.getString("armon3");
				heroInfo[32][i]=object.getString("armon4");
				heroInfo[33][i]=object.getString("armon5");
				heroInfo[34][i]=object.getString("armon6");
				heroInfo[35][i]=object.getString("armon7");
				heroInfo[36][i]=object.getString("armon8");
				heroInfo[37][i]=object.getString("armon9");
				heroInfo[38][i]=object.getString("armon10");
				heroInfo[39][i]=object.getString("life1");
				heroInfo[40][i]=object.getString("life2");
				heroInfo[41][i]=object.getString("life3");
				heroInfo[42][i]=object.getString("life4");
				heroInfo[43][i]=object.getString("life5");
				heroInfo[44][i]=object.getString("life6");
				heroInfo[45][i]=object.getString("life7");
				heroInfo[46][i]=object.getString("life8");
				heroInfo[47][i]=object.getString("life9");
				heroInfo[48][i]=object.getString("life10");
				heroInfo[49][i]=object.getString("mana1");
				heroInfo[50][i]=object.getString("mana2");
				heroInfo[51][i]=object.getString("mana3");
				heroInfo[52][i]=object.getString("mana4");
				heroInfo[53][i]=object.getString("mana5");
				heroInfo[54][i]=object.getString("mana6");
				heroInfo[55][i]=object.getString("mana7");
				heroInfo[56][i]=object.getString("mana8");
				heroInfo[57][i]=object.getString("mana9");
				heroInfo[58][i]=object.getString("mana10");
				heroInfo[59][i]=object.getString("skill1");
				heroInfo[60][i]=object.getString("skill2");
				heroInfo[61][i]=object.getString("skill3");
				heroInfo[62][i]=object.getString("skill4");
				heroInfo[63][i]=object.getString("skill1image");
				heroInfo[64][i]=object.getString("skill2image");
				heroInfo[65][i]=object.getString("skill3image");
				heroInfo[66][i]=object.getString("skill4image");
				heroInfo[67][i]=object.getString("skill1description");
				heroInfo[68][i]=object.getString("skill2description");
				heroInfo[69][i]=object.getString("skill3description");
				heroInfo[70][i]=object.getString("skill4description");
				heroInfo[71][i]=object.getString("skill1detail");
				heroInfo[72][i]=object.getString("skill2detail");
				heroInfo[73][i]=object.getString("skill3detail");
				heroInfo[74][i]=object.getString("skill4detail");
				heroInfo[75][i]=object.getString("imageName3");
				
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	//得到各组件
	private void getconpoment(){
		seekBar1=(SeekBar)view.findViewById(R.id.seekBar1);
		
		heroimage=(ImageView)view.findViewById(R.id.imageName);
		skill1image=(ImageView)view.findViewById(R.id.imageView2);
		skill2image=(ImageView)view.findViewById(R.id.imageView3);
		skill3image=(ImageView)view.findViewById(R.id.imageView4);
		skill4image=(ImageView)view.findViewById(R.id.imageView5);
		
		name=(TextView)view.findViewById(R.id.name);
		property=(TextView)view.findViewById(R.id.textView2);
		speed=(TextView)view.findViewById(R.id.textView7);
		hotkey=(TextView)view.findViewById(R.id.textView11);
		attackInterval=(TextView)view.findViewById(R.id.textView15);
		str=(TextView)view.findViewById(R.id.textView9);
		dex=(TextView)view.findViewById(R.id.textView13);
		intelli=(TextView)view.findViewById(R.id.textView17);
		
		level=(TextView)view.findViewById(R.id.textView23);
		attack=(TextView)view.findViewById(R.id.textView24);
		armon=(TextView)view.findViewById(R.id.textView25);
		life=(TextView)view.findViewById(R.id.textView26);
		mana=(TextView)view.findViewById(R.id.textView27);
		
		description=(TextView)view.findViewById(R.id.textView28);
		skillname1=(TextView)view.findViewById(R.id.textView29);
		skillname2=(TextView)view.findViewById(R.id.textView32);
		skillname3=(TextView)view.findViewById(R.id.textView35);
		skillname4=(TextView)view.findViewById(R.id.textView38);
		skill1desc=(TextView)view.findViewById(R.id.textView30);
		skill2desc=(TextView)view.findViewById(R.id.textView33);
		skill3desc=(TextView)view.findViewById(R.id.textView36);
		skill4desc=(TextView)view.findViewById(R.id.textView39);
		skill1detail=(TextView)view.findViewById(R.id.textView31);
		skill2detail=(TextView)view.findViewById(R.id.textView34);
		skill3detail=(TextView)view.findViewById(R.id.textView37);
		skill4detail=(TextView)view.findViewById(R.id.textView40);
	}

	private void setHeroView(int listNum){		
		String strdir;
		
		setSeekBar();
		
		name.setText(heroInfo[0][listNum]);
		property.setText(heroInfo[8][listNum]);
		speed.setText(heroInfo[16][listNum]);
		hotkey.setText(heroInfo[18][listNum]);
		attackInterval.setText(heroInfo[6][listNum]);
		str.setText(heroInfo[9][listNum]);
		dex.setText(heroInfo[10][listNum]);
		intelli.setText(heroInfo[11][listNum]);
		description.setText(heroInfo[1][listNum]);
		skillname1.setText(heroInfo[59][listNum]);
		skillname2.setText(heroInfo[60][listNum]);
		skillname3.setText(heroInfo[61][listNum]);
		skillname4.setText(heroInfo[62][listNum]);
		skill1desc.setText(heroInfo[67][listNum]);
		setTextSize(skill1desc,heroInfo[67][listNum].length());
		skill2desc.setText(heroInfo[68][listNum]);
		setTextSize(skill2desc,heroInfo[68][listNum].length());
		skill3desc.setText(heroInfo[69][listNum]);
		setTextSize(skill3desc,heroInfo[69][listNum].length());
		skill4desc.setText(heroInfo[70][listNum]);
		setTextSize(skill4desc,heroInfo[70][listNum].length());
		skill1detail.setText(heroInfo[71][listNum]);
		skill2detail.setText(heroInfo[72][listNum]);
		skill3detail.setText(heroInfo[73][listNum]);
		skill4detail.setText(heroInfo[74][listNum]);
		
		strdir=dirName+heroInfo[75][listNum];
		heroimage.setImageBitmap(getImage(strdir));
		strdir=dirName+heroInfo[63][listNum];
		skill1image.setImageBitmap(getImage(strdir));
		strdir=dirName+heroInfo[64][listNum];
		skill2image.setImageBitmap(getImage(strdir));
		strdir=dirName+heroInfo[65][listNum];
		skill3image.setImageBitmap(getImage(strdir));
		strdir=dirName+heroInfo[66][listNum];
		skill4image.setImageBitmap(getImage(strdir));
		
		
	}

	private void setTextSize(TextView textview,int len){
		if(len<50) textview.setTextSize(13);
		else if(len<57) textview.setTextSize(12);
		else if(len<60) textview.setTextSize(11);
		else if(len<88) textview.setTextSize(10);
		else textview.setTextSize(9);
	}
	
	private void setSeekBar(){
		//
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(progress==0) progress=1;
				String pro=""+progress+"";
				int pp=progress;
				level.setText(pro);
				attack.setText(heroInfo[18+pp][listNum]);
				armon.setText(heroInfo[28+pp][listNum]);
				life.setText(heroInfo[38+pp][listNum]);
				mana.setText(heroInfo[48+pp][listNum]);
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar){
				//TODO sth
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar){
				//TODO sth
			}
		});
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
}
