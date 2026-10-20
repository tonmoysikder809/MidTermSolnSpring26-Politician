package cse213.midtermsoln26sec4_politician;

import java.time.LocalDate;

public class Politician {

    private String memberId, partyName, memberType;
    private boolean isInGovtPosition, hasWonAnyElection;
    private float annualMembershipFee;
    private LocalDate dateOfJoining;

    public Politician(String memberId, String partyName, String memberType, boolean isInGovtPosition, boolean hasWonAnyElection, float annualMembershipFee, LocalDate dateOfJoining) {
        this.memberId = memberId;
        this.partyName = partyName;
        this.memberType = memberType;
        this.isInGovtPosition = isInGovtPosition;
        this.hasWonAnyElection = hasWonAnyElection;
        this.annualMembershipFee = annualMembershipFee;
        this.dateOfJoining = dateOfJoining;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPartyName() {
        return partyName;
    }

    public String getMemberType() {
        return memberType;
    }

    public boolean isInGovtPosition() {
        return isInGovtPosition;
    }

    public boolean isHasWonAnyElection() {
        return hasWonAnyElection;
    }

    public float getAnnualMembershipFee() {
        return annualMembershipFee;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public void setInGovtPosition(boolean inGovtPosition) {
        isInGovtPosition = inGovtPosition;
    }

    public void setHasWonAnyElection(boolean hasWonAnyElection) {
        this.hasWonAnyElection = hasWonAnyElection;
    }

    public void setAnnualMembershipFee(float annualMembershipFee) {
        this.annualMembershipFee = annualMembershipFee;
    }

    @Override
    public String toString() {
        return "Politician{" +
                "memberId='" + memberId + '\'' +
                ", partyName='" + partyName + '\'' +
                ", memberType='" + memberType + '\'' +
                ", isInGovtPosition=" + isInGovtPosition +
                ", hasWonAnyElection=" + hasWonAnyElection +
                ", annualMembershipFee=" + annualMembershipFee +
                ", dateOfJoining=" + dateOfJoining +
                '}';
    }

    public float returnMonthlyPayableToPartyFund(){

        float monthlyFee = annualMembershipFee / 12;
        if (isInGovtPosition){
            float annualFee = annualMembershipFee + (annualMembershipFee * 0.2f);
            monthlyFee = annualFee / 12;
        }
        return monthlyFee;
    }
}
