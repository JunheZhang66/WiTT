package witt;

import android.os.AsyncTask;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudTranslateAPI {
    /*private static final String [] LANGUAGES ={
            "af", "sq", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb",
            "ny", "zh-TW", "hr", "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr",
            "gl", "ka", "de", "el", "gu", "ht", "ha", "iw","hi", "hmn", "hu", "is",
            "ig", "id", "ga", "it", "ja", "jw", "kn", "kk", "km", "ko", "lo",
            "la", "lv", "lt", "mk", "mg", "ms", "ml","mt", "mi", "mr", "mn", "my", "ne", "no",
            "fa", "pl", "pt", "ro", "ru", "sr", "st", "si", "sk", "sl", "so", "es", "su",
            "sw", "sv", "tg", "ta", "te", "th", "tr", "uk", "ur", "uz", "vi", "cy", "yi", "yo", "zu"
    };*/
    private Translate trans;
    private Map<String, String> all;

    public String translate(String lang1, String lang2, String text) {
        Translation translation = trans.translate(text, TranslateOption.sourceLanguage(all.get(lang1)), TranslateOption.targetLanguage(all.get(lang2)));
        String fin = translation.getTranslatedText();
        return fin;
    }

    public String translate(String language, String text) {
        String last = translate("English", language, text);
        return last;
    }

    public void setTranslateObject() {
        this.trans = TranslateOptions.getDefaultInstance().getService();
        List<Language> languages = trans.listSupportedLanguages();
        all = new HashMap<>();
        for (Language language : languages) {
            all.put(language.getName(), language.getCode());
        }
    }
}
