package com.example.nguyenduy.projectbase.base.location.convertLocation;

public class ConvertLocationConstants {

    public class Result {
        public static final int NOT_FOUND = 0;
        public static final int SUCCESS = 1;
        public static final int SERVICE_NOT_AVAILABLE = 2;
        public static final int INVALID_LAT_LONG_USED = 3;
    }

    public static final String TYPE_CONVERT = "TYPE_CONVERT";

    public class TypeConvert {
        public static final int NAME_ADDRESS = 0;
        public static final int LOCATION_ADDRESS = 1;
    }

    public static final String NUMBER_ADDRESS = "NUMBER_ADDRESS";
    public static final String RESULT_RECEIVER = "RESULT_RECEIVER";
    public static final String RESULT_DATA = "RESULT_DATA";
    public static final String DATA_LOCATION = "DATA_LOCATION";
    public static final String DATA_NAME_ADDRESS = "DATA_NAME_ADDRESS";

    public class MessageErrorLog {
        public static final String NO_GEOCODER_AVAILABLE = "No geocoder available";
        public static final String FETCH_ADDRESS = "Fetch address";
        public static final String ADDRESS_FOUND = "Address found";
        public static final String NO_ADDRESS_FOUND = "Sorry, no address found";
        public static final String NO_LOCATION_DATA_PROVIDED = "No location data provided";
        public static final String SERVICE_NOT_AVAILABLE = "Sorry, the service is not available";
        public static final String INVALID_LAT_LONG_USED = "Invalid latitude or longitude used";
    }

}
