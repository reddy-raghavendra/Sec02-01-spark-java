package edu.nwmsu.sec02grp1.kurra;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageKurra extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageKurra>>{
    String voterName;
    int contributorVotes;
    public VotingPageKurra(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
    }
}