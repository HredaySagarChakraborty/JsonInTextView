package comp.example.hreday.jsonintextview;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Info extends AsyncTask<Void, Void, Void> {

    String textView = "";
    String singleParsed = "";
    String dataparsed = "";


    @Override
    protected Void doInBackground(Void... voids) {


        try {
            URL url = new URL("https://api.myjson.com/bins/la0rz");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null) {

                line = bufferedReader.readLine();
                textView = textView + line;
            }

            JSONArray jsonArray = new JSONArray(textView);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                singleParsed = "Name:" + jsonObject.get("name") + "\n" +
                        "Gender:" + jsonObject.get("gender") + "\n" +
                        "Email:" + jsonObject.get("email") + "\n" +
                        "Hobby:" + jsonObject.get("hobby") + "\n" ;

                dataparsed=dataparsed+ singleParsed+"\n \n ";


            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.textView.setText(this.dataparsed);
    }
}
