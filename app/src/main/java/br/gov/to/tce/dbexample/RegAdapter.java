package br.gov.to.tce.dbexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RegAdapter extends RecyclerView.Adapter<RegHolder> {
    private Context context;
    private ArrayList<Reg> regList;

    public RegAdapter(Context context, ArrayList<Reg> regList) {
        this.context = context;
        this.regList = regList;
    }

    @Override
    public int getItemCount() {
        //Informa quantas celulas preisa criar
        return regList.size();
    }

    @Override
    public RegHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Infla cada items no recycleview
        View celula = LayoutInflater.from(context).inflate(R.layout.celula, parent, false);
        return new RegHolder(celula);
    }

    @Override
    public void onBindViewHolder(RegHolder holder, final int position) {
        final Reg reg = regList.get(position);

        holder.getTxtName().setText(reg.getName());
        holder.getRatingBar().setRating(reg.getRating());
        holder.getImageView().setImageBitmap(BitmapFactory.decodeFile(reg.getImagepath()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicou no: " + reg.get_id() + " - " + reg.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle(R.string.action_excluir)
                        .setMessage(R.string.confirmar_excluir)
                        .setPositiveButton(R.string.action_excluir, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                regList.remove(position);
                                reg.delete(context);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.action_cancel, null)
                        .show();
                return true;
            }
        });

    }
}
