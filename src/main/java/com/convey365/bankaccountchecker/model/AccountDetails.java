package com.convey365.bankaccountchecker.model;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountDetails implements Serializable {

    private static final long serialVersionUID = -889804240160494018L;
    protected String sortCode;
    protected String accountNumber;

    public AccountDetails withAccountDetails(final String sortCode, final String accountNumber) {
        this.setSortCode(sortCode);
        this.setAccountNumber(accountNumber);
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof AccountDetails)
                && (StringUtils.equals(getSortCode(), ((AccountDetails) obj).getSortCode()))
                && (StringUtils.equals(getAccountNumber(), ((AccountDetails) obj).getAccountNumber())));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.sortCode);
        hash = 37 * hash + Objects.hashCode(this.accountNumber);
        return hash;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        if (StringUtils.isNotBlank(sortCode)) {
            sortCode = sortCode.replaceAll("[^\\d]", "");
        }
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (StringUtils.isNotBlank(accountNumber)) {
            accountNumber = accountNumber.replaceAll("[^\\d]", "");
        }
        this.accountNumber = accountNumber;
    }

}
