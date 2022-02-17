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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMaterialsActivity extends AppCompatActivity {

    private List<MyMaterialResponse> myMaterialResponseList = new ArrayList<>();

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_materials);

        gridView = findViewById(R.id.gridView);

        getMyMaterial();
    }

    public void getMyMaterial(){
        Call<List<MyMaterialResponse>> mymaterialresponse = ApiClient.getService().getMyMaterial();

        mymaterialresponse.enqueue(new Callback<List<MyMaterialResponse>>() {
            @Override
            public void onResponse(Call<List<MyMaterialResponse>> call, Response<List<MyMaterialResponse>> response) {
                if(response.isSuccessful()){

                    String message = "Successful";
                    Toast.makeText(MyMaterialsActivity.this, message, Toast.LENGTH_LONG).show();

                    myMaterialResponseList = response.body();

                    CursorAdapter cursorAdapter = new CursorAdapter(myMaterialResponseList,MyMaterialsActivity.this);
                    gridView.setAdapter(cursorAdapter);

                }else {


                    String message = "An error occured";
                    Toast.makeText(MyMaterialsActivity.this, message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<MyMaterialResponse>> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(MyMaterialsActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
    public class CursorAdapter extends BaseAdapter {

        private List<MyMaterialResponse> myMaterialResponseList;
        private Context context;
        private LayoutInflater layoutInflater;



        public CursorAdapter(List<MyMaterialResponse> myMaterialResponseList,Context context) {
            this.myMaterialResponseList = myMaterialResponseList;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return myMaterialResponseList.size();
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

            textView.setText(myMaterialResponseList.get(i).getTitle());

            Glide.with(context).load(myMaterialResponseList.get(i).getId())
                    .into(imageView);
            return view;

        }
    }
}