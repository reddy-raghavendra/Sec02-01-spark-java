package edu.nwmsu.sec02grp1.reddy;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageTelluri extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageTelluri>>{
    String voterName;
    int contributorVotes;
    public VotingPageTelluri(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
    }
}
