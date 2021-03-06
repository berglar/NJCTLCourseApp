package org.njctl.courseapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by ying on 11/3/13.
 */
public class NJCTLClass implements Parcelable {
	
    private int classId;
    private String classTitle;
    private ArrayList<Unit> units = new ArrayList<Unit>();
    private Date lastUpdate;

    public NJCTLClass(String name, ArrayList<Unit> unitList) {
        this.classTitle = name;
        this.units = unitList;
    }
    
    public NJCTLClass(String name) {
        this.classTitle = name;
    }
    
    public NJCTLClass(JSONObject json)
    {
    	try {
    		classTitle = json.getString("post_title");
    		
			String modified = json.getString("post_modified");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			lastUpdate = df.parse(modified);
			
			JSONArray unitList = json.getJSONArray("pages");
			
			for(int i = 0; i < unitList.length(); i++)
			{
				units.add(new Unit(unitList.getJSONObject(i)));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w("JSON ERR", e.toString());
		} catch(ParseException e)
		{
			Log.w("JSON ERR", e.toString());
		}
    }
    
    public void add(Unit chapter)
    {
    	this.units.add(chapter);
    }
    
    // Parcelable constructor.
    public NJCTLClass(Parcel in) {
    	readFromParcel(in);
    }
    
    public String getTitle() {
    	return classTitle;
    }
    
    public int getId() {
    	return classId;
    }
    
    public ArrayList<Unit> getContents() {
    	return units;
    }
    
    
    // Methods for Parcelable implementation.
    
    @Override
    public int describeContents() {
    	return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    	dest.writeInt(classId);
    	dest.writeString(classTitle);
    	dest.writeParcelableArray(units.toArray(new Unit[units.size()]), 0);
    }
    
    private void readFromParcel(Parcel in) {
    	classId = in.readInt();
    	classTitle = in.readString();
    	units = new ArrayList<Unit>();
        in.readList(units, Unit.class.getClassLoader());
    }
    
    public static final Parcelable.Creator<NJCTLClass> CREATOR = new Parcelable.Creator<NJCTLClass>() {
    	public NJCTLClass createFromParcel(Parcel in) {
    		return new NJCTLClass(in);
    	}
    	public NJCTLClass[] newArray(int size) {
    		return new NJCTLClass[size];
    	}
    };
    
}
