package com.nicholas.timetable.viewmodels;

import android.content.Context;
import android.widget.Toast;
import com.nicholas.timetable.models.DayOfWeek;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TimetableViewModel{

    private static TimetableViewModel instance;

    private static final String FILENAME = "cache.json";


    private TimetableViewModel(){}

    private HashMap<String, List<DayOfWeek>> groups;


    public static TimetableViewModel getInstance(){
        if (instance == null)
            instance = new TimetableViewModel();
        return instance;
    }

    /**
     *
     * @param newData Новый набор данных, полученный от сервера (json)
     * @return true, если данные совпадают, иначе обновить View
     */
    public boolean checkoutCacheData(String newData, Context context){
        String savedContent = "";
        try{
            Scanner scanner = new Scanner(context.openFileInput(FILENAME));
            while(scanner.hasNextLine())
                savedContent+= scanner.nextLine();
            Toast.makeText(context, Boolean.toString(newData.equals(savedContent)), Toast.LENGTH_SHORT).show();
            return newData.equals(savedContent);

        }
        catch (IOException e){
            try {
                Toast.makeText(context, "File not found. Creating a new file", Toast.LENGTH_SHORT).show();
                OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
                writer.write(newData);
                writer.flush();
                writer.close();
            }
            catch (FileNotFoundException e1){

            }
            catch (IOException e2){

            }
        }
        return false;
    }

    private void setGroupsByLocalCache(){

    }

    public void setGroups(HashMap<String, List<DayOfWeek>> groups){this.groups = groups;}
    public HashMap<String, List<DayOfWeek>> getGroups(){return groups;}








}
