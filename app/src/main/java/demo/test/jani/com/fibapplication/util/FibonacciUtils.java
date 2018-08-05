package demo.test.jani.com.fibapplication.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

import demo.test.jani.com.fibapplication.bean.ListItem;

public class FibonacciUtils {

    public long[] perFebCalTime;
    public ArrayList<ListItem> data = new ArrayList<>();
    public long perFebCalStartTime, getPerFebCalEndTime;
    //android sdk api version
    public static int SDK_INT = 27;

    public ArrayList<ListItem> perOpTimeData = new ArrayList<>();

    //method to generate  the all fibonacci numbers in the given range
    public ArrayList<ListItem> getFib(int n) {

        if(data != null && data.size() > 0)
            data.clear();

        if(perOpTimeData != null && perOpTimeData.size() > 0)
            perOpTimeData.clear();

        int t1 = 0, t2 = 1;
        int len = n;
        int[] result = new int[len+1];
        perFebCalTime = new long[len+1];

        int i = 1;
        while (i <= n) {
            perFebCalStartTime = System.currentTimeMillis();
            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
            result[i] = t1;


            ListItem item = new ListItem(i,result[i]);
            if(data != null)
                data.add(item);
            getPerFebCalEndTime = System.currentTimeMillis();

            perFebCalTime[i] = getPerFebCalEndTime-perFebCalStartTime;

            ListItem item1 = new ListItem(i,perFebCalTime[i]);
            if(perOpTimeData != null)
                perOpTimeData.add(item1);

            i++;
        }
        setPerFebCalTimeData(perOpTimeData);

        return data;
    }

    public void setPerFebCalTimeData(ArrayList<ListItem> perOpTimeData)
    {
        this.perOpTimeData = perOpTimeData;
    }

    public  ArrayList<ListItem> getPerFebCalData()
    {
        if(perOpTimeData != null && perOpTimeData.size() > 0) {
            return perOpTimeData;
        }
        return null;
    }

    //method to hide the soft keyboard
    public void hideKeyboard(View view,Context context)
    {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
