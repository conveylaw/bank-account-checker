package com.convey365.bankaccountchecker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountProperties implements Serializable {

    private static final long serialVersionUID = -981102456185991566L;

    @JsonProperty("institution")
    protected String institution;
    @JsonProperty("branch")
    protected String branch;
    @JsonProperty("fast_payment")
    protected boolean fasterPayments;
    @JsonProperty("bacs_credit")
    protected boolean bacsCredit;
    @JsonProperty("bacs_direct_debit")
    protected boolean bacsDirectDebit;
    @JsonProperty("chaps")
    protected boolean chaps;
    @JsonProperty("cheque")
    protected boolean cheque;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isFasterPayments() {
        return fasterPayments;
    }

    public void setFasterPayments(boolean fasterPayments) {
        this.fasterPayments = fasterPayments;
    }

    public boolean isBacsCredit() {
        return bacsCredit;
    }

    public void setBacsCredit(boolean bacsCredit) {
        this.bacsCredit = bacsCredit;
    }

    public boolean isBacsDirectDebit() {
        return bacsDirectDebit;
    }

    public void setBacsDirectDebit(boolean bacsDirectDebit) {
        this.bacsDirectDebit = bacsDirectDebit;
    }

    public boolean isChaps() {
        return chaps;
    }

    public void setChaps(boolean chaps) {
        this.chaps = chaps;
    }

    public boolean isCheque() {
        return cheque;
    }

    public void setCheque(boolean cheque) {
        this.cheque = cheque;
    }
}
