import android.util.Log;

import com.swc.parsedweatherviewerkotlin.BuildConfig;

public class LoggingUtils{

    public static void e(String tag, String msg){
        if (BuildConfig.DEBUG){
            Log.e(tag, msg);
        }
    }
}
