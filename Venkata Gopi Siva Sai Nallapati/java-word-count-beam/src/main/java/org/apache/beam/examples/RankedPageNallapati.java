package edu.nwmsu.sec02grp1.nallapati;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.beam.sdk.values.KV;

public class RankedPageNallapati implements Serializable, Comparator<KV<Double,String>>{
    String voter;
    double rank = 1.0;
    ArrayList<VotingPageNallapati> voterList = new ArrayList<>();
    
    /**
     * 
     * @param voter
     * @param rank
     * @param voters
     */
    public RankedPageNallapati(String voter,double rank, ArrayList<VotingPageNallapati> voters){
        this.voter = voter;
        this.voterList = voters;
        this.rank = rank;
    }    

    public RankedPageNallapati() {
        voter = "";
        rank = 0.0;
    }

    /**
     * 
     * @param voter
     * @param voters
     */
    public RankedPageNallapati(String voter, ArrayList<VotingPageNallapati> voters){
        this.voter = voter;
        this.voterList = voters;
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