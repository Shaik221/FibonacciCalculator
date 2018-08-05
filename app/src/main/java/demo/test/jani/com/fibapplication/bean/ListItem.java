package demo.test.jani.com.fibapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ListItem implements Parcelable{
    public long number;
    public long fibValue;

    // main constructor
    public ListItem(long index, long value) {
        super();
        this.number = index;
        this.fibValue = value;
    }

    // Parcelling part
    public ListItem(Parcel in){
        long[] data = new long[2];

        in.readLongArray(data);
        this.number = data[0];
        this.fibValue = data[1];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLongArray(new long[] {this.number,
                this.fibValue});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    // String representation
    public String toString() {
        return this.number + "        :            " + this.fibValue;
    }
}