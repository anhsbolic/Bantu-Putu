package id.bantu.bantuputu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.bantu.bantuputu.R;
import id.bantu.bantuputu.model.MyImage;

public class DetailImageActivity extends AppCompatActivity {
    private TextView txtId, txtName;
    private ImageView imgImage;

    public static String DATA_IMAGE = "DataImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        initComponent();

        //Get data from Intent
        if(getIntent().hasExtra(DATA_IMAGE)){
            MyImage myImage = getIntent().getParcelableExtra(DATA_IMAGE);
            setDataToUI(myImage);
        }else {
            Toast.makeText(DetailImageActivity.this, "MAAF DATA TIDAK ADA",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void initComponent() {
        txtId = (TextView)findViewById(R.id.detailImageTxtImageId);
        txtName = (TextView)findViewById(R.id.detailImageTxtImageName);
        imgImage = (ImageView) findViewById(R.id.detailImageImgImage);
    }

    private void setDataToUI(MyImage myImage) {
        String strId = myImage.getIdMyImage();
        String strName = myImage.getName();
        String strUrl = myImage.getUrl();

        txtId.setText(strId);
        txtName.setText(strName);
        Glide.with(this).load(strUrl).into(imgImage);
    }
}
