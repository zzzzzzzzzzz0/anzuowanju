package org.zhscript.clas;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by zzzzzzzzzzz on 17-2-12.
 */

public class TTS_ {
    TextToSpeech tts_;
    Locale lang_;

    String s_;
    void shuo__() {
        if(s_ != null) {
            tts_.speak(s_, TextToSpeech.QUEUE_FLUSH, null);
            s_ = null;
        }
    }

    public String[] shuo__(String[] a, ActivityResult_ ar, I_ i1) {
        Activity c = i1.activity__();
        s_ = a[0];
        if(a.length > 1)
            lang_ = new Locale(a[1]);
        if(tts_ == null) {
            Intent it = new Intent();
            it.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            Start_.it__(a, 2, -1, i1, it);
            c.startActivityForResult(it, ar.add__(new ActivityResult_.CallBack_() {

                @Override
                protected void result__(int resultCode, Intent data) {
                    switch (resultCode) {
                        case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
                            tts_ = new TextToSpeech(c, new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                switch (status) {
                                    case TextToSpeech.SUCCESS:
                                        /*if(epname_ != null)
                                            tts_.setEngineByPackageName(epname_);*/
                                        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            for (Locale l : tts_.getAvailableLanguages())
                                                System.out.println(l);
                                        }*/

                                        if(lang_ != null) {
                                            if (tts_.isLanguageAvailable(lang_) == TextToSpeech.LANG_AVAILABLE) {
                                                tts_.setLanguage(lang_);
                                            } else {
                                                i1.sendMessage__("tts 不支持 " + lang_);
                                                delete__();
                                                break;
                                            }
                                        }

                                        shuo__();
                                        break;
                                    case TextToSpeech.ERROR:
                                        i1.sendMessage__("不支持 tts");
                                        delete__();
                                        break;
                                }
                                }
                            });
                            break;
                        default: {
                            Intent it = new Intent();
                            it.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                            c.startActivity(it);
                            break;
                        }
                        case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
                            break;
                    }
                }
            }));
            return null;
        }
        shuo__();
        return null;
    }

    public void delete__() {
        if(tts_ != null) {
            tts_.shutdown();
            tts_ = null;
        }
    }
}
