package com.convey365.bankaccountchecker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountValidationResult implements Serializable {

    private static final long serialVersionUID = 4880964144951710528L;

    protected LocalDateTime checked;
    @JsonProperty("resultCode")
    protected int resultCode;
    @JsonProperty("resultDescription")
    protected String resultDescription;
    @JsonProperty("accountProperties")
    protected AccountProperties accountProperties;
    protected AccountDetails details;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isValidResult() {
        return resultCode == 1;
    }
    
    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public AccountProperties getAccountProperties() {
        return accountProperties;
    }

    public void setAccountProperties(AccountProperties accountProperties) {
        this.accountProperties = accountProperties;
    }

    public AccountDetails getDetails() {
        return details;
    }

    public void setDetails(AccountDetails details) {
        this.details = details;
    }

    public LocalDateTime getChecked() {
        return checked;
    }

    public void setChecked(LocalDateTime checked) {
        this.checked = checked;
    }
}
