package edu.nwmsu.sec02grp1.mylavarapu;

import java.util.ArrayList;

public class RankedPageMylavarapu {
    String voter;
    ArrayList<VotingPageMylavarapu> voterList = new ArrayList<>();
    
    public RankedPageMylavarapu(String voter, ArrayList<VotingPageMylavarapu> voters){
        this.voter = voter;
        this.voterList = voters;
    }
}