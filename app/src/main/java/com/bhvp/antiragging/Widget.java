package com.bhvp.antiragging;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.bhvp.antiragging.widbutons.*;


/**
 * Implementation of App Widget functionality.
 */
public class Widget extends AppWidgetProvider {


    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            Intent intent = new Intent(context, jacks.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.imageButton4, pendingIntent);

           Intent intent1 = new Intent(context, library.class);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, 0);
            views.setOnClickPendingIntent(R.id.imageButton5, pendingIntent1);

           Intent intent2 = new Intent(context, patron.class);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
            views.setOnClickPendingIntent(R.id.imageButton6, pendingIntent2);

            Intent intent3 = new Intent(context, pg.class);
            PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 0, intent3, 0);
            views.setOnClickPendingIntent(R.id.imageButton7, pendingIntent3);


            Intent intent4 = new Intent(context, sport.class);
            PendingIntent pendingIntent4 = PendingIntent.getActivity(context, 0, intent4, 0);
            views.setOnClickPendingIntent(R.id.imageButton8, pendingIntent4);

            Intent intent5 = new Intent(context, d.class);
            PendingIntent pendingIntent5 = PendingIntent.getActivity(context, 0, intent5, 0);
            views.setOnClickPendingIntent(R.id.imageButton9, pendingIntent5);


            Intent intent6 = new Intent(context, audi.class);
            PendingIntent pendingIntent6 = PendingIntent.getActivity(context, 0, intent6, 0);
            views.setOnClickPendingIntent(R.id.imageButton10, pendingIntent6);


            Intent intent7 = new Intent(context, canteen.class);
            PendingIntent pendingIntent7 = PendingIntent.getActivity(context, 0, intent7, 0);
            views.setOnClickPendingIntent(R.id.imageButton11, pendingIntent7);


            Intent intent8 = new Intent(context, gate.class);
            PendingIntent pendingIntent8 = PendingIntent.getActivity(context, 0, intent8, 0);
            views.setOnClickPendingIntent(R.id.imageButton12, pendingIntent8);


            Intent intent9 = new Intent(context, ground.class);
            PendingIntent pendingIntent9 = PendingIntent.getActivity(context, 0, intent9, 0);
            views.setOnClickPendingIntent(R.id.imageButton13, pendingIntent9);

            Intent intent10 = new Intent(context, sp.class);
            PendingIntent pendingIntent10 = PendingIntent.getActivity(context, 0, intent10, 0);
            views.setOnClickPendingIntent(R.id.imageButton14, pendingIntent10);

            Intent intent11 = new Intent(context, wtsp.class);
            PendingIntent pendingIntent11 = PendingIntent.getActivity(context, 0, intent11, 0);
            views.setOnClickPendingIntent(R.id.imageButton15, pendingIntent11);

           Intent intent12 = new Intent(context, other.class);
            PendingIntent pendingIntent12 = PendingIntent.getActivity(context, 0, intent12, 0);
            views.setOnClickPendingIntent(R.id.imageButton, pendingIntent12);


            appWidgetManager.updateAppWidget(appWidgetId, views);



        }

    }


}

