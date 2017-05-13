package app.amrelmasry.capstone_project.features.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.features.create.CreateNoteActivity;

/**
 * Created by Amr on 13/05/17.
 */

public class NotesWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, CreateNoteActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.notes_appwidget);
            views.setOnClickPendingIntent(R.id.widget_container_view, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }
}
