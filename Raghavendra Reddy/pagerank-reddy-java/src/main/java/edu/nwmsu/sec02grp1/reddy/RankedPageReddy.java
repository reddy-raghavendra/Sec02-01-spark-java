package edu.nwmsu.sec02grp1.reddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.beam.sdk.values.KV;

public class RankedPageReddy implements Serializable, Comparator<KV<Double,String>>{
    String voter;
    double rank = 1.0;
    ArrayList<VotingPageReddy> voterList = new ArrayList<>();
    
    /**
     * 
     * @param voter
     * @param rank
     * @param voters
     */
    public RankedPageReddy(String voter,double rank, ArrayList<VotingPageReddy> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = rank;
    }    

    public RankedPageReddy() {
        voter = "";
        rank = 0.0;
    }

    /**
     * 
     * @param voter
     * @param voters
     */
    public RankedPageReddy(String voter, ArrayList<VotingPageReddy> voters){
        this.voter = voter;
        this.voterList = voters;
    }    
    
    /**
     * 
     * @return voter
     */
    public String getVoter() {
        return voter;
    }

    /**
     * 
     * @param voter
     */
    public void setVoter(String voter) {
        this.voter = voter;
    }

    /**
     * 
     * @return votaingpage list
     */
    public ArrayList<VotingPageReddy> getVoterList() {
        return voterList;
    }

    /**
     * 
     * @param voterList
     */
    public void setVoterList(ArrayList<VotingPageReddy> voterList) {
        this.voterList = voterList;
    }

    /**
     * @return String
     */
    @Override
    public String toString(){
        return this.voter +"<"+ this.rank +","+ voterList +">";
    }

    /**
     * 
     * @return rank
     */
    public double getRank() {
        return this.rank;
    }

    @Override
    public int compare(KV<Double, String> o1, KV<Double, String> o2) {
        double rank1 = o1.getKey();
        double rank2 = o2.getKey();
        if (rank1 > rank2) {
            return 1;
        } else if(rank1 < rank2) {
            return -1;
        }else{
            return 0;
        }
    }

}
