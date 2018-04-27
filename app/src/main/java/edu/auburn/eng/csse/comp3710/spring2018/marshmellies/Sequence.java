package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import java.util.ArrayList;


/**
 * Created by kaeul on 4/19/18.
 */

public class Sequence {
    private ArrayList<Integer> mSysSequence;      //system sequence
    private ArrayList<Integer> mUserSequence;

    //constructor
    public Sequence(){
        mSysSequence = new ArrayList<Integer>();
        mUserSequence = new ArrayList<Integer>();
    }

    public Sequence (ArrayList<Integer> sysSequence, ArrayList<Integer> userSequence) {
        mSysSequence = sysSequence;
        mUserSequence = userSequence;

    }

    public void addToSystemSequence(int num){
        mSysSequence.add(num);
    }

    public void addToUserSequence(int num){
        mUserSequence.add(num);
    }

    public ArrayList<Integer> getSysSequence() {
        return mSysSequence;
    }

    public ArrayList<Integer> getUserSequence() {return mUserSequence; }
}
