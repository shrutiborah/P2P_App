package com.example.p2p_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.p2p_app.R;
import com.example.p2p_app.utility.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialActivity extends AppCompatActivity {

    private List<Materialresponse> materialresponseList = new ArrayList<>();

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        gridView = findViewById(R.id.gridView);

        getAllMaterial();
    }

    public void getAllMaterial(){
        Call<List<Materialresponse>> materialresponse = ApiClient.getService().getAllMaterial();

        materialresponse.enqueue(new Callback<List<Materialresponse>>() {
            @Override
            public void onResponse(Call<List<Materialresponse>> call, Response<List<Materialresponse>> response) {
                if(response.isSuccessful()){

                    String message = "Successful";
                    Toast.makeText(MaterialActivity.this, message, Toast.LENGTH_LONG).show();

                    materialresponseList = response.body();

                    CursorAdapter cursorAdapter = new CursorAdapter(materialresponseList,MaterialActivity.this);
                    gridView.setAdapter(cursorAdapter);

                }else {


                    String message = "An error occured";
                    Toast.makeText(MaterialActivity.this, message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Materialresponse>> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(MaterialActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
    public class CursorAdapter extends BaseAdapter {

        private List<Materialresponse> materialresponseList;
        private Context context;
        private LayoutInflater layoutInflater;



        public CursorAdapter(List<Materialresponse> materialresponseList,Context context) {
            this.materialresponseList = materialresponseList;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return materialresponseList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = layoutInflater.inflate(R.layout.row_grid_items,viewGroup,false);
            }

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.textView);

            textView.setText(materialresponseList.get(i).getTitle());

            Glide.with(context).load(materialresponseList.get(i).getId())
                    .into(imageView);
            return view;

        }
    }
}
