/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;

import edu.iit.sat.itmd4515.sfuseini.domain.Parts;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class PartsService extends BaseService<Parts> {

    /**
     *
     */
    public PartsService() {
        super(Parts.class);
    }

    /**
     *
     * @param serialNumber
     * @return
     */
    public Parts findByPartId(int serialNumber) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        Parts p = new Parts();
        
         try {
            HttpGet getRequest = new HttpGet("http://localhost:8080/CarServices/webresources/parts/" + serialNumber);
            getRequest.addHeader("accept", "application/json");
            HttpResponse httpResponse = httpclient.execute(getRequest);
            HttpEntity entity = httpResponse.getEntity();
            DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            BufferedReader br = new BufferedReader(
                         new InputStreamReader((entity.getContent())));
            String output = br.readLine();
            String[] ar=output.split(",");
            
            Long serialNum = Long.parseLong(ar[0].substring(ar[0].lastIndexOf("=") + 1));
            String type = ar[1].substring(ar[1].lastIndexOf("=") + 1);
            String category = ar[2].substring(ar[2].lastIndexOf("=") + 1);
            String brandName = ar[3].substring(ar[3].lastIndexOf("=") + 1);
            String model = ar[4].substring(ar[4].lastIndexOf("=") + 1);
            Integer registrationNum = Integer.parseInt(ar[5].substring(ar[5].lastIndexOf("=") + 1));
            Integer engineNum = Integer.parseInt(ar[6].substring(ar[6].lastIndexOf("=") + 1));
            Integer chassisNum = Integer.parseInt(ar[7].substring(ar[7].lastIndexOf("=") + 1));
            Double odometerReading = Double.parseDouble(ar[8].substring(ar[8].lastIndexOf("=") + 1));
            Date manufactureDate = df.parse(ar[9].substring(ar[9].lastIndexOf("=") + 1));
            Date purchaseDate = df.parse(ar[10].substring(ar[10].lastIndexOf("=") + 1));

            p = new Parts(serialNum, type, category, brandName, model, registrationNum, engineNum, chassisNum, odometerReading, manufactureDate, purchaseDate);

            } catch (Exception e) {
            System.out.println(e);
        }
            return p;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Parts> findAll() {
        return getEntityManager().createNamedQuery("Parts.findAll", Parts.class).getResultList();
    }

    /**
     *
     * @param jsfPart
     */
    @Override
    public void update(Parts jsfPart) {
        Parts oldPart = getEntityManager().find(Parts.class, jsfPart.getSerialNumber());

        jsfPart.setSerialNumber(oldPart.getSerialNumber());
        getEntityManager().merge(jsfPart);
    }

    /**
     *
     * @param part
     */
    @Override
    public void create(Parts parts) {
        super.create(parts); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     */
    @Override
    public void remove(Parts e) {
        super.remove(e); //To change body of generated methods, choose Tools | Templates.
    }
}
