package com.fgardila.nativapps.ui.list_report;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fgardila.nativapps.R;
import com.fgardila.nativapps.data.model.Report;
import com.fgardila.nativapps.util.ImageUtil;

import java.util.List;

public class ListReportsAdapter extends RecyclerView.Adapter<ListReportsAdapter.ReportsViewHolder> {

    private List<Report> reports;
    private Context context;

    public ListReportsAdapter(List<Report> reports) {
        this.reports = reports;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @NonNull
    @Override
    public ReportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View reportsView = inflater.inflate(R.layout.report_item, parent, false);

        return new ReportsViewHolder(reportsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsViewHolder holder, int position) {
        Report report = reports.get(position);

        TextView tvDescription = holder.tvDescription;
        ImageView imgPhoto = holder.imgPhoto;

        tvDescription.setText(report.getDescription());
        if (report.getUrlImage().length() > 0) {
            Bitmap bitmap = ImageUtil.convert(report.getUrlImage());
            imgPhoto.setImageBitmap(bitmap);
        } else {
            imgPhoto.setImageDrawable(context.getDrawable(R.drawable.ic_hide_image));
        }
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ReportsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        public TextView tvDescription;

        public ReportsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);

        }
    }





}
