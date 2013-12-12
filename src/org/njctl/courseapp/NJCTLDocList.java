package org.njctl.courseapp;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 * 
 * Created by Colin on 12/12/13.
 *
 */

public class NJCTLDocList implements Parcelable {
	private int _id;
	private String _name;
	private ArrayList<NJCTLDocument> _docs;
	
	public NJCTLDocList(String name, ArrayList<NJCTLDocument> docs) {
		this._name = name;
		this._docs = docs;
	}
	
	public NJCTLDocList(Parcel in) {
		readFromParcel(in);
	}
	
	public int getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public ArrayList<NJCTLDocument> getDocs() {
		return _docs;
	}

    // Methods for Parcelable implementation.
    
    @Override
    public int describeContents() {
    	return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    	dest.writeInt(_id);
    	dest.writeString(_name);
    	dest.writeParcelableArray((NJCTLDocument[]) _docs.toArray(), flags);
    }
    
    private void readFromParcel(Parcel in) {
    	_id = in.readInt();
    	_name = in.readString();
    	_docs = new ArrayList<NJCTLDocument>();
        in.readList(_docs, NJCTLDocument.class.getClassLoader());
    }
    
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    	public NJCTLDocList createFromParcel(Parcel in) {
    		return new NJCTLDocList(in);
    	}
    	public NJCTLDocList[] newArray(int size) {
    		return new NJCTLDocList[size];
    	}
    };
	
}
