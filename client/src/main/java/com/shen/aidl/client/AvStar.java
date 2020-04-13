package com.shen.aidl.client;
import android.os.Parcel;
import android.os.Parcelable;

public class AvStar implements Parcelable {
    private String number;  // 番号
    private String name;    // 名字
    private int age;        // 年龄
    /**
     * 描述
     * 返回的是内容的描述信息,只针对一些特殊的需要描述信息的对象,需要返回1,其他情况返回0就可以
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * 序列化
     * 如果不实现该方法，在进行数据传输的时候数据无法存储
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(name);
        parcel.writeInt(age);
    }
    /**
     * 反序列化
     * 静态成员变量CREATOR实现反序列化，必须定义否则传输数据过程中无法解析到内存中
     */
    public static final Creator<AvStar> CREATOR = new Creator<AvStar>() {
        @Override
        public AvStar createFromParcel(Parcel parcel) {
            AvStar des = new AvStar();
            des.setNumber(parcel.readString()); //注意 与writeToParcel顺序一样，否则出现数据错位情况
            des.setName(parcel.readString());
            des.setAge(parcel.readInt());
            return des;
        }
        @Override
        public AvStar[] newArray(int i) {
            return new AvStar[0];
        }
    };
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
