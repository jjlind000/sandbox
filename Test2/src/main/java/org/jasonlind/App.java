package org.jasonlind;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.jasonlind.apple.AppleNotificationPayloadDTO;
import org.jasonlind.apple.ApplePayloadDTO;
import org.mvel2.MVEL;

import javax.ejb.EJBTransactionRolledbackException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */



/*class MapAdapter implements JsonDeserializer<Map<String,String>> {
    @Override
    public Map deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement element = jsonObject.get("metadataGroups");
        Type metadataGroupsMapType = new TypeToken<Map<String, String>>() {}.getType();
        return context.deserialize(element, metadataGroupsMapType);
    }
}

class AppleAdapter implements JsonDeserializer<AppleNotificationPayloadDTO> {

    @Override
    public AppleNotificationPayloadDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        //String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("aps");
//        JsonElement alertElement = jsonObject.get("alert");
//        JsonElement metadataGroupsElement = jsonObject.get("metadataGroups");

//        Type empMapType = new TypeToken<Map<String, String>>() {}.getType();
//        return context.deserialize(element, empMapType);
        try {
            //return context.deserialize(element, Class.forName("org.jasonlind.apple.ApplePayloadSimpleDTO"));
            return context.deserialize(element, Class.forName("org.jasonlind.apple.AppleNotificationPayloadDTO"));
            //return context.deserialize(element, empMapType);
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Could not deserialize: ", cnfe);
        }
    }
}
class AppleAdapter2 implements JsonDeserializer<ApplePayloadDTO> {

    @Override
    public ApplePayloadDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        //String type = jsonObject.get("type").getAsString();
        JsonElement alertElement = jsonObject.get("alert");
        JsonElement element = jsonObject.get("metadataGroups");
//        JsonElement alertElement = jsonObject.get("alert");
//        JsonElement metadataGroupsElement = jsonObject.get("metadataGroups");

//        Type empMapType = new TypeToken<Map<String, String>>() {}.getType();
//        return context.deserialize(element, empMapType);
        try {
            //return context.deserialize(element, Class.forName("org.jasonlind.apple.ApplePayloadSimpleDTO"));
            return context.deserialize(element, Class.forName("org.jasonlind.apple.ApplePayloadSimpleDTO"));
            //return context.deserialize(element, empMapType);
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Could not deserialize: ", cnfe);
        }
    }
}*/

class AppleAdapter3 implements JsonDeserializer<AppleNotificationPayloadDTO> {

    @Override
    public AppleNotificationPayloadDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement contentElement = jsonObject.get("aps");
        JsonElement fsbElement = jsonObject.get("fsb");
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        final Map<String,String> fsbMap = context.deserialize(fsbElement, mapType);
        try {
            final JsonElement alert = contentElement.getAsJsonObject().get("alert");
            final ApplePayloadDTO payloadDTO;
            if(alert.getClass() == JsonPrimitive.class)
            {
                payloadDTO = context.deserialize(contentElement, Class.forName("org.jasonlind.apple.ApplePayloadSimpleDTO"));
            }
            else
            {
                payloadDTO = context.deserialize(contentElement, Class.forName("org.jasonlind.apple.ApplePayloadComplexDTO"));
            }
            return new AppleNotificationPayloadDTO(payloadDTO, fsbMap);
        }
        catch (ClassNotFoundException cnfe)
        {
            throw new JsonParseException("Could not deserialize: ", cnfe);
        }
    }
}


public class App 
{
    public static void main( String[] args )
    {
        //classNames();
        //gson();
        //testGetPSHAwayWin();;
        //testDoDate();
        //callTestParseConfig();
        //testthrowejb();
        //deserializeToMap();
        //deserializeToApple();
        //deserializeToApple3();
        tostringtest();
    }

    static void tostringtest(){
        Map<String,String> metadataGroups = new HashMap<>();
        metadataGroups.put("k1","v1");
        metadataGroups.put("k2","v2");
        metadataGroups.put("k3","v3");

        final String s = metadataGroups.entrySet().stream()
                .map(entry -> String.format("[%s:%s]", entry.getValue(), entry.getValue()))
                .collect(Collectors.joining(","));
        System.out.println(s);


    }


/*    private static void deserializeToMap()
    {
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(ApplePayloadDTO.class, new MapAdapter());
        Gson gson = gsonBilder.create();
        String json = "{\"aps\":{\"alert\":\"t1\",\"badge\":0,\"content-available\":1,\"thread-id\":\"145\",\"metadataGroups\":{\"FOOTBALL\":\"test\"}}}";
        final Map map = gson.fromJson(json, Map.class);
        System.out.println(map.entrySet());

    }*/
/*    private static void deserializeToApple()
    {
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(AppleNotificationPayloadDTO.class, new AppleAdapter());
        Gson gson = gsonBilder.create();
        String json = "{\"aps\":{\"alert\":\"t1\",\"badge\":0,\"content-available\":1,\"thread-id\":\"145\",\"metadataGroups\":{\"FOOTBALL\":\"test\"}}}";
        //final AppleNotificationPayloadDTO appleNotificationPayloadDTO =
        final AppleNotificationPayloadDTO appleNotificationPayloadDTO = gson.fromJson(json, AppleNotificationPayloadDTO.class);
        //final Map map = gson.fromJson(json, Map.class);
        System.out.println(appleNotificationPayloadDTO);
        //System.out.println(map.entrySet());

    }*/

/*    private static void deserializeToApple2()
    {
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(ApplePayloadDTO.class, new AppleAdapter2());
        gsonBilder.registerTypeAdapter(AppleNotificationPayloadDTO.class, new AppleAdapter3());
        Gson gson = gsonBilder.create();
        String json = "{\"aps\":{\"alert\":\"t1\",\"badge\":0,\"content-available\":1,\"thread-id\":\"145\",\"metadataGroups\":{\"FOOTBALL\":\"test\"}}}";
        //final AppleNotificationPayloadDTO appleNotificationPayloadDTO =
        final AppleNotificationPayloadDTO appleNotificationPayloadDTO = gson.fromJson(json, AppleNotificationPayloadDTO.class);
        //final Map map = gson.fromJson(json, Map.class);
        System.out.println(appleNotificationPayloadDTO);
        //System.out.println(map.entrySet());

    }*/

