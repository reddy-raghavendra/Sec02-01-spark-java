package edu.nwmsu.sec02grp1.nalla;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageNalla extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageNalla>>{
    String voterName;
    int contributorVotes;
    public VotingPageNalla(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
    }
}
