package com.example.krisar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class KrisarList extends ArrayAdapter<Krisar> {
    private Activity context;
    private List<Krisar> krisarList;

    public KrisarList(Activity context, List<Krisar> krisarList){
        super(context, R.layout.list_layout, krisarList);
        this.context = context;
        this.krisarList = krisarList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView txtNados =(TextView) listViewItem.findViewById(R.id.txtNados);
        TextView txtMatkul = (TextView) listViewItem.findViewById(R.id.txtMatkul);
        TextView txtKelas = (TextView) listViewItem.findViewById(R.id.txtKelas);
        TextView txtKrisar = (TextView) listViewItem.findViewById(R.id.txtKrisar);

        Krisar krisar = krisarList.get(position);

        txtNados.setText(krisar.getNados());
        txtMatkul.setText(krisar.getMatkul());
        txtKelas.setText(krisar.getKelas());
        txtKrisar.setText(krisar.getKrisar());

        return listViewItem;
    }
}
