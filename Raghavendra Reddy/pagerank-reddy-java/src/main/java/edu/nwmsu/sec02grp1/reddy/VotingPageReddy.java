package edu.nwmsu.sec02grp1.reddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageReddy extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageReddy>> implements Serializable{
    String voterName;
    int contributorVotes;
    public VotingPageReddy(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
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
        return "contributorVotes=" + contributorVotes + ", voterName=" + voterName;
    }


}
