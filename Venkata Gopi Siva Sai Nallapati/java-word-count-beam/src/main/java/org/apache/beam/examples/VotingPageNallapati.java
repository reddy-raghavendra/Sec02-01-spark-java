package edu.nwmsu.sec02grp1.nallapati;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public  class VotingPageNallapati extends DoFn<KV<String,Iterable<String>>,KV<String,RankedPageNallVotingPageNallapati>>{
    String voterName;
    int contributorVotes;
    public VotingPageNallapati(String voterName,Integer contributorVotes2){
        this.voterName = voterName;
        this.contributorVotes = contributorVotes2;        
    }
}