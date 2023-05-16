package com.vvit.stickynotz;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText noteEdit;
    private Button plus,minus,boldbtn,underlinebtn,italicbtn;
    private TextView size;

    float currentSize;
    SticckyNote note = new SticckyNote();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdit = findViewById(R.id.id_editnotes);
        plus = findViewById(R.id.id_plus);
        minus = findViewById(R.id.id_minus);
        boldbtn = findViewById(R.id.id_boldbtn);
        underlinebtn = findViewById(R.id.id_underlinebtn);
        italicbtn = findViewById(R.id.id_italicbtn);
        size = findViewById(R.id.id_size);
        currentSize = noteEdit.getTextSize();
        size.setText(""+currentSize);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size.setText(""+currentSize);
                currentSize++;
                noteEdit.setTextSize(currentSize);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size.setText(""+currentSize);
                currentSize--;
                noteEdit.setTextSize(currentSize);
            }
        });

        boldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                italicbtn.setTextColor(getResources().getColor(R.color.white));
                italicbtn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                if (noteEdit.getTypeface().isBold()){
                    noteEdit.setTypeface(Typeface.DEFAULT);
                    boldbtn.setTextColor(getResources().getColor(R.color.white));
                    boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }else {
                    boldbtn.setTextColor(getResources().getColor(R.color.purple_500));
                    boldbtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });

        underlinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteEdit.getPaintFlags()==8){
                    underlinebtn.setTextColor(getResources().getColor(R.color.white));
                    underlinebtn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    noteEdit.setPaintFlags(noteEdit.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                }
                else {
                    underlinebtn.setTextColor(getResources().getColor(R.color.purple_500));
                    underlinebtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdit.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });

        italicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boldbtn.setTextColor(getResources().getColor(R.color.white));
                boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                if (noteEdit.getTypeface().isItalic()){
                    noteEdit.setTypeface(Typeface.DEFAULT);
                    italicbtn.setTextColor(getResources().getColor(R.color.white));
                    italicbtn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }else {
                    italicbtn.setTextColor(getResources().getColor(R.color.purple_500));
                    italicbtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
            }
        });
    }

    public void saveButton(View view) {
        note.setStick(noteEdit.getText().toString(),this);
        updateWidget();
        Toast.makeText(this, "Note has been updated", Toast.LENGTH_SHORT).show();
    }

    void updateWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.widget);
        ComponentName thisWidget = new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.id_widgetText,noteEdit.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
    }
}