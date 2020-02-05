package tramitaseguro.sqlite.tramita_tramitador.syncronizacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tramitaseguro.sqlite.tramita_tramitador.R;

public class NameAdapter extends ArrayAdapter<ModelName> {

    private List<ModelName> names;

    //context object
    private Context context;

    //constructor
    public NameAdapter(Context context, int resource, List<ModelName> names) {
        super(context, resource, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //getting the layoutinflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //getting listview itmes
        View listViewItem = inflater.inflate(R.layout.name, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById( R.id.textViewName);
        ImageView imageViewStatus = (ImageView) listViewItem.findViewById(R.id.imageViewStatus);

        //getting the current name
        ModelName name = names.get(position);

        //setting the name to textview
        textViewName.setText(name.getName());

        //if the synced status is 0 displaying
        //queued icon
        //else displaying synced icon
        if (name.getStatus() == 0)
            imageViewStatus.setBackgroundResource(R.drawable.ic_fingerprint);
        else
            imageViewStatus.setBackgroundResource(R.drawable.iconousuarioazul);

        return listViewItem;
    }
}
