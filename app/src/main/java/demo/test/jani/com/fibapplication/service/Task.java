package demo.test.jani.com.fibapplication.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import demo.test.jani.com.fibapplication.util.FibonacciUtils;
import demo.test.jani.com.fibapplication.adapter.CustomDataAdapter;
import demo.test.jani.com.fibapplication.bean.ListItem;

public class Task extends AsyncTask<String , String, ArrayList<ListItem> > {

        int fibNumCal;

        /** application context. */
        private Context context;
        private ProgressDialog progressDialog;
        private FibonacciUtils fibonacciUtils;
        private CustomDataAdapter adapter;
        private RecyclerView listView;
        private String num;

        public Task(Context context, String number, RecyclerView listView, FibonacciUtils fibonacciUtils, ProgressDialog progressDialog) {
            this.context = context;
            this.listView = listView;
            this.num = number;
            this.fibonacciUtils = fibonacciUtils;
            this.progressDialog = progressDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            if(progressDialog != null) {
                progressDialog.setMessage("Please Wait");
                progressDialog.show();
                progressDialog.setCancelable(false);
            }
        }

        @Override
        protected ArrayList<ListItem> doInBackground(String... params) {
            ArrayList<ListItem> list = new ArrayList<>();
            try {
                fibNumCal = Integer.parseInt(num);
                list = fibonacciUtils.getFib(fibNumCal);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            return list;

        }

        @Override
        protected void onPostExecute(ArrayList<ListItem> s) {
            super.onPostExecute(s);
            if(s != null && s.size() > 0) {
                // dismiss the progress dialog after receiving data from API
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                //set the values to the adapter
                adapter = new CustomDataAdapter(context, s);
                if(listView != null)
                    listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
}
