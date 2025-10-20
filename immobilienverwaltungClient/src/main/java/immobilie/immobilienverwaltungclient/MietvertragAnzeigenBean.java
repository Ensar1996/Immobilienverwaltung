/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package immobilie.immobilienverwaltungclient;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ensar
 */

@Named
@SessionScoped
public class MietvertragAnzeigenBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<MietvertragTO> mietvertraege;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    @PostConstruct
    public void init() {
        loadVertraege();
    }

    public void loadVertraege() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path("/mietvertrag");

        mietvertraege = target
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<MietvertragTO>>() {});
    }

   public List<MietvertragTO> getMietvertraege() {
    loadVertraege(); 
    return mietvertraege;
}

    public void setMietvertraege(List<MietvertragTO> mietvertraege) {
        this.mietvertraege = mietvertraege;
    }
}