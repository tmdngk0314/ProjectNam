package com.example.projectnam;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.graphics.Color;
import android.widget.Button;

public class PageChangeActivity {
    Button[] pageBtn;

    public PageChangeActivity(Button[] pageBtn) {
        this.pageBtn = pageBtn;
    }

    public void setPage(int pageNow, int pageMax) {
        // 1~5페이지는 <가 없다
        int madeFive = 5 - (pageNow % 5);
        int startPage = pageNow - (pageNow % 5) - 1;

        for(int i=0;i<7;i++){
            pageBtn[i].setVisibility(VISIBLE);
            pageBtn[i].setTextColor(Color.parseColor("#888888"));
        }

        if (pageNow - 5 <= 0) pageBtn[0].setVisibility(INVISIBLE);

        if ((pageNow + madeFive) * 10 >= pageMax){
            pageBtn[6].setVisibility(INVISIBLE);
            int i;
            for(i=0; (pageNow+madeFive-i)*10>=pageMax;i++){
                pageBtn[5-i].setVisibility(INVISIBLE);
            }
            for(;i<5;i++){
                pageBtn[5-i].setText(Integer.toString(pageNow+madeFive-i));
            }
        }
        else for(int i=0;i<5;i++) pageBtn[5-i].setText(Integer.toString(pageNow+madeFive-i));

        pageBtn[pageNow%5].setTextColor(Color.parseColor("#000000"));
    }

}
