import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public int i=10;

    //final static Pattern pattern = Pattern.compile(".*http:.*");
    final static Pattern pattern = Pattern.compile("(.*)(http:)(.*)\\.(jpg|jpeg|png|gif|svg)(\\?.*)?");


    public static void main(String[] args) throws IOException
    {

        //testMissingKey();
        //split();
        //equalsTest();
        //regex2();
        //getResults();
        //cvvRegex();
        //list();
        //testComparatorSort();
        //sformat();
        //regex3();
        //bddivide();
        //fooHttp();
        //replaceInsecureStringsInFile();
        //replaceInsecureMapProperties();
        //final String s = "<div class='app app--text'>\n\n\t<a href=\"http://www.dabblebet.com/\"><div class='promotion--big'>\t\n\t\t\n<div class='promotion--big__image' style='background-image: url(\"{{imageUrl}}\");'></div>\n\t\t<div class='promotion--big__description'>\n\t\t\t<h1>{{promotion}}</h1>\n\t\t\t<h5>{{description}}</h5>\n<br/>\n<br/>\n\n\n\t\t\t\n\t\t\t<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n\t\t</div></a>\n\t<a href=\"http://www.blah.com/\"></div>\n</div>\n\n\n";
        //System.out.println(secureContent(s));
        //optional();
        //mapvflatmap();
        //opt2();
        batch();

    }


    static void classNames(){



    }

    static void batch(){
        double batchSize = 100.0;
        final List<Integer> tokens = IntStream.rangeClosed(1, 950).mapToObj(i -> i).collect(Collectors.toList());
        int numDeviceTokens = tokens.size();
        System.out.println("batchSize:" + batchSize); //e.g. 100
        System.out.println("message.getDeviceToken().size():" + numDeviceTokens); //eg 950
        System.out.println("Math.ceil(message.getDeviceToken().size() / batchSize:" + Math.ceil(numDeviceTokens / batchSize)); //eg 10.0
        System.out.println("message.getDeviceToken().size() / batchSize:" + numDeviceTokens / batchSize); //

        for(int batchNumber=0; batchNumber< ((Double) (Math.ceil(numDeviceTokens / batchSize))).intValue(); batchNumber++){
            System.out.print("processing batch " + batchNumber);
            int fromIndex = (int)(batchNumber * batchSize);
            int toIndex = Math.min(fromIndex + (int)batchSize, numDeviceTokens);
            foo(tokens, fromIndex, toIndex);
            System.out.println("");
        }
    }

    static void foo( List<Integer> tokens, int fromIndex, int toIndex){
        final List<Integer> subList = tokens.subList(fromIndex, toIndex);
        subList.stream().forEach(i->System.out.print("..." + i));
    }


    static String doubleToString(Double d){
        return d.toString();
    }

    static Optional<String> doubleToStringOptional(Double d){
        return d==null? Optional.empty() : Optional.of(d.toString());
    }

    static Optional<Integer> getRandomEvenNumber(){
        final int random = new Random().ints(0, 100).findFirst().getAsInt();
        return random % 2 == 0 ? Optional.of(random) : Optional.ofNullable(null);
    }

    static void opt2() {

        //map vs flatMap
        //create an Optional
        Optional<Double> opt = Optional.of(2.0);
        //apply a map(Function(Double,String)) to the optional: after applying the function, the map will
        //implicitly wrap the function's result (a String) in an Optional, i.e. returning an Optional<String>
        final Optional<String> s1 = opt.map(Main::doubleToString);

        //apply a map(Function(Double,Optional<String>)) to the optional: as before, after applying the function, the
        //map will implicitly wrap the function's result (an Optional<String>) in an Optional; hence it will return
        //an Optional<Optional<String>>
        final Optional<Optional<String>> s2 = opt.map(Main::doubleToStringOptional);

        //if instead we apply a Function(Double,Optional<String>) to an optional using flatMap, then because flatMap
        //is defined to take a fn that returns an Optional<U>, and return an Optional<U>, whatever Optional the fn
        //returns, will be returned as-is by flatmap; in this case then it will return an Optional<String>; the same as
        // what the function returns
        final Optional<String> s3 = opt.flatMap(Main::doubleToStringOptional);

        //use orElseThrow when you want to get the enclosed value or throw an exception if the optional is empty
        Optional<String> petName = Optional.empty();
        try{
            final String s = petName.orElseThrow(IllegalArgumentException::new);
            System.out.println(petName.orElseThrow(IllegalArgumentException::new));
        } catch (IllegalArgumentException e ) {
            System.out.println("--> " + e);
        }
        //nb this could be chained onto a function call that returns an optional:
        try{
            int randomEvenInteger = getRandomEvenNumber().orElseThrow(IllegalArgumentException::new);
            System.out.println("got number: " + randomEvenInteger);
        } catch (IllegalArgumentException e ) {
            System.out.println("--> " + e);
        }
        /*The point of optional is to be able to create a lightweight structure that may or may not contain a value, and
          which can have operations performed on it whether or not a value is there, without having to use messy if-not-null
          checks; this starts at the point where you have a T-generator that may or may not generate a T; so instead of
          returning a T you return an Optional<T> (which will either contain a T t or be empty (i.e. Optional.empty()),
          and then in your T-processor you process an Optional<T> rather than a T.  Importantly, you can run a map on an
          Optional and it will not blow up if the optional is empty; it will just return an Optional.empty()

          Similarly, you can run a filter on an optional and the output from the filter will either be a T or Optional.empty().
          You can combine filter with orElseThrow in a validator, to verify that the filter matched, o/w throw an exception:
        */
        try
        {
            doubleToStringOptional(2.0)
                    .filter(s -> s.equals("2.0")).orElseThrow(() -> new IllegalArgumentException("unexpected double!"));
            doubleToStringOptional(4.0)
                    .filter(s -> s.equals("2.0")).orElseThrow(() -> new IllegalArgumentException("unexpected double!"));
        } catch (IllegalArgumentException e ) {
            System.out.println("--> " + e);
        }

        /*myOpt.orElse(T t) allows you to return the contents of myOpt or return t if myOpt is empty
          myOpt.orElseGet(Main::TSupplier) allows you to return the contents of myOpt or run TSupplier if myOpt is empty
          myOpt.ifPresent(func) allows you to run func if myOpt is not empty, o/w do nothing
        */
        /*Java 9:
          Java 9 has or() which, unlike orElse/orElseGet (which return a T t rather than an Optional<T>), returns an
          Optional<T>
        */
        Optional<String> myOpt = Optional.empty();
        final Optional<String> res = myOpt.or(() -> Optional.of("xyz"));

        //also, ifPresentOrElse, which runs a consumer(T t) or a runnable(), the latter of which is simply a fn
        //that takes no arguments and returns void:
        myOpt.ifPresentOrElse(s-> System.out.println("found " + s), ()-> System.out.println("nothing found "));
        res.ifPresentOrElse(s-> System.out.println("found " + s), ()-> System.out.println("nothing found "));

        //also, stream() converts an optional into a Stream<T> (not a Stream<Optional<T>>!)
        res.stream().forEach(someString-> System.out.println("===> " + someString));


    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }


    static void opt1() {
//        final Optional<String> xyz = Optional.of("xyz").map(String::toUpperCase);
//        final Optional<Optional<String>> xyz1 = Optional.of(Optional.of("xyz").map(String::toUpperCase));
        //xyz1.flatMap(String::toLowerCase);

        final Optional<String> syz = optStringUC("syz");
        final Optional<Optional<String>> syz2 = optStringUC("syz").map(Main::optStringRepeat);
        final Optional<String> syz1 = optStringUC("syz").flatMap(Main::optStringRepeat);

    }

    public static Optional<String> optStringUC(String s){
        return s== null ? Optional.empty() : Optional.of(s.toUpperCase());
    }

    public static Optional<String> optStringRepeat(String s){
        return s== null ? Optional.empty() : Optional.of(s + s);
    }



    public static void mapvflatmap() {
        Optional<String> s = Optional.of("input");
        System.out.println(s.map(Main::getOutput));
        System.out.println(s.flatMap(Main::getOutputOpt));
    }

    static String getOutput(String input) {
        return input == null ? null : "output for " + input;
    }

    static Optional<String> getOutputOpt(String input) {
        return input == null ? Optional.empty() : Optional.of("output for " + input);
    }


    public interface I { public String getAsString(); }
    static class X implements I {
        int i;
        public X(int i){ this.i = i;}

        @Override
        public String getAsString()
        {
            return String.valueOf(this.i);
        }
    }

    static void testOptional(){
        final Optional<String> s = getOptionalX().map(X::getAsString);
        //getOptionalX().flatMap(X::getAsString);
    }

    static Optional<X> getOptionalX(){
        return Optional.of(new X(10));
    }

    private static void optional()
    {
        Optional<Integer> optional = Optional.ofNullable(null);
        optional.filter(o->o.equals(5)).ifPresent(o-> System.out.println("is present"));
        optional.filter(o->o.equals(5)).orElseThrow(() -> new RuntimeException("not present!"));

    }


    static void replaceInsecureMapProperties(){
        Map<String, String> properties = new HashMap<>();
        properties.put("a1", "aaa");
        properties.put("https://www.google.com", "https://www.google.com/i/x.jpg");
        properties.put("a3", "aaa");
        properties.put("http://www.cnn.com", "http://www.cnn.com/i/x.jpeg");
        properties.put("http://www.bbc.com", "http://www.bbc.com");
        properties.put("a6", "aaa");
        properties.put("https://www.toals.com", "https://www.toals.com/i/x.xpg");
        properties.put("http://www.goldchip.com", "http://www.toals.com");
        properties.put("a7", "aaa");
        final Map<String, String> securedMap = properties.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> secureContent(entry.getValue())));
        securedMap.entrySet().stream().forEach(entry-> {
            System.out.println(String.format("key: %s, value: %s", entry.getKey(), entry.getValue()));
        });
    }


    static void replaceInsecureStringsInFile() throws IOException
    {
        final String content = new String(Files.readAllBytes(Paths.get("/home/jasonl/tmp/content.txt")));
        if(content == null || content.length()==0) throw new RuntimeException("cannot locate file");
        if(content.contains("http:"))
        {
            System.out.println("insecure content found");
            Files.write(Paths.get("/home/jasonl/tmp/contentOut.txt"), secureContent(content).getBytes());
            System.out.println("secured content written");
        } else {
            System.out.println("no insecure content found");
        }
    }

    static String secureContent(String content){
        //Pattern pattern = Pattern.compile("(.*)(http:)(.*)(png|svg|jpg|jpeg|wmv|avi|gif)");
        Pattern pattern = Pattern.compile("(http:)(.*)\\.(jpg|jpeg|png|gif|svg)(.*)");
        final Matcher matcher = pattern.matcher(content);
        StringBuffer out = new StringBuffer();
        while(matcher.find()){
            final int groupCount = matcher.groupCount();
            System.out.println(groupCount);
            System.out.println(String.format("group %d : %s", 0, matcher.group(0)));
            for(int i=1; i<=groupCount; i++){
                System.out.println(String.format("group %d : %s", i, matcher.group(i)));
            }
            //matcher.appendReplacement(out, matcher.group(1) + "https:" + matcher.group(3) + "." + matcher.group(4)/*+ matcher.group(5)*/);
            matcher.appendReplacement(out, "https:" + matcher.group(2) + "." + matcher.group(3) + matcher.group(4));
        }
        matcher.appendTail(out);
        return out.toString();
    }

    static void fooHttp(){
        String s = "https://google.com";
        System.out.println((s.startsWith("http:")) ? String.format("%s : yes",s) : String.format("%s : no",s));

        String result = s.startsWith("http:") ? "https:" + s.substring(5) : s;
        System.out.println("result:" + result);


    }

    static void bddivide(){

        BigDecimal bd = BigDecimal.valueOf(5.00);
        BigDecimal bd2 = BigDecimal.valueOf(6.00);
        System.out.println(bd.divide(bd2, 5));

    }

    static void regex3(){
        String s = "£20.00 on Arthur de Greef @ 1.90";
        String regex = "\\d+\\.\\d+";
        String regex2 = BigDecimal.valueOf(20.00).setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
        System.out.println(s.replaceFirst(regex2, BigDecimal.valueOf(5.55).setScale(2, BigDecimal.ROUND_DOWN).toPlainString()));

    }

    static void sformat(){
        int id = 1;
        BigDecimal bd = BigDecimal.valueOf(1.1);
        System.out.println(String.format("[Original bet: id: %d, unitStake:%s, totalStake:%s, totalStakeGbp:%s], payoutFromAdjustments:%s, cashOutRequest - unitStake:%s, totalStake:%s, payout:%s"
                , id, bd.toPlainString(), bd.toPlainString(), bd.toPlainString(), bd.toPlainString(), bd.toPlainString(), bd.toPlainString(), bd.toPlainString()));

    }
    static void testComparatorSort(){
        class X {
            int i;
            Date d;
            X(int i, Date d){
                this.i = i;
                this.d = d;
            }
            Date getDate(){
                return d;
            }

            @Override
            public String toString()
            {
                return "X{" +
                        "i=" + i +
                        ", d=" + d +
                        '}';
            }
        }
        List<X> list = Arrays.asList(
                    new X(1, Date.from(LocalDateTime.now().plusSeconds(10).toInstant(ZoneOffset.UTC)))
                   ,new X(2, Date.from(LocalDateTime.now().plusSeconds(30).toInstant(ZoneOffset.UTC)))
                   ,new X(3, Date.from(LocalDateTime.now().plusSeconds(-50).toInstant(ZoneOffset.UTC)))
                   ,new X(4, Date.from(LocalDateTime.now().plusSeconds(-15).toInstant(ZoneOffset.UTC)))
                );
        list.stream().forEach(x->System.out.println(x));
        list = Collections.emptyList();

        X x = list.stream()
                .sorted(Comparator.comparing(X::getDate).reversed())
                .findFirst().orElse(null);
        //X x = list.stream().sorted(Comparator.comparing(X::getDate, Comparator.nullsLast(Comparator.reverseOrder()))).findFirst().orElseGet(null);
        System.out.println(">>" + x);
    }



    static void list(){
        List<String> list1 = Arrays.asList( new String[] { "setDetailsByCustomerId",
                "setDetailByCustomerId"});
        System.out.println("list1: " + list1);
        for(String s : list1 )
            System.out.println(s);
        List<String> list2 = Arrays.asList( "setDetailsByCustomerId",
                "setDetailByCustomerId");
        System.out.println("list2: " + list2);
        for(String s : list2 )
            System.out.println(s);
    }

    private static void custRegex()
    {
        System.out.println("233".matches("\\d\\d\\d\\d?"));
        System.out.println("000".matches("\\d\\d\\d\\d?"));
        System.out.println("0000".matches("\\d\\d\\d\\d?"));
        System.out.println("00000".matches("\\d\\d\\d\\d?"));
        System.out.println("00A".matches("\\d\\d\\d\\d?"));
    }

    private static void cvvRegex()
    {
        System.out.println("00".matches("\\d\\d\\d\\d?"));
        System.out.println("000".matches("\\d\\d\\d\\d?"));
        System.out.println("0000".matches("\\d\\d\\d\\d?"));
        System.out.println("00000".matches("\\d\\d\\d\\d?"));
        System.out.println("00A".matches("\\d\\d\\d\\d?"));
    }


    static void getResults(){
        getResults(new int[] {80, 71, 71});
        getResults(new int[] {65, 71, 71});
    }


    static void getResults(int[] scores){
        int winningScore = getWinningScore(scores);
        System.out.println(String.format("%s winning score: %d",Arrays.toString(scores), winningScore));
        int numWinners = (int) Arrays.stream(scores).filter(score-> score == winningScore).count();
        System.out.println(String.format("%s numwinners: %d",Arrays.toString(scores), numWinners));
        for(int _score : scores){
            int rank = getRank(scores, _score);
            int ties = getTies(scores, _score);
            System.out.println(String.format("%s score %d: rank: %d ties: %d",Arrays.toString(scores), _score, rank, ties));
        }
    }

    static int getWinningScore(int[] scores){
        int winningScore = Arrays.stream(scores).sorted().findFirst().getAsInt();
        return winningScore;
    }

    static int getRank(int[] scores, int forScore){
        return (int) Arrays.stream(scores).filter(score -> score < forScore).count()+1;
    }

    static int getTies(int[] scores, int forScore){
        return (int) Arrays.stream(scores).filter(score -> score == forScore).count();
    }


    static void regex2(){
        String s = "DEAD HEAT: NO WINNER CAN BE DECIDED, RESULTS: 80,71,71";
        Pattern p = Pattern.compile("DEAD HEAT: NO WINNER CAN BE DECIDED, RESULTS: (\\d+),(\\d+),(\\d+)");
        Matcher m = p.matcher(s);
        System.out.println(m.matches());
        //System.out.println(p.matcher("DEAD HEAT: NO WINNER CAN BE DECIDED, RESULTS:80,71,71").matches());
        if(!m.matches()){
            //exception
            System.out.println("uck");
        } else {
//            m.reset();
//            while(m.find()){
//                System.out.println("==>" + m.start());
                System.out.println("==>" + m.group(1));
                System.out.println("==>" + m.group(2));
                System.out.println("==>" + m.group(3));
                //break;
            //}
        }

    }

    static void regex(){
        String s = "21Black Type-Test";
        String s2 = s.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(s2);

    }

    static void equalsTest(){
        String nullString = null;
        String someString = "x";
        if(someString.equals(nullString))
            System.out.println("worksA");
        else
            System.out.println("worksB");
        if(nullString.equals(someString))
            System.out.println("worksC");
        else
            System.out.println("worksD");
    }

    static void split(){
        String s = "flock:toals";
        String[] array = s.split(":");
        System.out.println(Arrays.toString(array));
    }

    static void testMissingKey(){
        Map<String, Object> m = new HashMap<>();
        Boolean b = m.get("XXX") != null && (Boolean) m.get("XXX") ;
        System.out.println("b:" + b.toString());
    }


    static void f6(){
        Instant sevenDaysAgo = Instant.now().plus(-7, ChronoUnit.DAYS);
        System.out.println(sevenDaysAgo);
        Calendar cal = new GregorianCalendar(2019,1, 10);
        Date date = cal.getTime();
        System.out.println(date);
        System.out.println("is before 7 days ago:" + date.toInstant().isBefore(sevenDaysAgo));

    }

    static void f5(){
        Map<String, Object> map = new HashMap<>();
        String s = (String) map.get("SOMEKEY");
        System.out.println("s:"+s);
    }

    static void f4(){
        // FIXME - temporary fix BACKOFFICE-384
        final Calendar cal = Calendar.getInstance();
        Date storedDob = null;
        cal.setTime(storedDob);
        cal.set(Calendar.HOUR, 0);
        storedDob = cal.getTime();

        if(storedDob == null){
            System.out.println("isnull");
        } else {
            System.out.println("is not null");
        }


    }


    static void f3(){
        double d = 1868916962.01;
        System.out.println(String.valueOf(d));
        System.out.println(String.format("%.2f",d));
        System.out.println(new BigDecimal(d).toPlainString());
    }

    static void callf2(){
        List<Double> totals = Arrays.asList(1.03, 1.04);
        f2(totals);
    }

    static void f2(List<Double> totals){
        List<String> outcomeTypes = Arrays.asList("OVER", "UNDER");
        List<String> strings = totals.stream().map(total -> {String s = null;
                                                             for (String outcomeType : outcomeTypes){
                                                                    System.out.println("outcomeType: " + outcomeType);
                                                                    if(outcomeType.equals("OVER")){
                                                                        System.out.println("--equals OVER:" + outcomeType);
                                                                    } else {
                                                                        System.out.println("--NOT equals OVER:" + outcomeType);
                                                                    }
                                                                    s = outcomeType.equals("OVER") ? "X" : "Y";
                                                                    //return s;
                                                                 } ;
                                                            return s;}).collect(Collectors.toList());
        System.out.println(strings);
    }

    static void f1(){
        System.out.println("Hello World!!!");

        Main main = new Main();
        try
        {
            Field intField = main.getClass().getDeclaredField("i");
            System.out.printf("Value of i: %d%n", intField.getInt(main));
            main.i=50;
            System.out.printf("Value of i: %d%n", intField.getInt(main));

        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
