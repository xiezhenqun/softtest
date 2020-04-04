package SoftTest02;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class ListHomeTest {
    @Rule
public final SystemOutRule systemOutRule=new SystemOutRule().enableLog();
private  IData iData=null;
    @Test
    public void search() {
        iData = createMock("idata", IData.class);
        for (int i = 0; i < HotelConstant.HOTEL_TOTAL_FLOOR; i++) {
            for (int j = 0; j < HotelConstant.HOTEL_ROOMS_EVERYFLOOR; j++) {
                expect(iData.getStation((i + 1) * 100 + j + 1)).andReturn(HotelConstant.EMPTY);
            }
        }
        replay(iData);
        ListHome lh = new ListHome(iData);
        lh.search();
        String lineSeperator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < HotelConstant.HOTEL_TOTAL_FLOOR; i++) {
            for (int j = 0; j < HotelConstant.HOTEL_ROOMS_EVERYFLOOR; j++) {
                if (j + 1 < 10) {
                    sb.append(i + 1 + "0" + (j + 1) + "\t");
                } else {
                    sb.append(i + 1 + "" + (j + 1) + "\t");
                }
            }

        sb.append(lineSeperator);
        for (int j = 0; j < HotelConstant.HOTEL_ROOMS_EVERYFLOOR; j++) {
            sb.append(HotelConstant.EMPTY + "\t");
        }
        sb.append(lineSeperator);
    }
        assertEquals(sb.toString(),systemOutRule.getLog());
        verify(iData);
    }
}