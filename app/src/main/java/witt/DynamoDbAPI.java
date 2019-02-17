package witt;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

public class DynamoDbAPI {

    DynamoDBMapper client;

    public DynamoDbAPI(DynamoDBMapper client) {
        this.client = client;
    }


    public void createTranslation(String language, String text, String translation) {
        if (language.equals("English"))
            return;
        LanguageDO langItem = null;
        switch (language) {
            case "Spanish":
                langItem = new SpanishDO();
                break;
            case "Chinese":
                langItem = new ChineseDO();
                break;
            case "French":
                langItem = new FrenchDO();
                break;
        }
        langItem.setText(text);
        langItem.setTranslation(translation);
        DynamoDBMapper d = this.client;
        LanguageDO finalLangItem = langItem;
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.save(finalLangItem);
            }
        }).start();
    }

    public String getTranslation(String language, String text) {
        if (language.equals("English")) {
            return text;
        }
        LanguageDO langItem = null;
        switch (language) {
            case "Spanish":
                langItem = new SpanishDO();
                break;
            case "Chinese":
                langItem = new ChineseDO();
                break;
            case "French":
                langItem = new FrenchDO();
                break;
        }
        DynamoDBMapper d = this.client;
        LanguageDO returnItem = d.load(langItem.getClass(), text);

        if (returnItem == null) {
            return null;
        } else {
            return returnItem.getTranslation();
        }
    }

}
