package br.gov.to.tce.dbexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RegHolder extends RecyclerView.ViewHolder {
    private TextView txtName;
    private RatingBar ratingBar;
    private ImageView imageView;

    public RegHolder(View view) {
        super(view);
        txtName = view.findViewById(R.id.celula_txt);
        ratingBar = view.findViewById(R.id.celula_rating);
        imageView = view.findViewById(R.id.celula_img);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
