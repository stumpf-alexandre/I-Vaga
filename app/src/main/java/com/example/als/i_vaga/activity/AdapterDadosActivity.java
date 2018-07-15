package com.example.als.i_vaga.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.als.i_vaga.R;
import com.example.als.i_vaga.model.Usuario;

import java.util.List;

public class AdapterDadosActivity extends ArrayAdapter<Usuario> {
    public AdapterDadosActivity(@NonNull Context context, @NonNull List<Usuario> object){
        super(context, 0,object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View list = convertView;
        if (list == null){
            list = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_dados, parent, false);
        }
        Usuario current = getItem(position);


        TextView cidade_estado = list.findViewById(R.id.cidadeEstadoAdapter);
        TextView bairro = list.findViewById(R.id.bairroAdapter);
        TextView rua_numero = list.findViewById(R.id.ruaNumeroAdapter);
        TextView complemento = list.findViewById(R.id.complementoAdapter);

        cidade_estado.setText("Cidade: " + current.getCidade().toString() + " - " + "Estado: " + current.getEstado().toString());
        bairro.setText("Bairro: " + current.getBairro().toString());
        rua_numero.setText("Rua: " + current.getRua().toString() + " - " + "Nº: " + String.valueOf(current.getNumero()).toString());
        complemento.setText(current.getComplemento().toString());

        return list;
    }
}