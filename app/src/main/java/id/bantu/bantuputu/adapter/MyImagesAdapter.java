package id.bantu.bantuputu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.bantu.bantuputu.R;
import id.bantu.bantuputu.model.MyImage;

public class MyImagesAdapter extends RecyclerView.Adapter<MyImagesAdapter.ViewHolder>{

    private ArrayList<MyImage> dataImages;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(MyImage myImage);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtImageName;
        private ImageView imgImage;

        ViewHolder(View itemView) {
            super(itemView);

            txtImageName = itemView.findViewById(R.id.mainAdapterListTxtImageName);
            imgImage = itemView.findViewById(R.id.mainAdapterListImgImage);
        }

        void setOnClickListener(View.OnClickListener onClickListener){
            itemView.setOnClickListener(onClickListener);
        }
    }

    @NonNull
    private OnItemClickListener onItemClickListener;

    public MyImagesAdapter(Context context,
                           ArrayList<MyImage> dataImages,
                           @NonNull OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataImages = dataImages;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_main_my_images_list, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyImagesAdapter.ViewHolder holder, int position) {
        String strImageName = dataImages.get(position).getName();
        String strImageUrl = dataImages.get(position).getUrl();

        holder.txtImageName.setText(strImageName);
        Glide.with(context).load(strImageUrl).into(holder.imgImage);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(dataImages.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataImages.size();
    }
}
