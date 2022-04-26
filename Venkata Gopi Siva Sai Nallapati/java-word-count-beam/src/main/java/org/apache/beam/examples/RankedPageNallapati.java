package edu.nwmsu.sec02grp1.nallapati;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageNallapati implements Serializable{
    String voter;
    double rank;
    ArrayList<VotingPageNallapati> voterList = new ArrayList<>();
    
    public RankedPageNallapati(String voter,double rank, ArrayList<VotingPageNallapati> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = rank;
    }    
    public RankedPageNallapati(String voter, ArrayList<VotingPageNallapati> voters){
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

    public ArrayList<VotingPageNallapati> getVoterList() {
        return voterList;
    }

    public void setVoterList(ArrayList<VotingPageNallapati> voterList) {
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