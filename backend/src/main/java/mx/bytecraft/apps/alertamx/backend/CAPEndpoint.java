package mx.bytecraft.apps.alertamx.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.publicalerts.cap.Info;
import com.google.publicalerts.cap.feed.CapFeedParser;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import org.xml.sax.InputSource;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Api(name = "capEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.alertamx.apps.bytecraft.mx", ownerName = "backend.alertamx.apps.bytecraft.mx", packagePath=""))
public class CAPEndpoint {

    @SuppressWarnings("unchecked")
    @ApiMethod(name = "getAlerts")
    public Response getAlerts(@Named("count") int count) {

        Response response = new Response();

        try {
            CapFeedParser parser = new CapFeedParser(true);
            FileInputStream stream = new FileInputStream("WEB-INF/feed.atom");
            InputSource source = new InputSource(stream);
            SyndFeed feed = parser.parseFeed(source);
            List<SyndEntry> syndEntries = feed.getEntries();

            List<Alert> alerts = new ArrayList<Alert>();
            for (int i = 0; i < count; i++) {
                SyndEntry entry = syndEntries.get(i);
                com.google.publicalerts.cap.Alert capAlert = parser.parseAlert(entry);

                Alert alert = new Alert();
                alert.setId(capAlert.getIdentifier());
                alert.setSent(capAlert.getSent());

                Info info = capAlert.getInfo(0);
                alert.setDescription(info.getDescription());
                alert.setEvent(info.getEvent());
                alert.setSenderName(info.getSenderName());
                alert.setInstruction(info.getInstruction());

                alerts.add(alert);
            }
            response.setAlerts(alerts);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}