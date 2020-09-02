package com.company.MattLometU1Capstone.dao;


import com.company.MattLometU1Capstone.models.ProcessingFee;

import java.util.List;

public interface ProccessingFeeDao {
    ProcessingFee getProcessingFee(String product_type);
    List<ProcessingFee> getAllProcessingFees();
}
