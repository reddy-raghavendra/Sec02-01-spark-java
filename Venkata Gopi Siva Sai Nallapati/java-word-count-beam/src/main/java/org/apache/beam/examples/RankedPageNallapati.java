package edu.nwmsu.sec02grp1.nallapati;

import java.util.ArrayList;

public class RankedPageNallapati {
    String voter;
    ArrayList<VotingPageNallapati> voterList = new ArrayList<>();
    
    public RankedPageNallapati(String voter, ArrayList<VotingPageNallapati> voters){
        this.voter = voter;
        this.voterList = voters;
    }
}