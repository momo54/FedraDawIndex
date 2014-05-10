package DAW;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pascal
 */
public class EndPoint {

    private HashMap<String, Capability> capabilities = new HashMap<String, Capability>();
    private String dumpFileName;
    private String url;
    private long nb;

    public HashMap<String, Capability> getCapabilities() {
        return capabilities;
    }

    public String getDumpFileName() {
        return dumpFileName;
    }

    public String getUrl() {
        return url;
    }

    public EndPoint(String fileName, String url) {

        this.dumpFileName = fileName;
        this.url = url;
    }

    void processDump() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(dumpFileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] triple = line.split("\\s");
            Capability capability = capabilities.get(triple[1]);

            if (capability == null) {
                capability = new Capability(triple[1]);
                capabilities.put(capability.getProperty(), capability);
            }

            String v = triple[0] + triple[2];
            capability.addSubj(triple[0].hashCode());
            capability.addObj(triple[2].hashCode());
            capability.addId(v.hashCode());
            nb++;

            if (nb % 1000 == 0) {
                System.err.println("Processed:" + nb + " lines");
                System.err.println("Got:" + capabilities.size() + " different capabilities");
            }

        }
        br.close();

        //ok all capabilities created now, maybe thousands
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        CompletionService<String> pool = new ExecutorCompletionService<String>(threadPool);

        Iterator it = capabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            pool.submit(new MipTask((Capability)pairs.getValue()));
        }
        
        for (int i=0;i<capabilities.size();i++) {
            String result=pool.take().get();
            System.err.println(result+": mip completed");
        }

    }

    public void print() {
        System.out.println("@prefix sd: <http://www.w3.org/ns/sparql-service-description#> .");
        System.out.println("[] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> sd:Service ;");
        System.out.println("sd:endpointUrl <\"" + url + "\"> \"");

        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

        int i = 0;
        for (Iterator<Map.Entry<String, Capability>> it = capabilities.entrySet().iterator(); it.hasNext();) {
            Capability c = (Capability) it.next().getValue();

            i++;
            System.err.println("Processing:" + c.getProperty() + ":" + i + " on " + capabilities.size());

            System.out.println(" ;\n  sd:capability [\n      sd:predicate <" + c.getProperty() + "> ;");
            System.out.println("      sd:totalTriples   " + c.getTotal() + " ;");
            System.out.println("      sd:avgSbjSel     \"" + nf.format(1 / ((double) c.getSubjectNb())) + "\" ;");
            System.out.println("      sd:avgObjSel     \"" + nf.format(1 / ((double) c.getObjectNb())) + "\" ;");
            System.out.print("      sd:MIPs   \"");
            System.out.println(c.getMipsAsString());
            System.out.println("\" ; ] ");
            System.out.flush();
        }
    }
}
