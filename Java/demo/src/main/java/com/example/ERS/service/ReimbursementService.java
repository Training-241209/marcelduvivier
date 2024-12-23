package com.example.ERS.service;

import java.util.List;
import com.example.ERS.entity.User;
import com.example.ERS.dto.Request.EditRequest;
import com.example.ERS.entity.Reimbursement;

public interface ReimbursementService {
    Reimbursement createReimbursement(String token,Reimbursement reimbursement);
    List<Reimbursement> getAllReimbursements(); //check role
    List<Reimbursement> getAllPendingReimbursements(); //check role
    Reimbursement updateReimbursement(String token,EditRequest reimbursement); //check role
}
