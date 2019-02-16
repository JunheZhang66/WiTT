package witt;


import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

public class DynamoDbAPI {

    DynamoDBMapper client;

    public DynamoDbAPI(DynamoDBMapper client) {
        this.client = client;
    }


    public void createItem(String language, String text, String translation) {
        final SpanishDO spanishItem = new SpanishDO();
        spanishItem.setText(text);
        spanishItem.setTranslation(translation);
        DynamoDBMapper d = this.client;
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.save(spanishItem);
            }
        }).start();
    }

    public void getItem(String language, String text) {
        DynamoDBMapper d = this.client;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SpanishDO spanishItem = d.load(SpanishDO.class, text);
                Log.d("Found Item", spanishItem.getTranslation());
            }
        }).start();
    }
}
