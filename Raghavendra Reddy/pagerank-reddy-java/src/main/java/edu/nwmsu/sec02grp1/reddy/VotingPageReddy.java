package edu.nwmsu.sec02grp1.reddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageReddy extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageReddy>> implements Serializable{

    String voterName;
    int contributorVotes;
    double pageRank = 1.0;

    /**
     * 
     * @param voterName
     * @param contributorVotes2
     * @param pageRank
     */
    public VotingPageReddy(String voterName,Integer contributorVotes2, double pageRank){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;      
        this.pageRank = pageRank;  
    }

    /**
     * 
     * @param voterName
     * @param contributorVotes2
     */
    public VotingPageReddy(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;      
   
    }
    
    /**
     * 
     * @return voter name
     */
    public String getVoterName() {
        return voterName;
    }

    /**
     * 
     * @param voterName
     */
    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    /**
     * 
     * @return contributed votes
     */
    public int getContributorVotes() {
        return contributorVotes;
    }

    /**
     * 
     * @param contributorVotes
     */
    public void setContributorVotes(int contributorVotes) {
        this.contributorVotes = contributorVotes;
    }

    /**
     * return String
     */
    @Override
    public String toString() {
        return "voterName = "+ voterName +", Page rank = "+this.pageRank +" ContributorVotes = " + contributorVotes;
    }

    /**
     * 
     * @return page rank
     */
    public double getPageRank() {
        return this.pageRank;
    }

    /**
     * 
     * @param pageRank
     */
    public void setPageRank(double pageRank){
        this.pageRank = pageRank;
    }


}
