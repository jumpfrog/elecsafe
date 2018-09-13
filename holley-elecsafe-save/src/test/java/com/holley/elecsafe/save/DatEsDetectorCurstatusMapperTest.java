package com.holley.elecsafe.save;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.save.dao.DatEsDetectorCurstatusMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class DatEsDetectorCurstatusMapperTest {

    @Autowired
    DatEsDetectorCurstatusMapper datEsDetectorCurstatusMapper;

    @Test
    public void testmapper() {
        List<DatEsDetectorCurstatus> list = new ArrayList<DatEsDetectorCurstatus>();
        DatEsDetectorCurstatus status = new DatEsDetectorCurstatus();
        status.setIsfault((short) 1);
        list.add(status);
        datEsDetectorCurstatusMapper.updateCurstatusList(list);
    }
}
