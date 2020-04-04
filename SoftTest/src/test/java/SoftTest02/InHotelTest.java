package SoftTest02;

import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class InHotelTest {
private IData iData=null;
    @Test
    public void in() {
        iData= EasyMock.createMock(IData.class);
        EasyMock.expect(iData.in_out_Room(101,"张三")).andReturn("张三成功入住101房间");
        EasyMock.replay(iData);
        InHotel inHotel=new InHotel(iData);
        String result=inHotel.in(101,"张三");
        assertEquals("张三成功入住101房间",result);
        EasyMock.verify(iData);
    }
    @Test
    public  void testInfailure(){
        iData=EasyMock.createMock(IData.class);
        EasyMock.expect(iData.in_out_Room(101,"张三")).andReturn("该房间已经有客人入住!");
        EasyMock.replay(iData);
        InHotel inHotel=new InHotel(iData);
        assertEquals(inHotel.in(101,"张三"),"该房间已经有客人入住!");
        EasyMock.verify(iData);
    }
}