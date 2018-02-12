package id.bantu.bantuputu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties //biar bisa dapet id
public class MyImage implements Parcelable{
    private String name;
    private String url;
    @Exclude private String idMyImage; //karena id ga ada di struktur, tp parent child nya

    public MyImage(){
        // Default constructor required for calls to DataSnapshot.getValue(MyImage.class)
    }

    public MyImage(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Exclude //setter getter harus di exclude jg
    public String getIdMyImage() {
        return idMyImage;
    }

    @Exclude //setter getter harus di exclude jg
    public void setIdMyImage(String idMyImage) {
        this.idMyImage = idMyImage;
    }

    //Parcelable : Biar bisa di kirim leat intent atau argument fragment
    public MyImage(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<MyImage> CREATOR = new Parcelable.Creator<MyImage>() {
        public MyImage createFromParcel(Parcel in) {
            return new MyImage(in);
        }

        public MyImage[] newArray(int size) {

            return new MyImage[size];
        }

    };

    public void readFromParcel(Parcel in) {
        name = in.readString();
        url = in.readString();
        idMyImage = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(idMyImage);
    }

}
