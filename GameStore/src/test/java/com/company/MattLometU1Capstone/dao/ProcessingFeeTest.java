package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeTest {

    @Autowired
    ProccessingFeeDao proccessingFeeDao;

    @Test
    public void getGetAllProcessingFeeTest(){
        List<ProcessingFee> processingFeeList = proccessingFeeDao.getAllProcessingFees();
        ProcessingFee processingFee = processingFeeList.get(0);

        assertEquals(processingFee, proccessingFeeDao.getAllProcessingFees().get(0));

        assertEquals(processingFee,proccessingFeeDao.getProcessingFee("Consoles"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenProcessingFeeDoesNotExist(){
        proccessingFeeDao.getProcessingFee("DVD");
    }
}
