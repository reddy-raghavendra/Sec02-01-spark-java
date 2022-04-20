package edu.nwmsu.sec02grp1.kurra;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageKurra implements Serializable{
    String voter;
    ArrayList<VotingPageKurra> voterList = new ArrayList<>();
    
    public RankedPageKurra(String voter, ArrayList<VotingPageKurra> voters){
        this.voter = voter;
        this.voterList = voters;
    }
    
    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public ArrayList<VotingPageKurra> getVoterList() {
        return voterList;
    }

    public void setVoterList(ArrayList<VotingPageKurra> voterList) {
        this.voterList = voterList;
    }

    @Override
    public String toString(){
        return voter + voterList;
    }
}
