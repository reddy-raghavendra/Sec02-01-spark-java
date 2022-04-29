package edu.nwmsu.sec02grp1.nalla;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageNalla implements Serializable {
    String voter;
    double rank = 1.0;
    ArrayList<VotingPageNalla> voterList = new ArrayList<>();
    
    public RankedPageNalla(String voter,double rank, ArrayList<VotingPageNalla> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = rank;
    }    
    public RankedPageNalla(String voter, ArrayList<VotingPageNalla> voters){
        this.voter = voter;
        this.voterList = voters;
    }  
    public RankedPageNalla() {
        voter = "";
        rank = 0.0;
    }  
    
    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public ArrayList<VotingPageNalla> getVoterList() {
        return voterList;
    }

    public void setVoterList(ArrayList<VotingPageNalla> voterList) {
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