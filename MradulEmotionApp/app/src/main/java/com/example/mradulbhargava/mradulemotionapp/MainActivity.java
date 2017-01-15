package com.example.mradulbhargava.mradulemotionapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button mBtn_clickImage, mBtn_upload;
    ImageView mImgView_displayImage;
    TextView et_heading, et_content;
    private static final int CAMERA_REQUEST = 101;
    ProgressDialog pd;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        pd=new ProgressDialog(this);
        mBtn_clickImage = (Button) findViewById(R.id.btn_clickImage);
        mBtn_upload = (Button) findViewById(R.id.btn_upload);
        mImgView_displayImage = (ImageView) findViewById(R.id.imgview_displayImage);

        et_heading = (TextView) findViewById(R.id.tv_heading);

        mBtn_clickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        mBtn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMicroServices();
            }
        });



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo=null;
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
             photo = (Bitmap) data.getExtras().get("data");
            //mImgView_displayImage.setImageBitmap(photo);

        }String filename = "mradul.jpg";
        File sd = Environment.getExternalStorageDirectory();
        Log.d("Path",sd+"");
        File dest = new File(sd+"/", filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            photo.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();
            File file= new File(Environment.getExternalStorageDirectory()+"/"+"mradul.jpg");
            mImgView_displayImage.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + "mradul.jpg"));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

 public void callMicroServices(){
        new MicroSoftServices().execute();
    }


    private class MicroSoftServices extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Analysing your Image...");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
            /*    HttpClient httpclient = new DefaultHttpClient();
                HttpPost postRequest = new HttpPost("https://api.projectoxford.ai/emotion/v1.0/recognize");
                postRequest.addHeader("Ocp-Apim-Subscription-Key", "e271338dffdc4949854d6ea1e95b6147");
                //don't set the content type here
                //postRequest.addHeader("Content-Type","multipart/form-data");

                File file= new File(Environment.getExternalStorageDirectory()+"/"+"mradul.jpg");
                InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1);
                reqEntity.setContentType("binary/octet-stream");
                reqEntity.setChunked(true); // Send in multiple parts if needed
                postRequest.setEntity(reqEntity);
                HttpResponse response = httpclient.execute(postRequest);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("response",json);*/

             //   String fileName = sourceFileUri;

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File file= new File(Environment.getExternalStorageDirectory()+"/"+"mradul.jpg");
                FileInputStream fileInputStream = new FileInputStream(file);
                URL url = new URL("https://abc.tcsmobilitycloud.com/ReactNative/File/upload");

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", "mradul.jpg");

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
                        + "mradul.jpg" + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                int serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {

                          /*  String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    +" http://www.androidexample.com/media/uploads/"
                                    +uploadFileName;

                            messageText.setText(msg);*/
                          Log.d("response","uploaded successfullyy");
                            //Calling microsoft services
                           try {
                               HttpClient httpclient = new DefaultHttpClient();

                               // 2. make POST request to the given URL
                               HttpPost httpPost = new HttpPost("https://api.projectoxford.ai/emotion/v1.0/recognize");

                               String json = "";


                               // 3. build jsonObject
                               JSONObject jsonObject = new JSONObject();
                               jsonObject.accumulate("url", "https://abc.tcsmobilitycloud.com/MFileUploader/mradul.jpg");


                               // 4. convert JSONObject to JSON to String
                               json = jsonObject.toString();

                               // ** Alternative way to convert Person object to JSON string usin Jackson Lib
                               // ObjectMapper mapper = new ObjectMapper();
                               // json = mapper.writeValueAsString(person);

                               // 5. set json to StringEntity
                               StringEntity se = new StringEntity(json);

                               // 6. set httpPost Entity
                               httpPost.setEntity(se);

                               // 7. Set some headers to inform server about the type of the content
                               httpPost.setHeader("Ocp-Apim-Subscription-Key", "e271338dffdc4949854d6ea1e95b6147");
                               httpPost.setHeader("Content-Type", "application/json");


                               // 8. Execute POST request to the given URL
                               HttpResponse response = httpclient.execute(httpPost);

                               // 9. receive response as inputStream
                               BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                               String json2 = reader.readLine();
                               Log.d("services ",json2);


                               JSONArray jsonObj = new JSONArray(json2);

                               // Getting JSON Array node
                               JSONObject jk = jsonObj.getJSONObject(0);
                               JSONObject scores= jk.getJSONObject("scores");




                                   int anger= (int)(scores.getDouble("anger")*100);
                               int contempt= (int)(scores.getDouble("contempt")*100);
                               int disgust= (int)(scores.getDouble("disgust")*100);
                               int fear= (int)(scores.getDouble("fear")*100);
                               int happiness= (int)(scores.getDouble("happiness")*100);
                               int neutral= (int)(scores.getDouble("neutral")*100);
                               int sadness= (int)(scores.getDouble("sadness")*100);
                               int surprise= (int)(scores.getDouble("surprise")*100);
                               if(flag==0) {
                                   flag=1;
                                   FragmentManager fragmentManager = getFragmentManager();
                                   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                   ShowEmotionFragment hello = new ShowEmotionFragment(anger, contempt, disgust, fear, happiness, neutral, sadness, surprise);
                                   fragmentTransaction.add(R.id.showEmotionLayout, hello, "HELLO");
                                   fragmentTransaction.commit();
                               }
                               else {

                               }


                               ShowEmotionFragment hello = new ShowEmotionFragment(anger, contempt, disgust, fear, happiness, neutral, sadness, surprise);

                               FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                               transaction.replace(R.id.showEmotionLayout, hello);
                               transaction.addToBackStack(null);

// Commit the transaction
                               transaction.commit();
                           }catch (Exception e)
                           {
                               e.printStackTrace();
                           }




                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (Exception e) {
                Log.e("URISyntaxException", e.toString());
                return null;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
        }
    }
}

