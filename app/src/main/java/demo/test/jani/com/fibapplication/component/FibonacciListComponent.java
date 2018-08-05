package demo.test.jani.com.fibapplication.component;

import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import demo.test.jani.com.fibapplication.FibonacciListActivity;
import demo.test.jani.com.fibapplication.service.Task;
import demo.test.jani.com.fibapplication.util.FibonacciUtils;

public class FibonacciListComponent implements LifecycleObserver,TextView.OnEditorActionListener{

    private FibonacciUtils fibonacciUtils;
    private FibonacciListActivity context;
    private RecyclerView listView;
    private TextView totalTime, emptyView;
    private ProgressDialog progressDialog;
    private ActionBar actionBar;
    private EditText number;
    private Task task;
    private long startTime, endTime;

    //empty constructor
    public FibonacciListComponent(FibonacciListActivity context)
    {  this.context = context;  }

    public FibonacciListComponent(FibonacciListActivity context, EditText number, FibonacciUtils fibonacciUtils, RecyclerView listView,
                                  TextView totalTime, ProgressDialog progressDialog, TextView emptyView)
    {
        this.context = context;
        this.fibonacciUtils = fibonacciUtils;
        this.listView = listView;
        this.totalTime = totalTime;
        this.progressDialog = progressDialog;
        this.number = number;
        this.emptyView = emptyView;

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        initalizeList();
        number.setOnEditorActionListener(this);

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        if (FibonacciUtils.SDK_INT > 23) {
            releaseResource();
        }
    }

   @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        if (FibonacciUtils.SDK_INT <= 23) {
            //releaseResource();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        if ((FibonacciUtils.SDK_INT <= 23)) {
            initalizeList();
        }
    }

    public void initalizeList()
    {
        ActionBar actionBar = context.getSupportActionBar();
        actionBar = context.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
    }

    public void releaseResource()
    {
        listView = null;
        fibonacciUtils = null;
        progressDialog = null;
        actionBar = null;
        totalTime = null;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            //async task to load the fib numbers
            if(!number.getText().toString().isEmpty()) {

                if(fibonacciUtils != null) {
                    startTime = System.currentTimeMillis();
                    task = new Task(context, number.getText().toString(), listView, fibonacciUtils, progressDialog);
                    task.execute();
                    //hide the  keyboard
                    fibonacciUtils.hideKeyboard(number, context);
                    endTime = System.currentTimeMillis();
                    totalTime.setText(Long.toString(endTime - startTime) + " ms");
                }
            } else
                showToast("Please enter the number");

            return true;
        }
        return false;
    }
}