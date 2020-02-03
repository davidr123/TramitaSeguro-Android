package tramitaseguro.sqlite.tramita_tramitador.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tramitaseguro.sqlite.tramita_tramitador.R;
import tramitaseguro.sqlite.tramita_tramitador.objetos.TramiteEstudioJuridico;


public class AdapterEstudioJuridico extends BaseAdapter {
    private ArrayList<TramiteEstudioJuridico> listData;
    private LayoutInflater layoutInflater;
    public AdapterEstudioJuridico(Context aContext, ArrayList<TramiteEstudioJuridico> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.item_estudiojuridico, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.txtNombre);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(listData.get(position).getNombre());

        return v;
    }
    static class ViewHolder {
        TextView uName;

    }
}
