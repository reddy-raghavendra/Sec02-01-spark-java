package edu.nwmsu.sec02grp1.mylavarapu;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageMylavarapu implements Serializable{
    String voter;
    double rank = 1.0;
    ArrayList<VotingPageMylavarapu> voterList = new ArrayList<>();
    
    public RankedPageMylavarapu(String voter,double rank, ArrayList<VotingPageMylavarapu> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = rank;
    }    
    public RankedPageMylavarapu(String voter, ArrayList<VotingPageMylavarapu> voters){
        this.voter = voter;
        this.voterList = voters;
    }    
    
    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public ArrayList<VotingPageMylavarapu> getVoterList() {
        return voterList;
    }

    public void setVoterList(ArrayList<VotingPageMylavarapu> voterList) {
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
