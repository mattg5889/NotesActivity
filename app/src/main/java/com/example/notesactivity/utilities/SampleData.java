package com.example.notesactivity.utilities;

import com.example.notesactivity.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {

    private static final String sampTxt1 = "A simple note.";
    private static final String sampTxt2 = "A note with a\nline feed.";
    private static final String sampTxt3 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vel " +
            "nisi sed orci suscipit elementum. Curabitur mollis varius euismod. Phasellus eleifend dolor ut " +
            "condimentum elementum. Aenean aliquet viverra feugiat. Nam semper, arcu nec cursus pellentesque, " +
            "enim sapien pretium ante, eu pretium nibh leo eu quam. In posuere congue placerat. Etiam varius " +
            "scelerisque iaculis. In hac habitasse platea dictumst. Mauris blandit nunc pellentesque dolor \n\n" +
            "tincidunt, sagittis mattis ipsum placerat. Donec sit amet rutrum lorem, quis pretium ante. Nam iaculis " +
            "sodales cursus. Aenean magna mauris, elementum vitae dapibus in, maximus vel sem. Praesent vulputate " +
            "tortor a tempor vehicula. Vivamus quis dolor gravida justo tempor congue. Sed est ipsum, vulputate at " +
            "finibus in, convallis sit amet metus. Vestibulum vulputate consequat arcu, vel fermentum ipsum semper quis.";

    private static Date getDate(int diff){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    public static List <NoteEntity> getNotes(){
        List<NoteEntity> notes = new ArrayList<>();
        notes.add(new NoteEntity(getDate(0),sampTxt1));
        notes.add(new NoteEntity(getDate(-1),sampTxt2));
        notes.add(new NoteEntity(getDate(-2),sampTxt3));

        return notes;
    }
}
