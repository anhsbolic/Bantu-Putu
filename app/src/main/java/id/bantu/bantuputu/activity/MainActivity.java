package id.bantu.bantuputu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.bantu.bantuputu.R;
import id.bantu.bantuputu.model.MyImage;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMyImages;
    private RecyclerView.Adapter adapterRvMyImages;
    private ArrayList<MyImage> dataImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        setRecyclerView();
    }

    private void initComponent() {
        rvMyImages = (RecyclerView)findViewById(R.id.mainRv);
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager lmRvMyImages = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimatorRvMyImages = new DefaultItemAnimator();
        itemAnimatorRvMyImages.setAddDuration(300);
        itemAnimatorRvMyImages.setRemoveDuration(300);
        DividerItemDecoration dividerRvMyImages =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvMyImages.setLayoutManager(lmRvMyImages);
        rvMyImages.setItemAnimator(itemAnimatorRvMyImages);
        rvMyImages.addItemDecoration(dividerRvMyImages);
    }
}
