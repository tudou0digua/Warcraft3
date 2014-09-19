package com.example.warcreftverone;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

public class MainActivity extends FragmentActivity {
	private ViewPager mViewPager;
	public final static int TAB_INDEX_TAB_1=0;
	public final static int TAB_INDEX_TAB_2=1;
	public final static int TAB_INDEX_TAB_3=2;
	public final static int TAB_INDEX_TAB_4=3;
	public final static int TAB_INDEX_TAB_5=4;
	public final static int TAB_COUNT=5;
	FragmentManager fm=getSupportFragmentManager();
//	FragmentTransaction ftdetail=fm.beginTransaction();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addTab();
		setViewPager();
	}
	//添加5个tab,及设置actionbar模式
	private void addTab(){
		ActionBar actionbar=getActionBar();
		//添加5个tab
		actionbar.addTab(actionbar.newTab()
				.setText("人族")
				.setTabListener(mTabListener)
				);
		actionbar.addTab(actionbar.newTab()
				.setText("精灵")
				.setTabListener(mTabListener)
				);
		actionbar.addTab(actionbar.newTab()
				.setText("中立")
				.setTabListener(mTabListener)
				);
		actionbar.addTab(actionbar.newTab()
				.setText("兽族")
				.setTabListener(mTabListener)
				);
		actionbar.addTab(actionbar.newTab()
				.setText("不死")
				.setTabListener(mTabListener)
				);
		//actionbar设置成tab模式，无图标和标题
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  
		actionbar.setDisplayShowTitleEnabled(false);  
		actionbar.setDisplayShowHomeEnabled(false);
	}
	//设置viewpager
	private void setViewPager(){
		mViewPager = (ViewPager)findViewById(R.id.pager);  
        mViewPager.setAdapter(new MViewPagerAdapter(getSupportFragmentManager()));  
        mViewPager.setOnPageChangeListener(new MPagerListener());  
        mViewPager.setCurrentItem(TAB_INDEX_TAB_3);
	}
	//create mTabListener
	private final TabListener mTabListener = new TabListener() {  
        private final static String TAG = "TabListener";  
        @Override  
        public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {  
            // TODO Auto-generated method stub  
            Log.d(TAG, "onTabReselected");  
        }  
  
        @Override  
        public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {  
            // TODO Auto-generated method stub  
//        	FragmentManager fm=getSupportFragmentManager();
//    		FragmentTransaction ftdetail=fm.beginTransaction();
    		Fragment[] fragment_detail=new Fragment[5];
    		fragment_detail[0]=fm.findFragmentById(R.id.detail_human);
    		fragment_detail[1]=fm.findFragmentById(R.id.detail_nightelves);
    		fragment_detail[2]=fm.findFragmentById(R.id.detail_neutral);
    		fragment_detail[3]=fm.findFragmentById(R.id.detail_orcish);
    		fragment_detail[4]=fm.findFragmentById(R.id.detail_undead);
    		
    		for(int i=0;i<5;i++){
    			if(fragment_detail[i]!=null){
    				fm.popBackStack();
    			}
    		}    		
    		
//    		ftdetail.commit();
    		
    		
    		
//        	FrameLayout[] race=new FrameLayout[5];
//        	race[0]=(FrameLayout)findViewById(R.id.detail_human);
//        	race[1]=(FrameLayout)findViewById(R.id.detail_nightelves);
//        	race[2]=(FrameLayout)findViewById(R.id.detail_neutral);
//        	race[3]=(FrameLayout)findViewById(R.id.detail_orcish);
//        	race[4]=(FrameLayout)findViewById(R.id.detail_undead);
//        	
//        	Log.d(TAG, "onTabSelected()");  
//            
//        	race[0].setVisibility(View.INVISIBLE);
//        	race[1].setVisibility(View.GONE);
//        	race[2].setVisibility(View.GONE);
//        	race[3].setVisibility(View.GONE);
//        	race[4].setVisibility(View.GONE);
        	
        	
            if (mViewPager != null)  
                mViewPager.setCurrentItem(tab.getPosition());  
            
            
        }  
  
        @Override  
        public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {  
            // TODO Auto-generated method stub  
            Log.d(TAG, "onTabUnselected()");  
        }  
    };  
    //set TestPagerListener
    class MPagerListener implements OnPageChangeListener{  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
        }  
  
        @Override  
        public void onPageSelected(int arg0) {  
            getActionBar().selectTab(getActionBar().getTabAt(arg0));
        }  
    } 
    
//    @Override  
//    public boolean onKeyDown(int keyCode, KeyEvent event) {   
//        if(keyCode==KeyEvent.KEYCODE_BACK) {   
//    		Fragment[] fragment_detail=new Fragment[5];
//    		fragment_detail[0]=fm.findFragmentById(R.id.detail_human);
//    		fragment_detail[1]=fm.findFragmentById(R.id.detail_nightelves);
//    		fragment_detail[2]=fm.findFragmentById(R.id.detail_neutral);
//    		fragment_detail[3]=fm.findFragmentById(R.id.detail_orcish);
//    		fragment_detail[4]=fm.findFragmentById(R.id.detail_undead);
//    		if(fragment_detail[0]==null){
//    			num1=100;num2=100;
//        	
//    		}
//    		return super.onKeyDown(keyCode, event);
//    		
//    		
//        }else
//        	return super.onKeyDown(keyCode, event); 
//         
//    }
}
