package com.huntercollab.app.network.loopjtasks;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class CollabModel implements Parcelable {
    public int id;
    private String owner;
    private int size;
    private long date;
    private long duration;
    private String location;
    private Boolean status;
    private String title;
    private String description;
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<String> skills = new ArrayList<>();
    private ArrayList<String> applicants = new ArrayList<>();
    private ArrayList<String> members = new ArrayList<>();
    private String collabId;

    //@author: Hugh Leow & Edwin Quintuna
    CollabModel(
            int id,
            String owner,
            int size,
            long duration,
            long date,
            String location,
            Boolean status,
            String title,
            String description,
            ArrayList<String> classes,
            ArrayList<String> skills,
            ArrayList<String> applicants,
            ArrayList<String> members,
            String collabId){

        this.id = id;
        this.owner = owner;
        this.size = size;
        this.duration = duration;
        this.date = date;
        this.location = location;
        this.status = status;
        this.title = title;
        this.description = description;
        this.classes = classes;
        this.skills = skills;
        this.applicants = applicants;
        this.members = members;
        this.collabId = collabId;
    }

    //@author: Hugh Leow & Edwin Quintuna
    //@brief: Constructor to copy values from the Parcel into the above variables
    //@params: [Parcel in]
    public CollabModel(Parcel in) {
        id = in.readInt();
        owner = in.readString();
        size = in.readInt();
        duration = in.readLong();
        date = in.readLong();
        location = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        title = in.readString();
        description = in.readString();
        skills = in.createStringArrayList();
        classes = in.createStringArrayList();
        members = in.createStringArrayList();
        collabId = in.readString();
    }

    //@author: Hugh Leow & Edwin Quintuna
    //@brief: Create CollabModel(s) from a parcel
    public static final Creator<CollabModel> CREATOR = new Creator<CollabModel>() {
        @Override
        public CollabModel createFromParcel(Parcel in) {
            return new CollabModel(in);
        }

        @Override
        public CollabModel[] newArray(int size) {
            return new CollabModel[size];
        }
    };

    public CollabModel() {

        id = 0;
        owner = "None";
        size = 1;
        duration = 1;
        date = 1;
        location = "None";
        byte tmpStatus = 8;
        status = tmpStatus == 0;
        title = "Error, Could not retrieve data";
        description = "None";
        collabId = "1";

    }

    public String getOwner(){
        return owner;
    }
    public int getSize(){ return size; }
    public ArrayList<String> getMembers(){
        return members;
    }
    public long getDate(){
        return date;
    }
    public long getDuration(){
        return duration;
    }
    public String getLocation(){
        return location;
    }
    public Boolean getStatus(){
        return status;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public ArrayList<String> getClasses(){
        return classes;
    }
    public ArrayList<String> getSkills(){
        return skills;
    }
    public ArrayList<String> getApplicants(){
        return applicants;
    }
    public int getId() {return id;}
    public String getCollabId(){
        return collabId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(owner);
        dest.writeInt(size);
        dest.writeLong(duration);
        dest.writeLong(date);
        dest.writeString(location);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        dest.writeString(title);
        dest.writeString(description);
        dest.writeStringList(skills);
        dest.writeStringList(classes);
        dest.writeStringList(members);
        dest.writeString(collabId);
    }
}