package edu.nwmsu.sec02grp1.nallapati;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageNallapati extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageNallapati>> implements Serializable{
    String voterName;
    int contributorVotes;
    double pageRank;
    public VotingPageNallapati(String voterName,Integer contributorVotes2, double pageRank){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;      
        this.pageRank = pageRank;  
    }

    public VotingPageNallapati(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;      
        this.pageRank = 1.0;  
    }
    
    public String getVoterName() {
        return voterName;
    }
    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }
    public int getContributorVotes() {
        return contributorVotes;
    }
    public void setContributorVotes(int contributorVotes) {
        this.contributorVotes = contributorVotes;
    }
    @Override
    public String toString() {
        return "voterName = "+ voterName +", Page rank = "+this.pageRank +" ContributorVotes = " + contributorVotes;
    }

    public double getPageRank() {
        return this.pageRank;
    }
    public void setPageRank(double pageRank){
        this.pageRank = pageRank;
    }


}