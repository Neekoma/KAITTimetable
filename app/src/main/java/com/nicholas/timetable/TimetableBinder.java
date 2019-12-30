package com.nicholas.timetable;

import android.content.Context;
import android.util.Log;

import com.nicholas.timetable.JsonHandler.Handler;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

public class TimetableBinder {

    private static final String CACHE_FILENAME = "cache.json";

    /**
     *
     * @param context
     * @param newJson json расписания, которое только что пришло с сервера
     * @return true, если расписания совпадают (ничего не делать) false, если расписание с сервера отличается от сохраненного
     */
    public static boolean compareTimetable(Context context, String newJson){
       try(BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(CACHE_FILENAME)))){
           String localJson = "";
           String line;
           while((line = reader.readLine()) != null)
               localJson+= line;
           Handler jsonHandler = new Handler();
           HashMap<String, List<DayOfWeek>> localGroups = jsonHandler.setGroups(localJson);
           HashMap<String, List<DayOfWeek>> serverGroups = jsonHandler.setGroups(newJson);
           if(localGroups.equals(serverGroups))
               return true;
           writeNewTimetableJson(context, newJson);
       }

       catch (IOException e){
            writeNewTimetableJson(context, newJson);
       }
        return false;
    }

    public static void writeNewTimetableJson(Context context, String newJson){
        try(OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(CACHE_FILENAME, Context.MODE_PRIVATE))){
            writer.write(newJson.trim());
        }
        catch (IOException e){

        }
    }

    public static String getCacheJsonFile(Context context){
        String result = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(CACHE_FILENAME)))){
            result = "";
            String line;
            while((line = reader.readLine()) != null)
                result+= line;
        }
        catch(FileNotFoundException e){

        }
        catch (IOException e1){

        }
        return result;
    }

    public static boolean haveSave(Context context){
        try(InputStream inputStream = context.openFileInput(CACHE_FILENAME)){
            return true;
        }
        catch(FileNotFoundException e){
            return false;
        }
        catch (IOException e1){
            return false;
        }
    }

    public static HashMap<String, List<DayOfWeek>> getLocalGroups(Context context){
        if(haveSave(context)) {
            Handler jsonHandler = new Handler();
            HashMap<String, List<DayOfWeek>>localGroups = jsonHandler.setGroups(getCacheJsonFile(context));
            return localGroups;
        }
        return null;
    }



}
