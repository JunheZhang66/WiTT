package witt;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Detection;
import java.util.LinkedList;
public class CloudTranslateAPI {
    private static final String [] LANGUAGES ={
            "af", "sq", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb",
            "ny", "zh-TW", "hr", "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr",
            "gl", "ka", "de", "el", "gu", "ht", "ha", "iw","hi", "hmn", "hu", "is",
            "ig", "id", "ga", "it", "ja", "jw", "kn", "kk", "km", "ko", "lo",
            "la", "lv", "lt", "mk", "mg", "ms", "ml","mt", "mi", "mr", "mn", "my", "ne", "no",
            "fa", "pl", "pt", "ro", "ru", "sr", "st", "si", "sk", "sl", "so", "es", "su",
            "sw", "sv", "tg", "ta", "te", "th", "tr", "uk", "ur", "uz", "vi", "cy", "yi", "yo", "zu"
    };

    //googleapis git hub
    Translate trans = TranslateOptions.getDefaultInstance().getService();
    List <Language> languages = trans.listSupportedLanguages();
    Set<String> supportedLanguages = new HashSet<>();
    for(Language language : languages){
        supportedLanguages.add(language.getCode());
        assertNotNull(Language.getName());
    }
    for(String code : LANGUAGES){
        assertTrue(supportedLanguages.contains(code));
    }


    public static String translate(String lang1, String lang2, String text){
        List<String> texts = new LinkedList<>();
        texts.add(lang1);
        texts.add(lang2)
        Translation translation = trans.translate(text, TranslateOption.sourceLanguage(texts.get(0).getCode()),TranslateOption.targetLanguage(texts.get(1).getCode()));
        String fin = translation.getTranslatedText();
        return fin;
    }
    public String translate(String language, String text){
        String last = translate("English",language,text);
        return last;
    }
}
