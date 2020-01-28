package com.nicholas.timetable.lists;

public class TableHeader implements IListDataset {

    public static final int TABLE_HEADER_VIEW_TYPE = 0x00A1;
    public String title = "Default title";

    public TableHeader(String title){
        this.title = title;
    }

}
