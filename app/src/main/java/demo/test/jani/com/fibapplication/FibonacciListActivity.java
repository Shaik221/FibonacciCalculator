package demo.test.jani.com.fibapplication;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import demo.test.jani.com.fibapplication.util.FibonacciUtils;
import demo.test.jani.com.fibapplication.component.FibonacciListComponent;
import demo.test.jani.com.fibapplication.fragments.SummaryFragment;

public class FibonacciListActivity extends AppCompatActivity {

    private EditText text;
    private RecyclerView listView;
    private TextView totalTime, emptyView;
    private ProgressDialog progressDialog;
    private FibonacciUtils fibonacciUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.number);
        listView = findViewById(R.id.listView);
        totalTime =  findViewById(R.id.totalTime);
        emptyView = findViewById(R.id.emptyElement);
        fibonacciUtils = new FibonacciUtils();
        listView.setLayoutManager(new LinearLayoutManager(this));

        getLifecycle().addObserver(new FibonacciListComponent(FibonacciListActivity.this, text, fibonacciUtils,listView,
                totalTime,progressDialog,emptyView));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.navigation_summary:
            if(fibonacciUtils.data != null && fibonacciUtils.data.size() > 0) {
                //goto summary fragment
                Fragment summaryFragment = new SummaryFragment();
                replaceFragment(summaryFragment);
            } else {
                new FibonacciListComponent(this).showToast("No summary exists!!");
            }
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }



    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment)
    {
        Bundle args = new Bundle();
        args.putParcelableArrayList("my_custom_object", fibonacciUtils.getPerFebCalData());
        destFragment.setArguments(args);

        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.fragmentContainer, destFragment);

        // Commit the Fragment replace action.
        fragmentTransaction.commit();
    }
}
