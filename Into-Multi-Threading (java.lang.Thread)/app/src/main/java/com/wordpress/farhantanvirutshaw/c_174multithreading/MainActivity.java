package com.wordpress.farhantanvirutshaw.c_174multithreading;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private EditText editText;
    private ListView listView;
    private String[] listOfImages;
    private ProgressBar progressBar;
    private LinearLayout loadingSection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.download_url);
        listView = (ListView) findViewById(R.id.url_list);
        listView.setOnItemClickListener(this);
        listOfImages = getResources().getStringArray(R.array.imageUrls);
        progressBar = (ProgressBar) findViewById(R.id.download_progress);
        loadingSection = (LinearLayout) findViewById(R.id.loading_section);

    }

    public void downloadImage(View view)
    {
        String url = editText.getText().toString();
        Thread myThread = new Thread(new DownloadImagesThread(url));
        myThread.start();
    }

    public boolean downloadImageUsingThreads(String url)
    {
        HttpURLConnection connection = null;
        boolean successful = false;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            URL downloadURL = new URL(url);
            connection = (HttpURLConnection) downloadURL.openConnection();
            inputStream = connection.getInputStream();

           file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + Uri.parse(url).getLastPathSegment());
            fileOutputStream = new FileOutputStream(file);
            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = inputStream.read(buffer)) != -1)
            {
                fileOutputStream.write(buffer,0,read);
            }
            successful = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingSection.setVisibility(View.GONE);
                }
            });
            if(connection != null)
            {
                connection.disconnect();
            }
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileOutputStream != null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return successful;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        editText.setText(listOfImages[i]);
    }

    private class DownloadImagesThread implements Runnable
    {

        private String url ;
        public DownloadImagesThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingSection.setVisibility(View.VISIBLE);
                }
            });
            downloadImageUsingThreads(url);
        }
    }
}
