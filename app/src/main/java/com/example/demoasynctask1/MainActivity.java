package com.example.demoasynctask1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadFileFromServer().execute();
    }
    class DownloadFileFromServer extends AsyncTask<Void,Integer,Boolean> {

        private ProgressBar mProgressBarDownload;
        private TextView mTextViewResult;

        @Override
        protected void onPreExecute() {
            mProgressBarDownload=findViewById(R.id.progressbar_download);
            mTextViewResult=findViewById(R.id.textView_result);
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i=0;i<100;i++){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i+1);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress=values[0];
            mProgressBarDownload.setProgress(progress);
            mTextViewResult.setText("Loading " + progress + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            mTextViewResult.setText("Download Complete");
            super.onPostExecute(aBoolean);
        }
    }
}
