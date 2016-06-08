package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.125.50.170");
        assertEquals(geoIP.getCountryCode(),"BLR");
    }

    @Test
    public void testMyInvalidIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.125.50.xxx");
        assertEquals(geoIP.getCountryCode(),"BLR");
    }

}
