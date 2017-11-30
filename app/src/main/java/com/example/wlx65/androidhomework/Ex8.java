package com.example.wlx65.androidhomework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Ex8 extends MyActivity {
    Button btn_single;
    Button btn_multi;
    Button btn_end;
    Button btn1;
    Button btn2;
    Button btn_downlaod;
    ProgressBar pg_download;
    TextView tv_download;
    ProgressBar pg1;
    EditText text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8);

        btn_single = findViewById(R.id.btn_single);
        btn_multi = findViewById(R.id.btn_multi);
        btn_end = findViewById(R.id.btn_end);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn_downlaod = findViewById(R.id.btn_download);
        pg_download = findViewById(R.id.pg_download);
        tv_download = findViewById(R.id.tv_download);
        pg1 = findViewById(R.id.pg1);
        text1 = findViewById(R.id.et1);


        btn_single.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int sum = 0;
                for(int i=0 ; i<1000000000 ; ++i) {
                    sum += i;
                }
                text1.setText(Math.random() + "asd");
            }
        });
        final Runnable f = new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0 ; i<1000000000 ; ++i) {
                    sum += i;
                }
            }
        };
        final Thread thread = new Thread(f);
        btn_multi.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                thread.start();
                text1.setText(Math.random() + "asd");
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable f = new Runnable() {
                    @Override
                    public void run() {
                        while(pg1.getProgress() < 100) {
                            pg1.incrementProgressBy(3);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                Log.e("asd", "run: ", e);
                            }
                        }
                    }
                };
                Thread t = new Thread(f);
                t.start();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable f = new Runnable() {
                    @Override
                    public void run() {
                        while(pg1.getProgress() < 100) {
                            pg1.incrementProgressBy(5);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                Log.e("asd", "run: ", e);
                            }
                        }
                    }
                };
                Thread t = new Thread(f);
                t.start();
            }
        });

        btn_downlaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask asycTask = new MyAsyncTask();
                asycTask.execute("http://acm.hznu.edu.cn/OJ");

            }
        });
    }
    private class MyAsyncTask extends AsyncTask<String,Integer,String>
    {
        private static final String TAG = "MyAsyncTask";
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                Boolean gzipFlag = false;
//                URL url = new URL("http://www.bilibili.com/");        //0或者-1 不知怎么解决
//                URL url = new URL("https://www.apache.org");
//                URL url = new URL("http://acm.hznu.edu.cn/OJ/");
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(6000);
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
                conn.setRequestProperty("Accept-Encoding", "gzip"); //Transfer-Encoding 始终为chunked identity;q=1.0,gzip;q=0.5
                conn.connect();

                Map<String ,List<String>> header = conn.getHeaderFields();  //http://nxlhero.blog.51cto.com/962631/1868483
                for(String key : header.keySet())
                {
                    Log.i(TAG,key+"---");
                    for(String value : header.get(key))
                    {
                        Log.i(TAG,value);
                    }
                    Log.i(TAG,"-------------");
                }
                int total = conn.getContentLength();
                InputStream is = conn.getInputStream();
//                GZIPInputStream gzin = null;
                if(header.containsKey("Content-Encoding"))
                {
                    for(String value : header.get("Content-Encoding"))
                    {
                        if(value.equals("gzip"))
                        {
                            gzipFlag = true;
                            break;
                        }
                    }
                }
//                if(gzipFlag)
//                    gzin = new GZIPInputStream(is);
                Log.i(TAG,total+"");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int count = 0;
                int length = -1;
                while ((length = is.read(buf)) != -1)//gzipFlag==true?((length = is.read(buf)) != -1):((length = gzin.read(buf)) != -1)
                {
                    baos.write(buf, 0, length);
                    count += length;
                    if (total > 0)
                    {
                        publishProgress((int) ((count / (float) total) * 100));
                    }
                    Log.i(TAG,new String(buf));
                    Thread.sleep(1000);
                }
                conn.disconnect();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            tv_download.setText(values[0]+"%");
            pg_download.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
        }
    }

}


