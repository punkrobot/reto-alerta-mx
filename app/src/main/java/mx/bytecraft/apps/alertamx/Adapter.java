package mx.bytecraft.apps.alertamx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mx.bytecraft.apps.alertamx.backend.capEndpoint.model.Alert;

public class Adapter extends ArrayAdapter<Alert> {
    private final Context context;
    private final List<Alert> values;

    public Adapter(Context context, List<Alert> values) {
        super(context, R.layout.listitem_alert, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.listitem_alert, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.eventText = (TextView) rowView.findViewById(R.id.alert_name);
            viewHolder.descText = (TextView) rowView.findViewById(R.id.alert_desc);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Alert alert = values.get(position);
        holder.eventText.setText(alert.getEvent());
        holder.descText.setText(alert.getDescription());

        return rowView;
    }

    static class ViewHolder {
        public TextView eventText;
        public TextView descText;
    }
}