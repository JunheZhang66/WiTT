package witt;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;



@DynamoDBTable(tableName = "witt-mobilehub-64719766-Chinese")

public class ChineseDO implements LanguageDO {
    private String _text;
    private String _translation;

    @DynamoDBHashKey(attributeName = "text")
    @DynamoDBAttribute(attributeName = "text")
    public String getText() {
        return _text;
    }

    public void setText(final String _text) {
        this._text = _text;
    }
    @DynamoDBAttribute(attributeName = "translation")
    public String getTranslation() {
        return _translation;
    }

    public void setTranslation(final String _translation) {
        this._translation = _translation;
    }

}
