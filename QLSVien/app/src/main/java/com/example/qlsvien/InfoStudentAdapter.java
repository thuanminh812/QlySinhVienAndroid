package com.example.qlsvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InfoStudentAdapter extends BaseAdapter implements Filterable {

    private Context context;
    int layout;
    private List<InfoStudent> infoStudents;
    private List<InfoStudent> infoStudentsOld;

    public InfoStudentAdapter(Context context, int layout, List<InfoStudent> infoStudents) {
        this.context = context;
        this.layout = layout;
        this.infoStudents = infoStudents;
        this.infoStudentsOld = infoStudents;
    }

    @Override
    public int getCount() {
        return infoStudents.size();
    }

    @Override
    public Object getItem(int i) {
        return infoStudents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return infoStudents.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.tvName = view.findViewById(R.id.tw_name);
            viewHolder.tvNamSinh = view.findViewById(R.id.tw_namsinh);
            viewHolder.tvLopHoc = view.findViewById(R.id.tw_lopHoc);
            viewHolder.tvQueQuan = view.findViewById(R.id.tw_quequan);
            viewHolder.tvMaSV = view.findViewById(R.id.tw_masv);
            viewHolder.imageView = view.findViewById(R.id.imgPhoto);
            view.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder)  view.getTag();
        }
        InfoStudent information = infoStudents.get(i);
        viewHolder.tvName.setText("Họ và tên:"+ information.getName());
        viewHolder.tvNamSinh.setText("Năm sinh: " + information.getNamSinh());
        viewHolder.tvLopHoc.setText("Lớp học: " + information.getLopHoc());
        viewHolder.tvQueQuan.setText("Quê quán: " + information.getQueQuan());
        viewHolder.tvMaSV.setText("Mã sinh viên: "+information.getMaSV());
        viewHolder.imageView.setImageResource(R.drawable.user);
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String arrSearch = charSequence.toString();
                if(arrSearch.isEmpty()){
                    infoStudents = infoStudentsOld;
                } else {
                    List<InfoStudent> list = new ArrayList<>();
                    for (InfoStudent infoStudent : infoStudentsOld) {
                        if(infoStudent.getName().toLowerCase().contains(arrSearch.toLowerCase()) || infoStudent.getNamSinh().toLowerCase().contains(arrSearch.toLowerCase())
                        || infoStudent.getLopHoc().toLowerCase().contains(arrSearch.toLowerCase()) || infoStudent.getQueQuan().toLowerCase().contains(arrSearch.toLowerCase())
                        || infoStudent.getQueQuan().toLowerCase().contains(arrSearch.toLowerCase())) {
                            list.add(infoStudent);
                        }
                    }

                    infoStudents = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = infoStudents;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                infoStudents = (List<InfoStudent>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder
    {
        ImageView imageView;
        TextView tvName,tvNamSinh,tvLopHoc,tvQueQuan,tvMaSV;
    }

}
