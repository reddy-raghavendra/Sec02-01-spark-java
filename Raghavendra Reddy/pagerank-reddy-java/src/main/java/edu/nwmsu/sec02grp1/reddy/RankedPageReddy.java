package edu.nwmsu.sec02grp1.reddy;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageReddy implements Serializable{
    String voter;
    double rank;
    ArrayList<VotingPageReddy> voterList = new ArrayList<>();
    
    public RankedPageReddy(String voter,double rank, ArrayList<VotingPageReddy> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = rank;
    }    
    public RankedPageReddy(String voter, ArrayList<VotingPageReddy> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = 1.0;
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
        return this.voter +"<"+ this.rank +","+ voterList +">";
    }

    public double getRank() {
        return this.rank;
    }
}
