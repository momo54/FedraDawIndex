package DAW;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import jdk.nashorn.internal.objects.NativeArray;

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
                System.err.println("Processed:"+nb+" lines");
                System.err.println("Got:"+capabilities.size()+" different capabilities");
            }

        }
        br.close();
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("@prefix sd: <http://www.w3.org/ns/sparql-service-description#> .");
        result.append("\"[] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> sd:Service ;\\n sd:endpointUrl <\"+url+\"> \"");

        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

        for (Iterator<Map.Entry<String, Capability>> it = capabilities.entrySet().iterator(); it.hasNext();) {
            Capability c = (Capability) it.next().getValue();
            result.append(" ;\n  sd:capability [\n      sd:predicate <" + c.getProperty() + "> ;\n");
            result.append("      sd:totalTriples   " + c.getTotal() + " ;\n");
            result.append("      sd:avgSbjSel     \"" + nf.format(1 / ((double) c.getSubjectNb())) + "\" ;\n");
            result.append("      sd:avgObjSel     \"" + nf.format(1 / ((double) c.getObjectNb())) + "\" ;\n");
            result.append("      sd:MIPs   \"");
            result.append(c.getMipsAsString());
            result.append("\" ; ] ");
        }
        return result.toString();
    }
}
