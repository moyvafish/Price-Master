package com.example.mc;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_activity);
        String good_name = (String) getIntent().getSerializableExtra("good_name");
        String good_company = (String) getIntent().getSerializableExtra("good_company");
        String good_description = (String) getIntent().getSerializableExtra("good_description");
        String good_price = (String) getIntent().getSerializableExtra("good_price");
        int good_image = (int) getIntent().getSerializableExtra("good_image");

        final ImageView imageView;
        final TextView nameView, companyView, priceView, descriptionView;

        imageView = (ImageView) findViewById(R.id.imageGood);
        nameView = (TextView) findViewById(R.id.nameGood);
        companyView = (TextView) findViewById(R.id.companyGood);
        priceView = (TextView) findViewById(R.id.priceGood);
        descriptionView = (TextView) findViewById(R.id.descriptionGood);



        imageView.setImageResource(good_image);
        nameView.setText(good_name);
        companyView.setText(good_company);
        priceView.setText(good_price);
        descriptionView.setText(good_description);




    }
}
