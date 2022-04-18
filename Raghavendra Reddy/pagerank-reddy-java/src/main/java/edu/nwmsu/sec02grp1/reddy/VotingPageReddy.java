package edu.nwmsu.sec02grp1.reddy;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageReddy extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageReddy>>{
    String voterName;
    int contributorVotes;
    public VotingPageReddy(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
    }
}
