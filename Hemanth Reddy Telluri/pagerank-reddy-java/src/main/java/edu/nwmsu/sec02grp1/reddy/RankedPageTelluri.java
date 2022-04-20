package edu.nwmsu.sec02grp1.reddy;

import java.util.ArrayList;

public class RankedPageTelluri {
    String voter;
    ArrayList<VotingPageTelluri> voterList = new ArrayList<>();
    
    public RankedPageTelluri(String voter, ArrayList<VotingPageTelluri> voters){
        this.voter = voter;
        this.voterList = voters;
    }
}
