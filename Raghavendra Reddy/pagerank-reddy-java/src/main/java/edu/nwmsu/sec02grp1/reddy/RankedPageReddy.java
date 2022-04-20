package edu.nwmsu.sec02grp1.reddy;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageReddy implements Serializable{
    String voter;
    ArrayList<VotingPageReddy> voterList = new ArrayList<>();
    
    public RankedPageReddy(String voter, ArrayList<VotingPageReddy> voters){
        this.voter = voter;
        this.voterList = voters;
    }
    
    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public ArrayList<VotingPageReddy> getVoterList() {
        return voterList;
    }

    public void setVoterList(ArrayList<VotingPageReddy> voterList) {
        this.voterList = voterList;
    }

    @Override
    public String toString(){
        return voter + voterList;
    }
}
