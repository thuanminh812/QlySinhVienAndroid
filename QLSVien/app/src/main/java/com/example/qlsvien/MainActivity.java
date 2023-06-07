package com.example.qlsvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtNamSinh,edtLopHoc,edtQueQuan,edtMaSv;
    Button btnAdd,btnEdit;
    ListView listView;
    InfoStudentAdapter adapter;
    int id = 0, pos = 0;
    SQLite sqLite = new SQLite(this);
    List<InfoStudent> infoStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addAction();
    }

    private void addAction() {
        btnAdd.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String namSinh = edtNamSinh.getText().toString();
            String lopHoc = edtLopHoc.getText().toString();
            String queQuan = edtQueQuan.getText().toString();
            String maSV = edtMaSv.getText().toString();
            InfoStudent information = new InfoStudent(0, name, namSinh, lopHoc, queQuan,maSV);
            if(name.length() > 0 && namSinh.length() > 0 && lopHoc.length() >0 && queQuan.length() > 0 && maSV.length() > 0){
                addNewInfor(information);
                infoStudentList.add(information);
                adapter.notifyDataSetChanged();
                getAll();
                Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                edtName.setText("");
                edtNamSinh.setText("");
                edtLopHoc.setText("");
                edtQueQuan.setText("");
                edtMaSv.setText("");
            }else
            {
                Toast.makeText(MainActivity.this,"Bạn chưa thêm đủ thông tin",Toast.LENGTH_SHORT).show();
            }


        });
        btnEdit.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String namSinh = edtNamSinh.getText().toString();
            String lopHoc = edtLopHoc.getText().toString();
            String queQuan = edtQueQuan.getText().toString();
            String maSV = edtMaSv.getText().toString();
            InfoStudent information = new InfoStudent(0, name, namSinh, lopHoc, queQuan,maSV);
            if(name.length() > 0 && namSinh.length() > 0 && lopHoc.length() >0 && queQuan.length() > 0 && maSV.length() > 0) {
                UpdateStudent(information);
                infoStudentList.set(pos, information);
                adapter.notifyDataSetChanged();
                getAll();
                Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(MainActivity.this,"Bạn chưa thêm đủ thông tin",Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            InfoStudent information = infoStudentList.get(i);
            edtName.setText(information.getName());
            edtNamSinh.setText(information.getNamSinh());
            edtLopHoc.setText(information.getLopHoc());
            edtQueQuan.setText(information.getQueQuan());
            edtMaSv.setText(information.getMaSV());
            id = information.getId();
            pos = i;
        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            acceptDelete(i);

            return false;
        });
    }
    private void init(){
        infoStudentList = new ArrayList<>();
        infoStudentList = getAll();
        adapter = new InfoStudentAdapter(this, R.layout.activity_info_student, infoStudentList);
        listView = findViewById(R.id.lvw_list);
        listView.setAdapter(adapter);
        edtName = findViewById(R.id.edt_name);
        edtNamSinh = findViewById(R.id.edt_namSinh);
        edtLopHoc = findViewById(R.id.edt_lopHoc);
        edtQueQuan = findViewById(R.id.edt_quequan);
        edtMaSv = findViewById(R.id.edt_masv);
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
    }
    private List<InfoStudent> getAll() {
        List<InfoStudent> informationList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        Cursor cursor = sqLite.getData(sql);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String namSinh = cursor.getString(2);
            String  lopHoc = cursor.getString(3);
            String queQuan = cursor.getString(4);
            String maSV = cursor.getString(5);

            InfoStudent information = new InfoStudent(id, name, namSinh, lopHoc, queQuan,maSV);
            informationList.add(information);
        }
        return informationList;
    }
    private void addNewInfor(InfoStudent information) {
        try {
            SQLiteDatabase database = sqLite.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",information.getName());
            values.put("namsinh",information.getNamSinh());
            values.put("lophoc",information.getLopHoc());
            values.put("quequan",information.getQueQuan());
            values.put("masv",information.getMaSV());
            database.insert("students",null,values);

        } catch (Exception e) {
            Log.e("Insert fail!", e.getMessage());
        }
    }
    private void UpdateStudent(InfoStudent information) {
        try {
            SQLiteDatabase database = sqLite.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",information.getName());
            values.put("namsinh",information.getNamSinh());
            values.put("lophoc",information.getLopHoc());
            values.put("quequan",information.getQueQuan());
            values.put("masv",information.getMaSV());
            String id = String.valueOf(information.getId());
            database.update("students",values,"id = ?",new String[] {id});

        } catch (Exception e) {
            Log.e("Update fail!", e.getMessage());
        }
    }

    private boolean deleteById(int id) {
        try {
            String sql = "DELETE FROM students WHERE id = " + id;

            sqLite.queryData(sql);

            return true;
        } catch (Exception e) {
            Log.e("Delete fail!", e.getMessage());
            return false;
        }
    }

    private void acceptDelete(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xoá");
        builder.setMessage("Bạn có thật sự muốn xoá " + infoStudentList.get(i).getName() + " không?");
        builder.setPositiveButton("Có", (dialogInterface, i1) -> {

            int id = infoStudentList.get(i).getId();

            if (deleteById(id)) {
                Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            }

            infoStudentList.remove(i);
            adapter.notifyDataSetChanged();
            edtName.setText("");
            edtNamSinh.setText("");
            edtLopHoc.setText("");
            edtQueQuan.setText("");
            edtMaSv.setText("");
        });

        builder.setNegativeButton("Không", (dialogInterface, i1) -> {
        });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusearch,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập thông tin cần tìm kiếm");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}