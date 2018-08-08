package com.iyer.shailesh.dbar;

/**
 * Created by sweth on 9/11/2016.
 */
public class Riddles {
    private int RID;
    private String QUESTION;
    private String ANSWER;
    private String DBID;
    private int STATUS;
    public Riddles(){
        RID=0;
        QUESTION="";
        ANSWER="";
        DBID="";
        STATUS=0;
    }
    public Riddles(int rid,String question,String answer,String dbid){
        RID=rid;
        QUESTION=question;
        ANSWER=answer;
        DBID=dbid;
    }
    public int getRID(){return RID;}
    public String getQUESTION(){return QUESTION;}
    public String getANSWER(){return ANSWER;}
    public String getDBID(){return DBID;}
    public int getStatus(){return STATUS;}
    public void setRID(int rid){RID=rid;}
    public void setQUESTION(String question){QUESTION=question;}
    public void setANSWER(String answer){ANSWER=answer;}
    public void setDBID(String dbid){DBID=dbid;}
    public void setStatus(int status){STATUS=status;}
}
