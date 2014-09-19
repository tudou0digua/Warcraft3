package com.example.warcreftverone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MViewPagerAdapter extends FragmentPagerAdapter {

	public MViewPagerAdapter(FragmentManager fm) {  
        super(fm);  
        // TODO Auto-generated constructor stub  
    }  
  
    @Override  
    public Fragment getItem(int arg0) {  
        // TODO Auto-generated method stub  
        switch (arg0) {  
        case MainActivity.TAB_INDEX_TAB_1:  
            return new HumanFragment();  
  
        case MainActivity.TAB_INDEX_TAB_2:  
        	return new NightElvesFragment(); 
  
        case MainActivity.TAB_INDEX_TAB_3:  
            return new NeutralFragment();  
  
        case MainActivity.TAB_INDEX_TAB_4:  
            return new OrcishFragment(); 
        
        case MainActivity.TAB_INDEX_TAB_5:
        	return new UndeadFragment();
        }
  
        throw new IllegalStateException("No fragment at position " + arg0);  
    }  
  
    @Override  
    public int getCount() {  
        // TODO Auto-generated method stub  
        return MainActivity.TAB_COUNT;  
    }

}
