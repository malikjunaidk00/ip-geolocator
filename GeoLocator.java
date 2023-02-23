package geolocator;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
public interface GeoLocator {

    @RequestLine("GET")
    GeoLocation getGeoLocation();

    @RequestLine("GET /{ipOrHostName}")
    GeoLocation getGeoLocation(@Param("ipOrHostName") String ipOrHostName);

    static GeoLocator newInstance() {
        return Feign.builder()
            .decoder(new GsonDecoder(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()))
            .target(GeoLocator.class, "https://reallyfreegeoip.org/json/");
    }

}
