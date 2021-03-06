import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artyom on 30.09.16.
 */
public aspect Main {
    pointcut profiledOperation() : (call(public * *(..)) || call(*.new(..))) && within(me.postaddict.instagramscraper..*);

    before() : profiledOperation() {
        if (!executed) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    theEnd();
                }
            });
            executed = true;
        }
//        System.out.println(String.format("%s at position: %s:%d",
//                thisJoinPoint.getSignature().getName(),
//                thisJoinPoint.getSourceLocation().getFileName(),
//                thisJoinPoint.getSourceLocation().getLine()));
        List<String> args = new ArrayList<>();
        for (Object arg: thisJoinPoint.getArgs()) {
            args.add(defaultToString(arg));
        }
        invocations.add(new Invocation(
                thisJoinPoint.getSignature().getName(),
                thisJoinPoint.getSourceLocation().getFileName(),
                thisJoinPoint.getSourceLocation().getLine(),
                thisJoinPoint.getSignature().getDeclaringTypeName(),
                thisEnclosingJoinPointStaticPart.getSignature().getName(),
                thisJoinPoint.getKind(),
                args,
                defaultToString(thisJoinPoint.getTarget()),
                "before"));
    }

    private void theEnd() {
        System.out.println("The end. Instrumentation finished!");
        try {
            new ObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .writeValue(new File("log.json"), invocations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Invocation {
        public String name;
        public String filename;
        public int line;
        public String type;
        public String callerName;
        public String kind;
        public List<String> args;
        public String id;
        public String place;

        public Invocation(String name, String filename, int line, String type, String callerName, String kind, List<String> args, String id, String place) {
            this.name = name;
            this.filename = filename;
            this.line = line;
            this.type = type;
            this.callerName = callerName;
            this.kind = kind;
            this.args = args;
            this.id = id;
            this.place = place;
        }
    }

    public static String defaultToString(Object o) {
        if (o == null) {
            return "null";
        } else {
            return o.getClass().getName() + "@" +
                    Integer.toHexString(System.identityHashCode(o));
        }
    }

    public static List<Invocation> invocations = new ArrayList<>();
    private static boolean executed = false;
}