package domain;

public class Listing {
    private String title;
    private int optionNum;
    private int voteNum;
    private boolean isVote;


    public boolean getIsVote() {
        return isVote;
    }
    public void setIsVote(boolean isVote) {
        this.isVote = isVote;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getOptionNum() {
        return optionNum;
    }
    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }
    public int getVoteNum() {
        return voteNum;
    }
    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

}
