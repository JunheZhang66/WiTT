package witt;

import android.util.Log;

import com.loopj.android.http.*;
import org.json.*;

import cz.msebera.android.httpclient.Header;

public class CloudTranslateAPI {
    private static final String BASE_URL = "https://translation.googleapis.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public String translate(String text, String from, String to) {
        RequestParams rp = new RequestParams();
        rp.add("q", text);
        rp.add("source", from);
        rp.add("target", to);
        rp.add("key", "AIzaSyBj9LPGWoQyGa7JurS6gfTA4wIW4YcN6JM");

        String out = "";

        post("language/translate/v2", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONObject data = null;
                try {
                    data = response.getJSONObject("data");
                    Log.d("translate", data.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                //nothing
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable a, JSONObject response) {
                Log.d("translate", a.getMessage());
                Log.d("translate", response.toString());
            }
        });
        return out;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
