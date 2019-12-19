import com.nicholas.timetable.models.DayOfWeek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupPreferences {

    private String[] groupsTitles;
    private String currentGroup;


    public void setGroupsTitles(HashMap<String, List<DayOfWeek>> groupsData){
        int groupsCount = groupsData.size();
        groupsTitles = new String[groupsCount];
        int i = 0;
        for(Map.Entry<String, List<DayOfWeek>> entry : groupsData.entrySet())
            groupsTitles[i] = entry.getKey();
    }
    public void setCurrentGroup(String group){currentGroup = group;}

    public String[] getGroupsTitles(){return groupsTitles;}

    public String getCurrentGroup(){return  currentGroup;}



}
