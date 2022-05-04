package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import Adapter.AdapterViewPagerTrangChu;
import hcmute.phamvohonglam19110154.foodyapplication.R;

public class MainPageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    ViewPager viewPagerTrangChu;
    RadioButton radio_angi, radio_odau;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mainpage);

        viewPagerTrangChu = findViewById(R.id.viewPager_Trangchu);
        radio_odau = findViewById(R.id.radio_odau);
        radio_angi = findViewById(R.id.radio_angi);
        radioGroup = findViewById(R.id.radio_group_angi_odau);
        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position)
        {
            case 0: radio_odau.setChecked(true);
                break;
            case 1: radio_angi.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i)
        {
            case R.id.radio_angi:
                viewPagerTrangChu.setCurrentItem(1);
                break;
            case R.id.radio_odau:
                viewPagerTrangChu.setCurrentItem(0);
                break;
        }

    }
}