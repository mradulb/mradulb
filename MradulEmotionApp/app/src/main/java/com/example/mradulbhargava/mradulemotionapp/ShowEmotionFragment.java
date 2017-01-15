package com.example.mradulbhargava.mradulemotionapp;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowEmotionFragment extends Fragment {

    private int anger,contempt,disguish,fear,happiness,neutral,sadness,surprise;
    public ShowEmotionFragment() {
        // Required empty public constructor
    }

    public ShowEmotionFragment(int anger, int contempt, int disguish, int fear, int happiness, int neutral, int sadness, int surprise) {
        this.anger = anger;
        this.contempt = contempt;
        this.disguish = disguish;
        this.fear = fear;
        this.happiness = happiness;
        this.neutral = neutral;
        this.sadness = sadness;
        this.surprise = surprise;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_show_emotion, container, false);
        drawAnger(anger, v);
        drawcontempt(contempt, v);
        drawdisguish(disguish, v);
        drawFear(fear, v);
        drawhappy(happiness, v);
        drawNeutral(neutral, v);
        drawsad(sadness, v);
        drawSurprise(surprise, v);
        drawoverall(0,v);

        return v;
    }





    public void  drawAnger(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView)v.findViewById(R.id.dynamicArcView_anger);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }
    public void  drawdisguish(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_disguist);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }

    public void  drawhappy(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_happy);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }

    public void  drawsad(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_sad);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }

    public void  drawcontempt(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_contempt);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }

    public void  drawFear(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_fear);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }
    public void  drawNeutral(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_neutral);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }
    public void  drawoverall(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_overall);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }

    public void  drawSurprise(int anger,View v){
        //Code for anger
        DecoView arcView_ = (DecoView) v.findViewById(R.id.dynamicArcView_surpise);

// Create background track
        arcView_.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)

                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index = arcView_.addSeries(seriesItem1);

        arcView_.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)

                .setDuration(2000)
                .build());

        arcView_.addEvent(new DecoEvent.Builder(anger).setIndex(series1Index).setDelay(0).build());
    }
}
