package DAW;



public class Main {
    public static void main(String args[]) throws Exception {
        if (args.length<3) {
            System.err.println("expecting <.nt filename> <url> <n porcentage >10>");
            System.exit(1);
        }

        Capability.maxn=new Integer(args[2]);
        EndPoint ep=new EndPoint(args[0],args[1]);
        ep.processDump();
        System.out.println(ep.toString());

    }
}
