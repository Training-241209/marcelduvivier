package com.example.ERS.dto.response;

import com.example.ERS.entity.Reimbursement;

public class ReimbursementResponse {
    //here also
    private Integer reimbursementId;
    private String description;
    private Double amount;
    private String status;

    public ReimbursementResponse(Reimbursement r) {
        this.reimbursementId = r.getReimbursementId();
        this.description = r.getDescription();
        this.amount = r.getAmount();
        this.status = r.getStatus();
    }
}
