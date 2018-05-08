package none.sbaixas.laboratorio5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sebastian on 10-04-18.
 */

public class FormAdapter extends ArrayAdapter<Forms> {


    private List<Forms> dataSet;
    Context mContext;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView nameTextView;
        TextView dateTextView;
        TextView descriptionTextView;
    }


    public FormAdapter(List<Forms> data, Context context) {
        super(context, R.layout.fragment_form_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Forms forms = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.form_list_layout, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.nameTextView = convertView.findViewById(R.id.nameTextView);
        viewHolder.dateTextView = convertView.findViewById(R.id.dateTextView);
        viewHolder.descriptionTextView = convertView.findViewById(R.id.descriptionTextView);

        /*if (convertView == null) {





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/
        lastPosition = position;

        viewHolder.nameTextView.setText(forms.getUserName());
        viewHolder.dateTextView.setText(forms.getDate());
        viewHolder.descriptionTextView.setText(forms.getComment());
        // Return the completed view to render on screen
        return convertView;
    }
}
