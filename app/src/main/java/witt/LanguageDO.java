package witt;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;

interface LanguageDO {

    @DynamoDBHashKey(attributeName = "text")
    @DynamoDBAttribute(attributeName = "text")
    String getText();

    void setText(final String _text);

    @DynamoDBAttribute(attributeName = "translation")
    String getTranslation();

    void setTranslation(final String _translation);

}
