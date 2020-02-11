package com.nicholas.timetable;

import android.content.Context;

import com.nicholas.timetable.Json.Handler;
import com.nicholas.timetable.models.DayOfWeek;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimetableBinder {

    private static final String CACHE_FILENAME = "cache.json";

    /**
     * @param newJson json расписания, которое только что пришло с сервера
     * @return true, если расписания совпадают (ничего не делать)
     * false, если расписание с сервера отличается от сохраненного
     */
    public static boolean compareTimetable(Context context, String newJson) {
        final boolean[] result = new boolean[1];
        Disposable dispose = timetableComparator(context, newJson)
                .subscribeOn(Schedulers.newThread())
                .subscribe(it -> {
                    result[0] = it.booleanValue();
                }, it -> {
                    result[0] = false;
                });
        return result[0];
    }


    public static Observable<Boolean> timetableComparator(Context context, String newJson) {
        return Observable.create(subscriber -> {
            String local = getCacheJsonFile(context);
            local.replaceAll("\n", "");
            boolean result = local.equals(newJson);
            if(!result)
                writeNewTimetableJson(context, newJson);
            subscriber.onNext(result);
        });
    }


    /**
     * Записать новый файл с расписание в память устройства
     *
     * @param newJson новая строка в формате json, полученная с сервера
     */
    public static void writeNewTimetableJson(Context context, String newJson) {
        try (OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(CACHE_FILENAME, Context.MODE_PRIVATE))) {
            writer.write(newJson.trim());
        } catch (IOException e) {

        }
    }

    /**
     * Получить сохраненный файл с расписанием в формате json
     *
     * @return null, если файла нет
     * json строка, если файл имеется
     */
    public static String getCacheJsonFile(Context context) {
        String result = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(CACHE_FILENAME)))) {
            result = "";
            String line;
            while ((line = reader.readLine()) != null)
                result += line;
        } catch (FileNotFoundException e) {

        } catch (IOException e1) {

        }
        return result;
    }

    /**
     * @return true, если файл есть
     * false, если файла нет
     */
    public static boolean haveSave(Context context) {
        try (InputStream inputStream = context.openFileInput(CACHE_FILENAME)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static HashMap<String, List<DayOfWeek>> getLocalGroups(Context context) {
        if (haveSave(context)) {
            Handler jsonHandler = new Handler();
            HashMap<String, List<DayOfWeek>> localGroups = jsonHandler.setGroups(getCacheJsonFile(context));
            return localGroups;
        }
        return null;
    }

    private static boolean compareMaps(HashMap<String, List<DayOfWeek>> map1, HashMap<String, List<DayOfWeek>> map2) {
        return map1.entrySet().containsAll(map2.entrySet()) && map2.entrySet().containsAll(map1.entrySet());
    }

    private static boolean compareTwoMaps(Context context, String newJson){
        Handler jsonHandler = new Handler();
        HashMap<String, List<DayOfWeek>> localGroups = jsonHandler.setGroups(getCacheJsonFile(context));
        return true;
    }
}
