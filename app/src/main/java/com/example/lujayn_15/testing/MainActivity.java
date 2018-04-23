package com.example.lujayn_15.testing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.lujayn_15.testing.Utility.checkConnectivity;

public class MainActivity extends AppCompatActivity {

    Activity activity;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        queue = Volley.newRequestQueue(this);

        makeJsonObjReq();
        getPollsData();
        ParseJson(loadJSONFromAsset());
    }

    private void ParseJson(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONObject jsonVideoId = jsonObject1.getJSONObject("id");
                JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                if (jsonVideoId.has("videoId")){
                    String videoid = jsonVideoId.getString("videoId");
                    Log.e("MAinActivity", " New Video Id" + videoid);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getPollsData() {
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("email", "test@gmail.com");
        //postParam.put("p", "somepasswordhere");
        if (checkConnectivity()) {
           /* StringRequest request = new StringRequest(Request.Method.POST, "Service name", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("res", response);
                    *//*try {
                        JSONObject object = new JSONObject(response);
                        if (object.has("Polls_Array")) {
                            JSONArray jsonArray = object.getJSONArray("Polls_Array");
                            if (jsonArray.length() != 0) {
                                polls_radio = false;
                                polls_linear = false;
                                ArrayList<BeanPoll> beanPolls = new ArrayList<>();
                                beanPolls.addAll((Collection<? extends BeanPoll>) new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<BeanPoll>>() {
                                }.getType()));
                                AdapterPolls adapter = new AdapterPolls(activity, beanPolls);
                                polls.recylerpolls.setAdapter(adapter);

                            } else {
                                polls.recylerpolls.setAdapter(null);

                            }
                        }

                        if (object.has("My_Polls_Array")) {
                            JSONArray jsonArray = object.getJSONArray("My_Polls_Array");
                            if (jsonArray.length() != 0) {
                                mypolls_radio = false;
                                mypolls_linear = false;
                                ArrayList<BeanPoll> beanPolls = new ArrayList<>();
                                beanPolls.addAll((Collection<? extends BeanPoll>) new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<BeanPoll>>() {
                                }.getType()));
                                AdapterMyPolls adapter = new AdapterMyPolls(activity, beanPolls);
                                mypolls.recylerpolls.setAdapter(adapter);

                            } else {
                                mypolls.recylerpolls.setAdapter(null);

                            }
                        }

                    } catch (JSONException e) {

                        showMsg(R.string.json_error);
                        e.printStackTrace();
                    }*//*

                    pd.dismiss();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error", error.toString());
                    pd.dismiss();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("PeopleId", Utility.getPeopleIdPreference());
                    map.put("Hashkey", Utility.getHashKeyPreference());
                    return map;
                }
            };*/

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    "http://phpjoomlacoders.com/ios/?webservice=1&vootouchservice=get_forgetpassword", new JSONObject(postParam),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("res", response.toString());
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("res", "Error: " + error.getMessage());
                }
            }) {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };
            jsonObjReq.setTag(MainActivity.this);
            // Adding request to request queue
            Contant.getInstance().getRequestQueue().add(jsonObjReq);
/*            Utility.SetvollyTime30Sec(request);
            Contant.getInstance().getRequestQueue().add(request);*/
        } else {
            Toast.makeText(activity, "There is no network connection at the moment.Try again later", Toast.LENGTH_SHORT).show();
        }
    }


    private void makeJsonObjReq() {
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("email", "test@gmail.com");
        // postParam.put("p", "somepasswordhere");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "http://phpjoomlacoders.com/ios/?webservice=1&vootouchservice=get_forgetpassword", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("res2", "" + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error2", "" + error.toString());
            }
        }) {
            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjReq.setTag(MainActivity.this);
        // Adding request to request queue
        queue.add(jsonObjReq);
        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("youtube.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
