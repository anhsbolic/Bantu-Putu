package id.bantu.bantuputu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import id.bantu.bantuputu.R;
import id.bantu.bantuputu.adapter.MyImagesAdapter;
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

        loadDataThenSetToRvMyImages();
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
        adapterRvMyImages = new MyImagesAdapter(MainActivity.this, dataImages,
                new MyImagesAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(MyImage myImage) {
                        String strId = myImage.getIdMyImage();
                        String strName = myImage.getName();
                        String strUrl = myImage.getUrl();
                        String strToast = strId + "-" + strName + "-" + strUrl;
                        Toast.makeText(MainActivity.this, strToast, Toast.LENGTH_LONG).show();
                    }
                });
        rvMyImages.setAdapter(adapterRvMyImages);
    }

    private void loadDataThenSetToRvMyImages() {
        DatabaseReference myImagesDbReff = FirebaseDatabase.getInstance().getReference()
                .child("my-images");

        myImagesDbReff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()){
                    MyImage myImageData = dataSnapshot.getValue(MyImage.class);
                    if(myImageData != null){
                        myImageData.setIdMyImage(dataSnapshot.getKey());
                        dataImages.add(myImageData);
                        adapterRvMyImages.notifyItemChanged(dataImages.size()-1);
                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
