package com.example.mradulbhargava.mradulemotionapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
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
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Button mBtn_clickImage, mBtn_upload;
    ImageView mImgView_displayImage;
    TextView et_heading, et_content,et_display;
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
        Locale losl[]= Locale.getAvailableLocales();
        for(int i=0;i<losl.length;i++){
            Log.d("Local "+i,losl[i].toString());
        }

        Locale l2= new Locale("zh_TW_#Hans");
        Locale l3= new Locale("en");

        Log.d("Local ",l2.toString());
        Log.d("Local ",l3.toString());
        mBtn_clickImage = (Button) findViewById(R.id.btn_clickImage);
        mBtn_upload = (Button) findViewById(R.id.btn_upload);
        mImgView_displayImage = (ImageView) findViewById(R.id.imgview_displayImage);
        et_display= (TextView) findViewById(R.id.tv_showComputerVision);
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

        }String filename = "mradulCV.jpg";
        File sd = Environment.getExternalStorageDirectory();
        Log.d("Path",sd+"");
        File dest = new File(sd+"/", filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            photo.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();
            File file= new File(Environment.getExternalStorageDirectory()+"/"+"mradulCV.jpg");
            mImgView_displayImage.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + "mradulCV.jpg"));

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
                File file= new File(Environment.getExternalStorageDirectory()+"/"+"mradulCV.jpg");
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
                conn.setRequestProperty("uploaded_file", "mradulCV.jpg");

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
                        + "mradulCV.jpg" + "\"" + lineEnd);

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
                               HttpPost httpPost = new HttpPost("https://api.projectoxford.ai/vision/v1.0/analyze?visualFeatures=Categories,Tags,Description,Faces,ImageType,Color,Adult&language=en");


                               String json = "";


                               // 3. build jsonObject
                               JSONObject jsonObject = new JSONObject();
                               jsonObject.accumulate("url", "https://abc.tcsmobilitycloud.com/MFileUploader/mradulCV.jpg");


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
                               httpPost.setHeader("Ocp-Apim-Subscription-Key", "e2840052d673493097db47eb197423db");
                               httpPost.setHeader("Content-Type", "application/json");


                               // 8. Execute POST request to the given URL
                               HttpResponse response = httpclient.execute(httpPost);

                               // 9. receive response as inputStream
                               BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                               String json2 = reader.readLine();
                               Log.d("services ",json2);

                               JSONObject jsonObject1= new JSONObject(json2);
                               JSONObject jsonObject2= jsonObject1.getJSONObject("description");
                               JSONArray ComputerVision=jsonObject2.getJSONArray("captions");
                               JSONObject result=ComputerVision.getJSONObject(0);
                               String resultToShow="I am "+(int)(result.getDouble("confidence")*100)+" percent sure \nthis pic has "+
                               result.getString("text");
                                et_display.setText(resultToShow);


                               JSONArray ComputerVisionFaces=jsonObject1.getJSONArray("faces");
                               JSONObject resultFaces=ComputerVisionFaces.getJSONObject(0);
                                resultToShow= resultToShow + "\nPerson is "+resultFaces.getString("gender")+" of Age "+resultFaces.getInt("age");
                               et_display.setText(resultToShow);






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