    private static void deserializeToApple3()
    {
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(AppleNotificationPayloadDTO.class, new AppleAdapter3());
        Gson gson = gsonBilder.create();
        String json = //"{\"aps\":{\"alert\":\"t1\",\"badge\":0,\"content-available\":1,\"thread-id\":\"145\",\"metadataGroups\":{\"FOOTBALL\":\"test\"}}}";
                      " {\"aps\":{\"alert\":\"ENGLAND all out for 85 against Ireland and yet they are still 4/9 \uD83D\uDC40. Back IRELAND to follow up Shane Lowry's success at 21/10! \uD83D\uDD25\",\"badge\":0,\"content-available\":1,\"thread-id\":\"2361\", \"metadataGroups\":{\"FOOTBALL\":\"test\"}},\"fsb\":{\"cid\":\"10\",\"sid\":\"3091\"}}";
        final AppleNotificationPayloadDTO appleNotificationPayloadDTO = gson.fromJson(json, AppleNotificationPayloadDTO.class);
        System.out.println(appleNotificationPayloadDTO.getAps().getMetadataGroups());
    }

    static void testthrowejb(){
        try {
            throwejb();
        } catch (Exception e){
            if(e.getCause() instanceof RuntimeException)
            {
                System.out.println("got it");

            }
            else
            {
                //just rethrow
                throw e;
            }
        }
    }

    public static void throwejb(){
        EJBTransactionRolledbackException e = new EJBTransactionRolledbackException("ejb exception msg", new RuntimeException("runtime exception message"));
        throw e;
    }

    public static void callTestParseConfig()
    {
        String in = "aaa:true, bbb:false,ccc:false";
        testParseConfig(in);
        in = "{aaa:true, bbb:false,ccc:false}";
        testParseConfig(in);
        in = "{\"aaa\":\"true\", \"bbb\":\"false\",\"ccc\":\"false\"}";
        testParseConfig(in);

    }

    public static void testParseConfig(String in)
    {
        String out = parseConfig(in);
        System.out.println(String.format("sent : %s, received: %s", in, out));
        Map<String, Object> configCustomerMetadata = (Map) MVEL.eval(out);
    }

    private static String parseConfig(String config)
    {
        String out;
        if(config.indexOf('{') == 0)
        {
            config = config.substring(1, config.length()-1);
        }

        final String[] pairs = config.split(",");
        out = Arrays.asList(pairs).stream().map(
                pair->{
                    String[] keyvalue = pair.split(":");
                    String key = keyvalue[0].trim();
                    key = key.charAt(0)!='"'? "\"" + key + "\"" : key;
                    String value = keyvalue[1].trim();
                    value = value.charAt(0)!='"'? "\"" + value + "\"" : value;
                    return key + ":" + value;
                })
             .collect(Collectors.joining(","));
        out = "{" + out + "}";
        return out;
    }


    private static void testDoDate()
    {
        doDate(48);
    }

    public static void doDate(int hoursInAdvance){

        final LocalDate localDate = LocalDateTime.now().plusHours(hoursInAdvance).toLocalDate();
        System.out.println(localDate.toString());

    }


    public static void testGetPSHAwayWin(){
        getPSHAwayWin(0,0);
        getPSHAwayWin(1,0);
        getPSHAwayWin(0,1);
        getPSHAwayWin(3,0);
        getPSHAwayWin(5,2);
        getPSHAwayWin(0,4);

    }

    public static double getPSHAwayWin(int fhHomeGoals, int fhAwayGoals)
    {
        System.out.println(String.format("%ngetPSHAwayWin called with %d,%d",fhHomeGoals, fhAwayGoals));

        double p = 0 ;

        for(int i = fhHomeGoals ; i < 7 ; i++)
            for(int j = fhAwayGoals ; j < 7 ; j++)
                if(j-fhAwayGoals > i-fhHomeGoals)
                {
                    p += getPScore(i, j);
                }

        return p ;
    }

    static double getPScore(int i, int j){
        System.out.println(String.format("  getPScore called with %d,%d",i,j));
        return 0.0;
    }


    static void gson() {
        Gson g = new Gson();
        String s = "test";
        final String json = g.toJson(s);
        System.out.println(json);


    }





    static void classNames(){
        App app = new App();
        System.out.println(app.getClass().getTypeName());
        System.out.println(app.getClass().getSimpleName());
        System.out.println(app.getClass().getName());
        System.out.println(app.getClass().getCanonicalName());
    }


    static void properties(){

        //message.getPropertyNames()


    }

}
