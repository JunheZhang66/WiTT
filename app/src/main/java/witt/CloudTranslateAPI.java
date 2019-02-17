package witt;

import android.util.Log;

import com.loopj.android.http.*;
import org.json.*;

import cz.msebera.android.httpclient.Header;

public class CloudTranslateAPI {
    private static final String BASE_URL = "https://translation.googleapis.com/";

    private static SyncHttpClient client = new SyncHttpClient();

    public String translate(String text, String from, String to) {
        if(from.equals(to))
            return text;
        RequestParams rp = new RequestParams();
        rp.add("q", text);
        rp.add("source", from);
        rp.add("target", to);
        rp.add("key", "AIzaSyBj9LPGWoQyGa7JurS6gfTA4wIW4YcN6JM");

        String[] out = new String[1];

        post("language/translate/v2", rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                JSONObject data = null;
                try {
                    JSONObject response = new JSONObject(new String(bytes));
                    data = response.getJSONObject("data");
                    JSONArray translations = data.getJSONArray("translations");
                    JSONObject translation = (JSONObject) translations.get(0);
                    String translatedText = translation.getString("translatedText");
                    Log.d("translate", translatedText);
                    out[0] = translatedText;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable a) {

                Log.d("translate", a.getMessage());
                //Log.d("translate", response.toString());
            }
        });
        return out[0];
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

    public String getCode(String lang) {
        switch (lang) {
            case "Chinese":
                return "zh-CN";
            case "Spanish":
                return "es";
            case "French":
                return "fr";
            default:
                return "en";
        }
    }
}
