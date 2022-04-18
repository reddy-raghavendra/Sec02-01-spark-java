package edu.nwmsu.sec02grp1.mylavarapu;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageMylavarapu extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageMylavarapu>>{
    String voterName;
    int contributorVotes;
    public VotingPageMylavarapu(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
    }
}